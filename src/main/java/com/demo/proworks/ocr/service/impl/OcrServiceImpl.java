package com.demo.proworks.ocr.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.proworks.ocr.service.OcrService;
import com.demo.proworks.ocr.vo.OcrAnalysisResult;
import com.demo.proworks.s3.service.S3Service;

/**
 * @subject : OCR 분석 서비스 구현체 (성능 측정 강화)
 * @description : Python OCR API를 호출하여 이미지 문서 분석 처리 및 성능 모니터링
 * @author : 시스템 관리자
 * @since : 2025/07/17
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/17 시스템 관리자 최초 생성 
 *               2025/07/17 시스템 관리자 유니코드 디코딩 및 로직 개선
 *               2025/07/18 시스템 관리자 성능 측정 기능 추가
 * 
 */
@Service("ocrService")
public class OcrServiceImpl implements OcrService {

	// OCR API URL
	private String ocrApiUrl = "http://localhost:5000";

	// S3 버킷명
	private String bucketName = "insure-claim-docs-final-project";

	// OCR 분석 실패시 기본값 (질병으로 설정)
	private static final String DEFAULT_CLAIM_TYPE = "disease";

	// 시간 포맷터
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	@Resource(name = "s3Service")
	private S3Service s3Service;

	// 문서 유형 우선순위 매핑
	private static final Map<String, Integer> DOCUMENT_PRIORITY = new HashMap<String, Integer>();
	static {
		DOCUMENT_PRIORITY.put("사망진단서", 1);
		DOCUMENT_PRIORITY.put("장해진단서", 2);
		DOCUMENT_PRIORITY.put("수술확인서", 3);
		DOCUMENT_PRIORITY.put("미분류", 999);
	}

	// OCR 결과를 claim_type으로 매핑 (영문 코드로 반환)
	private static final Map<String, String> OCR_TO_CLAIM_TYPE = new HashMap<String, String>();
	static {
		OCR_TO_CLAIM_TYPE.put("사망진단서", "death");
		OCR_TO_CLAIM_TYPE.put("장해진단서", "disability");
		OCR_TO_CLAIM_TYPE.put("수술확인서", "surgery");
		OCR_TO_CLAIM_TYPE.put("미분류", DEFAULT_CLAIM_TYPE); // 미분류시 질병으로 처리
	}

	@Override
	public String analyzeDocumentType(List<String> s3ObjectKeys) throws Exception {
		// ========== 전체 프로세스 시작 시간 측정 ==========
		long totalStartTime = System.currentTimeMillis();
		LocalDateTime processStartTime = LocalDateTime.now();
		
		System.out.println("==========================================");
		System.out.println("=== OCR 분석 프로세스 시작 ===");
		System.out.println("시작 시간: " + processStartTime.format(TIME_FORMATTER));
		System.out.println("분석할 파일 수: " + (s3ObjectKeys != null ? s3ObjectKeys.size() : 0));
		System.out.println("==========================================");

		if (s3ObjectKeys == null || s3ObjectKeys.isEmpty()) {
			System.out.println("⚠️  S3 객체 키가 없어서 기본값 반환: " + DEFAULT_CLAIM_TYPE);
			logProcessCompletion(totalStartTime, processStartTime, 0, 0, DEFAULT_CLAIM_TYPE);
			return DEFAULT_CLAIM_TYPE;
		}

		List<OcrAnalysisResult> results = new ArrayList<OcrAnalysisResult>();
		int successCount = 0;
		int failCount = 0;
		long totalOcrTime = 0; // 총 OCR 처리 시간

		// 각 이미지를 개별적으로 분석
		for (int i = 0; i < s3ObjectKeys.size(); i++) {
			String objectKey = s3ObjectKeys.get(i);
			
			// 개별 파일 처리 시작 시간
			long fileStartTime = System.currentTimeMillis();
			
			try {
				System.out.println(String.format("📄 [%d/%d] OCR 분석 시작: %s", 
					i + 1, s3ObjectKeys.size(), objectKey));
				
				OcrAnalysisResult result = analyzeSingleDocument(objectKey);
				results.add(result);
				successCount++;
				
				// 개별 파일 처리 완료 시간
				long fileEndTime = System.currentTimeMillis();
				long fileProcessTime = fileEndTime - fileStartTime;
				totalOcrTime += fileProcessTime;
				
				System.out.println(String.format("✅ OCR 분석 성공 - 파일: %s, 유형: %s, 처리시간: %dms", 
					objectKey, result.getDocumentType(), fileProcessTime));
					
			} catch (Exception e) {
				failCount++;
				long fileEndTime = System.currentTimeMillis();
				long fileProcessTime = fileEndTime - fileStartTime;
				
				System.err.println(String.format("❌ OCR 분석 실패 - 파일: %s, 처리시간: %dms, 오류: %s", 
					objectKey, fileProcessTime, e.getMessage()));
				// 개별 파일 실패는 무시하고 계속 진행
			}
		}

		// ========== 분석 결과 처리 시작 ==========
		long analysisStartTime = System.currentTimeMillis();
		System.out.println("\n📊 === OCR 분석 결과 집계 시작 ===");
		System.out.println("성공: " + successCount + "건, 실패: " + failCount + "건");
		System.out.println("총 OCR 처리 시간: " + totalOcrTime + "ms");
		System.out.println("평균 파일당 처리 시간: " + (successCount > 0 ? totalOcrTime / successCount : 0) + "ms");

		// 분석 결과가 없으면 기본값 반환
		if (results.isEmpty()) {
			System.out.println("⚠️  모든 OCR 분석이 실패하여 기본값 반환: " + DEFAULT_CLAIM_TYPE);
			logProcessCompletion(totalStartTime, processStartTime, successCount, failCount, DEFAULT_CLAIM_TYPE);
			return DEFAULT_CLAIM_TYPE;
		}

		// 문서 유형별 개수 집계 (미분류 제외)
		Map<String, Integer> documentCounts = new HashMap<String, Integer>();
		for (OcrAnalysisResult result : results) {
			String docType = result.getDocumentType();
			// 미분류는 제외하고 집계
			if (docType != null && !docType.equals("미분류") && !docType.trim().isEmpty()) {
				documentCounts.put(docType, documentCounts.getOrDefault(docType, 0) + 1);
			}
		}

		System.out.println("\n📈 === 문서 유형별 집계 ===");
		for (Map.Entry<String, Integer> entry : documentCounts.entrySet()) {
			System.out.println(String.format("  %s: %d건", entry.getKey(), entry.getValue()));
		}

		// 집계된 문서 중에서 우선순위가 가장 높은 유형 선택
		String highestPriorityType = null;
		int highestPriority = Integer.MAX_VALUE;

		for (String docType : documentCounts.keySet()) {
			Integer priority = DOCUMENT_PRIORITY.get(docType);
			if (priority != null && priority.intValue() < highestPriority) {
				highestPriority = priority.intValue();
				highestPriorityType = docType;
			}
		}

		// 유효한 문서 유형이 없으면 기본값
		if (highestPriorityType == null) {
			System.out.println("⚠️  유효한 문서 유형이 없어 기본값 반환: " + DEFAULT_CLAIM_TYPE);
			logProcessCompletion(totalStartTime, processStartTime, successCount, failCount, DEFAULT_CLAIM_TYPE);
			return DEFAULT_CLAIM_TYPE;
		}

		// claim_type으로 변환하여 반환
		String claimType = OCR_TO_CLAIM_TYPE.get(highestPriorityType);
		if (claimType == null) {
			claimType = DEFAULT_CLAIM_TYPE;
		}

		// 분석 결과 처리 완료 시간
		long analysisEndTime = System.currentTimeMillis();
		long analysisTime = analysisEndTime - analysisStartTime;

		System.out.println("\n === 최종 결정 ===");
		System.out.println("선택된 문서 유형: " + highestPriorityType + " (우선순위: " + highestPriority + ")");
		System.out.println("최종 claim_type: " + claimType);
		System.out.println("결과 분석 처리 시간: " + analysisTime + "ms");

		// ========== 전체 프로세스 완료 로그 ==========
		logProcessCompletion(totalStartTime, processStartTime, successCount, failCount, claimType);

		return claimType;
	}

	/**
	 * 전체 프로세스 완료 로그
	 */
	private void logProcessCompletion(long totalStartTime, LocalDateTime processStartTime, 
			int successCount, int failCount, String finalResult) {
		
		long totalEndTime = System.currentTimeMillis();
		LocalDateTime processEndTime = LocalDateTime.now();
		long totalProcessTime = totalEndTime - totalStartTime;

		System.out.println("\n==========================================");
		System.out.println("=== OCR 분석 프로세스 완료 ===");
		System.out.println("시작 시간: " + processStartTime.format(TIME_FORMATTER));
		System.out.println("종료 시간: " + processEndTime.format(TIME_FORMATTER));
		System.out.println("총 처리 시간: " + totalProcessTime + "ms (" + String.format("%.2f", totalProcessTime / 1000.0) + "초)");
		System.out.println("처리 결과: 성공 " + successCount + "건, 실패 " + failCount + "건");
		System.out.println("최종 반환값: " + finalResult);
		
		if (successCount > 0) {
			System.out.println("평균 파일당 처리 시간: " + String.format("%.2f", (double)totalProcessTime / successCount) + "ms");
		}
		
		// 성능 등급 표시
		String performanceGrade = getPerformanceGrade(totalProcessTime, successCount);
		System.out.println("성능 등급: " + performanceGrade);
		System.out.println("==========================================\n");
	}

	/**
	 * 성능 등급 계산
	 */
	private String getPerformanceGrade(long totalTime, int fileCount) {
		if (fileCount == 0) return "N/A";
		
		double avgTimePerFile = (double) totalTime / fileCount;
		
		if (avgTimePerFile < 1000) return "🚀 EXCELLENT (< 1초/파일)";
		else if (avgTimePerFile < 3000) return "⚡ GOOD (< 3초/파일)";
		else if (avgTimePerFile < 5000) return "👍 FAIR (< 5초/파일)";
		else if (avgTimePerFile < 10000) return "⚠️  SLOW (< 10초/파일)";
		else return "🐌 VERY SLOW (> 10초/파일)";
	}

	@Override
	public OcrAnalysisResult analyzeSingleDocument(String s3ObjectKey) throws Exception {
		// ========== 단일 문서 분석 시작 시간 측정 ==========
		long singleDocStartTime = System.currentTimeMillis();
		LocalDateTime docStartTime = LocalDateTime.now();
		
		System.out.println("  🔍 단일 문서 분석 시작: " + s3ObjectKey);
		System.out.println("  📅 시작 시간: " + docStartTime.format(TIME_FORMATTER));

		// OCR API 서버 상태 확인
		long healthCheckStart = System.currentTimeMillis();
		if (!isOcrApiAvailable()) {
			throw new Exception("OCR API 서버가 실행되지 않음 - " + ocrApiUrl);
		}
		long healthCheckTime = System.currentTimeMillis() - healthCheckStart;
		System.out.println("  ✅ API 서버 상태 확인 완료: " + healthCheckTime + "ms");

		try {
			// 1. S3에서 Pre-signed URL 생성
			long s3StartTime = System.currentTimeMillis();
			System.out.println("  🔗 S3 Pre-signed URL 생성 중: " + s3ObjectKey);
			URL presignedUrl = s3Service.generatePresignedGetUrl(bucketName, s3ObjectKey);
			long s3Time = System.currentTimeMillis() - s3StartTime;
			System.out.println("  ✅ Pre-signed URL 생성 완료: " + s3Time + "ms");

			// 2. OCR API 호출
			long ocrApiStart = System.currentTimeMillis();
			System.out.println("  🤖 OCR API 호출 시작...");
			String response = callOcrApi(presignedUrl.toString(), s3ObjectKey);
			long ocrApiTime = System.currentTimeMillis() - ocrApiStart;
			System.out.println("  ✅ OCR API 호출 완료: " + ocrApiTime + "ms");

			// 3. 응답 파싱
			long parseStart = System.currentTimeMillis();
			System.out.println("  📝 응답 파싱 시작...");
			OcrAnalysisResult result = parseOcrResponse(response, s3ObjectKey);
			long parseTime = System.currentTimeMillis() - parseStart;
			System.out.println("  ✅ 응답 파싱 완료: " + parseTime + "ms");

			// ========== 단일 문서 분석 완료 로그 ==========
			long singleDocEndTime = System.currentTimeMillis();
			long totalSingleDocTime = singleDocEndTime - singleDocStartTime;
			LocalDateTime docEndTime = LocalDateTime.now();

			System.out.println("  📊 === 단일 문서 분석 완료 ===");
			System.out.println("  📁 파일: " + s3ObjectKey);
			System.out.println("  📅 완료 시간: " + docEndTime.format(TIME_FORMATTER));
			System.out.println("  ⏱️  총 처리 시간: " + totalSingleDocTime + "ms");
			System.out.println("  📋 세부 시간:");
			System.out.println("    - 서버 상태 확인: " + healthCheckTime + "ms");
			System.out.println("    - S3 URL 생성: " + s3Time + "ms");
			System.out.println("    - OCR API 호출: " + ocrApiTime + "ms");
			System.out.println("    - 응답 파싱: " + parseTime + "ms");
			System.out.println("  🎯 분석 결과: " + result.getDocumentType() + " (신뢰도: " + String.format("%.1f%%", result.getConfidence() * 100) + ")");

			return result;

		} catch (Exception e) {
			long singleDocEndTime = System.currentTimeMillis();
			long totalSingleDocTime = singleDocEndTime - singleDocStartTime;
			
			System.err.println("  ❌ OCR 분석 실패 상세:");
			System.err.println("    - 파일: " + s3ObjectKey);
			System.err.println("    - 처리 시간: " + totalSingleDocTime + "ms");
			System.err.println("    - 오류: " + e.getMessage());
			
			throw new Exception("OCR API 호출 실패: " + e.getMessage(), e);
		}
	}

	/**
	 * OCR API 서버 상태 확인
	 */
	private boolean isOcrApiAvailable() {
		try {
			URL url = new URL(ocrApiUrl + "/health");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);

			int responseCode = conn.getResponseCode();
			System.out.println("    OCR API 서버 상태: HTTP " + responseCode);

			return responseCode == 200;

		} catch (Exception e) {
			System.err.println("    OCR API 서버 연결 실패: " + e.getMessage());
			return false;
		}
	}

	/**
	 * OCR API 호출 (성능 측정 강화)
	 */
	private String callOcrApi(String imageUrl, String s3ObjectKey) throws Exception {
		try {
			URL url = new URL(ocrApiUrl + "/classify-s3");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 요청 설정 (UTF-8 인코딩 명시)
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setConnectTimeout(30000); // 30초
			conn.setReadTimeout(60000); // 60초

			// JSON 요청 바디 생성
			String jsonRequest = String.format("{\"image_url\":\"%s\",\"s3_object_key\":\"%s\"}", imageUrl,
					s3ObjectKey);

			// 요청 전송 시간 측정
			long requestStart = System.currentTimeMillis();
			System.out.println("    📤 OCR API 요청 전송 중...");

			// 요청 전송
			OutputStream os = conn.getOutputStream();
			os.write(jsonRequest.getBytes("UTF-8"));
			os.flush();
			os.close();

			// 응답 읽기 시간 측정
			long responseStart = System.currentTimeMillis();
			long requestTime = responseStart - requestStart;

			// 응답 읽기
			int responseCode = conn.getResponseCode();
			System.out.println("    📥 OCR API 응답 코드: " + responseCode + " (요청 전송: " + requestTime + "ms)");

			BufferedReader br;

			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}

			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			long responseTime = System.currentTimeMillis() - responseStart;
			System.out.println("    📥 응답 읽기 완료: " + responseTime + "ms");

			if (responseCode != 200) {
				throw new Exception("OCR API 오류 (HTTP " + responseCode + "): " + response.toString());
			}

			return response.toString();

		} catch (IOException e) {
			throw new Exception("OCR API 통신 오류: " + e.getMessage(), e);
		}
	}

	/**
	 * OCR API 응답 파싱 (간단한 JSON 파싱)
	 */
	private OcrAnalysisResult parseOcrResponse(String jsonResponse, String s3ObjectKey) {
		OcrAnalysisResult result = new OcrAnalysisResult();
		result.setS3ObjectKey(s3ObjectKey);

		try {
			System.out.println("    🔍 OCR 응답 파싱 중...");

			// 간단한 JSON 파싱 (정규식 사용)
			String documentType = extractJsonValue(jsonResponse, "document_type");
			String confidenceStr = extractJsonValue(jsonResponse, "confidence");

			// 유니코드 이스케이프 디코딩 적용
			String decodedDocumentType = decodeUnicodeEscape(documentType);

			System.out.println("    📄 OCR 원본 문서타입: " + documentType);
			System.out.println("    📝 디코딩된 문서타입: " + decodedDocumentType);

			result.setDocumentType(decodedDocumentType != null ? decodedDocumentType : "미분류");

			if (confidenceStr != null) {
				try {
					result.setConfidence(Double.parseDouble(confidenceStr));
				} catch (NumberFormatException e) {
					result.setConfidence(0.0);
				}
			} else {
				result.setConfidence(0.0);
			}

			// found_keywords 파싱 (기본적인 배열 파싱)
			List<String> keywords = extractJsonArray(jsonResponse, "found_keywords");
			// 키워드도 유니코드 디코딩
			List<String> decodedKeywords = new ArrayList<String>();
			for (String keyword : keywords) {
				decodedKeywords.add(decodeUnicodeEscape(keyword));
			}
			result.setFoundKeywords(decodedKeywords);

			System.out.println("    ✅ OCR 응답 파싱 완료 - 문서유형: " + result.getDocumentType() + 
				", 신뢰도: " + String.format("%.1f%%", result.getConfidence() * 100));

		} catch (Exception e) {
			System.err.println("    ❌ OCR 응답 파싱 오류: " + e.getMessage());
			result.setError("응답 파싱 오류: " + e.getMessage());
			result.setDocumentType("미분류");
			result.setConfidence(0.0);
		}

		return result;
	}

	/**
	 * 유니코드 이스케이프 문자열을 한글로 디코딩 \\uc7a5\\ud574\\uc9c4\\ub2e8\\uc11c -> 장해진단서
	 */
	private String decodeUnicodeEscape(String input) {
		if (input == null || input.trim().isEmpty()) {
			return input;
		}

		try {
			// Java 8 호환 방식으로 유니코드 이스케이프 패턴 처리
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\\\u([0-9a-fA-F]{4})");
			java.util.regex.Matcher matcher = pattern.matcher(input);

			StringBuffer result = new StringBuffer();

			while (matcher.find()) {
				try {
					String hexCode = matcher.group(1);
					int codePoint = Integer.parseInt(hexCode, 16);
					String unicodeChar = String.valueOf((char) codePoint);
					matcher.appendReplacement(result, unicodeChar);
				} catch (NumberFormatException e) {
					System.err.println("    ⚠️  유니코드 변환 실패: " + matcher.group(0));
					// 변환 실패시 원본 그대로 유지
					matcher.appendReplacement(result, java.util.regex.Matcher.quoteReplacement(matcher.group(0)));
				}
			}
			matcher.appendTail(result);

			String decoded = result.toString();

			if (!decoded.equals(input)) {
				System.out.println("    🔤 유니코드 디코딩: " + input + " -> " + decoded);
			}

			return decoded;

		} catch (Exception e) {
			System.err.println("    ❌ 유니코드 디코딩 오류: " + e.getMessage());
			return input; // 실패시 원본 반환
		}
	}

	/**
	 * JSON에서 값 추출 (간단한 정규식 방식)
	 */
	private String extractJsonValue(String json, String key) {
		try {
			String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]+)\"";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
			java.util.regex.Matcher m = p.matcher(json);

			if (m.find()) {
				return m.group(1);
			}

			// 숫자값인 경우
			String numberPattern = "\"" + key + "\"\\s*:\\s*([0-9\\.]+)";
			java.util.regex.Pattern np = java.util.regex.Pattern.compile(numberPattern);
			java.util.regex.Matcher nm = np.matcher(json);

			if (nm.find()) {
				return nm.group(1);
			}

		} catch (Exception e) {
			System.err.println("    ❌ JSON 값 추출 오류: " + e.getMessage());
		}

		return null;
	}

	/**
	 * JSON 배열 추출 (간단한 방식)
	 */
	private List<String> extractJsonArray(String json, String key) {
		List<String> result = new ArrayList<String>();

		try {
			String pattern = "\"" + key + "\"\\s*:\\s*\\[([^\\]]+)\\]";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
			java.util.regex.Matcher m = p.matcher(json);

			if (m.find()) {
				String arrayContent = m.group(1);
				String[] items = arrayContent.split(",");

				for (String item : items) {
					String cleaned = item.trim().replaceAll("\"", "");
					if (!cleaned.isEmpty()) {
						result.add(cleaned);
					}
				}
			}

		} catch (Exception e) {
			System.err.println("    ❌ JSON 배열 추출 오류: " + e.getMessage());
		}

		return result;
	}
}
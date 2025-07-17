package com.demo.proworks.ocr.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
 * @subject : OCR 분석 서비스 구현체
 * @description : Python OCR API를 호출하여 이미지 문서 분석 처리
 * @author : 시스템 관리자
 * @since : 2025/07/17
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/17 시스템 관리자 최초 생성 2025/07/17 시스템 관리자 유니코드 디코딩 및 로직 개선
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
		if (s3ObjectKeys == null || s3ObjectKeys.isEmpty()) {
			System.out.println("S3 객체 키가 없어서 기본값 반환: " + DEFAULT_CLAIM_TYPE);
			return DEFAULT_CLAIM_TYPE;
		}

		System.out.println("=== OCR 분석 시작 ===");
		System.out.println("분석할 파일 수: " + s3ObjectKeys.size());

		List<OcrAnalysisResult> results = new ArrayList<OcrAnalysisResult>();
		int successCount = 0;
		int failCount = 0;

		// 각 이미지를 개별적으로 분석
		for (String objectKey : s3ObjectKeys) {
			try {
				System.out.println("OCR 분석 시작: " + objectKey);
				OcrAnalysisResult result = analyzeSingleDocument(objectKey);
				results.add(result);
				successCount++;
				System.out.println("OCR 분석 성공 - 파일: " + objectKey + ", 유형: " + result.getDocumentType());
			} catch (Exception e) {
				failCount++;
				System.err.println("OCR 분석 실패 - 파일: " + objectKey + ", 오류: " + e.getMessage());
				// 개별 파일 실패는 무시하고 계속 진행
			}
		}

		System.out.println("=== OCR 분석 결과 요약 ===");
		System.out.println("성공: " + successCount + "건, 실패: " + failCount + "건");

		// 분석 결과가 없으면 기본값 반환
		if (results.isEmpty()) {
			System.out.println("모든 OCR 분석이 실패하여 기본값 반환: " + DEFAULT_CLAIM_TYPE);
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

		System.out.println("=== 문서 유형별 집계 ===");
		for (Map.Entry<String, Integer> entry : documentCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + "건");
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
			System.out.println("유효한 문서 유형이 없어 기본값 반환: " + DEFAULT_CLAIM_TYPE);
			return DEFAULT_CLAIM_TYPE;
		}

		// claim_type으로 변환하여 반환
		String claimType = OCR_TO_CLAIM_TYPE.get(highestPriorityType);
		if (claimType == null) {
			claimType = DEFAULT_CLAIM_TYPE;
		}

		System.out.println("=== 최종 결정 ===");
		System.out.println("선택된 문서 유형: " + highestPriorityType + " (우선순위: " + highestPriority + ")");
		System.out.println("최종 claim_type: " + claimType);

		return claimType;
	}

	@Override
	public OcrAnalysisResult analyzeSingleDocument(String s3ObjectKey) throws Exception {

		// OCR API 서버 상태 확인
		if (!isOcrApiAvailable()) {
			throw new Exception("OCR API 서버가 실행되지 않음 - " + ocrApiUrl);
		}

		try {
			// 1. S3에서 Pre-signed URL 생성
			System.out.println("S3 Pre-signed URL 생성 중: " + s3ObjectKey);
			URL presignedUrl = s3Service.generatePresignedGetUrl(bucketName, s3ObjectKey);
			System.out.println("Pre-signed URL 생성 완료");

			// 2. OCR API 호출
			String response = callOcrApi(presignedUrl.toString(), s3ObjectKey);

			// 3. 응답 파싱
			OcrAnalysisResult result = parseOcrResponse(response, s3ObjectKey);

			return result;

		} catch (Exception e) {
			System.err.println("OCR 분석 실패 상세: " + e.getMessage());
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
			System.out.println("OCR API 서버 상태 확인: HTTP " + responseCode);

			return responseCode == 200;

		} catch (Exception e) {
			System.err.println("OCR API 서버 연결 실패: " + e.getMessage());
			return false;
		}
	}

	/**
	 * OCR API 호출
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

			System.out.println("OCR API 요청 전송 중...");

			// 요청 전송
			OutputStream os = conn.getOutputStream();
			os.write(jsonRequest.getBytes("UTF-8"));
			os.flush();
			os.close();

			// 응답 읽기
			int responseCode = conn.getResponseCode();
			System.out.println("OCR API 응답 코드: " + responseCode);

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

			if (responseCode != 200) {
				throw new Exception("OCR API 오류 (HTTP " + responseCode + "): " + response.toString());
			}

			System.out.println("OCR API 응답 수신 완료");
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
			System.out.println("OCR 응답 파싱 중...");

			// 간단한 JSON 파싱 (정규식 사용)
			String documentType = extractJsonValue(jsonResponse, "document_type");
			String confidenceStr = extractJsonValue(jsonResponse, "confidence");

			// 유니코드 이스케이프 디코딩 적용
			String decodedDocumentType = decodeUnicodeEscape(documentType);

			System.out.println("OCR 원본 문서타입: " + documentType);
			System.out.println("디코딩된 문서타입: " + decodedDocumentType);

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

			System.out.println("OCR 응답 파싱 완료 - 문서유형: " + result.getDocumentType());

		} catch (Exception e) {
			System.err.println("OCR 응답 파싱 오류: " + e.getMessage());
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
					System.err.println("유니코드 변환 실패: " + matcher.group(0));
					// 변환 실패시 원본 그대로 유지
					matcher.appendReplacement(result, java.util.regex.Matcher.quoteReplacement(matcher.group(0)));
				}
			}
			matcher.appendTail(result);

			String decoded = result.toString();

			if (!decoded.equals(input)) {
				System.out.println("유니코드 디코딩: " + input + " -> " + decoded);
			}

			return decoded;

		} catch (Exception e) {
			System.err.println("유니코드 디코딩 오류: " + e.getMessage());
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
			System.err.println("JSON 값 추출 오류: " + e.getMessage());
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
			System.err.println("JSON 배열 추출 오류: " + e.getMessage());
		}

		return result;
	}
}
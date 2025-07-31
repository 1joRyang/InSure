package com.demo.proworks.ocr.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.ocr.service.OcrService;
import com.demo.proworks.ocr.vo.OcrAnalysisResult;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;

/**  
 * @subject     : OCR 분석 테스트 컨트롤러
 * @description : OCR 분석 기능을 테스트하기 위한 컨트롤러
 * @author      : 시스템 관리자
 * @since       : 2025/07/17
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/17			시스템 관리자	 		최초 생성
 * 
 */
@Controller
public class OcrController {
    
    @Resource(name = "ocrService")
    private OcrService ocrService;
    
    /**
     * OCR 분석 테스트 API
     */
    @ElService(key = "testOcrAnalysis")
    @RequestMapping(value = "testOcrAnalysis")
    @ElDescription(sub = "OCR 분석 테스트", desc = "S3 이미지를 OCR 분석하여 문서 유형을 분류한다.")
    public OcrAnalysisResult testOcrAnalysis(HttpServletRequest request) throws Exception {
        
        // 테스트용 S3 객체 키 (실제로는 파라미터로 받아야 함)
        String testS3Key = request.getParameter("s3ObjectKey");
        
        if (testS3Key == null || testS3Key.isEmpty()) {
            // 테스트용 기본값
            testS3Key = "uploads/test-document.jpg";
        }
        
        System.out.println("OCR 분석 테스트 시작 - S3 키: " + testS3Key);
        
        try {
            // 단일 문서 분석
            OcrAnalysisResult result = ocrService.analyzeSingleDocument(testS3Key);
            
            System.out.println("OCR 분석 테스트 결과: " + result.toString());
            
            return result;
            
        } catch (Exception e) {
            System.err.println("OCR 분석 테스트 실패: " + e.getMessage());
            e.printStackTrace();
            
            // 오류 결과 반환
            OcrAnalysisResult errorResult = new OcrAnalysisResult();
            errorResult.setS3ObjectKey(testS3Key);
            errorResult.setDocumentType("오류");
            errorResult.setConfidence(0.0);
            errorResult.setError(e.getMessage());
            
            return errorResult;
        }
    }
    
    /**
     * 다중 파일 OCR 분석 테스트 API
     */
    @ElService(key = "testBatchOcrAnalysis")
    @RequestMapping(value = "testBatchOcrAnalysis")
    @ElDescription(sub = "다중 파일 OCR 분석 테스트", desc = "여러 S3 이미지를 OCR 분석하여 최종 문서 유형을 결정한다.")
    public Map<String, Object> testBatchOcrAnalysis(HttpServletRequest request) throws Exception {
        
        // 테스트용 S3 객체 키 목록
        String s3Keys = request.getParameter("s3ObjectKeys");
        
        List<String> s3ObjectKeys;
        if (s3Keys != null && !s3Keys.isEmpty()) {
            s3ObjectKeys = Arrays.asList(s3Keys.split(","));
        } else {
            // 테스트용 기본값
            s3ObjectKeys = new ArrayList<String>();
            s3ObjectKeys.add("uploads/test-death-certificate.jpg");
            s3ObjectKeys.add("uploads/test-disability-report.jpg");
            s3ObjectKeys.add("uploads/test-surgery-document.jpg");
        }
        
        System.out.println("다중 파일 OCR 분석 테스트 시작 - 파일 수: " + s3ObjectKeys.size());
        
        Map<String, Object> response = new HashMap<String, Object>();
        
        try {
            // 다중 문서 분석
            String finalClaimType = ocrService.analyzeDocumentType(s3ObjectKeys);
            
            System.out.println("다중 파일 OCR 분석 테스트 결과: " + finalClaimType);
            
            response.put("success", true);
            response.put("finalClaimType", finalClaimType);
            response.put("fileCount", s3ObjectKeys.size());
            response.put("message", "OCR 분석 완료");
            
        } catch (Exception e) {
            System.err.println("다중 파일 OCR 분석 테스트 실패: " + e.getMessage());
            e.printStackTrace();
            
            response.put("success", false);
            response.put("finalClaimType", "disease");
            response.put("fileCount", s3ObjectKeys.size());
            response.put("error", e.getMessage());
            response.put("message", "OCR 분석 실패 - 기본값(disease) 사용");
        }
        
        return response;
    }
    
    /**
     * OCR 서비스 상태 확인 API
     */
    @ElService(key = "checkOcrStatus")
    @RequestMapping(value = "checkOcrStatus")
    @ElDescription(sub = "OCR 서비스 상태 확인", desc = "OCR API 서버 상태를 확인한다.")
    public Map<String, Object> checkOcrStatus() throws Exception {
        
        Map<String, Object> status = new HashMap<String, Object>();
        
        try {
            // 간단한 테스트 분석
            List<String> testKeys = new ArrayList<String>();
            testKeys.add("test-key");
            
            String result = ocrService.analyzeDocumentType(testKeys);
            
            status.put("ocrApiAvailable", true);
            status.put("testResult", result);
            status.put("message", "OCR 서비스 정상 동작");
            
        } catch (Exception e) {
            status.put("ocrApiAvailable", false);
            status.put("error", e.getMessage());
            status.put("message", "OCR 서비스 비정상 - 기본값(disease) 사용됨");
        }
        
        status.put("defaultClaimType", "disease");
        status.put("timestamp", System.currentTimeMillis());
        
        return status;
    }
}

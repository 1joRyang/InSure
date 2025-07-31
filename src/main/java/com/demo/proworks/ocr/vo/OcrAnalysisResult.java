package com.demo.proworks.ocr.vo;

import java.util.List;

/**  
 * @subject     : OCR 분석 결과 VO
 * @description : OCR API 분석 결과를 담는 Value Object
 * @author      : 시스템 관리자
 * @since       : 2025/07/17
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/17			시스템 관리자	 		최초 생성
 * 
 */
public class OcrAnalysisResult {
    
    /** S3 객체 키 */
    private String s3ObjectKey;
    
    /** 분석된 문서 유형 */
    private String documentType;
    
    /** 분석 신뢰도 */
    private Double confidence;
    
    /** 발견된 키워드 목록 */
    private List<String> foundKeywords;
    
    /** 오류 메시지 */
    private String error;
    
    /** 분석된 텍스트 (일부) */
    private List<String> ocrText;
    
    // 기본 생성자
    public OcrAnalysisResult() {}
    
    // Getters and Setters
    public String getS3ObjectKey() {
        return s3ObjectKey;
    }
    
    public void setS3ObjectKey(String s3ObjectKey) {
        this.s3ObjectKey = s3ObjectKey;
    }
    
    public String getDocumentType() {
        return documentType;
    }
    
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    
    public Double getConfidence() {
        return confidence;
    }
    
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
    
    public List<String> getFoundKeywords() {
        return foundKeywords;
    }
    
    public void setFoundKeywords(List<String> foundKeywords) {
        this.foundKeywords = foundKeywords;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public List<String> getOcrText() {
        return ocrText;
    }
    
    public void setOcrText(List<String> ocrText) {
        this.ocrText = ocrText;
    }
    
    @Override
    public String toString() {
        return "OcrAnalysisResult{" +
                "s3ObjectKey='" + s3ObjectKey + '\'' +
                ", documentType='" + documentType + '\'' +
                ", confidence=" + confidence +
                ", foundKeywords=" + foundKeywords +
                ", error='" + error + '\'' +
                ", ocrText=" + ocrText +
                '}';
    }
}

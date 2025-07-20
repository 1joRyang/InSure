package com.demo.proworks.ocr.service;

import java.util.List;

import com.demo.proworks.ocr.vo.OcrAnalysisResult;

/**  
 * @subject     : OCR 분석 서비스 인터페이스
 * @description : 이미지 문서 분석을 통한 문서 유형 분류 처리
 * @author      : 시스템 관리자
 * @since       : 2025/07/17
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/17			시스템 관리자	 		최초 생성
 * 
 */
public interface OcrService {
    
    /**
     * S3에 저장된 이미지들을 OCR 분석하여 문서 유형을 반환
     * @param s3ObjectKeys S3 객체 키 목록
     * @return 분석된 문서 유형 (death, disability, surgery, other)
     * @throws Exception
     */
    String analyzeDocumentType(List<String> s3ObjectKeys) throws Exception;
    
    /**
     * 단일 이미지 OCR 분석
     * @param s3ObjectKey S3 객체 키
     * @return 분석 결과
     * @throws Exception
     */
    OcrAnalysisResult analyzeSingleDocument(String s3ObjectKey) throws Exception;
}

package com.demo.proworks.insimagefile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.proworks.insimagefile.vo.InsimagefileListVo;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;

/**  
 * @subject     : 이미지파일테이블 관련 처리를 담당하는 인터페이스
 * @description : 이미지파일테이블 관련 처리를 담당하는 인터페이스
 * @author      : 이지현
 * @since       : 2025/07/04
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/04			 이지현	 		최초 생성
 * 
 */
public interface InsimagefileService {
	
    /**
     * 이미지파일테이블 페이징 처리하여 목록을 조회한다.
     *
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 이미지파일테이블 목록 List<InsimagefileVo>
     * @throws Exception
     */
	public List<InsimagefileVo> selectListInsimagefile(InsimagefileVo insimagefileVo) throws Exception;
	
    /**
     * 조회한 이미지파일테이블 전체 카운트
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 이미지파일테이블 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountInsimagefile(InsimagefileVo insimagefileVo) throws Exception;
	
    /**
     * 이미지파일테이블를 상세 조회한다.
     *
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public InsimagefileVo selectInsimagefile(InsimagefileVo insimagefileVo) throws Exception;
		
    /**
     * 이미지파일테이블를 등록 처리 한다.
     *
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 번호
     * @throws Exception
     */
	public int insertInsimagefile(InsimagefileVo insimagefileVo) throws Exception;
	
    /**
     * 이미지파일테이블를 갱신 처리 한다.
     *
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 번호
     * @throws Exception
     */
	public int updateInsimagefile(InsimagefileVo insimagefileVo) throws Exception;
	
    /**
     * 이미지파일테이블를 삭제 처리 한다.
     *
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 번호
     * @throws Exception
     */
	public int deleteInsimagefile(InsimagefileVo insimagefileVo) throws Exception;
	
	/**
     * 보험금 청구 서류(이미지)를 접수하여 저장 한다.
     *
     * @param  param InsimagefileListVo 클라이언트에서 전송한 이미지 데이터 목록
     * @throws Exception
     */
	public void saveImageFiles(List<MultipartFile> files, String claimType) throws Exception;
	
}

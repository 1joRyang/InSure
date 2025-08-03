package com.demo.proworks.additionalreq.service;

import java.util.List;

import com.demo.proworks.additionalreq.vo.AdditionalReqCusVo;
import com.demo.proworks.additionalreq.vo.AdditionalReqVo;

/**  
 * @subject     : 추가요청 정보 관련 처리를 담당하는 인터페이스
 * @description : 추가요청 정보 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/16
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/16			 hyunwoo	 		최초 생성
 * 
 */
public interface AdditionalReqService {
	
    /**
     * 추가요청 정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 추가요청 정보 목록 List<AdditionalReqVo>
     * @throws Exception
     */
	public List<AdditionalReqVo> selectListAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
	
    /**
     * 조회한 추가요청 정보 전체 카운트
     * 
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 추가요청 정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
	
    /**
     * 추가요청 정보를 상세 조회한다.
     *
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public AdditionalReqVo selectAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
	
    /**
     * 추가요청 존재 여부를 확인한다.
     *
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 존재 여부
     * @throws Exception
     */
	public boolean existsAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
		
    /**
     * 추가요청 정보를 등록 처리 한다. (INSERT만)
     *
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 번호
     * @throws Exception
     */
	public int insertAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
	
    /**
     * 추가요청 정보를 갱신 처리 한다. (UPDATE만)
     *
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 번호
     * @throws Exception
     */
	public int updateAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
	
    /**
     * 추가요청 정보를 삭제 처리 한다.
     *
     * @param  additionalReqVo 추가요청 정보 AdditionalReqVo
     * @return 번호
     * @throws Exception
     */
	public int deleteAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception;
	
}
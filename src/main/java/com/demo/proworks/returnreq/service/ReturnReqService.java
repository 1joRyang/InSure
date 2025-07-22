package com.demo.proworks.returnreq.service;

import java.util.List;

import com.demo.proworks.returnreq.vo.ReturnReqVo;

/**  
 * @subject     : 반송요청정보 관련 처리를 담당하는 인터페이스
 * @description : 반송요청정보 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/22
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/22			 hyunwoo	 		최초 생성
 * 
 */
public interface ReturnReqService {
	
    /**
     * 반송요청정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 반송요청정보 목록 List<ReturnReqVo>
     * @throws Exception
     */
	public List<ReturnReqVo> selectListReturnReq(ReturnReqVo returnReqVo) throws Exception;
	
    /**
     * 조회한 반송요청정보 전체 카운트
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 반송요청정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountReturnReq(ReturnReqVo returnReqVo) throws Exception;
	
    /**
     * 반송요청정보를 상세 조회한다.
     *
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ReturnReqVo selectReturnReq(ReturnReqVo returnReqVo) throws Exception;
		
    /**
     * 반송요청정보를 등록 처리 한다.
     *
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 번호
     * @throws Exception
     */
	public int insertReturnReq(ReturnReqVo returnReqVo) throws Exception;
	
    /**
     * 반송요청정보를 갱신 처리 한다.
     *
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 번호
     * @throws Exception
     */
	public int updateReturnReq(ReturnReqVo returnReqVo) throws Exception;
	
    /**
     * 반송요청정보를 삭제 처리 한다.
     *
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 번호
     * @throws Exception
     */
	public int deleteReturnReq(ReturnReqVo returnReqVo) throws Exception;

	public int insertReturnReqAndClaimStatus(ReturnReqVo returnReqVo);

	public int insertAdditionalReqAndUpdateClaimStatus(ReturnReqVo returnReqVo);
	
}

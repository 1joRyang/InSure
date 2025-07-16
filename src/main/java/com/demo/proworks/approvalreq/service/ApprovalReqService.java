package com.demo.proworks.approvalreq.service;

import java.util.List;

import com.demo.proworks.approvalreq.vo.ApprovalReqVo;

/**  
 * @subject     : 결재요청 관련 처리를 담당하는 인터페이스
 * @description : 결재요청 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/14
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/14			 Inswave	 		최초 생성
 * 
 */
public interface ApprovalReqService {
	
    /**
     * 결재요청 페이징 처리하여 목록을 조회한다.
     *
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 결재요청 목록 List<ApprovalReqVo>
     * @throws Exception
     */
	public List<ApprovalReqVo> selectListApprovalReq(ApprovalReqVo approvalReqVo) throws Exception;
	
    /**
     * 조회한 결재요청 전체 카운트
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 결재요청 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountApprovalReq(ApprovalReqVo approvalReqVo) throws Exception;
	
    /**
     * 결재요청를 상세 조회한다.
     *
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ApprovalReqVo selectApprovalReq(ApprovalReqVo approvalReqVo) throws Exception;
		
    /**
     * 결재요청를 등록 처리 한다.
     *
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 번호
     * @throws Exception
     */
	public int insertApprovalReq(ApprovalReqVo approvalReqVo) throws Exception;
	
    /**
     * 결재요청를 갱신 처리 한다.
     *
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 번호
     * @throws Exception
     */
	public int updateApprovalReq(ApprovalReqVo approvalReqVo) throws Exception;
	
    /**
     * 결재요청를 삭제 처리 한다.
     *
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 번호
     * @throws Exception
     */
	public int deleteApprovalReq(ApprovalReqVo approvalReqVo) throws Exception;
	
}

package com.demo.proworks.approvalreq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.approvalreq.vo.ApprovalReqVo;
import com.demo.proworks.approvalreq.dao.ApprovalReqDAO;

/**  
 * @subject     : 결재요청 관련 처리를 담당하는 DAO
 * @description : 결재요청 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/14
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/14			 Inswave	 		최초 생성
 * 
 */
@Repository("approvalReqDAO")
public class ApprovalReqDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 결재요청 상세 조회한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return ApprovalReqVo 결재요청
     * @throws ElException
     */
    public ApprovalReqVo selectApprovalReq(ApprovalReqVo vo) throws ElException {
        return (ApprovalReqVo) selectByPk("com.demo.proworks.approvalreq.selectApprovalReq", vo);
    }

    /**
     * 페이징을 처리하여 결재요청 목록조회를 한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return List<ApprovalReqVo> 결재요청
     * @throws ElException
     */
    public List<ApprovalReqVo> selectListApprovalReq(ApprovalReqVo vo) throws ElException {      	
        return (List<ApprovalReqVo>)list("com.demo.proworks.approvalreq.selectListApprovalReq", vo);
    }

    /**
     * 결재요청 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 결재요청 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountApprovalReq(ApprovalReqVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.approvalreq.selectListCountApprovalReq", vo);
    }
        
    /**
     * 결재요청를 등록한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 번호
     * @throws ElException
     */
    public int insertApprovalReq(ApprovalReqVo vo) throws ElException {    	
        return insert("com.demo.proworks.approvalreq.insertApprovalReq", vo);
    }

    /**
     * 결재요청를 갱신한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 번호
     * @throws ElException
     */
    public int updateApprovalReq(ApprovalReqVo vo) throws ElException {
        return update("com.demo.proworks.approvalreq.updateApprovalReq", vo);
    }

    /**
     * 결재요청를 삭제한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 번호
     * @throws ElException
     */
    public int deleteApprovalReq(ApprovalReqVo vo) throws ElException {
        return delete("com.demo.proworks.approvalreq.deleteApprovalReq", vo);
    }

    /**
     * 결재요청 메모만 업데이트한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 번호
     * @throws ElException
     */
    public int updateApprovalReqMemo(ApprovalReqVo vo) throws ElException {
        return update("com.demo.proworks.approvalreq.updateApprovalReqMemo", vo);
    }

    /**
     * 청구 상태를 결재반려로 변경한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 번호
     * @throws ElException
     */
    public int updateClaimStatusToReject(ApprovalReqVo vo) throws ElException {
        return update("com.demo.proworks.approvalreq.updateClaimStatusToReject", vo);
    }

    /**
     * 청구 상태를 결재완료로 변경한다.
     *  
     * @param  ApprovalReqVo 결재요청
     * @return 번호
     * @throws ElException
     */
    public int updateClaimStatusToApprove(ApprovalReqVo vo) throws ElException {
        return update("com.demo.proworks.approvalreq.updateClaimStatusToApprove", vo);
    }
    /**
	 * claim_no로 approval_id가 가장 높은 결재요청의 메모를 조회한다.
	 *  
	 * @param  ApprovalReqVo 결재요청
	 * @return ApprovalReqVo 결재요청 (approval_memo 포함)
	 * @throws ElException
	 */
	public ApprovalReqVo selectLatestApprovalReqMemo(ApprovalReqVo vo) throws ElException {
	    return (ApprovalReqVo) selectByPk("com.demo.proworks.approvalreq.selectLatestApprovalReqMemo", vo);
	}

}

package com.demo.proworks.approvalreq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.demo.proworks.approvalreq.service.ApprovalReqService;
import com.demo.proworks.approvalreq.vo.ApprovalReqVo;
import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.notification.service.NotificationService;
import com.demo.proworks.approvalreq.dao.ApprovalReqDAO;

/**  
 * @subject     : 결재요청 관련 처리를 담당하는 ServiceImpl
 * @description	: 결재요청 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/14
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/14			 Inswave	 		최초 생성
 * 
 */
@Service("approvalReqServiceImpl")
public class ApprovalReqServiceImpl implements ApprovalReqService {

    @Resource(name="approvalReqDAO")
    private ApprovalReqDAO approvalReqDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "claimServiceImpl") 
    private ClaimService claimService;
    
    @Resource(name = "notificationServiceImpl")
    private NotificationService notificationService;
    

    /**
     * 결재요청 목록을 조회합니다.
     *
     * @process
     * 1. 결재요청 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ApprovalReqVo>을(를) 리턴한다.
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 결재요청 목록 List<ApprovalReqVo>
     * @throws Exception
     */
	public List<ApprovalReqVo> selectListApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		List<ApprovalReqVo> list = approvalReqDAO.selectListApprovalReq(approvalReqVo);	
	
		return list;
	}

    /**
     * 조회한 결재요청 전체 카운트
     *
     * @process
     * 1. 결재요청 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 결재요청 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		return approvalReqDAO.selectListCountApprovalReq(approvalReqVo);
	}

    /**
     * 결재요청를 상세 조회한다.
     *
     * @process
     * 1. 결재요청를 상세 조회한다.
     * 2. 결과 ApprovalReqVo을(를) 리턴한다.
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ApprovalReqVo selectApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		ApprovalReqVo resultVO = approvalReqDAO.selectApprovalReq(approvalReqVo);			
        
        return resultVO;
	}

    /**
     * 결재요청를 등록 처리 한다.
     *
     * @process
     * 1. 결재요청를 등록 처리 한다.
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 번호
     * @throws Exception
     */
	public int insertApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		return approvalReqDAO.insertApprovalReq(approvalReqVo);	
	}
	
    /**
     * 결재요청를 갱신 처리 한다.
     *
     * @process
     * 1. 결재요청를 갱신 처리 한다.
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 번호
     * @throws Exception
     */
	public int updateApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {				
		return approvalReqDAO.updateApprovalReq(approvalReqVo);	   		
	}

    /**
     * 결재요청를 삭제 처리 한다.
     *
     * @process
     * 1. 결재요청를 삭제 처리 한다.
     * 
     * @param  approvalReqVo 결재요청 ApprovalReqVo
     * @return 번호
     * @throws Exception
     */
	public int deleteApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		return approvalReqDAO.deleteApprovalReq(approvalReqVo);
	}
	
	/**
	 * 결재요청을 반려 처리한다.
	 *
	 * @process
	 * 1. APPROVAL_REQ 테이블의 approval_memo 업데이트
	 * 2. CLAIM 테이블의 status를 "결재반려"로 변경
	 * 
	 * @param  approvalReqVo 결재요청 ApprovalReqVo
	 * @throws Exception
	 */
	@Transactional
	public void rejectApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		
		System.out.println("[결재반려] 처리 시작: " + approvalReqVo.getClaim_no());
		
	    // 1. 결재요청 테이블의 메모 업데이트
	    approvalReqDAO.updateApprovalReqMemo(approvalReqVo);
	    System.out.println("[결재반려] 메모 업데이트 완료");
	    
	    // 2. 청구 정보 조회
	    ClaimVo claimToFind = new ClaimVo();
	    claimToFind.setClaim_no(approvalReqVo.getClaim_no());
	    ClaimVo claimToUpdate = claimService.selectClaim(claimToFind);
	    
	    if (claimToUpdate == null) {
	        throw new Exception("해당 청구를 찾을 수 없습니다: " + approvalReqVo.getClaim_no());
	    }
	
	    // 3. ✨ 청구 상태를 '결재반려'로 변경 (알림은 ClaimService에서 자동 처리)
	    claimToUpdate.setStatus("결재반려");
        int updateResult = claimService.updateClaim(claimToUpdate);
        
        if (updateResult > 0) {
            System.out.println("[결재반려] 청구 상태 변경 및 알림 전송 완료: " + approvalReqVo.getClaim_no());
        } else {
            System.err.println("[결재반려] 청구 상태 변경 실패: " + approvalReqVo.getClaim_no());
        }
	}

	/**
	 * 결재요청을 승인 처리한다.
	 *
	 * @process
	 * 1. CLAIM 테이블의 status를 "결재완료"로 변경
	 * 
	 * @param  approvalReqVo 결재요청 ApprovalReqVo
	 * @throws Exception
	 */
	@Transactional
	public void approveApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
		System.out.println("[결재승인] 처리 시작: " + approvalReqVo.getClaim_no());
		
        // 1. 청구 정보 조회
        ClaimVo claimToFind = new ClaimVo();
        claimToFind.setClaim_no(approvalReqVo.getClaim_no());
        ClaimVo claimInfo = claimService.selectClaim(claimToFind);
        
        if (claimInfo == null) {
            throw new Exception("해당 청구를 찾을 수 없습니다: " + approvalReqVo.getClaim_no());
        }

        // 2. ✨ 청구 상태를 "결재완료"로 변경 (알림은 ClaimService에서 자동 처리)
        claimInfo.setStatus("결재완료");
        int updateResult = claimService.updateClaim(claimInfo);
        
        if (updateResult > 0) {
            System.out.println("[결재승인] 청구 상태 변경 및 알림 전송 완료: " + approvalReqVo.getClaim_no());
        } else {
            System.err.println("[결재승인] 청구 상태 변경 실패: " + approvalReqVo.getClaim_no());
        }
	}
	

	


}
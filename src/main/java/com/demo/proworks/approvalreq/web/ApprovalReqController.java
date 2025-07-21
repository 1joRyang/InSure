package com.demo.proworks.approvalreq.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.approvalreq.service.ApprovalReqService;
import com.demo.proworks.approvalreq.vo.ApprovalReqVo;
import com.demo.proworks.approvalreq.vo.ApprovalReqListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 결재요청 관련 처리를 담당하는 컨트롤러
 * @description : 결재요청 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/14
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/14			 Inswave	 		최초 생성
 * 
 */
@Controller
public class ApprovalReqController {
	
    /** ApprovalReqService */
    @Resource(name = "approvalReqServiceImpl")
    private ApprovalReqService approvalReqService;
	
    
    /**
     * 결재요청 목록을 조회합니다.
     *
     * @param  approvalReqVo 결재요청
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="APPROVALREQList")
    @RequestMapping(value="APPROVALREQList")    
    @ElDescription(sub="결재요청 목록조회",desc="페이징을 처리하여 결재요청 목록 조회를 한다.")               
    public ApprovalReqListVo selectListApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {    	   	

        List<ApprovalReqVo> approvalReqList = approvalReqService.selectListApprovalReq(approvalReqVo);                  
        long totCnt = approvalReqService.selectListCountApprovalReq(approvalReqVo);
	
		ApprovalReqListVo retApprovalReqList = new ApprovalReqListVo();
		retApprovalReqList.setApprovalReqVoList(approvalReqList); 
		retApprovalReqList.setTotalCount(totCnt);
		retApprovalReqList.setPageSize(approvalReqVo.getPageSize());
		retApprovalReqList.setPageIndex(approvalReqVo.getPageIndex());

        return retApprovalReqList;            
    }  
        
    /**
     * 결재요청을 단건 조회 처리 한다.
     *
     * @param  approvalReqVo 결재요청
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "APPROVALREQUpdView")    
    @RequestMapping(value="APPROVALREQUpdView") 
    @ElDescription(sub = "결재요청 갱신 폼을 위한 조회", desc = "결재요청 갱신 폼을 위한 조회를 한다.")    
    public ApprovalReqVo selectApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
    	ApprovalReqVo selectApprovalReqVo = approvalReqService.selectApprovalReq(approvalReqVo);    	    
		
        return selectApprovalReqVo;
    } 
 
    /**
     * 결재요청를 등록 처리 한다.
     *
     * @param  approvalReqVo 결재요청
     * @throws Exception
     */
    @ElService(key="APPROVALREQIns")    
    @RequestMapping(value="APPROVALREQIns")
    @ElDescription(sub="결재요청 등록처리",desc="결재요청를 등록 처리 한다.")
    public void insertApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {    	 
    	approvalReqService.insertApprovalReq(approvalReqVo);   
    }
       
    /**
     * 결재요청를 갱신 처리 한다.
     *
     * @param  approvalReqVo 결재요청
     * @throws Exception
     */
    @ElService(key="APPROVALREQUpd")    
    @RequestMapping(value="APPROVALREQUpd")    
    @ElValidator(errUrl="/approvalReq/approvalReqRegister", errContinue=true)
    @ElDescription(sub="결재요청 갱신처리",desc="결재요청를 갱신 처리 한다.")    
    public void updateApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {  
 
    	approvalReqService.updateApprovalReq(approvalReqVo);                                            
    }

    /**
     * 결재요청를 삭제 처리한다.
     *
     * @param  approvalReqVo 결재요청    
     * @throws Exception
     */
    @ElService(key = "APPROVALREQDel")    
    @RequestMapping(value="APPROVALREQDel")
    @ElDescription(sub = "결재요청 삭제처리", desc = "결재요청를 삭제 처리한다.")    
    public void deleteApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {
        approvalReqService.deleteApprovalReq(approvalReqVo);
    }

    /**
     * 결재요청을 반려 처리한다.
     *
     * @param  approvalReqVo 결재요청
     * @throws Exception
     */
    @ElService(key="APPROVALREQReject")    
    @RequestMapping(value="APPROVALREQReject")
    @ElDescription(sub="결재요청 반려처리",desc="결재요청을 반려 처리하고 청구 상태를 변경한다.")    
    public void rejectApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {  
        approvalReqService.rejectApprovalReq(approvalReqVo);                                            
    }

    /**
     * 결재요청을 승인 처리한다.
     *
     * @param  approvalReqVo 결재요청
     * @throws Exception
     */
    @ElService(key="APPROVALREQApprove")    
    @RequestMapping(value="APPROVALREQApprove")
    @ElDescription(sub="결재요청 승인처리",desc="결재요청을 승인 처리하고 청구 상태를 변경한다.")    
    public void approveApprovalReq(ApprovalReqVo approvalReqVo) throws Exception {  
        approvalReqService.approveApprovalReq(approvalReqVo);                                            
    }

}

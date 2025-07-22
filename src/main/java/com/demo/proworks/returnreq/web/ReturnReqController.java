package com.demo.proworks.returnreq.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.returnreq.service.ReturnReqService;
import com.demo.proworks.returnreq.vo.ReturnReqVo;
import com.demo.proworks.returnreq.vo.ReturnReqListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 반송요청정보 관련 처리를 담당하는 컨트롤러
 * @description : 반송요청정보 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/22
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/22			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class ReturnReqController {
	
    /** ReturnReqService */
    @Resource(name = "returnReqServiceImpl")
    private ReturnReqService returnReqService;
	
    
    /**
     * 반송요청정보 목록을 조회합니다.
     *
     * @param  returnReqVo 반송요청정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="RETURNREQList")
    @RequestMapping(value="RETURNREQList")    
    @ElDescription(sub="반송요청정보 목록조회",desc="페이징을 처리하여 반송요청정보 목록 조회를 한다.")               
    public ReturnReqListVo selectListReturnReq(ReturnReqVo returnReqVo) throws Exception {    	   	

        List<ReturnReqVo> returnReqList = returnReqService.selectListReturnReq(returnReqVo);                  
        long totCnt = returnReqService.selectListCountReturnReq(returnReqVo);
	
		ReturnReqListVo retReturnReqList = new ReturnReqListVo();
		retReturnReqList.setReturnReqVoList(returnReqList); 
		retReturnReqList.setTotalCount(totCnt);
		retReturnReqList.setPageSize(returnReqVo.getPageSize());
		retReturnReqList.setPageIndex(returnReqVo.getPageIndex());

        return retReturnReqList;            
    }  
        
    /**
     * 반송요청정보을 단건 조회 처리 한다.
     *
     * @param  returnReqVo 반송요청정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "RETURNREQUpdView")    
    @RequestMapping(value="RETURNREQUpdView") 
    @ElDescription(sub = "반송요청정보 갱신 폼을 위한 조회", desc = "반송요청정보 갱신 폼을 위한 조회를 한다.")    
    public ReturnReqVo selectReturnReq(ReturnReqVo returnReqVo) throws Exception {
    	ReturnReqVo selectReturnReqVo = returnReqService.selectReturnReq(returnReqVo);    	    
		
        return selectReturnReqVo;
    } 
 
    /**
     * 반송요청정보를 등록 처리 한다.
     *
     * @param  returnReqVo 반송요청정보
     * @throws Exception
     */
    @ElService(key="RETURNREQIns")    
    @RequestMapping(value="RETURNREQIns")
    @ElDescription(sub="반송요청정보 등록처리",desc="반송요청정보를 등록 처리 한다.")
    public void insertReturnReq(ReturnReqVo returnReqVo) throws Exception {    	 
    	returnReqService.insertAdditionalReqAndUpdateClaimStatus(returnReqVo);   
    }
       
    /**
     * 반송요청정보를 갱신 처리 한다.
     *
     * @param  returnReqVo 반송요청정보
     * @throws Exception
     */
    @ElService(key="RETURNREQUpd")    
    @RequestMapping(value="RETURNREQUpd")    
    @ElValidator(errUrl="/returnReq/returnReqRegister", errContinue=true)
    @ElDescription(sub="반송요청정보 갱신처리",desc="반송요청정보를 갱신 처리 한다.")    
    public void updateReturnReq(ReturnReqVo returnReqVo) throws Exception {  
 
    	returnReqService.updateReturnReq(returnReqVo);                                            
    }

    /**
     * 반송요청정보를 삭제 처리한다.
     *
     * @param  returnReqVo 반송요청정보    
     * @throws Exception
     */
    @ElService(key = "RETURNREQDel")    
    @RequestMapping(value="RETURNREQDel")
    @ElDescription(sub = "반송요청정보 삭제처리", desc = "반송요청정보를 삭제 처리한다.")    
    public void deleteReturnReq(ReturnReqVo returnReqVo) throws Exception {
        returnReqService.deleteReturnReq(returnReqVo);
    }
    
    
    @ElService(key = "RETURNREQInsUp")    
    @RequestMapping(value = "RETURNREQInsUp")
    @ElDescription(sub = "반송요청정보 등록처리", desc = "반송요청정보를 등록 처리 한다.")
    public Map<String, Object> insertReturnReqAndClaimStatus(ReturnReqVo returnReqVo) throws Exception {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        int insertResult = returnReqService.insertReturnReqAndClaimStatus(returnReqVo);
	        result.put("success", true);
	        result.put("message", "등록이 완료되었습니다.");
	    } catch (Exception e) {
	        result.put("success", false);
	        result.put("message", "등록 중 오류가 발생했습니다.");
	    }
	    return result;
   }
}

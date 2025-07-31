package com.demo.proworks.additionalreq.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.additionalreq.service.AdditionalReqService;
import com.demo.proworks.additionalreq.vo.AdditionalReqVo;
import com.demo.proworks.additionalreq.vo.AdditionalReqCusVo;
import com.demo.proworks.additionalreq.vo.AdditionalReqListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 추가요청 정보 관련 처리를 담당하는 컨트롤러
 * @description : 추가요청 정보 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/16
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/16			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class AdditionalReqController {
	
    /** AdditionalReqService */
    @Resource(name = "additionalReqServiceImpl")
    private AdditionalReqService additionalReqService;
	
    
    /**
     * 추가요청 정보 목록을 조회합니다.
     *
     * @param  additionalReqVo 추가요청 정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="ADDITIONALREQList")
    @RequestMapping(value="ADDITIONALREQList")    
    @ElDescription(sub="추가요청 정보 목록조회",desc="페이징을 처리하여 추가요청 정보 목록 조회를 한다.")               
    public AdditionalReqListVo selectListAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {    	   	

        List<AdditionalReqVo> additionalReqList = additionalReqService.selectListAdditionalReq(additionalReqVo);                  
        long totCnt = additionalReqService.selectListCountAdditionalReq(additionalReqVo);
	
		AdditionalReqListVo retAdditionalReqList = new AdditionalReqListVo();
		retAdditionalReqList.setAdditionalReqVoList(additionalReqList); 
		retAdditionalReqList.setTotalCount(totCnt);
		retAdditionalReqList.setPageSize(additionalReqVo.getPageSize());
		retAdditionalReqList.setPageIndex(additionalReqVo.getPageIndex());

        return retAdditionalReqList;            
    }  
        
    /**
     * 추가요청 정보을 단건 조회 처리 한다.
     *
     * @param  additionalReqVo 추가요청 정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "ADDITIONALREQUpdView")    
    @RequestMapping(value="ADDITIONALREQUpdView") 
    @ElDescription(sub = "추가요청 정보 갱신 폼을 위한 조회", desc = "추가요청 정보 갱신 폼을 위한 조회를 한다.")    
    public AdditionalReqVo selectAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
    	AdditionalReqVo selectAdditionalReqVo = additionalReqService.selectAdditionalReq(additionalReqVo);    	    
		
        return selectAdditionalReqVo;
    }

    /**
     * 추가요청 존재 여부만 확인한다.
     *
     * @param  additionalReqVo 추가요청 정보
     * @return 존재 여부 결과
     * @throws Exception
     */
    @ElService(key = "ADDITIONALREQCheck")    
    @RequestMapping(value = "ADDITIONALREQCheck")
    @ElDescription(sub = "추가요청 정보 존재 여부 확인", desc = "추가요청 정보 존재 여부를 확인한다.")
    public Map<String, Object> checkAdditionalReqExists(AdditionalReqVo additionalReqVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean exists = additionalReqService.existsAdditionalReq(additionalReqVo);
            result.put("exists", exists);
            result.put("success", true);
            result.put("message", "확인 완료");
        } catch (Exception e) {
            result.put("exists", false);
            result.put("success", false);
            result.put("message", "확인 중 오류가 발생했습니다.");
        }
        return result;
    }

    /**
     * 추가요청 정보를 INSERT만 처리 한다.
     *
     * @param  additionalReqVo 추가요청 정보
     * @throws Exception
     */
    @ElService(key="ADDITIONALREQInsertOnly")    
    @RequestMapping(value="ADDITIONALREQInsertOnly")
    @ElDescription(sub="추가요청 정보 등록처리 (INSERT만)",desc="추가요청 정보를 등록만 처리 한다.")
    public Map<String, Object> insertAdditionalReqOnly(AdditionalReqVo additionalReqVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        try {
            int insertResult = additionalReqService.insertAdditionalReq(additionalReqVo);
            result.put("success", true);
            result.put("message", "등록이 완료되었습니다.");
            result.put("result", insertResult);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "등록 중 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }
       
    /**
     * 추가요청 정보를 UPDATE만 처리 한다.
     *
     * @param  additionalReqVo 추가요청 정보
     * @throws Exception
     */
    @ElService(key="ADDITIONALREQUpdateOnly")    
    @RequestMapping(value="ADDITIONALREQUpdateOnly")    
    @ElDescription(sub="추가요청 정보 갱신처리 (UPDATE만)",desc="추가요청 정보를 갱신만 처리 한다.")    
    public Map<String, Object> updateAdditionalReqOnly(AdditionalReqVo additionalReqVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        try {
            int updateResult = additionalReqService.updateAdditionalReq(additionalReqVo);
            result.put("success", true);
            result.put("message", "수정이 완료되었습니다.");
            result.put("result", updateResult);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "수정 중 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }

    /**
     * 추가요청 정보를 삭제 처리한다.
     *
     * @param  additionalReqVo 추가요청 정보    
     * @throws Exception
     */
    @ElService(key = "ADDITIONALREQDel")    
    @RequestMapping(value="ADDITIONALREQDel")
    @ElDescription(sub = "추가요청 정보 삭제처리", desc = "추가요청 정보를 삭제 처리한다.")    
    public void deleteAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
        additionalReqService.deleteAdditionalReq(additionalReqVo);
    }
    
    
    // ============================================
    // 기존 메소드들 (호환성 유지)
    // ============================================
    
    /**
     * 추가요청 정보를 등록 처리 한다. (기존 호환성)
     *
     * @param  additionalReqVo 추가요청 정보
     * @throws Exception
     */
    @ElService(key="ADDITIONALREQIns")    
    @RequestMapping(value="ADDITIONALREQIns")
    @ElDescription(sub="추가요청 정보 등록처리",desc="추가요청 정보를 등록 처리 한다.")
    public void insertAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {    	
    	System.out.println("=== 컨트롤러 진입 ===");
    	System.out.println("claim_no: " + additionalReqVo.getClaim_no());
    	System.out.println("additional_memo: " + additionalReqVo.getAdditional_memo());
    	additionalReqService.insertAdditionalReqAndUpdateClaimStatus(additionalReqVo);   
    }
       
    /**
     * 추가요청 정보를 갱신 처리 한다. (기존 호환성)
     *
     * @param  additionalReqVo 추가요청 정보
     * @throws Exception
     */
    @ElService(key="ADDITIONALREQUpd")    
    @RequestMapping(value="ADDITIONALREQUpd")    
    @ElValidator(errUrl="/additionalReq/additionalReqRegister", errContinue=true)
    @ElDescription(sub="추가요청 정보 갱신처리",desc="추가요청 정보를 갱신 처리 한다.")    
    public void updateAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {  
 
    	additionalReqService.updateAdditionalReq(additionalReqVo);                                            
    }
    
    /**
     * 추가요청 정보를 Upsert 처리 한다. 
     *
     * @param  additionalReqCusVo 추가요청 정보
     * @throws Exception
     */
    @ElService(key = "ADDITIONALREQInsUp")    
    @RequestMapping(value = "ADDITIONALREQInsUp")
    @ElDescription(sub = "추가요청 정보 등록처리", desc = "추가요청 정보를 등록 처리 한다.")
    public void upsertAdditionalReqAndUpdateClaimStatus(AdditionalReqCusVo additionalReqCusVo) throws Exception {    	
    	System.out.println("=== 컨트롤러 진입 ===");
    	System.out.println("claim_no: " + additionalReqCusVo.getClaim_no());
    	System.out.println("additional_memo: " + additionalReqCusVo.getAdditional_memo());
    	additionalReqService.upsertAdditionalReqAndUpdateClaimStatus(additionalReqCusVo);   
    }
}
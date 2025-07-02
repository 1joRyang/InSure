package com.demo.proworks.claim.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.claim.vo.ClaimListVo;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 청구 관련 처리를 담당하는 컨트롤러
 * @description : 청구 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
@Controller
public class ClaimController {
	
    /** ClaimService */
    @Resource(name = "claimServiceImpl")
    private ClaimService claimService;
    
    /**
	 * 기지급이력 조회 (insure-second)
	 */
	 @ElService(key = "CLAIMnCResultList")
    @RequestMapping(value = "CLAIMnCResultList")    
    @ElDescription(sub = "기지급이력 조회 (CLAIM, CLAIM RESULT 조인)", desc = "기지급이력 조회 (페이징없음)")               
    public List<ClaimNClaimResultVo> selectListClaimNClaimResult(ClaimUserVo claimVo) throws Exception {    	   	
        List<ClaimNClaimResultVo> claimList = claimService.selectClaimNClaimResult(claimVo);                  

        return claimList;            
    }  
	
    
    /**
     * 청구 목록을 조회합니다.
     *
     * @param  claimVo 청구
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="CLAIMList")
    @RequestMapping(value="CLAIMList")    
    @ElDescription(sub="청구 목록조회",desc="페이징을 처리하여 청구 목록 조회를 한다.")               
    public ClaimListVo selectListClaim(ClaimVo claimVo) throws Exception {    	   	

        List<ClaimVo> claimList = claimService.selectListClaim(claimVo);                  
        long totCnt = claimService.selectListCountClaim(claimVo);
	
		ClaimListVo retClaimList = new ClaimListVo();
		retClaimList.setClaimVoList(claimList); 
		retClaimList.setTotalCount(totCnt);
		retClaimList.setPageSize(claimVo.getPageSize());
		retClaimList.setPageIndex(claimVo.getPageIndex());

        return retClaimList;            
    }  
        
    /**
     * 청구을 단건 조회 처리 한다.
     *
     * @param  claimVo 청구
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMUpdView")    
    @RequestMapping(value="CLAIMUpdView") 
    @ElDescription(sub = "청구 갱신 폼을 위한 조회", desc = "청구 갱신 폼을 위한 조회를 한다.")    
    public ClaimVo selectClaim(ClaimVo claimVo) throws Exception {
    	ClaimVo selectClaimVo = claimService.selectClaim(claimVo);    	    
		
        return selectClaimVo;
    } 
 
    /**
     * 청구를 등록 처리 한다.
     *
     * @param  claimVo 청구
     * @throws Exception
     */
    @ElService(key="CLAIMIns")    
    @RequestMapping(value="CLAIMIns")
    @ElDescription(sub="청구 등록처리",desc="청구를 등록 처리 한다.")
    public void insertClaim(ClaimVo claimVo) throws Exception {    	 
    	claimService.insertClaim(claimVo);   
    }
       
    /**
     * 청구를 갱신 처리 한다.
     *
     * @param  claimVo 청구
     * @throws Exception
     */
    @ElService(key="CLAIMUpd")    
    @RequestMapping(value="CLAIMUpd")    
    @ElValidator(errUrl="/claim/claimRegister", errContinue=true)
    @ElDescription(sub="청구 갱신처리",desc="청구를 갱신 처리 한다.")    
    public void updateClaim(ClaimVo claimVo) throws Exception {  
 
    	claimService.updateClaim(claimVo);                                            
    }

    /**
     * 청구를 삭제 처리한다.
     *
     * @param  claimVo 청구    
     * @throws Exception
     */
    @ElService(key = "CLAIMDel")    
    @RequestMapping(value="CLAIMDel")
    @ElDescription(sub = "청구 삭제처리", desc = "청구를 삭제 처리한다.")    
    public void deleteClaim(ClaimVo claimVo) throws Exception {
        claimService.deleteClaim(claimVo);
    }
   
}

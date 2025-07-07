package com.demo.proworks.claimoffset.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.claimoffset.service.ClaimOffsetService;
import com.demo.proworks.claimoffset.vo.ClaimOffsetVo;
import com.demo.proworks.claimoffset.vo.ClaimOffsetListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 상계 관련 처리를 담당하는 컨트롤러
 * @description : 상계 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Controller
public class ClaimOffsetController {
	
    /** ClaimOffsetService */
    @Resource(name = "claimOffsetServiceImpl")
    private ClaimOffsetService claimOffsetService;
	
    
    /**
     * 상계 목록을 조회합니다.
     *
     * @param  claimOffsetVo 상계
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="CLAIMOFFSETList")
    @RequestMapping(value="CLAIMOFFSETList")    
    @ElDescription(sub="상계 목록조회",desc="페이징을 처리하여 상계 목록 조회를 한다.")               
    public ClaimOffsetListVo selectListClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {    	   	

        List<ClaimOffsetVo> claimOffsetList = claimOffsetService.selectListClaimOffset(claimOffsetVo);                  
        long totCnt = claimOffsetService.selectListCountClaimOffset(claimOffsetVo);
	
		ClaimOffsetListVo retClaimOffsetList = new ClaimOffsetListVo();
		retClaimOffsetList.setClaimOffsetVoList(claimOffsetList); 
		retClaimOffsetList.setTotalCount(totCnt);
		retClaimOffsetList.setPageSize(claimOffsetVo.getPageSize());
		retClaimOffsetList.setPageIndex(claimOffsetVo.getPageIndex());

        return retClaimOffsetList;            
    }  
        
    /**
     * 상계을 단건 조회 처리 한다.
     *
     * @param  claimOffsetVo 상계
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMOFFSETUpdView")    
    @RequestMapping(value="CLAIMOFFSETUpdView") 
    @ElDescription(sub = "상계 갱신 폼을 위한 조회", desc = "상계 갱신 폼을 위한 조회를 한다.")    
    public ClaimOffsetVo selectClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
    	ClaimOffsetVo selectClaimOffsetVo = claimOffsetService.selectClaimOffset(claimOffsetVo);    	    
		
        return selectClaimOffsetVo;
    } 
 
    /**
     * 상계를 등록 처리 한다.
     *
     * @param  claimOffsetVo 상계
     * @throws Exception
     */
    @ElService(key="CLAIMOFFSETIns")    
    @RequestMapping(value="CLAIMOFFSETIns")
    @ElDescription(sub="상계 등록처리",desc="상계를 등록 처리 한다.")
    public void insertClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {    	 
    	claimOffsetService.insertClaimOffset(claimOffsetVo);   
    }
       
    /**
     * 상계를 갱신 처리 한다.
     *
     * @param  claimOffsetVo 상계
     * @throws Exception
     */
    @ElService(key="CLAIMOFFSETUpd")    
    @RequestMapping(value="CLAIMOFFSETUpd")    
    @ElValidator(errUrl="/claimOffset/claimOffsetRegister", errContinue=true)
    @ElDescription(sub="상계 갱신처리",desc="상계를 갱신 처리 한다.")    
    public void updateClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {  
 
    	claimOffsetService.updateClaimOffset(claimOffsetVo);                                            
    }

    /**
     * 상계를 삭제 처리한다.
     *
     * @param  claimOffsetVo 상계    
     * @throws Exception
     */
    @ElService(key = "CLAIMOFFSETDel")    
    @RequestMapping(value="CLAIMOFFSETDel")
    @ElDescription(sub = "상계 삭제처리", desc = "상계를 삭제 처리한다.")    
    public void deleteClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
        claimOffsetService.deleteClaimOffset(claimOffsetVo);
    }
   
}

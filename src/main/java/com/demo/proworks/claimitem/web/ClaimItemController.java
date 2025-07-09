package com.demo.proworks.claimitem.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.claimitem.service.ClaimItemService;
import com.demo.proworks.claimitem.vo.ClaimItemVo;
import com.demo.proworks.claimitem.vo.ClaimItemListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 청구 아이템들 관련 처리를 담당하는 컨트롤러
 * @description : 청구 아이템들 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Controller
public class ClaimItemController {
	
    /** ClaimItemService */
    @Resource(name = "claimItemServiceImpl")
    private ClaimItemService claimItemService;
	
    
    /**
     * 청구 아이템들 목록을 조회합니다.
     *
     * @param  claimItemVo 청구 아이템들
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="CLAIMITEMList")
    @RequestMapping(value="CLAIMITEMList")    
    @ElDescription(sub="청구 아이템들 목록조회",desc="페이징을 처리하여 청구 아이템들 목록 조회를 한다.")               
    public ClaimItemListVo selectListClaimItem(ClaimItemVo claimItemVo) throws Exception {    	   	

        List<ClaimItemVo> claimItemList = claimItemService.selectListClaimItem(claimItemVo);                  
        long totCnt = claimItemService.selectListCountClaimItem(claimItemVo);
	
		ClaimItemListVo retClaimItemList = new ClaimItemListVo();
		retClaimItemList.setClaimItemVoList(claimItemList); 
		retClaimItemList.setTotalCount(totCnt);
		retClaimItemList.setPageSize(claimItemVo.getPageSize());
		retClaimItemList.setPageIndex(claimItemVo.getPageIndex());

        return retClaimItemList;            
    }  
        
    /**
     * 청구 아이템들을 단건 조회 처리 한다.
     *
     * @param  claimItemVo 청구 아이템들
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMITEMUpdView")    
    @RequestMapping(value="CLAIMITEMUpdView") 
    @ElDescription(sub = "청구 아이템들 갱신 폼을 위한 조회", desc = "청구 아이템들 갱신 폼을 위한 조회를 한다.")    
    public ClaimItemVo selectClaimItem(ClaimItemVo claimItemVo) throws Exception {
    	ClaimItemVo selectClaimItemVo = claimItemService.selectClaimItem(claimItemVo);    	    
		
        return selectClaimItemVo;
    } 
 
    /**
     * 청구 아이템들를 등록 처리 한다.
     *
     * @param  claimItemVo 청구 아이템들
     * @throws Exception
     */
    @ElService(key="CLAIMITEMIns")    
    @RequestMapping(value="CLAIMITEMIns")
    @ElDescription(sub="청구 아이템들 등록처리",desc="청구 아이템들를 등록 처리 한다.")
    public void insertClaimItem(ClaimItemVo claimItemVo) throws Exception {    	 
    	claimItemService.insertClaimItem(claimItemVo);   
    }
       
    /**
     * 청구 아이템들를 갱신 처리 한다.
     *
     * @param  claimItemVo 청구 아이템들
     * @throws Exception
     */
    @ElService(key="CLAIMITEMUpd")    
    @RequestMapping(value="CLAIMITEMUpd")    
    @ElValidator(errUrl="/claimItem/claimItemRegister", errContinue=true)
    @ElDescription(sub="청구 아이템들 갱신처리",desc="청구 아이템들를 갱신 처리 한다.")    
    public void updateClaimItem(ClaimItemVo claimItemVo) throws Exception {  
 
    	claimItemService.updateClaimItem(claimItemVo);                                            
    }

    /**
     * 청구 아이템들를 삭제 처리한다.
     *
     * @param  claimItemVo 청구 아이템들    
     * @throws Exception
     */
    @ElService(key = "CLAIMITEMDel")    
    @RequestMapping(value="CLAIMITEMDel")
    @ElDescription(sub = "청구 아이템들 삭제처리", desc = "청구 아이템들를 삭제 처리한다.")    
    public void deleteClaimItem(ClaimItemVo claimItemVo) throws Exception {
        claimItemService.deleteClaimItem(claimItemVo);
    }
   
}

package com.demo.proworks.settlement.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.settlement.service.SettlementService;
import com.demo.proworks.settlement.vo.SettlementVo;
import com.demo.proworks.settlement.vo.SettlementListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 정산정보 관련 처리를 담당하는 컨트롤러
 * @description : 정산정보 관련 처리를 담당하는 컨트롤러
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
public class SettlementController {
	
    /** SettlementService */
    @Resource(name = "settlementServiceImpl")
    private SettlementService settlementService;
	
    
    /**
     * 정산정보 목록을 조회합니다.
     *
     * @param  settlementVo 정산정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="SETTLEMENTList")
    @RequestMapping(value="SETTLEMENTList")    
    @ElDescription(sub="정산정보 목록조회",desc="페이징을 처리하여 정산정보 목록 조회를 한다.")               
    public SettlementListVo selectListSettlement(SettlementVo settlementVo) throws Exception {    	   	

        List<SettlementVo> settlementList = settlementService.selectListSettlement(settlementVo);                  
        long totCnt = settlementService.selectListCountSettlement(settlementVo);
	
		SettlementListVo retSettlementList = new SettlementListVo();
		retSettlementList.setSettlementVoList(settlementList); 
		retSettlementList.setTotalCount(totCnt);
		retSettlementList.setPageSize(settlementVo.getPageSize());
		retSettlementList.setPageIndex(settlementVo.getPageIndex());

        return retSettlementList;            
    }  
        
    /**
     * 정산정보을 단건 조회 처리 한다.
     *
     * @param  settlementVo 정산정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "SETTLEMENTUpdView")    
    @RequestMapping(value="SETTLEMENTUpdView") 
    @ElDescription(sub = "정산정보 갱신 폼을 위한 조회", desc = "정산정보 갱신 폼을 위한 조회를 한다.")    
    public SettlementVo selectSettlement(SettlementVo settlementVo) throws Exception {
    	SettlementVo selectSettlementVo = settlementService.selectSettlement(settlementVo);    	    
		
        return selectSettlementVo;
    } 
 
    /**
     * 정산정보를 등록 처리 한다.
     *
     * @param  settlementVo 정산정보
     * @throws Exception
     */
    @ElService(key="SETTLEMENTIns")    
    @RequestMapping(value="SETTLEMENTIns")
    @ElDescription(sub="정산정보 등록처리",desc="정산정보를 등록 처리 한다.")
    public void insertSettlement(SettlementVo settlementVo) throws Exception {    	 
    	settlementService.insertSettlement(settlementVo);   
    }
       
    /**
     * 정산정보를 갱신 처리 한다.
     *
     * @param  settlementVo 정산정보
     * @throws Exception
     */
    @ElService(key="SETTLEMENTUpd")    
    @RequestMapping(value="SETTLEMENTUpd")    
    @ElValidator(errUrl="/settlement/settlementRegister", errContinue=true)
    @ElDescription(sub="정산정보 갱신처리",desc="정산정보를 갱신 처리 한다.")    
    public void updateSettlement(SettlementVo settlementVo) throws Exception {  
 
    	settlementService.updateSettlement(settlementVo);                                            
    }

    /**
     * 정산정보를 삭제 처리한다.
     *
     * @param  settlementVo 정산정보    
     * @throws Exception
     */
    @ElService(key = "SETTLEMENTDel")    
    @RequestMapping(value="SETTLEMENTDel")
    @ElDescription(sub = "정산정보 삭제처리", desc = "정산정보를 삭제 처리한다.")    
    public void deleteSettlement(SettlementVo settlementVo) throws Exception {
        settlementService.deleteSettlement(settlementVo);
    }
   
}

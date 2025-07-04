package com.demo.proworks.insurance.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.insurance.service.InsuranceService;
import com.demo.proworks.insurance.vo.InsuranceVo;
import com.demo.proworks.insurance.vo.SubInsuranceListVo;
import com.demo.proworks.insurance.vo.InsuranceListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 보험 관련 처리를 담당하는 컨트롤러
 * @description : 보험 관련 처리를 담당하는 컨트롤러
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
public class InsuranceController {
	
    /** InsuranceService */
    @Resource(name = "insuranceServiceImpl")
    private InsuranceService insuranceService;
	
    /**
     * 가입 보험 조회
     *
     */
    @ElService(key = "INSURANCESubList")
    @RequestMapping(value = "INSURANCESubList")    
    @ElDescription(sub = "가입 보험 목록을 조회", desc = "고객 가입 보험 목록 조회")               
    public List<SubInsuranceListVo> selectListSubInsurance(ClaimUserVo insuranceVo) throws Exception {    	   	
        List<SubInsuranceListVo> insuranceList = insuranceService.selectListSubInsurance(insuranceVo);                  
        System.out.println("hi ====================================================\n");
        return insuranceList;            
    }  
    
    /**
     * 보험 목록을 조회합니다.
     *
     * @param  insuranceVo 보험
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="INSURANCEList")
    @RequestMapping(value="INSURANCEList")    
    @ElDescription(sub="보험 목록조회",desc="페이징을 처리하여 보험 목록 조회를 한다.")               
    public InsuranceListVo selectListInsurance(InsuranceVo insuranceVo) throws Exception {    	   	

        List<InsuranceVo> insuranceList = insuranceService.selectListInsurance(insuranceVo);                  
        long totCnt = insuranceService.selectListCountInsurance(insuranceVo);
	
		InsuranceListVo retInsuranceList = new InsuranceListVo();
		retInsuranceList.setInsuranceVoList(insuranceList); 
		retInsuranceList.setTotalCount(totCnt);
		retInsuranceList.setPageSize(insuranceVo.getPageSize());
		retInsuranceList.setPageIndex(insuranceVo.getPageIndex());

        return retInsuranceList;            
    }  
        
    /**
     * 보험을 단건 조회 처리 한다.
     *
     * @param  insuranceVo 보험
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "INSURANCEUpdView")    
    @RequestMapping(value="INSURANCEUpdView") 
    @ElDescription(sub = "보험 갱신 폼을 위한 조회", desc = "보험 갱신 폼을 위한 조회를 한다.")    
    public InsuranceVo selectInsurance(InsuranceVo insuranceVo) throws Exception {
    	InsuranceVo selectInsuranceVo = insuranceService.selectInsurance(insuranceVo);    	    
		
        return selectInsuranceVo;
    } 
 
    /**
     * 보험를 등록 처리 한다.
     *
     * @param  insuranceVo 보험
     * @throws Exception
     */
    @ElService(key="INSURANCEIns")    
    @RequestMapping(value="INSURANCEIns")
    @ElDescription(sub="보험 등록처리",desc="보험를 등록 처리 한다.")
    public void insertInsurance(InsuranceVo insuranceVo) throws Exception {    	 
    	insuranceService.insertInsurance(insuranceVo);   
    }
       
    /**
     * 보험를 갱신 처리 한다.
     *
     * @param  insuranceVo 보험
     * @throws Exception
     */
    @ElService(key="INSURANCEUpd")    
    @RequestMapping(value="INSURANCEUpd")    
    @ElValidator(errUrl="/insurance/insuranceRegister", errContinue=true)
    @ElDescription(sub="보험 갱신처리",desc="보험를 갱신 처리 한다.")    
    public void updateInsurance(InsuranceVo insuranceVo) throws Exception {  
 
    	insuranceService.updateInsurance(insuranceVo);                                            
    }

    /**
     * 보험를 삭제 처리한다.
     *
     * @param  insuranceVo 보험    
     * @throws Exception
     */
    @ElService(key = "INSURANCEDel")    
    @RequestMapping(value="INSURANCEDel")
    @ElDescription(sub = "보험 삭제처리", desc = "보험를 삭제 처리한다.")    
    public void deleteInsurance(InsuranceVo insuranceVo) throws Exception {
        insuranceService.deleteInsurance(insuranceVo);
    }
   
}

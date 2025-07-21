package com.demo.proworks.subinsurance.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.subinsurance.service.SubInsuranceService;
import com.demo.proworks.subinsurance.vo.SubInsuranceVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceListVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceProductVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 가입보험정보 관련 처리를 담당하는 컨트롤러
 * @description : 가입보험정보 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class SubInsuranceController {
	
    /** SubInsuranceService */
    @Resource(name = "subInsuranceServiceImpl")
    private SubInsuranceService subInsuranceService;
	
    
    /**
     * 가입보험정보 목록을 조회합니다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEList")
    @RequestMapping(value="SUBINSURANCEList")    
    @ElDescription(sub="가입보험정보 목록조회",desc="페이징을 처리하여 가입보험정보 목록 조회를 한다.")               
    public SubInsuranceListVo selectListSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {    	   	

        List<SubInsuranceVo> subInsuranceList = subInsuranceService.selectListSubInsurance(subInsuranceVo);                  
        long totCnt = subInsuranceService.selectListCountSubInsurance(subInsuranceVo);
	
		SubInsuranceListVo retSubInsuranceList = new SubInsuranceListVo();
		retSubInsuranceList.setSubInsuranceVoList(subInsuranceList); 
		retSubInsuranceList.setTotalCount(totCnt);
		retSubInsuranceList.setPageSize(subInsuranceVo.getPageSize());
		retSubInsuranceList.setPageIndex(subInsuranceVo.getPageIndex());

        return retSubInsuranceList;            
    }  
        
    /**
     * 가입보험정보을 단건 조회 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "SUBINSURANCEUpdView")    
    @RequestMapping(value="SUBINSURANCEUpdView") 
    @ElDescription(sub = "가입보험정보 갱신 폼을 위한 조회", desc = "가입보험정보 갱신 폼을 위한 조회를 한다.")    
    public SubInsuranceVo selectSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
    	SubInsuranceVo selectSubInsuranceVo = subInsuranceService.selectSubInsurance(subInsuranceVo);    	    
		
        return selectSubInsuranceVo;
    } 
 
    /**
     * 가입보험정보를 등록 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEIns")    
    @RequestMapping(value="SUBINSURANCEIns")
    @ElDescription(sub="가입보험정보 등록처리",desc="가입보험정보를 등록 처리 한다.")
    public void insertSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {    	 
    	subInsuranceService.insertSubInsurance(subInsuranceVo);   
    }
       
    /**
     * 가입보험정보를 갱신 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEUpd")    
    @RequestMapping(value="SUBINSURANCEUpd")    
    @ElValidator(errUrl="/subInsurance/subInsuranceRegister", errContinue=true)
    @ElDescription(sub="가입보험정보 갱신처리",desc="가입보험정보를 갱신 처리 한다.")    
    public void updateSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {  
 
    	subInsuranceService.updateSubInsurance(subInsuranceVo);                                            
    }

    /**
     * 가입보험정보를 삭제 처리한다.
     *
     * @param  subInsuranceVo 가입보험정보    
     * @throws Exception
     */
    @ElService(key = "SUBINSURANCEDel")    
    @RequestMapping(value="SUBINSURANCEDel")
    @ElDescription(sub = "가입보험정보 삭제처리", desc = "가입보험정보를 삭제 처리한다.")    
    public void deleteSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
        subInsuranceService.deleteSubInsurance(subInsuranceVo);
    }

    /**
     * 사용자 ID로 보험상품 목록을 조회한다.
     *
     * @param  subInsuranceProductVo 보험상품조회정보
     * @return 보험상품 목록 조회 결과
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEProductsList")
    @RequestMapping(value="SUBINSURANCEProductsList")
    @ElDescription(sub="보험상품 목록조회",desc="사용자 ID로 보험상품 목록을 조회한다.")
    public List<SubInsuranceProductVo> selectInsuranceProductsByUserId(SubInsuranceProductVo subInsuranceProductVo) throws Exception {
        List<SubInsuranceProductVo> productList = subInsuranceService.selectInsuranceProductsByUserId(subInsuranceProductVo);
        return productList;
    }
   
}

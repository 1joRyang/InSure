package com.demo.proworks.treatment.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.treatment.service.TreatmentService;
import com.demo.proworks.treatment.vo.TreatmentVo;
import com.demo.proworks.treatment.vo.TreatmentListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 진료정보 관련 처리를 담당하는 컨트롤러
 * @description : 진료정보 관련 처리를 담당하는 컨트롤러
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
public class TreatmentController {
	
    /** TreatmentService */
    @Resource(name = "treatmentServiceImpl")
    private TreatmentService treatmentService;
	
    
    /**
     * 진료정보 목록을 조회합니다.
     *
     * @param  treatmentVo 진료정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="TREATMENTList")
    @RequestMapping(value="TREATMENTList")    
    @ElDescription(sub="진료정보 목록조회",desc="페이징을 처리하여 진료정보 목록 조회를 한다.")               
    public TreatmentListVo selectListTreatment(TreatmentVo treatmentVo) throws Exception {    	   	

        List<TreatmentVo> treatmentList = treatmentService.selectListTreatment(treatmentVo);                  
        long totCnt = treatmentService.selectListCountTreatment(treatmentVo);
	
		TreatmentListVo retTreatmentList = new TreatmentListVo();
		retTreatmentList.setTreatmentVoList(treatmentList); 
		retTreatmentList.setTotalCount(totCnt);
		retTreatmentList.setPageSize(treatmentVo.getPageSize());
		retTreatmentList.setPageIndex(treatmentVo.getPageIndex());

        return retTreatmentList;            
    }  
        
    /**
     * 진료정보을 단건 조회 처리 한다.
     *
     * @param  treatmentVo 진료정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "TREATMENTUpdView")    
    @RequestMapping(value="TREATMENTUpdView") 
    @ElDescription(sub = "진료정보 갱신 폼을 위한 조회", desc = "진료정보 갱신 폼을 위한 조회를 한다.")    
    public TreatmentVo selectTreatment(TreatmentVo treatmentVo) throws Exception {
    	TreatmentVo selectTreatmentVo = treatmentService.selectTreatment(treatmentVo);  
    	System.out.println("hi============" + selectTreatmentVo);  	    
		
        return selectTreatmentVo;
    } 
 
    /**
     * 진료정보를 등록 처리 한다.
     *
     * @param  treatmentVo 진료정보
     * @throws Exception
     */
    @ElService(key="TREATMENTIns")    
    @RequestMapping(value="TREATMENTIns")
    @ElDescription(sub="진료정보 등록처리",desc="진료정보를 등록 처리 한다.")
    public void insertTreatment(TreatmentVo treatmentVo) throws Exception {    	 
    	treatmentService.insertTreatment(treatmentVo);   
    }
       
    /**
     * 진료정보를 갱신 처리 한다.
     *
     * @param  treatmentVo 진료정보
     * @throws Exception
     */
    @ElService(key="TREATMENTUpd")    
    @RequestMapping(value="TREATMENTUpd")    
    @ElValidator(errUrl="/treatment/treatmentRegister", errContinue=true)
    @ElDescription(sub="진료정보 갱신처리",desc="진료정보를 갱신 처리 한다.")    
    public void updateTreatment(TreatmentVo treatmentVo) throws Exception {  
 
    	treatmentService.updateTreatment(treatmentVo);                                            
    }

    /**
     * 진료정보를 삭제 처리한다.
     *
     * @param  treatmentVo 진료정보    
     * @throws Exception
     */
    @ElService(key = "TREATMENTDel")    
    @RequestMapping(value="TREATMENTDel")
    @ElDescription(sub = "진료정보 삭제처리", desc = "진료정보를 삭제 처리한다.")    
    public void deleteTreatment(TreatmentVo treatmentVo) throws Exception {
        treatmentService.deleteTreatment(treatmentVo);
    }
   
}

package com.demo.proworks.paymentinfo.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.paymentinfo.service.PaymentInfoService;
import com.demo.proworks.paymentinfo.vo.PaymentInfoVo;
import com.demo.proworks.paymentinfo.vo.PaymentInfoListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 지불정보 관련 처리를 담당하는 컨트롤러
 * @description : 지불정보 관련 처리를 담당하는 컨트롤러
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
public class PaymentInfoController {
	
    /** PaymentInfoService */
    @Resource(name = "paymentInfoServiceImpl")
    private PaymentInfoService paymentInfoService;
	
    
    /**
     * 지불정보 목록을 조회합니다.
     *
     * @param  paymentInfoVo 지불정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="PAYMENTINFOList")
    @RequestMapping(value="PAYMENTINFOList")    
    @ElDescription(sub="지불정보 목록조회",desc="페이징을 처리하여 지불정보 목록 조회를 한다.")               
    public PaymentInfoListVo selectListPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {    	   	

        List<PaymentInfoVo> paymentInfoList = paymentInfoService.selectListPaymentInfo(paymentInfoVo);                  
        long totCnt = paymentInfoService.selectListCountPaymentInfo(paymentInfoVo);
	
		PaymentInfoListVo retPaymentInfoList = new PaymentInfoListVo();
		retPaymentInfoList.setPaymentInfoVoList(paymentInfoList); 
		retPaymentInfoList.setTotalCount(totCnt);
		retPaymentInfoList.setPageSize(paymentInfoVo.getPageSize());
		retPaymentInfoList.setPageIndex(paymentInfoVo.getPageIndex());

        return retPaymentInfoList;            
    }  
        
    /**
     * 지불정보을 단건 조회 처리 한다.
     *
     * @param  paymentInfoVo 지불정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "PAYMENTINFOUpdView")    
    @RequestMapping(value="PAYMENTINFOUpdView") 
    @ElDescription(sub = "지불정보 갱신 폼을 위한 조회", desc = "지불정보 갱신 폼을 위한 조회를 한다.")    
    public PaymentInfoVo selectPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
    	PaymentInfoVo selectPaymentInfoVo = paymentInfoService.selectPaymentInfo(paymentInfoVo);    	    
		
        return selectPaymentInfoVo;
    } 
 
    /**
     * 지불정보를 등록 처리 한다.
     *
     * @param  paymentInfoVo 지불정보
     * @throws Exception
     */
    @ElService(key="PAYMENTINFOIns")    
    @RequestMapping(value="PAYMENTINFOIns")
    @ElDescription(sub="지불정보 등록처리",desc="지불정보를 등록 처리 한다.")
    public void insertPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {    	 
    	paymentInfoService.insertPaymentInfo(paymentInfoVo);   
    }
       
    /**
     * 지불정보를 갱신 처리 한다.
     *
     * @param  paymentInfoVo 지불정보
     * @throws Exception
     */
    @ElService(key="PAYMENTINFOUpd")    
    @RequestMapping(value="PAYMENTINFOUpd")    
    @ElValidator(errUrl="/paymentInfo/paymentInfoRegister", errContinue=true)
    @ElDescription(sub="지불정보 갱신처리",desc="지불정보를 갱신 처리 한다.")    
    public void updatePaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {  
 
    	paymentInfoService.updatePaymentInfo(paymentInfoVo);                                            
    }

    /**
     * 지불정보를 삭제 처리한다.
     *
     * @param  paymentInfoVo 지불정보    
     * @throws Exception
     */
    @ElService(key = "PAYMENTINFODel")    
    @RequestMapping(value="PAYMENTINFODel")
    @ElDescription(sub = "지불정보 삭제처리", desc = "지불정보를 삭제 처리한다.")    
    public void deletePaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
        paymentInfoService.deletePaymentInfo(paymentInfoVo);
    }
   
}

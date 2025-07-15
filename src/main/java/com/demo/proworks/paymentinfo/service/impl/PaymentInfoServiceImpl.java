package com.demo.proworks.paymentinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.paymentinfo.service.PaymentInfoService;
import com.demo.proworks.paymentinfo.vo.PaymentInfoVo;
import com.demo.proworks.paymentinfo.dao.PaymentInfoDAO;

/**  
 * @subject     : 지불정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 지불정보 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Service("paymentInfoServiceImpl")
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Resource(name="paymentInfoDAO")
    private PaymentInfoDAO paymentInfoDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 지불정보 목록을 조회합니다.
     *
     * @process
     * 1. 지불정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<PaymentInfoVo>을(를) 리턴한다.
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 지불정보 목록 List<PaymentInfoVo>
     * @throws Exception
     */
	public List<PaymentInfoVo> selectListPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
		List<PaymentInfoVo> list = paymentInfoDAO.selectListPaymentInfo(paymentInfoVo);	
	
		return list;
	}

    /**
     * 조회한 지불정보 전체 카운트
     *
     * @process
     * 1. 지불정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 지불정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
		return paymentInfoDAO.selectListCountPaymentInfo(paymentInfoVo);
	}

    /**
     * 지불정보를 상세 조회한다.
     *
     * @process
     * 1. 지불정보를 상세 조회한다.
     * 2. 결과 PaymentInfoVo을(를) 리턴한다.
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public PaymentInfoVo selectPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
		PaymentInfoVo resultVO = paymentInfoDAO.selectPaymentInfo(paymentInfoVo);			
        
        return resultVO;
	}

    /**
     * 지불정보를 등록 처리 한다.
     *
     * @process
     * 1. 지불정보를 등록 처리 한다.
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 번호
     * @throws Exception
     */
	public int insertPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
		return paymentInfoDAO.insertPaymentInfo(paymentInfoVo);	
	}
	
    /**
     * 지불정보를 갱신 처리 한다.
     *
     * @process
     * 1. 지불정보를 갱신 처리 한다.
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 번호
     * @throws Exception
     */
	public int updatePaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {				
		return paymentInfoDAO.updatePaymentInfo(paymentInfoVo);	   		
	}

    /**
     * 지불정보를 삭제 처리 한다.
     *
     * @process
     * 1. 지불정보를 삭제 처리 한다.
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 번호
     * @throws Exception
     */
	public int deletePaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception {
		return paymentInfoDAO.deletePaymentInfo(paymentInfoVo);
	}
	
}

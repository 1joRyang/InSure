package com.demo.proworks.paymentinfo.service;

import java.util.List;

import com.demo.proworks.paymentinfo.vo.PaymentInfoVo;

/**  
 * @subject     : 지불정보 관련 처리를 담당하는 인터페이스
 * @description : 지불정보 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
public interface PaymentInfoService {
	
    /**
     * 지불정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 지불정보 목록 List<PaymentInfoVo>
     * @throws Exception
     */
	public List<PaymentInfoVo> selectListPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception;
	
    /**
     * 조회한 지불정보 전체 카운트
     * 
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 지불정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception;
	
    /**
     * 지불정보를 상세 조회한다.
     *
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public PaymentInfoVo selectPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception;
		
    /**
     * 지불정보를 등록 처리 한다.
     *
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 번호
     * @throws Exception
     */
	public int insertPaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception;
	
    /**
     * 지불정보를 갱신 처리 한다.
     *
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 번호
     * @throws Exception
     */
	public int updatePaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception;
	
    /**
     * 지불정보를 삭제 처리 한다.
     *
     * @param  paymentInfoVo 지불정보 PaymentInfoVo
     * @return 번호
     * @throws Exception
     */
	public int deletePaymentInfo(PaymentInfoVo paymentInfoVo) throws Exception;
	
}

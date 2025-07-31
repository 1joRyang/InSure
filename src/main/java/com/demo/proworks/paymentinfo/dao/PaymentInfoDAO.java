package com.demo.proworks.paymentinfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.paymentinfo.vo.PaymentInfoVo;
import com.demo.proworks.paymentinfo.dao.PaymentInfoDAO;

/**  
 * @subject     : 지불정보 관련 처리를 담당하는 DAO
 * @description : 지불정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Repository("paymentInfoDAO")
public class PaymentInfoDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 지불정보 상세 조회한다.
     *  
     * @param  PaymentInfoVo 지불정보
     * @return PaymentInfoVo 지불정보
     * @throws ElException
     */
    public PaymentInfoVo selectPaymentInfo(PaymentInfoVo vo) throws ElException {
        return (PaymentInfoVo) selectByPk("com.demo.proworks.paymentinfo.selectPaymentInfo", vo);
    }

    /**
     * 페이징을 처리하여 지불정보 목록조회를 한다.
     *  
     * @param  PaymentInfoVo 지불정보
     * @return List<PaymentInfoVo> 지불정보
     * @throws ElException
     */
    public List<PaymentInfoVo> selectListPaymentInfo(PaymentInfoVo vo) throws ElException {      	
        return (List<PaymentInfoVo>)list("com.demo.proworks.paymentinfo.selectListPaymentInfo", vo);
    }

    /**
     * 지불정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  PaymentInfoVo 지불정보
     * @return 지불정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountPaymentInfo(PaymentInfoVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.paymentinfo.selectListCountPaymentInfo", vo);
    }
        
    /**
     * 지불정보를 등록한다.
     *  
     * @param  PaymentInfoVo 지불정보
     * @return 번호
     * @throws ElException
     */
    public int insertPaymentInfo(PaymentInfoVo vo) throws ElException {    	
        return insert("com.demo.proworks.paymentinfo.insertPaymentInfo", vo);
    }

    /**
     * 지불정보를 갱신한다.
     *  
     * @param  PaymentInfoVo 지불정보
     * @return 번호
     * @throws ElException
     */
    public int updatePaymentInfo(PaymentInfoVo vo) throws ElException {
        return update("com.demo.proworks.paymentinfo.updatePaymentInfo", vo);
    }

    /**
     * 지불정보를 삭제한다.
     *  
     * @param  PaymentInfoVo 지불정보
     * @return 번호
     * @throws ElException
     */
    public int deletePaymentInfo(PaymentInfoVo vo) throws ElException {
        return delete("com.demo.proworks.paymentinfo.deletePaymentInfo", vo);
    }

}

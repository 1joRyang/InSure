package com.demo.proworks.insurance.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.insurance.vo.InsuranceVo;
import com.demo.proworks.insurance.vo.SubInsuranceListVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.insurance.dao.InsuranceDAO;

/**  
 * @subject     : 보험 관련 처리를 담당하는 DAO
 * @description : 보험 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
@Repository("insuranceDAO")
public class InsuranceDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 고객 가입 보험 조회
	 */
	 public List<SubInsuranceListVo> selectListSubInsurance(ClaimUserVo vo) throws ElException {
		 return (List<SubInsuranceListVo>) list("com.demo.proworks.insurance.selectListSubInsurance", vo);
	 }

    /**
     * 보험 상세 조회한다.
     *  
     * @param  InsuranceVo 보험
     * @return InsuranceVo 보험
     * @throws ElException
     */
    public InsuranceVo selectInsurance(InsuranceVo vo) throws ElException {
        return (InsuranceVo) selectByPk("com.demo.proworks.insurance.selectInsurance", vo);
    }

    /**
     * 페이징을 처리하여 보험 목록조회를 한다.
     *  
     * @param  InsuranceVo 보험
     * @return List<InsuranceVo> 보험
     * @throws ElException
     */
    public List<InsuranceVo> selectListInsurance(InsuranceVo vo) throws ElException {      	
        return (List<InsuranceVo>)list("com.demo.proworks.insurance.selectListInsurance", vo);
    }

    /**
     * 보험 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  InsuranceVo 보험
     * @return 보험 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountInsurance(InsuranceVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.insurance.selectListCountInsurance", vo);
    }
        
    /**
     * 보험를 등록한다.
     *  
     * @param  InsuranceVo 보험
     * @return 번호
     * @throws ElException
     */
    public int insertInsurance(InsuranceVo vo) throws ElException {    	
        return insert("com.demo.proworks.insurance.insertInsurance", vo);
    }

    /**
     * 보험를 갱신한다.
     *  
     * @param  InsuranceVo 보험
     * @return 번호
     * @throws ElException
     */
    public int updateInsurance(InsuranceVo vo) throws ElException {
        return update("com.demo.proworks.insurance.updateInsurance", vo);
    }

    /**
     * 보험를 삭제한다.
     *  
     * @param  InsuranceVo 보험
     * @return 번호
     * @throws ElException
     */
    public int deleteInsurance(InsuranceVo vo) throws ElException {
        return delete("com.demo.proworks.insurance.deleteInsurance", vo);
    }

}

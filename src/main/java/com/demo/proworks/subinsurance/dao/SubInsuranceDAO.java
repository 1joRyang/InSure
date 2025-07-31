package com.demo.proworks.subinsurance.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.subinsurance.vo.SubInsuranceVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceProductVo;
import com.demo.proworks.subinsurance.vo.UserInsuranceVo;
import com.demo.proworks.subinsurance.dao.SubInsuranceDAO;

/**  
 * @subject     : 가입보험정보 관련 처리를 담당하는 DAO
 * @description : 가입보험정보 관련 처리를 담당하는 DAO
 * @author      : hyunwoo
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 hyunwoo	 		최초 생성
 * 
 */
@Repository("subInsuranceDAO")
public class SubInsuranceDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 가입보험정보 상세 조회한다.
     *  
     * @param  SubInsuranceVo 가입보험정보
     * @return SubInsuranceVo 가입보험정보
     * @throws ElException
     */
    public SubInsuranceVo selectSubInsurance(SubInsuranceVo vo) throws ElException {
        return (SubInsuranceVo) selectByPk("com.demo.proworks.subinsurance.selectSubInsurance", vo);
    }

    /**
     * 페이징을 처리하여 가입보험정보 목록조회를 한다.
     *  
     * @param  SubInsuranceVo 가입보험정보
     * @return List<SubInsuranceVo> 가입보험정보
     * @throws ElException
     */
    public List<SubInsuranceVo> selectListSubInsurance(SubInsuranceVo vo) throws ElException {      	
        return (List<SubInsuranceVo>)list("com.demo.proworks.subinsurance.selectListSubInsurance", vo);
    }

    /**
     * 가입보험정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  SubInsuranceVo 가입보험정보
     * @return 가입보험정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountSubInsurance(SubInsuranceVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.subinsurance.selectListCountSubInsurance", vo);
    }
        
    /**
     * 가입보험정보를 등록한다.
     *  
     * @param  SubInsuranceVo 가입보험정보
     * @return 번호
     * @throws ElException
     */
    public int insertSubInsurance(SubInsuranceVo vo) throws ElException {    	
        return insert("com.demo.proworks.subinsurance.insertSubInsurance", vo);
    }

    /**
     * 가입보험정보를 갱신한다.
     *  
     * @param  SubInsuranceVo 가입보험정보
     * @return 번호
     * @throws ElException
     */
    public int updateSubInsurance(SubInsuranceVo vo) throws ElException {
        return update("com.demo.proworks.subinsurance.updateSubInsurance", vo);
    }

    /**
     * 가입보험정보를 삭제한다.
     *  
     * @param  SubInsuranceVo 가입보험정보
     * @return 번호
     * @throws ElException
     */
    public int deleteSubInsurance(SubInsuranceVo vo) throws ElException {
        return delete("com.demo.proworks.subinsurance.deleteSubInsurance", vo);
    }

    /**
     * 사용자 ID로 보험상품 목록을 조회한다.
     *  
     * @param  SubInsuranceProductVo 보험상품조회정보
     * @return List<SubInsuranceProductVo> 보험상품목록
     * @throws ElException
     */
    public List<SubInsuranceProductVo> selectInsuranceProductsByUserId(SubInsuranceProductVo vo) throws ElException {
        return (List<SubInsuranceProductVo>)list("com.demo.proworks.subinsurance.selectInsuranceProductsByUserId", vo);
    }

    /**
     * 사용자명으로 보험 목록을 조회한다 (JOIN 쿼리 사용).
     *  
     * @param  UserInsuranceVo 사용자보험정보
     * @return List<UserInsuranceVo> 사용자보험목록
     * @throws ElException
     */
    public List<UserInsuranceVo> selectUserInsuranceListByUserName(UserInsuranceVo vo) throws ElException {
        return (List<UserInsuranceVo>)list("com.demo.proworks.subinsurance.selectUserInsuranceListByUserName", vo);
    }

}

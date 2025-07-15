package com.demo.proworks.treatment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.treatment.vo.TreatmentVo;
import com.demo.proworks.treatment.dao.TreatmentDAO;

/**  
 * @subject     : 진료정보 관련 처리를 담당하는 DAO
 * @description : 진료정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Repository("treatmentDAO")
public class TreatmentDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 진료정보 상세 조회한다.
     *  
     * @param  TreatmentVo 진료정보
     * @return TreatmentVo 진료정보
     * @throws ElException
     */
    public TreatmentVo selectTreatment(TreatmentVo vo) throws ElException {
        return (TreatmentVo) selectByPk("com.demo.proworks.treatment.selectTreatment", vo);
    }

    /**
     * 페이징을 처리하여 진료정보 목록조회를 한다.
     *  
     * @param  TreatmentVo 진료정보
     * @return List<TreatmentVo> 진료정보
     * @throws ElException
     */
    public List<TreatmentVo> selectListTreatment(TreatmentVo vo) throws ElException {      	
        return (List<TreatmentVo>)list("com.demo.proworks.treatment.selectListTreatment", vo);
    }

    /**
     * 진료정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  TreatmentVo 진료정보
     * @return 진료정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountTreatment(TreatmentVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.treatment.selectListCountTreatment", vo);
    }
        
    /**
     * 진료정보를 등록한다.
     *  
     * @param  TreatmentVo 진료정보
     * @return 번호
     * @throws ElException
     */
    public int insertTreatment(TreatmentVo vo) throws ElException {    	
        return insert("com.demo.proworks.treatment.insertTreatment", vo);
    }

    /**
     * 진료정보를 갱신한다.
     *  
     * @param  TreatmentVo 진료정보
     * @return 번호
     * @throws ElException
     */
    public int updateTreatment(TreatmentVo vo) throws ElException {
        return update("com.demo.proworks.treatment.updateTreatment", vo);
    }

    /**
     * 진료정보를 삭제한다.
     *  
     * @param  TreatmentVo 진료정보
     * @return 번호
     * @throws ElException
     */
    public int deleteTreatment(TreatmentVo vo) throws ElException {
        return delete("com.demo.proworks.treatment.deleteTreatment", vo);
    }

}

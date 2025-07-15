package com.demo.proworks.settlement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.settlement.vo.SettlementVo;
import com.demo.proworks.settlement.vo.SettlementTreatmentVo;
import com.demo.proworks.settlement.dao.SettlementDAO;

/**  
 * @subject     : 정산정보 관련 처리를 담당하는 DAO
 * @description : 정산정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Repository("settlementDAO")
public class SettlementDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 정산정보 상세 조회한다.
     *  
     * @param  SettlementVo 정산정보
     * @return SettlementVo 정산정보
     * @throws ElException
     */
    public SettlementVo selectSettlement(SettlementVo vo) throws ElException {
        return (SettlementVo) selectByPk("com.demo.proworks.settlement.selectSettlement", vo);
    }

    /**
     * 페이징을 처리하여 정산정보 목록조회를 한다.
     *  
     * @param  SettlementVo 정산정보
     * @return List<SettlementVo> 정산정보
     * @throws ElException
     */
    public List<SettlementVo> selectListSettlement(SettlementVo vo) throws ElException {      	
        return (List<SettlementVo>)list("com.demo.proworks.settlement.selectListSettlement", vo);
    }

    /**
     * 정산정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  SettlementVo 정산정보
     * @return 정산정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountSettlement(SettlementVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.settlement.selectListCountSettlement", vo);
    }
        
    /**
     * 정산정보를 등록한다.
     *  
     * @param  SettlementVo 정산정보
     * @return 번호
     * @throws ElException
     */
    public int insertSettlement(SettlementVo vo) throws ElException {    	
        return insert("com.demo.proworks.settlement.insertSettlement", vo);
    }

    /**
     * 정산정보를 갱신한다.
     *  
     * @param  SettlementVo 정산정보
     * @return 번호
     * @throws ElException
     */
    public int updateSettlement(SettlementVo vo) throws ElException {
        return update("com.demo.proworks.settlement.updateSettlement", vo);
    }

    /**
     * 정산정보를 삭제한다.
     *  
     * @param  SettlementVo 정산정보
     * @return 번호
     * @throws ElException
     */
    public int deleteSettlement(SettlementVo vo) throws ElException {
        return delete("com.demo.proworks.settlement.deleteSettlement", vo);
    }

    /**
     * 정산정보와 치료정보를 조인하여 조회한다.
     *  
     * @param  SettlementTreatmentVo 정산치료정보
     * @return List<SettlementTreatmentVo> 정산치료정보
     * @throws ElException
     */
    public List<SettlementTreatmentVo> selectSettlementTreatment(SettlementTreatmentVo vo) throws ElException {
        return (List<SettlementTreatmentVo>) list("com.demo.proworks.settlement.selectSettlementTreatment", vo);
    }

}

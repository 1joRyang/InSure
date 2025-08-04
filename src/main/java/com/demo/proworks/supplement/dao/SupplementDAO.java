package com.demo.proworks.supplement.dao;

import org.springframework.stereotype.Repository;

import com.demo.proworks.supplement.vo.SuppVo;
import com.inswave.elfw.exception.ElException;

@Repository("supplementDAO")
public class SupplementDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
	
	/**
	 * 보완 요청 상세 정보 조회 로직
	 */
	public SuppVo selectSupplementInfo(SuppVo suppVo) throws Exception {
		return (SuppVo) selectByPk("com.demo.proworks.supplement.selectSupplementInfo", suppVo);
	}
	
	/**
	 * 보완 서류 추가
	 */
	public int updateSupplementCompleted(SuppVo vo) throws ElException {
    return update("com.demo.proworks.supplement.updateSupplementCompleted", vo);
}
	
	/**
	 * 보완 완료 후 상태 변경: 보완완료
	 */
	public int updateClaimStatus(SuppVo vo) throws ElException {
        return update("com.demo.proworks.supplement.updateClaimStatus", vo);
    }
}

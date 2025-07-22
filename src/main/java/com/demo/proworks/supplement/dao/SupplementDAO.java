package com.demo.proworks.supplement.dao;

import org.springframework.stereotype.Repository;

import com.demo.proworks.supplement.vo.SuppVo;
import com.inswave.elfw.exception.ElException;

@Repository("supplementDAO")
public class SupplementDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
	
	public SuppVo selectSupplementInfo(SuppVo suppVo) throws Exception {
		return (SuppVo) selectByPk("com.demo.proworks.supplement.selectSupplementInfo", suppVo);
	}
	
	public int updateSupplementCompleted(SuppVo vo) throws ElException {
    return update("com.demo.proworks.supplement.updateSupplementCompleted", vo);
}
}

package com.demo.proworks.batch.dao;


import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;



/**
 * @subject : 배치 처리를 담당하는 DAO
 * @description : 배치 처리를 담당하는 DAO
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Repository("batchDAO")
public class BatchDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
	
	public int updateMonthlyApprovalRate() throws ElException {
        return update("com.demo.proworks.batch.updateMonthlyApprovalRate", null);
    }
	

}

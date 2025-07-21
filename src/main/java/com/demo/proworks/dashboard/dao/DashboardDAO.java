package com.demo.proworks.dashboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claim.vo.ClaimFullJoinVo;
import com.demo.proworks.dashboard.vo.ClaimMonitorVo;
import com.demo.proworks.dashboard.vo.MonthlyPerfVo;
import com.demo.proworks.dashboard.vo.TodayStatusVo;

/**
 * @subject : 대시보드 처리를 담당하는 DAO
 * @description : 대시보드 처리를 담당하는 DAO
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Repository("dashboardDAO")
public class DashboardDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
	
	/**
	 * 오늘의 처리 현황 (상태별 건수)을 조회한다.
	 * @return List<TodayStatusVo> 오늘의 처리 현황 결과
	 * @throws ElException
	 */
	 @SuppressWarnings("unchecked")
	 public List<TodayStatusVo> selectTodayStatusCounts() throws ElException {
		 return (List<TodayStatusVo>) list("com.demo.proworks.dashboard.selectTodayStatusCounts");
	 }
	
	/**
	 * 고객 청구 모니터링을 조회한다.
	 */
	public ClaimMonitorVo selectClaimMonitorCounts() throws ElException {
		return (ClaimMonitorVo) selectByPk("com.demo.proworks.dashboard.selectClaimMonitorCounts");
	}

	/**
	 * 이번달 성과를 조회한다.
	 */
	public MonthlyPerfVo selectMonthlyPerformance() throws ElException {
	    return (MonthlyPerfVo) selectByPk("com.demo.proworks.dashboard.selectMonthlyPerformance");
	}
	

}

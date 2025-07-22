package com.demo.proworks.dashboard.service;

import java.util.List;
import java.util.Map;

import com.demo.proworks.dashboard.vo.ApprovalRateVo;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.ClaimMonitorVo;
import com.demo.proworks.dashboard.vo.DailyCountVo;
import com.demo.proworks.dashboard.vo.MonthlyPerfVo;
import com.demo.proworks.dashboard.vo.SupplementStatusVo;
import com.demo.proworks.dashboard.vo.TodayStatusVo;
import com.demo.proworks.dashboard.vo.UrgentClaimVo;
import com.demo.proworks.dashboard.vo.WeeklyTrendVo;
import com.inswave.elfw.exception.ElException;

/**
 * @subject : 대시보드 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 * @author  : Inswave
 * @since   : 2025/07/21
 */
public interface DashboardService {

	/**
	 * 오늘의 처리 현황을 조회한다.
	 * @return TodayStatusVo 오늘의 처리 현황 결과 VO
	 * @throws ElException
	 */
	 public TodayStatusVo selectTodayStatusCounts() throws ElException;
	 
	 public ClaimMonitorVo selectClaimMonitorCounts() throws ElException;
	 
	 public MonthlyPerfVo selectMonthlyPerformance() throws ElException;
	 
	 public SupplementStatusVo selectSupplementStatus() throws ElException;
	 
	 public DailyCountVo selectYesterdayProcessedCount() throws ElException;
	 
	 public ApprovalRateVo selectMonthlyApprovalRate() throws ElException;
	 
	 /**
     * 우선 처리 업무 목록을 조회한다.
     * @return List<UrgentClaimVo>
     * @throws ElException
     */
    public List<UrgentClaimVo> selectUrgentClaims() throws ElException;
    
    public List<WeeklyTrendVo> selectWeeklyTrend() throws ElException;
    
    public List<ChartVo> selectClaimTypeDistribution() throws ElException;

}

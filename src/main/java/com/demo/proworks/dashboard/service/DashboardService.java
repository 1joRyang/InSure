package com.demo.proworks.dashboard.service;

import java.util.List;
import java.util.Map;

import com.demo.proworks.dashboard.vo.PaymentVo;
import com.demo.proworks.dashboard.vo.ProcessingTimeVo;
import com.demo.proworks.dashboard.vo.ChartVo;
import com.demo.proworks.dashboard.vo.ClaimMonitorVo;
import com.demo.proworks.dashboard.vo.DailyCountVo;
import com.demo.proworks.dashboard.vo.MonthlyApprovalRateVo;
import com.demo.proworks.dashboard.vo.MonthlyPerfVo;
import com.demo.proworks.dashboard.vo.OutlierCountVo;
import com.demo.proworks.dashboard.vo.SupplementStatusVo;
import com.demo.proworks.dashboard.vo.TodayStatusVo;
import com.demo.proworks.dashboard.vo.UrgentClaimVo;
import com.demo.proworks.dashboard.vo.UrgentCountVo;
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
	 
	 /*고객 청구 모니터링*/
	 public ClaimMonitorVo selectClaimMonitorCounts() throws ElException;
	 
	 /*이번 달 성과 데이터*/
	 public MonthlyPerfVo selectMonthlyPerformance() throws ElException;
	 
	 /*보완 요청 현황 조회*/
	 public SupplementStatusVo selectSupplementStatus() throws ElException;
	 
	 /*전일 처리 건수 조회*/
	 public DailyCountVo selectYesterdayProcessedCount() throws ElException;
	 
	 /*이번 달 지급률 조회*/
	 public PaymentVo selectPaymentRate() throws ElException;
	 
	 /**
     * 우선 처리 업무 목록을 조회한다.
     * @return List<UrgentClaimVo>
     * @throws ElException
     */
    public List<UrgentClaimVo> selectUrgentClaims() throws ElException;
    
    /*주간 처리 현황*/
    public List<WeeklyTrendVo> selectWeeklyTrend() throws ElException;
    
    /*청구 유형 분포*/
    public List<ChartVo> selectClaimTypeDistribution() throws ElException;
    
    /*월별 승인률 차트*/
    public List<MonthlyApprovalRateVo> selectMonthlyApprovalRate() throws ElException;
    
    /*처리 시간 분포 차트*/
    public List<ProcessingTimeVo> selectProcessingTimeTrend() throws ElException;
    
    /*처리 시간 분포에서 제외하는 장기처리건을 조회*/
    public OutlierCountVo selectProcessingTimeOutlierCount() throws ElException;
    
    /*로그인한 사용자에게 할당된 긴급처리업무*/
    public int selectMyUrgentClaimCount(UrgentCountVo vo) throws ElException;
    

}

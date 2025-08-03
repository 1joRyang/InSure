package com.demo.proworks.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.proworks.dashboard.dao.DashboardDAO;
import com.demo.proworks.dashboard.service.DashboardService;
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
import com.inswave.elfw.log.AppLog;

/**
 * @subject : 대시보드 관련 비즈니스 로직을 처리하는 서비스 구현체
 * @author  : Inswave
 * @since   : 2025/07/21
 */
@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService{
	
	@Resource(name = "dashboardDAO")
    private DashboardDAO dashboardDAO;
    
    // 오늘의 처리 현황
    @Override
    public TodayStatusVo selectTodayStatusCounts() throws ElException {
        TodayStatusVo resultVo = null;

        List<TodayStatusVo> resultList = dashboardDAO.selectTodayStatusCounts();

        if (resultList != null && resultList.size() > 0) {
            resultVo = resultList.get(0);
        } else {
            resultVo = new TodayStatusVo(); 
            AppLog.warn("오늘의 처리 현황 데이터가 없습니다.");
        }

        return resultVo;
    }
    
    /*고객 청구 모니터링*/
    @Override
    public ClaimMonitorVo selectClaimMonitorCounts() throws ElException {
    	return dashboardDAO.selectClaimMonitorCounts();
    }
    
    /*이번 달 성과 데이터*/
    @Override
	public MonthlyPerfVo selectMonthlyPerformance() throws ElException {
	    return dashboardDAO.selectMonthlyPerformance();
	}
	
	/*보완 요청 현황 조회*/
	@Override
	public SupplementStatusVo selectSupplementStatus() throws ElException {
	    return dashboardDAO.selectSupplementStatus();
	}
	
	/*전일 처리 건수 조회*/
	@Override
	public DailyCountVo selectYesterdayProcessedCount() throws ElException {
	    return dashboardDAO.selectYesterdayProcessedCount();
	}
	
	/*이번 달 지급률 조회*/
	@Override
	public PaymentVo selectPaymentRate() throws ElException {
	    return dashboardDAO.selectPaymentRate();
	}
	
	/**
     * 우선 처리 업무 목록을 조회한다.
     * @return List<UrgentClaimVo>
     * @throws ElException
     */
    @Override
    public List<UrgentClaimVo> selectUrgentClaims() throws ElException {
        return dashboardDAO.selectUrgentClaims();
    }
    
    /*주간 처리 현황*/
    @Override
	public List<WeeklyTrendVo> selectWeeklyTrend() throws ElException {
	    return dashboardDAO.selectWeeklyTrend();
	}
	
	/*청구 유형 분포*/
	@Override
	public List<ChartVo> selectClaimTypeDistribution() throws ElException {
	    return dashboardDAO.selectClaimTypeDistribution();
	}
	
	/*월별 승인률 차트*/
	@Override
	public List<MonthlyApprovalRateVo> selectMonthlyApprovalRate() throws ElException {
	    return dashboardDAO.selectMonthlyApprovalRate();
	}
	
	/*처리 시간 분포 차트*/
	@Override
    public List<ProcessingTimeVo> selectProcessingTimeTrend() throws ElException {
        return dashboardDAO.selectProcessingTimeTrend();
    }
    
    /*처리 시간 분포에서 제외하는 장기처리건을 조회*/
    @Override
    public OutlierCountVo selectProcessingTimeOutlierCount() throws ElException {
        int count = dashboardDAO.selectProcessingTimeOutlierCount();
        OutlierCountVo vo = new OutlierCountVo();
        vo.setOutlierCount(count);
        return vo;
    }
    
    /*로그인한 사용자에게 할당된 긴급처리업무*/
    @Override
	public int selectMyUrgentClaimCount(UrgentCountVo vo) throws ElException {
	    return dashboardDAO.selectMyUrgentClaimCount(vo);
	}
}

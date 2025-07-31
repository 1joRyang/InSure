package com.demo.proworks.dashboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Override // 인터페이스의 메서드를 구현한다는 의미
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
    
    @Override
    public ClaimMonitorVo selectClaimMonitorCounts() throws ElException {
    	return dashboardDAO.selectClaimMonitorCounts();
    }
    
    @Override
	public MonthlyPerfVo selectMonthlyPerformance() throws ElException {
	    return dashboardDAO.selectMonthlyPerformance();
	}
	
	@Override
	public SupplementStatusVo selectSupplementStatus() throws ElException {
	    return dashboardDAO.selectSupplementStatus();
	}
	
	@Override
	public DailyCountVo selectYesterdayProcessedCount() throws ElException {
	    return dashboardDAO.selectYesterdayProcessedCount();
	}
	
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
    
    @Override
	public List<WeeklyTrendVo> selectWeeklyTrend() throws ElException {
	    // DAO를 통해 조회한 VO 리스트를 그대로 반환합니다.
	    // 불필요한 데이터 가공 로직을 제거합니다.
	    return dashboardDAO.selectWeeklyTrend(); // ✅ 이 한 줄이면 충분합니다.
	}
	
	@Override
	public List<ChartVo> selectClaimTypeDistribution() throws ElException {
	    return dashboardDAO.selectClaimTypeDistribution();
	}
	
	@Override
	public List<MonthlyApprovalRateVo> selectMonthlyApprovalRate() throws ElException {
	    return dashboardDAO.selectMonthlyApprovalRate();
	}
	
	@Override
    public List<ProcessingTimeVo> selectProcessingTimeTrend() throws ElException {
        return dashboardDAO.selectProcessingTimeTrend();
    }
    
    @Override
    public OutlierCountVo selectProcessingTimeOutlierCount() throws ElException {
        int count = dashboardDAO.selectProcessingTimeOutlierCount();
        OutlierCountVo vo = new OutlierCountVo();
        vo.setOutlierCount(count);
        return vo;
    }
    
    @Override
	public int selectMyUrgentClaimCount(UrgentCountVo vo) throws ElException {
	    return dashboardDAO.selectMyUrgentClaimCount(vo);
	}
}

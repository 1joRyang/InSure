package com.demo.proworks.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.proworks.dashboard.dao.DashboardDAO;
import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.ClaimMonitorVo;
import com.demo.proworks.dashboard.vo.MonthlyPerfVo;
import com.demo.proworks.dashboard.vo.TodayStatusVo;
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

}

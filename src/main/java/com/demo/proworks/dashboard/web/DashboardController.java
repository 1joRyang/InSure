package com.demo.proworks.dashboard.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.TodayStatusVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;

/**
 * @subject : 대시보드 관련 요청을 처리하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/21
 */
@Controller
public class DashboardController {
	
	@Resource(name = "dashboardService")
    private DashboardService dashboardService;
    
    /**
     * 오늘의 처리 현황 데이터를 조회한다.
     * @param todayStatusVo 조회 결과를 담을 VO
     * @throws Exception
     */
	@ElService(key = "selectTodayStatusCounts")
	@RequestMapping(value = "selectTodayStatusCounts")
	@ElDescription(sub = "오늘의 처리 현황 조회", desc = "대시보드 상단의 오늘의 처리 현황 데이터를 조회한다.")
	public void selectTodayStatusCounts(TodayStatusVo todayStatusVo) throws Exception {
		TodayStatusVo resultVo = dashboardService.selectTodayStatusCounts();
		
		if (resultVo != null) {
            todayStatusVo.setWaitingCount(resultVo.getWaitingCount());
            todayStatusVo.setProcessingCount(resultVo.getProcessingCount());
            todayStatusVo.setCompletedCount(resultVo.getCompletedCount());
        }

	}

}

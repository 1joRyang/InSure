package com.demo.proworks.dashboard.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.dashboard.service.DashboardService;
import com.demo.proworks.dashboard.vo.ClaimMonitorVo;
import com.demo.proworks.dashboard.vo.MonthlyPerfVo;
import com.demo.proworks.dashboard.vo.TodayStatusVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import org.springframework.web.bind.annotation.RequestMethod;
import com.inswave.elfw.annotation.ElValidator;

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
	public Map<String, Object> selectTodayStatusCounts(TodayStatusVo todayStatusVo) throws Exception {
		System.out.println("====== 1. DashboardController 진입! ======");
		// 1. Service를 호출하여 조회 결과를 가져옵니다.
        TodayStatusVo resultVo = dashboardService.selectTodayStatusCounts();
        System.out.println("====== 2. Service로부터 받은 데이터 ======");
        System.out.println(resultVo); // resultVo 객체의 내용을 그대로 출력
        // 2. 최종 응답을 담을 새로운 Map을 생성합니다.
        Map<String, Object> responseWrapper = new HashMap<>();

        // 3. "elData" 라는 키(Key)로 조회한 결과(resultVo)를 Map에 담습니다.
        responseWrapper.put("todayStatusVo", resultVo);

        // 4. 이 Map 객체를 직접 return 합니다.
        System.out.println("====== 3. 클라이언트로 보낼 데이터 ======");
        System.out.println(responseWrapper);
        return responseWrapper;

	}
	
	/**
     * 고객 청구 모니터링을 조회한다.
     */
	@ElService(key = "selectClaimMonitorCounts")
	@RequestMapping(value = "selectClaimMonitorCounts")
	@ElDescription(sub = "고객 청구 모니터링 조회", desc = "대시보드의 고객 청구 모니터링 데이터를 조회한다.")
	public Map<String, Object> selectClaimMonitorCounts() throws Exception {
		ClaimMonitorVo resultVo = dashboardService.selectClaimMonitorCounts();
	    Map<String, Object> responseWrapper = new HashMap<>();
	    responseWrapper.put("claimMonitorVo", resultVo);
	    return responseWrapper;

	}
	
	/**
	 * 이번 달 성과 데이터를 조회한다.
	 * @return MonthlyPerfVo
	 * @throws Exception
     */
	@ElService(key = "selectMonthlyPerformance")
	@RequestMapping(value = "selectMonthlyPerformance")
	@ElDescription(sub = "이번 달 성과 조회", desc = "대시보드의 이번 달 성과 데이터를 조회한다.")
	public Map<String, Object> selectMonthlyPerformance() throws Exception {
		MonthlyPerfVo resultVo = dashboardService.selectMonthlyPerformance();
    Map<String, Object> responseWrapper = new HashMap<>();
    responseWrapper.put("monthlyPerfVo", resultVo);
    return responseWrapper;

	}
	
	

}

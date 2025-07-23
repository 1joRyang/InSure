package com.demo.proworks.dashboard.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.demo.proworks.dashboard.vo.WeeklyTrendVo;
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
	
	/**
	 * 보완 요청 현황을 조회한다.
	 * @return MonthlyPerfVo
	 * @throws Exception
     */
	@ElService(key = "selectSupplementStatus")
	@RequestMapping(value = "selectSupplementStatus")
	@ElDescription(sub = "보완 요청 현황 조회", desc = "대시보드의 보완 요청 현황 데이터를 조회한다.")
	public Map<String, Object> selectSupplementStatus() throws Exception {
		SupplementStatusVo resultVo = dashboardService.selectSupplementStatus();
	    Map<String, Object> responseWrapper = new HashMap<>();
	    responseWrapper.put("supplementStatusVo", resultVo);
	    return responseWrapper;

	}
	
	/**
	 * 전일 처리 건수를 조회한다.
	 * @return DailyCountVo
	 * @throws Exception
     */
	@ElService(key = "selectYesterdayCount")
	@RequestMapping(value = "selectYesterdayCount")
	@ElDescription(sub = "전일 처리 건수 조회", desc = "대시보드의 전일 처리 건수를 조회한다.")
	public Map<String, Object> selectYesterdayCount() throws Exception {
		DailyCountVo resultVo = dashboardService.selectYesterdayProcessedCount();
	    Map<String, Object> responseWrapper = new HashMap<>();
	    responseWrapper.put("dailyCountVo", resultVo);
	    return responseWrapper;

	}
	
	/**
	 * 이번 달 지급률을 조회한다.
	 * @return ApprovalRateVo
	 * @throws Exception
     */
	@ElService(key = "selectPaymentRate")
	@RequestMapping(value = "selectPaymentRate")
	@ElDescription(sub = "이번 달 지급률 조회", desc = "대시보드의 이번 달 지급률 데이터를 조회한다.")
	public Map<String, Object> selectPaymentRate() throws Exception {
		PaymentVo resultVo = dashboardService.selectPaymentRate();
	    Map<String, Object> responseWrapper = new HashMap<>();
	    responseWrapper.put("PaymentVo", resultVo);
	    return responseWrapper;

	}
	
	
	/**
	 * 이번 달 승인률을 조회한다.
	 * @return ApprovalRateVo
	 * @throws Exception
     */
	@ElService(key = "selectUrgentClaims")
	@RequestMapping(value = "selectUrgentClaims")
	@ElDescription(sub = "우선 처리 업무 조회", desc = "마감 기한이 임박한 청구 목록을 조회한다.")
	public Map<String, Object> selectUrgentClaims() throws Exception {
		System.out.println("====== [서버] 우선 처리 업무 조회 시작 ======");
        List<UrgentClaimVo> resultList = dashboardService.selectUrgentClaims();
        
        if (resultList != null) {
            System.out.println("DB 조회 결과 건수: " + resultList.size());
            if (!resultList.isEmpty()) {
                System.out.println("첫 번째 데이터: " + resultList.get(0).toString());
            }
        } else {
            System.out.println("DB 조회 결과가 null입니다.");
        }
        
        // 2. 클라이언트의 DataList ID("dlt_urgentClaims")를 key로 하여 Map에 담아 반환
        Map<String, Object> responseWrapper = new HashMap<>();
        responseWrapper.put("dlt_urgentClaims", resultList);

        return responseWrapper;

	}
	
	
	/**
	 * 주간 처리 현황 추이 데이터를 조회한다.
	 * @return List<WeeklyTrendVo>
	 * @throws Exception
     */
	@ElService(key = "selectWeeklyTrend")
	@RequestMapping(value = "selectWeeklyTrend")
	@ElDescription(sub = "주간 처리 현황 추이 조회", desc = "대시보드의 주간 처리 현황 추이 데이터를 조회한다.")
	public Map<String, Object> selectWeeklyTrend() throws Exception {
	    System.out.println("====== [서버] 주간 처리 현황 추이 데이터를 조회 시작 ======");
	    
	    // 반환 타입을 List<WeeklyTrendVo>로 변경 ✅
	    List<WeeklyTrendVo> resultList = dashboardService.selectWeeklyTrend();
	
	    Map<String, Object> responseWrapper = new HashMap<>();
	    responseWrapper.put("weeklyTrendList", resultList);
	    return responseWrapper;
	}
	
	/**
	 * 청구 유형 데이터를 조회한다.
     */
	@ElService(key = "selectClaimTypeDistribution")
	@RequestMapping(value = "selectClaimTypeDistribution")
	@ElDescription(sub = "청구 유형 데이터 조회", desc = "청구 유형 데이터 조회한다.")
	public Map<String, Object> selectClaimTypeDistribution() throws Exception {
	    System.out.println("====== [서버] 청구 유형 데이터를 조회 시작 ======");
	    
	    List<ChartVo> resultList = dashboardService.selectClaimTypeDistribution();
	    Map<String, Object> response = new HashMap<>();
	    response.put("claimTypeData", resultList);
	    return response;
	}
	
	/**
	 * 월별 승인률 차트를 조회한다.
     */
	@ElService(key = "selectMonthlyApprovalRate")
	@RequestMapping(value = "selectMonthlyApprovalRate")
	@ElDescription(sub = "월별 승인률 데이터 조회", desc = "월별 승인률 데이터 조회한다.")
	public Map<String, Object> selectMonthlyApprovalRate() throws Exception {
	    List<MonthlyApprovalRateVo> resultList = dashboardService.selectMonthlyApprovalRate();
	    Map<String, Object> response = new HashMap<>();
	    response.put("monthlyRateData", resultList);
	    return response;
	}
	
	/**
	 * 처리 시간 분포 차트를 조회한다.
     */
	@ElService(key = "selectProcessingTimeTrend")
	@RequestMapping(value = "selectProcessingTimeTrend")
	@ElDescription(sub = "처리 시간 분포 데이터 조회", desc = "처리 시간 분포 데이터 조회한다.")
	public Map<String, Object> selectProcessingTimeTrend() throws Exception {
        List<ProcessingTimeVo> resultList = dashboardService.selectProcessingTimeTrend();
        Map<String, Object> response = new HashMap<>();
        response.put("processingTimeData", resultList);
        return response;
    }
    
    /**
	 * 처리 시간 분포에서 제외하는 장기처리건을 조회한다.
     */
	@ElService(key = "selectProcessingTimeOutlierCount")
	@RequestMapping(value = "selectProcessingTimeOutlierCount")
	@ElDescription(sub = "장기 처리 건수 조회", desc = "처리 시간 분포 차트의 장기 처리 건수를 조회한다.")
	public Map<String, Object> selectProcessingTimeOutlierCount() throws Exception {
        OutlierCountVo resultVo = dashboardService.selectProcessingTimeOutlierCount();
        Map<String, Object> response = new HashMap<>();
        response.put("outlierCountData", resultVo);
        return response;
    }
	
	

}

package com.demo.proworks.batch.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.batch.service.BatchService;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;

/**
 * @subject : 배치 테스트 처리를 담당하는 컨트롤러
 * @description : 배치 테스트 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Controller
public class BatchTestController {

	@Resource(name = "batchService")
    private BatchService batchService;
    
    /**
     * 월별 승인율 집계 배치를 수동으로 실행한다. (테스트용)
     * @return Map<String, String> 배치 실행 결과 메시지
     * @throws Exception
     */
	@ElService(key = "runManualBatch")
	@RequestMapping(value = "runManualBatch")
	@ElDescription(sub = "배치 수동 실행", desc = "월별 승인율 집계 배치를 수동으로 실행하는 테스트용.")
	public Map<String, String> runManualBatch() throws Exception {
        
        Map<String, String> result = new HashMap<>();

        try {
            batchService.executeMonthlyRateBatch();
            String successMessage = "배치 작업이 성공적으로 실행되었습니다. 대시보드 화면을 새로고침하여 차트를 확인하세요.";
            result.put("message", successMessage);
            System.out.println("====== [수동 배치 실행] " + successMessage + " ======");
            
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "배치 실행 중 오류가 발생했습니다. 서버 로그를 확인하세요.";
            result.put("message", errorMessage);
            System.out.println("====== [수동 배치 오류] " + errorMessage + " ======");
        }
        
        // 실행 결과 메시지를 담은 Map 객체를 직접 반환
        return result;
    }
  
}

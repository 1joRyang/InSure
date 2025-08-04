package com.demo.proworks.notification.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.claim.dao.ClaimDAO;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.employee.dao.EmployeeDAO;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.notification.service.NotificationService;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;

/**
 * @subject     : 웹소켓 알림 전용 컨트롤러 
 * @description : 실손보험 자동 배정 후 웹소켓 실시간 알림을 전송하는 전용 컨트롤러
 * @author      : 웹소켓팀
 * @since       : 2025/07/21
 */
@Controller
public class WebSocketNotificationController {
    
    private static final Logger logger = LoggerFactory.getLogger(WebSocketNotificationController.class);
    
    @Resource(name = "assignRuleServiceImpl")
    private AssignRuleService assignRuleService;
    
    @Resource(name = "claimDAO")
    private ClaimDAO claimDAO;
    
    @Resource(name = "employeeDAO")  
    private EmployeeDAO employeeDAO;
    
    @Resource(name = "notificationServiceImpl")
    private NotificationService notificationService;
    
   
    
    /**
     * 웹소켓 연결 테스트
     */
    @ElService(key="websocket0003TestConnection")
    @RequestMapping(value="websocket0003TestConnection")
    @ElDescription(sub="웹소켓 연결 테스트", desc="웹소켓 서버 연결 상태를 확인한다.")
    public Map<String, Object> testWebSocketConnection() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            Map<String, Object> response = restTemplate.getForObject(
                "http://localhost:8081/api/connection-status", 
                Map.class
            );
            
            if (response != null) {
                result.put("success", true);
                result.put("message", "웹소켓 서버 연결 정상");
                result.put("serverStatus", response);
            } else {
                result.put("success", false);
                result.put("message", "웹소켓 서버 응답 없음");
            }
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "웹소켓 서버 연결 실패: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 수동 웹소켓 알림 전송
     */
    @ElService(key="websocket0004SendTestNotification")
    @RequestMapping(value="websocket0004SendTestNotification")
    @ElDescription(sub="테스트 알림 전송", desc="지정된 직원에게 테스트 웹소켓 알림을 전송한다.")
    public Map<String, Object> sendTestNotification(AssignRuleVo assignRuleVo) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            String targetEmpNo = assignRuleVo.getKeyword(); // keyword를 targetEmpNo로 사용
            String testMessage = assignRuleVo.getDept(); // dept를 테스트 메시지로 사용
            
            if (targetEmpNo == null || targetEmpNo.trim().isEmpty()) {
                result.put("success", false);
                result.put("message", "대상 직원번호를 입력해주세요.");
                return result;
            }
            
            // 테스트 알림 전송
            Map<String, Object> notificationData = new HashMap<String, Object>();
            notificationData.put("claimNo", "TEST_" + System.currentTimeMillis());
            notificationData.put("claimType", "테스트청구");
            notificationData.put("targetEmpNo", targetEmpNo);
            notificationData.put("assignedBy", "테스트관리자");
            notificationData.put("priority", "TEST");
            
            if (testMessage != null && !testMessage.trim().isEmpty()) {
                notificationData.put("claimContent", testMessage);
            } else {
                notificationData.put("claimContent", "웹소켓 연동 테스트 알림입니다.");
            }
            
            Map<String, Object> notificationResult = sendManualAssignNotification(notificationData);
            
            result.put("success", true);
            result.put("message", "테스트 알림 전송 완료");
            result.put("notificationResult", notificationResult);
            result.put("targetEmpNo", targetEmpNo);
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "테스트 알림 전송 실패: " + e.getMessage());
        }
        
        return result;
    }
    
    // =========================== Private 메서드들 ===========================
    
    /**
     * 청구 정보 조회
     */
    private ClaimVo getClaimInfo(String claimNo) throws Exception {
        ClaimVo searchVo = new ClaimVo();
        searchVo.setClaim_no(claimNo);
        
        List<ClaimVo> claimList = claimDAO.selectListClaim(searchVo);
        
        if (claimList != null && !claimList.isEmpty()) {
            return claimList.get(0);
        }
        
        throw new Exception("청구 정보를 찾을 수 없습니다: " + claimNo);
    }
    
    /**
	 * 자동 배정 웹소켓 알림 전송
	 */
	private Map<String, Object> sendWebSocketNotification(ClaimVo afterClaim, String assignResult) {
	    // beforeClaim 파라미터 제거 (사실 사용되지 않았음)
	    Map<String, Object> result = new HashMap<String, Object>();
	    
	    try {
	        RestTemplate restTemplate = new RestTemplate();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        Map<String, Object> requestBody = new HashMap<String, Object>();
	        requestBody.put("claimNo", afterClaim.getClaim_no());
	        requestBody.put("claimType", afterClaim.getClaim_type());
	        requestBody.put("claimContent", afterClaim.getClaim_content());
	        requestBody.put("receiptDate", afterClaim.getReceipt_date());
	        requestBody.put("diseaseCode", afterClaim.getDisease_code());
	        requestBody.put("dateOfAccident", afterClaim.getDate_of_accident());
	        requestBody.put("assignedEmpNo", afterClaim.getEmp_no());
	        requestBody.put("assignResult", assignResult);
	        requestBody.put("priority", "AUTO");
	        
	        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);
	        
	        // 웹소켓 서버로 알림 전송
	        Map<String, Object> response = restTemplate.postForObject(
	            "http://localhost:8081/api/notify-auto-assign", 
	            entity, 
	            Map.class
	        );
	        
	        if (response != null && Boolean.TRUE.equals(response.get("success"))) {
	            result.put("sent", true);
	            result.put("message", "웹소켓 알림 전송 성공");
	            result.put("response", response);
	        } else {
	            result.put("sent", false);
	            result.put("message", "웹소켓 알림 전송 실패");
	            result.put("response", response);
	        }
	        
	    } catch (Exception e) {
	        result.put("sent", false);
	        result.put("message", "웹소켓 알림 전송 오류: " + e.getMessage());
	        logger.warn("웹소켓 알림 전송 실패: {}", e.getMessage());
	    }
	    
	    return result;
	}
    
    /**
     * 일괄 배정 완료 웹소켓 알림 전송
     */
    private Map<String, Object> sendBatchCompleteNotification(int totalProcessed, int successCount, int failCount, List<String> results) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, Object> requestBody = new HashMap<String, Object>();
            requestBody.put("totalProcessed", totalProcessed);
            requestBody.put("successCount", successCount);
            requestBody.put("failCount", failCount);
            requestBody.put("processedClaims", results);
            
            HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);
            
            Map<String, Object> response = restTemplate.postForObject(
                "http://localhost:8081/api/notify-batch-complete", 
                entity, 
                Map.class
            );
            
            result.put("sent", true);
            result.put("message", "일괄 배정 완료 알림 전송 성공");
            result.put("response", response);
            
        } catch (Exception e) {
            result.put("sent", false);
            result.put("message", "일괄 배정 완료 알림 전송 실패: " + e.getMessage());
            logger.warn("일괄 배정 완료 알림 전송 실패: {}", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 수동 배정 웹소켓 알림 전송
     */
    private Map<String, Object> sendManualAssignNotification(Map<String, Object> notificationData) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(notificationData, headers);
            
            Map<String, Object> response = restTemplate.postForObject(
                "http://localhost:8081/api/notify-manual-assign",
                entity, 
                Map.class
            );
            
            result.put("sent", true);
            result.put("message", "수동 배정 알림 전송 성공");
            result.put("response", response);
            
        } catch (Exception e) {
            result.put("sent", false);
            result.put("message", "수동 배정 알림 전송 실패: " + e.getMessage());
        }
        
        return result;
    }
}
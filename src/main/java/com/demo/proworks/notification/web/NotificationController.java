package com.demo.proworks.notification.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.demo.proworks.notification.service.NotificationService;
import com.demo.proworks.notification.vo.NotificationVo;
import com.demo.proworks.notification.vo.NotificationListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 알림 관련 처리를 담당하는 컨트롤러
 * @description : 알림 관련 처리를 담당하는 컨트롤러
 * @author      : 임소희
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 임소희	 		최초 생성
 * 
 */
@Controller
public class NotificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
    /** NotificationService */
    @Resource(name = "notificationServiceImpl")
    private NotificationService notificationService;
	
    
    /**
     * 알림 목록을 조회합니다.
     *
     * @param  notificationVo 알림
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="NotificationList")
    @RequestMapping(value="NotificationList")    
    @ElDescription(sub="알림 목록조회",desc="페이징을 처리하여 알림 목록 조회를 한다.")               
    public NotificationListVo selectListNotification(Object requestData) throws Exception {
    
	    NotificationVo notificationVo = new NotificationVo();
	    
	    // requestData에서 필요한 값 추출
	    if (requestData instanceof Map) {
	        Map<String, Object> dataMap = (Map<String, Object>) requestData;
	        String empNo = (String) dataMap.get("emp_no");
	        if (empNo != null) {
	            notificationVo.setEmp_no(empNo);
	        }
	    }
	    
	    // 기존 로직 실행
	    List<NotificationVo> notificationList = notificationService.selectListNotification(notificationVo);                  
	    long totCnt = notificationService.selectListCountNotification(notificationVo);
	
	    NotificationListVo retNotificationList = new NotificationListVo();
	    retNotificationList.setNotificationVoList(notificationList); 
	    retNotificationList.setTotalCount(totCnt);
	    retNotificationList.setPageSize(10);  // 기본값
	    retNotificationList.setPageIndex(1);  // 기본값
	    
	    return retNotificationList;            
	}

    /**
     * 알림을 단건 조회 처리 한다.
     *
     * @param  notificationVo 알림
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "NotificationUpdView")    
    @RequestMapping(value="NotificationUpdView") 
    @ElDescription(sub = "알림 갱신 폼을 위한 조회", desc = "알림 갱신 폼을 위한 조회를 한다.")    
    public NotificationVo selectNotification(NotificationVo notificationVo) throws Exception {
    	NotificationVo selectNotificationVo = notificationService.selectNotification(notificationVo);    	    
		
        return selectNotificationVo;
    } 
 
    /**
     * 알림를 등록 처리 한다.
     *
     * @param  notificationVo 알림
     * @throws Exception
     */
    @ElService(key="NotificationIns")    
    @RequestMapping(value="NotificationIns")
    @ElDescription(sub="알림 등록처리",desc="알림를 등록 처리 한다.")
    public void insertNotification(NotificationVo notificationVo) throws Exception {    	 
    	notificationService.insertNotification(notificationVo);   
    }
       
    /**
     * 알림를 갱신 처리 한다.
     *
     * @param  notificationVo 알림
     * @throws Exception
     */
    @ElService(key="NotificationUpd")    
    @RequestMapping(value="NotificationUpd")    
    @ElValidator(errUrl="/notification/notificationRegister", errContinue=true)
    @ElDescription(sub="알림 갱신처리",desc="알림를 갱신 처리 한다.")    
    public void updateNotification(NotificationVo notificationVo) throws Exception {  
 
    	notificationService.updateNotification(notificationVo);                                            
    }

    /**
     * 알림를 삭제 처리한다.
     *
     * @param  notificationVo 알림    
     * @throws Exception
     */
    @ElService(key = "NotificationDel")    
    @RequestMapping(value="NotificationDel")
    @ElDescription(sub = "알림 삭제처리", desc = "알림를 삭제 처리한다.")    
    public void deleteNotification(NotificationVo notificationVo) throws Exception {
        notificationService.deleteNotification(notificationVo);
    }
   
// ==================== 웹소켓 알림용 추가 메서드들 ====================
    
    /**
     *  직원별 알림 목록 조회 (웹소켓용)
     */
    @ElService(key="notification0001GetNotificationList")
    @RequestMapping(value="notification0001GetNotificationList")
    @ElDescription(sub="알림 목록 조회", desc="로그인한 직원의 알림 목록을 조회한다.")
    @ResponseBody
    public Map<String, Object> getNotificationList(NotificationVo searchVo) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            // 알림 목록 조회 (웹소켓용)
            List<NotificationVo> notificationList = notificationService.selectNotificationList(searchVo);
            
            // 읽지 않은 알림 개수 조회
            int unreadCount = notificationService.selectUnreadNotificationCount(searchVo.getEmp_no());
            
            result.put("success", true);
            result.put("notificationList", notificationList);
            result.put("totalCount", notificationList.size());
            result.put("unreadCount", unreadCount);
            result.put("message", "알림 목록 조회 성공");
            
            logger.info("알림 목록 조회 완료: 직원번호={}, 총 {}건, 미읽음 {}건", 
                       searchVo.getEmp_no(), notificationList.size(), unreadCount);
            
        } catch (Exception e) {
            logger.error("알림 목록 조회 실패: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "알림 목록 조회 중 오류 발생: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 읽지 않은 알림 개수 조회
     */
    @ElService(key="notification0002GetUnreadCount")
    @RequestMapping(value="notification0002GetUnreadCount")
    @ElDescription(sub="미읽음 알림 개수", desc="로그인한 직원의 읽지 않은 알림 개수를 조회한다.")
    @ResponseBody
    public Map<String, Object> getUnreadNotificationCount(NotificationVo searchVo) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            int unreadCount = notificationService.selectUnreadNotificationCount(searchVo.getEmp_no());
            
            result.put("success", true);
            result.put("unreadCount", unreadCount);
            result.put("empNo", searchVo.getEmp_no());
            
        } catch (Exception e) {
            logger.error("미읽음 알림 개수 조회 실패: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("unreadCount", 0);
            result.put("message", "미읽음 알림 개수 조회 실패: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 알림 읽음 처리
     */
    @ElService(key="notification0003MarkAsRead")
    @RequestMapping(value="notification0003MarkAsRead")
    @ElDescription(sub="알림 읽음 처리", desc="특정 알림을 읽음 처리한다.")
    @ResponseBody
    public Map<String, Object> markNotificationAsRead(NotificationVo notificationVo) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            int updateResult = notificationService.updateNotificationAsRead(notificationVo);
            
            if (updateResult > 0) {
                result.put("success", true);
                result.put("message", "알림 읽음 처리 완료");
                
                // 업데이트 후 미읽음 개수 조회
                int unreadCount = notificationService.selectUnreadNotificationCount(notificationVo.getEmp_no());
                result.put("unreadCount", unreadCount);
                
                logger.info("알림 읽음 처리 완료: 알림ID={}, 직원번호={}", 
                           notificationVo.getNoti_id(), notificationVo.getEmp_no());
            } else {
                result.put("success", false);
                result.put("message", "읽음 처리할 알림을 찾을 수 없습니다");
            }
            
        } catch (Exception e) {
            logger.error("알림 읽음 처리 실패: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "알림 읽음 처리 중 오류 발생: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 모든 알림 읽음 처리
     */
    @ElService(key="notification0004MarkAllAsRead")
    @RequestMapping(value="notification0004MarkAllAsRead")
    @ElDescription(sub="모든 알림 읽음 처리", desc="로그인한 직원의 모든 미읽음 알림을 읽음 처리한다.")
    @ResponseBody
    public Map<String, Object> markAllNotificationAsRead(NotificationVo notificationVo) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            int updateResult = notificationService.updateAllNotificationAsRead(notificationVo.getEmp_no());
            
            result.put("success", true);
            result.put("updatedCount", updateResult);
            result.put("unreadCount", 0);
            result.put("message", updateResult + "건의 알림을 읽음 처리했습니다");
            
            logger.info("모든 알림 읽음 처리 완료: 직원번호={}, 처리건수={}", 
                       notificationVo.getEmp_no(), updateResult);
            
        } catch (Exception e) {
            logger.error("모든 알림 읽음 처리 실패: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "모든 알림 읽음 처리 중 오류 발생: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 모든 알림 삭제
     */
    @ElService(key="notification0006DeleteAllNotifications")
    @RequestMapping(value="notification0006DeleteAllNotifications")
    @ElDescription(sub="모든 알림 삭제", desc="로그인한 직원의 모든 알림을 삭제한다.")
    @ResponseBody
    public Map<String, Object> deleteAllNotifications(NotificationVo notificationVo) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            int deleteResult = notificationService.deleteAllNotificationByEmpNo(notificationVo.getEmp_no());
            
            result.put("success", true);
            result.put("deletedCount", deleteResult);
            result.put("unreadCount", 0);
            result.put("message", deleteResult + "건의 알림을 삭제했습니다");
            
            logger.info("모든 알림 삭제 완료: 직원번호={}, 삭제건수={}", 
                       notificationVo.getEmp_no(), deleteResult);
            
        } catch (Exception e) {
            logger.error("모든 알림 삭제 실패: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "모든 알림 삭제 중 오류 발생: " + e.getMessage());
        }
        
        return result;
    }
    
    
    
}

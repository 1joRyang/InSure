package com.demo.proworks.notification.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.notification.vo.NotificationVo;
import com.demo.proworks.notification.dao.NotificationDAO;

/**  
 * @subject     : 알림 관련 처리를 담당하는 DAO
 * @description : 알림 관련 처리를 담당하는 DAO
 * @author      : 임소희
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 임소희	 		최초 생성
 * 
 */
@Repository("notificationDAO")
public class NotificationDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 알림 상세 조회한다.
     *  
     * @param  NotificationVo 알림
     * @return NotificationVo 알림
     * @throws ElException
     */
    public NotificationVo selectNotification(NotificationVo vo) throws ElException {
        return (NotificationVo) selectByPk("com.demo.proworks.notification.selectNotification", vo);
    }

    /**
     * 페이징을 처리하여 알림 목록조회를 한다.
     *  
     * @param  NotificationVo 알림
     * @return List<NotificationVo> 알림
     * @throws ElException
     */
    public List<NotificationVo> selectListNotification(NotificationVo vo) throws ElException {      	
        return (List<NotificationVo>)list("com.demo.proworks.notification.selectListNotification", vo);
    }

    /**
     * 알림 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  NotificationVo 알림
     * @return 알림 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountNotification(NotificationVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.notification.selectListCountNotification", vo);
    }
        
    /**
     * 알림를 등록한다.
     *  
     * @param  NotificationVo 알림
     * @return 번호
     * @throws ElException
     */
    public int insertNotification(NotificationVo vo) throws ElException {    	
        return insert("com.demo.proworks.notification.insertNotification", vo);
    }

    /**
     * 알림를 갱신한다.
     *  
     * @param  NotificationVo 알림
     * @return 번호
     * @throws ElException
     */
    public int updateNotification(NotificationVo vo) throws ElException {
        return update("com.demo.proworks.notification.updateNotification", vo);
    }

    /**
     * 알림를 삭제한다.
     *  
     * @param  NotificationVo 알림
     * @return 번호
     * @throws ElException
     */
    public int deleteNotification(NotificationVo vo) throws ElException {
        return delete("com.demo.proworks.notification.deleteNotification", vo);
    }

 // ==================== 웹소켓 알림용 추가 메서드들 ====================
    
    /**
     * 직원별 알림 목록 조회 (웹소켓용)
     * 
     * @param NotificationVo 검색조건 (emp_no 필수)
     * @return List<NotificationVo> 알림 목록
     * @throws ElException
     */
    public List<NotificationVo> selectNotificationList(NotificationVo vo) throws ElException {
        return (List<NotificationVo>) list("com.demo.proworks.notification.selectNotificationList", vo);
    }
    
    /**
     * 직원별 읽지 않은 알림 개수 조회
     * 
     * @param String empNo 직원번호
     * @return int 읽지 않은 알림 개수
     * @throws ElException
     */
    public int selectUnreadNotificationCount(String empNo) throws ElException {
        Integer count = (Integer) selectByPk("com.demo.proworks.notification.selectUnreadNotificationCount", empNo);
        return count != null ? count : 0;
    }
    
    /**
     * 알림 읽음 처리
     * 
     * @param NotificationVo vo (noti_id, emp_no 필요)
     * @return int 처리 결과
     * @throws ElException
     */
    public int updateNotificationAsRead(NotificationVo vo) throws ElException {
        return update("com.demo.proworks.notification.updateNotificationAsRead", vo);
    }
    
    /**
     * 직원의 모든 알림 읽음 처리
     * 
     * @param String empNo 직원번호
     * @return int 처리된 건수
     * @throws ElException
     */
    public int updateAllNotificationAsRead(String empNo) throws ElException {
        return update("com.demo.proworks.notification.updateAllNotificationAsRead", empNo);
    }
    
    /**
     * 직원의 모든 알림 삭제
     * 
     * @param String empNo 직원번호
     * @return int 삭제된 건수
     * @throws ElException
     */
    public int deleteAllNotificationByEmpNo(String empNo) throws ElException {
        return delete("com.demo.proworks.notification.deleteAllNotificationByEmpNo", empNo);
    }
    
    /**
     * 오래된 알림 정리 (30일 이상)
     * 
     * @return int 삭제된 건수
     * @throws ElException
     */
    public int deleteOldNotifications() throws ElException {
        return delete("com.demo.proworks.notification.deleteOldNotifications", null);
    }
    
    // ==================== 웹소켓 알림 생성 및 저장 편의 메서드들 ====================
    
    /**
     * 자동 배정 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param String claimNo 청구번호  
     * @param String message 메시지
     * @return int 저장 결과
     * @throws ElException
     */
    public int insertAutoAssignNotification(String empNo, String claimNo, String message) throws ElException {
        NotificationVo vo = new NotificationVo();
        vo.setEmp_no(empNo);
        vo.setNoti_type("AUTO_ASSIGN");
        vo.setIs_read("N");
        
        // claimNo 정보를 content에 포함
        if (claimNo != null && !claimNo.trim().isEmpty()) {
            vo.setNoti_content(message + " (청구번호: " + claimNo + ")");
        } else {
            vo.setNoti_content(message);
        }
        
        return insertNotification(vo);
    }
    
    /**
     * 수동 배정 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param String claimNo 청구번호
     * @param String assignedBy 배정자
     * @return int 저장 결과
     * @throws ElException
     */
    public int insertManualAssignNotification(String empNo, String claimNo, String assignedBy) throws ElException {
        NotificationVo vo = new NotificationVo();
        vo.setEmp_no(empNo);
        vo.setNoti_type("MANUAL_ASSIGN");
        vo.setIs_read("N");
        
        String content = "관리자(" + assignedBy + ")가 새로운 심사 작업을 배정했습니다";
        if (claimNo != null && !claimNo.trim().isEmpty()) {
            content += " (청구번호: " + claimNo + ")";
        }
        vo.setNoti_content(content);
        
        return insertNotification(vo);
    }
    
    /**
     * 일괄 배정 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param int totalProcessed 총 처리건수
     * @param int successCount 성공건수
     * @param int failCount 실패건수
     * @return int 저장 결과
     * @throws ElException
     */
    public int insertBatchAssignNotification(String empNo, int totalProcessed, int successCount, int failCount) throws ElException {
        NotificationVo vo = new NotificationVo();
        vo.setEmp_no(empNo);
        vo.setNoti_type("BATCH_ASSIGN");
        vo.setIs_read("N");
        vo.setNoti_content(String.format("일괄 배정 완료 - 총 %d건 처리 (성공: %d건, 실패: %d건)", totalProcessed, successCount, failCount));
        
        return insertNotification(vo);
    }
    
    /**
     * 부서 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param String deptName 부서명
     * @param int assignCount 배정건수
     * @return int 저장 결과  
     * @throws ElException
     */
    public int insertDeptNotification(String empNo, String deptName, int assignCount) throws ElException {
        NotificationVo vo = new NotificationVo();
        vo.setEmp_no(empNo);
        vo.setNoti_type("DEPT_NOTIFICATION");
        vo.setIs_read("N");
        vo.setNoti_content(String.format("%s 부서에 %d건의 업무가 배정되었습니다", deptName, assignCount));
        
        return insertNotification(vo);
    }
    
    // ==================== 조회 편의 메서드들 ====================
    
    /**
     * 특정 직원의 최근 알림 목록 조회 (20개 제한)
     * 
     * @param String empNo 직원번호
     * @return List<NotificationVo> 알림 목록
     * @throws ElException
     */
    public List<NotificationVo> selectRecentNotifications(String empNo) throws ElException {
        return (List<NotificationVo>) list("com.demo.proworks.notification.selectRecentNotificationList", empNo);
    }
    
    /**
     * 특정 직원의 읽지 않은 알림 목록 조회
     * 
     * @param String empNo 직원번호
     * @return List<NotificationVo> 읽지 않은 알림 목록
     * @throws ElException
     */
    public List<NotificationVo> selectUnreadNotifications(String empNo) throws ElException {
        NotificationVo searchVo = new NotificationVo();
        searchVo.setEmp_no(empNo);
        searchVo.setIs_read("N");
        
        return selectNotificationList(searchVo);
    }
    
    /**
     * 특정 타입의 알림 목록 조회
     * 
     * @param String empNo 직원번호
     * @param String notiType 알림 타입
     * @return List<NotificationVo> 알림 목록
     * @throws ElException
     */
    public List<NotificationVo> selectNotificationsByType(String empNo, String notiType) throws ElException {
        NotificationVo searchVo = new NotificationVo();
        searchVo.setEmp_no(empNo);
        searchVo.setNoti_type(notiType);
        
        return selectNotificationList(searchVo);
    }
    
    /**
     * 알림 ID로 읽음 처리
     * 
     * @param String notiId 알림 ID
     * @param String empNo 직원번호
     * @return int 처리 결과
     * @throws ElException
     */
    public int markAsReadById(String notiId, String empNo) throws ElException {
        NotificationVo vo = new NotificationVo();
        vo.setNoti_id(notiId);
        vo.setEmp_no(empNo);
        
        return updateNotificationAsRead(vo);
    }
  }

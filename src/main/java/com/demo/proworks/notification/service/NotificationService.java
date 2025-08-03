package com.demo.proworks.notification.service;

import java.util.List;

import com.demo.proworks.notification.vo.NotificationVo;

/**  
 * @subject     : 알림 관련 처리를 담당하는 인터페이스
 * @description : 알림 관련 처리를 담당하는 인터페이스
 * @author      : 임소희
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 임소희	 		최초 생성
 * 
 */
public interface NotificationService {
	
    /**
     * 알림 페이징 처리하여 목록을 조회한다.
     *
     * @param  notificationVo 알림 NotificationVo
     * @return 알림 목록 List<NotificationVo>
     * @throws Exception
     */
	public List<NotificationVo> selectListNotification(NotificationVo notificationVo) throws Exception;
	
    /**
     * 조회한 알림 전체 카운트
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 알림 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountNotification(NotificationVo notificationVo) throws Exception;
	
    /**
     * 알림를 상세 조회한다.
     *
     * @param  notificationVo 알림 NotificationVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public NotificationVo selectNotification(NotificationVo notificationVo) throws Exception;
		
    /**
     * 알림를 등록 처리 한다.
     *
     * @param  notificationVo 알림 NotificationVo
     * @return 번호
     * @throws Exception
     */
	public int insertNotification(NotificationVo notificationVo) throws Exception;
	
    /**
     * 알림를 갱신 처리 한다.
     *
     * @param  notificationVo 알림 NotificationVo
     * @return 번호
     * @throws Exception
     */
	public int updateNotification(NotificationVo notificationVo) throws Exception;
	
    /**
     * 알림를 삭제 처리 한다.
     *
     * @param  notificationVo 알림 NotificationVo
     * @return 번호
     * @throws Exception
     */
	public int deleteNotification(NotificationVo notificationVo) throws Exception;
	
// ==================== 웹소켓 알림용 추가 메서드들 ====================
    
    /**
     * 직원별 알림 목록 조회 (웹소켓용)
     */
    List<NotificationVo> selectNotificationList(NotificationVo searchVo) throws Exception;
    
    /**
     * 직원별 읽지 않은 알림 개수 조회
     */
    int selectUnreadNotificationCount(String empNo) throws Exception;
    
    /**
     * 알림 읽음 처리
     */
    int updateNotificationAsRead(NotificationVo vo) throws Exception;
    
    /**
     * 직원의 모든 알림 읽음 처리
     */
    int updateAllNotificationAsRead(String empNo) throws Exception;
    
    /**
     * 직원의 모든 알림 삭제
     */
    int deleteAllNotificationByEmpNo(String empNo) throws Exception;
    
    /**
     * 오래된 알림 정리 (30일 이상)
     */
    int deleteOldNotifications() throws Exception;
    
    // ==================== 웹소켓 알림 생성 및 저장 편의 메서드들 ====================
    
    /**
     * 자동 배정 알림 생성 및 저장
     */
    int insertAutoAssignNotification(String empNo, String claimNo, String message) throws Exception;
    
    /**
     * 수동 배정 알림 생성 및 저장
     */
    int insertManualAssignNotification(String empNo, String claimNo, String assignedBy) throws Exception;
    
    /**
     * 일괄 배정 알림 생성 및 저장
     */
    int insertBatchAssignNotification(String empNo, int totalProcessed, int successCount, int failCount) throws Exception;
    
    /**
     * 부서 알림 생성 및 저장
     */
    int insertDeptNotification(String empNo, String deptName, int assignCount) throws Exception;

}


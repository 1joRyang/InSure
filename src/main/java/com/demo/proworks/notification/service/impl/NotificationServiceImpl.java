package com.demo.proworks.notification.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.notification.service.NotificationService;
import com.demo.proworks.notification.vo.NotificationVo;
import com.demo.proworks.notification.dao.NotificationDAO;

/**  
 * @subject     : 알림 관련 처리를 담당하는 ServiceImpl
 * @description	: 알림 관련 처리를 담당하는 ServiceImpl
 * @author      : 임소희
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 임소희	 		최초 생성
 * 
 */
@Service("notificationServiceImpl")
public class NotificationServiceImpl implements NotificationService {

    @Resource(name="notificationDAO")
    private NotificationDAO notificationDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 알림 목록을 조회한다.
     *
     * @process
     * 1. 알림 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<NotificationVo>을(를) 리턴한다.
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 알림 목록 List<NotificationVo>
     * @throws Exception
     */
	public List<NotificationVo> selectListNotification(NotificationVo notificationVo) throws Exception {
		List<NotificationVo> list = notificationDAO.selectListNotification(notificationVo);	
	
		return list;
	}

    /**
     * 조회한 알림 전체 카운트
     *
     * @process
     * 1. 알림 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 알림 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountNotification(NotificationVo notificationVo) throws Exception {
		return notificationDAO.selectListCountNotification(notificationVo);
	}

    /**
     * 알림를 상세 조회한다.
     *
     * @process
     * 1. 알림를 상세 조회한다.
     * 2. 결과 NotificationVo을(를) 리턴한다.
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public NotificationVo selectNotification(NotificationVo notificationVo) throws Exception {
		NotificationVo resultVO = notificationDAO.selectNotification(notificationVo);			
        
        return resultVO;
	}

    /**
     * 알림를 등록 처리 한다.
     *
     * @process
     * 1. 알림를 등록 처리 한다.
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 번호
     * @throws Exception
     */
	public int insertNotification(NotificationVo notificationVo) throws Exception {
		return notificationDAO.insertNotification(notificationVo);	
	}
	
    /**
     * 알림를 갱신 처리 한다.
     *
     * @process
     * 1. 알림를 갱신 처리 한다.
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 번호
     * @throws Exception
     */
	public int updateNotification(NotificationVo notificationVo) throws Exception {				
		return notificationDAO.updateNotification(notificationVo);	   		
	}

    /**
     * 알림를 삭제 처리 한다.
     *
     * @process
     * 1. 알림를 삭제 처리 한다.
     * 
     * @param  notificationVo 알림 NotificationVo
     * @return 번호
     * @throws Exception
     */
	public int deleteNotification(NotificationVo notificationVo) throws Exception {
		return notificationDAO.deleteNotification(notificationVo);
	}
	
// ==================== 웹소켓 알림용 추가 메서드들 ====================
    
    /**
     * 직원별 알림 목록 조회 (웹소켓용)
     * 
     * @param NotificationVo searchVo 검색조건 
     * @return List<NotificationVo> 알림 목록
     * @throws Exception
     */
    public List<NotificationVo> selectNotificationList(NotificationVo searchVo) throws Exception {
        return notificationDAO.selectNotificationList(searchVo);
    }
    
    /**
     * 직원별 읽지 않은 알림 개수 조회
     * 
     * @param String empNo 직원번호
     * @return int 읽지 않은 알림 개수
     * @throws Exception
     */
    public int selectUnreadNotificationCount(String empNo) throws Exception {
        return notificationDAO.selectUnreadNotificationCount(empNo);
    }
    
    /**
     * 알림 읽음 처리
     * 
     * @param NotificationVo vo 
     * @return int 처리 결과
     * @throws Exception
     */
    public int updateNotificationAsRead(NotificationVo vo) throws Exception {
        return notificationDAO.updateNotificationAsRead(vo);
    }
    
    /**
     * 직원의 모든 알림 읽음 처리
     * 
     * @param String empNo 직원번호
     * @return int 처리된 건수
     * @throws Exception
     */
    public int updateAllNotificationAsRead(String empNo) throws Exception {
        return notificationDAO.updateAllNotificationAsRead(empNo);
    }
    
    /**
     * 직원의 모든 알림 삭제
     * 
     * @param String empNo 직원번호
     * @return int 삭제된 건수
     * @throws Exception
     */
    public int deleteAllNotificationByEmpNo(String empNo) throws Exception {
        return notificationDAO.deleteAllNotificationByEmpNo(empNo);
    }
    
    /**
     * 오래된 알림 정리 (30일 이상)
     * 
     * @return int 삭제된 건수
     * @throws Exception
     */
    public int deleteOldNotifications() throws Exception {
        return notificationDAO.deleteOldNotifications();
    }
    
    // ==================== 웹소켓 알림 생성 및 저장 편의 메서드들 ====================
    
    /**
     * 자동 배정 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param String claimNo 청구번호
     * @param String message 메시지
     * @return int 저장 결과
     * @throws Exception
     */
    public int insertAutoAssignNotification(String empNo, String claimNo, String message) throws Exception {
        return notificationDAO.insertAutoAssignNotification(empNo, claimNo, message);
    }
    
    /**
     * 수동 배정 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param String claimNo 청구번호
     * @param String assignedBy 배정자
     * @return int 저장 결과
     * @throws Exception
     */
    public int insertManualAssignNotification(String empNo, String claimNo, String assignedBy) throws Exception {
        return notificationDAO.insertManualAssignNotification(empNo, claimNo, assignedBy);
    }
    
    /**
     * 일괄 배정 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param int totalProcessed 총 처리건수
     * @param int successCount 성공건수
     * @param int failCount 실패건수
     * @return int 저장 결과
     * @throws Exception
     */
    public int insertBatchAssignNotification(String empNo, int totalProcessed, int successCount, int failCount) throws Exception {
        return notificationDAO.insertBatchAssignNotification(empNo, totalProcessed, successCount, failCount);
    }
    
    /**
     * 부서 알림 생성 및 저장
     * 
     * @param String empNo 직원번호
     * @param String deptName 부서명
     * @param int assignCount 배정건수
     * @return int 저장 결과
     * @throws Exception
     */
    public int insertDeptNotification(String empNo, String deptName, int assignCount) throws Exception {
        return notificationDAO.insertDeptNotification(empNo, deptName, assignCount);
    }
    
    
}

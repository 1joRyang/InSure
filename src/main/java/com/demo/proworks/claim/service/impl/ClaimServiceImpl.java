package com.demo.proworks.claim.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.claim.dao.ClaimDAO;
import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimNoVo;
import com.demo.proworks.claim.vo.ClaimUserCalcVo;
import com.demo.proworks.claim.vo.ClaimUserEmpVo;
import com.demo.proworks.claim.vo.ClaimEmployeeVo;
import com.demo.proworks.claim.vo.ClaimFullJoinVo;
import com.demo.proworks.claim.vo.ClaimListwStatusVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.cmmn.ProworksUserHeader;

import com.demo.proworks.claim.vo.ClaimStatusCountVo;


import com.demo.proworks.insimagefile.dao.InsimagefileDAO;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.notification.service.NotificationService;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claim.dao.ClaimDAO;

/**
 * @subject : 청구 관련 처리를 담당하는 ServiceImpl
 * @description : 청구 관련 처리를 담당하는 ServiceImpl
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Service("claimServiceImpl")
public class ClaimServiceImpl implements ClaimService {

	@Resource(name = "claimDAO")
	private ClaimDAO claimDAO;

	@Resource(name = "assignRuleServiceImpl")
	private AssignRuleService assignRuleService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "notificationServiceImpl")
	private NotificationService notificationService;
	
	
	/**
	 * 고객 계산
	 */
	public ClaimUserCalcVo selectUserClaimCalc(ClaimVo claimVo) throws Exception {
		return claimDAO.selectUserClaimCalc(claimVo);
	}
	
	/**
	 * 관리자 청구 목록 조회
	 */
	public List<ClaimListwStatusVo> selectClaimWithStatusManager(ClaimVo claimVo) throws Exception {
		return claimDAO.selectClaimWithStatusManager(claimVo);
	}
	
	/**
	 *  내 청구 목록 조회
	 */
	public List<ClaimListwStatusVo> selectClaimWithStatus(ClaimVo claimVo) throws Exception {
		System.out.println("service" + claimDAO.selectClaimWithStatus(claimVo));
		return claimDAO.selectClaimWithStatus(claimVo);
	}
	
	
	public List<ClaimListwStatusVo> selectClaimWithStatusWait(ClaimVo claimVo) throws Exception {
		System.out.println("service" + claimDAO.selectClaimWithStatusWait(claimVo));
		return claimDAO.selectClaimWithStatusWait(claimVo);
	}

	public List<ClaimNClaimResultVo> selectClaimNClaimResult(ClaimUserVo claimVo) throws Exception {
		return claimDAO.selectClaimNClaimResult(claimVo);
	}

	/**
	 * 청구 목록을 조회합니다.
	 *
	 * @process 1. 청구 페이징 처리하여 목록을 조회한다. 2. 결과 List<ClaimVo>을(를) 리턴한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 청구 목록 List<ClaimVo>
	 * @throws Exception
	 */
	public List<ClaimVo> selectListClaim(ClaimVo claimVo) throws Exception {
		List<ClaimVo> list = claimDAO.selectListClaim(claimVo);

		return list;
	}

	/**
	 * 조회한 청구 전체 카운트
	 *
	 * @process 1. 청구 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 청구 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.selectListCountClaim(claimVo);
	}

	/**
	 * 청구를 상세 조회한다.
	 *
	 * @process 1. 청구를 상세 조회한다. 2. 결과 ClaimVo을(를) 리턴한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public ClaimVo selectClaim(ClaimVo claimVo) throws Exception {
		ClaimVo resultVO = claimDAO.selectClaim(claimVo);

		return resultVO;
	}

	/**
	 * 청구를 등록 처리 한다.
	 *
	 * @process 1. 청구를 등록 처리 한다. 2. 자동 배정 설정이 활성화된 경우 자동으로 배정을 실행한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	@Transactional
	public int insertClaim(ClaimVo claimVo) throws Exception {
    int result = claimDAO.insertClaim(claimVo);
    System.out.println("[청구 등록 완료] 청구번호: " + claimVo.getClaim_no() + ", claim_type: " + claimVo.getClaim_type());
    
    // ✅ claimType이 null인 경우 자동배정 시도하지 않음 (OCR 분석 대기 상태)
    if (result > 0 && claimVo.getClaim_no() != null && claimVo.getClaim_type() != null) {
        try {
            String originalClaimType = claimVo.getClaim_type();
            System.out.println("[자동 배정 준비] 원본 claim_type: " + originalClaimType);
            
            String koreanClaimType = convertEngToKoreanForAssignment(originalClaimType);
            System.out.println("[자동 배정] 영문 '" + originalClaimType + "' -> 한글 '" + koreanClaimType + "'로 변환");
            
            ClaimVo tempUpdateVo = new ClaimVo();
            tempUpdateVo.setClaim_no(claimVo.getClaim_no());
            tempUpdateVo.setClaim_type(koreanClaimType);
            claimDAO.updateClaim(tempUpdateVo);
            System.out.println("[자동 배정] DB의 claim_type을 '" + koreanClaimType + "'로 임시 업데이트");
            
            String assignResult = assignRuleService.assignEmployeeToClaim(claimVo.getClaim_no());
            System.out.println("[자동 배정 완료] " + assignResult);
            
            // ✨ 자동 배정 성공 시 고객 등록 알림 추가
            if (assignResult.contains("자동 배정 완료")) {
                // 배정 완료 후 최신 청구 정보 조회
                ClaimVo updatedClaim = claimDAO.selectClaim(tempUpdateVo);
                if (updatedClaim != null && updatedClaim.getEmp_no() != null) {
                   // 🔔 담당자에게 "고객이 새 청구를 등록했다"는 알림
                    sendCustomerClaimRegistrationNotification(updatedClaim, originalClaimType);
                }
            }
            
            
            ClaimVo restoreVo = new ClaimVo();
            restoreVo.setClaim_no(claimVo.getClaim_no());
            restoreVo.setClaim_type(originalClaimType);
            claimDAO.updateClaim(restoreVo);
            System.out.println("[자동 배정] DB의 claim_type을 원본 '" + originalClaimType + "'로 복구");
            
        } catch (Exception e) {
            System.err.println("[자동 배정 실패] 청구번호: " + claimVo.getClaim_no() + ", 오류: " + e.getMessage());
            System.err.println("[알림] 청구 등록은 완료되었으나 자동 배정만 실패함. 수동 배정 필요.");
            
            try {
                ClaimVo restoreVo = new ClaimVo();
                restoreVo.setClaim_no(claimVo.getClaim_no());
                restoreVo.setClaim_type(claimVo.getClaim_type());
                claimDAO.updateClaim(restoreVo);
                System.out.println("[자동 배정 실패] DB를 원본 타입으로 복구 완료");
            } catch (Exception restoreEx) {
                System.err.println("[자동 배정 실패] DB 복구 실패: " + restoreEx.getMessage());
            }
            
        }
    } else if (claimVo.getClaim_type() == null) {
        System.out.println("[자동 배정 건너뜀] claimType이 null (OCR 분석 대기 상태)");
    }

    return result;
	}
	private String convertEngToKoreanForAssignment(String engClaimType) {
//	    if (engClaimType == null) return "실손";
	    
	    switch (engClaimType) {
	        case "death":
	            return "사망";
	        case "disability":
	            return "장해";
	        case "surgery":
	            return "수술";
	        case "disease":
	            return "실손";  // ✅ "질병" → "실손"으로 변경
	        case "injury":
	            return "재해";  // 이제 "재해"로 변환되어 배정 규칙과 매칭됨
	        case "other":
	            return "실손";  // ✅ 기타는 실손으로 처리
	        default:
	            return null;  // 기본값
	    }
	}
	
	
	/**
	 * 고객이 청구를 등록했다는 알림을 담당자에게 전송 (청구번호 기반)
	 */
	private void sendCustomerClaimRegistrationNotification(ClaimVo claimVo, String originalClaimType) {
	    CompletableFuture.runAsync(() -> {
	        try {
	            System.out.println("[NOTIFICATION] 고객 청구 등록 알림 전송 시작: " + claimVo.getClaim_no());
	            
	            // 고객 이름 조회
	            String customerName = getCustomerName(claimVo.getID());
	            
	            Map<String, Object> data = new HashMap<>();
	            data.put("claimNo", claimVo.getClaim_no());
	            data.put("targetEmpNo", claimVo.getEmp_no());
	            data.put("actionType", "new_claim");
	            data.put("customerName", customerName);
	            data.put("claimType", originalClaimType);
	            
	            // 기존 sendNotification 메서드 사용
	            sendNotification("/api/notify-customer-action", data);
	            
	            // 데이터베이스에도 알림 저장
	            notificationService.insertAutoAssignNotification(
	                claimVo.getEmp_no(), 
	                claimVo.getClaim_no(), 
	                customerName + "님이 새로운 " + originalClaimType + " 청구를 등록했습니다."
	            );
	            
	            System.out.println("[WEBSOCKET] 고객 청구 등록 알림 전송 완료: " + claimVo.getClaim_no() + " -> " + claimVo.getEmp_no());
	            
	        } catch (Exception e) {
	            System.err.println("고객 청구 등록 알림 전송 실패: " + e.getMessage());
	            e.printStackTrace();
	        }
	    });
	}
	
	
	
	/**
	 * 청구를 갱신 처리 한다.
	 *
	 * @process 1. 청구를 갱신 처리 한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	/*public int updateClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.updateClaim(claimVo);
	}*/
	public int updateClaim(ClaimVo claimVo) throws Exception {
        // 1. 기존 상태 조회 (알림 비교용)
         ClaimVo beforeClaim = null;
	    try {
	        beforeClaim = claimDAO.selectClaim(claimVo);
	    } catch (Exception e) {
	        System.out.println("기존 청구 조회 실패, 새 청구로 처리: " + e.getMessage());
	    }
	    
	    String beforeStatus = beforeClaim != null ? beforeClaim.getStatus() : null;
	    
	    // 청구 정보 업데이트
	    int result = claimDAO.updateClaim(claimVo);
	    
	    // 상태 변경에 따른 알림 처리
	    if (result > 0) {
	        sendStatusChangeNotification(claimVo, beforeStatus, claimVo.getStatus());
	    }
	    
	    return result;
	}
    
    
	
	/**
	 * 청구번호로 관리자 번호 조회
	 */
	private String getManagerNoByClaimNo(String claimNo) {
	    try {
	        return claimDAO.selectManagerNo(claimNo);
	    } catch (Exception e) {
	        System.err.println("관리자 번호 조회 실패: " + e.getMessage());
	        return null;
	    }
	}
    







// ==================== 웹소켓 알림 관련 메서드들 ====================

		/**
		 * 웹소켓 알림 전송 공통 메서드
		 */
		private void sendNotification(String apiEndpoint, Map<String, Object> data) {
		    try {
		        RestTemplate restTemplate = new RestTemplate();
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        
		        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);
		        
		        restTemplate.postForObject(
		            "http://localhost:3000" + apiEndpoint,  // 포트 3000으로 수정!
		            entity,
		            Map.class
		        );
		        
		        System.out.println("✅ 알림 전송 완료: " + apiEndpoint);
		        
		    } catch (Exception e) {
		        System.err.println("❌ 알림 전송 실패: " + e.getMessage());
		    }
		}
		
		/**
		 * 1. 자동 배정 알림
		 */
		private void sendAutoAssignNotification(ClaimVo claimVo) {
		    Map<String, Object> data = new HashMap<>();
		    data.put("claim_no", claimVo.getClaim_no());
		    data.put("claim_type", claimVo.getClaim_type());
		    data.put("emp_no", claimVo.getEmp_no());
		    
		    sendNotification("/api/notify-auto-assign", data);
		}
		
		/**
		 * 2. 결재 요청 알림 (관리자에게)
		 * Node.js 서버의 /api/notify-approval-request API를 호출
		 */
		private void sendApprovalRequestNotification(String claim_no, String manager_no, String requester_name) {
		    Map<String, Object> data = new HashMap<>();
		    data.put("claimNo", claim_no);
		    data.put("targetEmpNo", manager_no);       // 알림 받을 대상: 관리자
		    data.put("requesterName", requester_name); // 요청한 사람: 실무자
		    
		    // 공통 알림 전송 함수 호출
		    sendNotification("/api/notify-approval-request", data);
		}
		
		/**
		 * 3. 결재 결과 알림 (실무자에게)
		 */
		private void sendApprovalResultNotification(String claim_no, String emp_no, String approval_result, String approver_name) {
		    Map<String, Object> data = new HashMap<>();
		    data.put("claimNo", claim_no);
		    data.put("targetEmpNo", emp_no);
		    data.put("approvalResult", approval_result);
		    data.put("approverName", approver_name);
		    
		    sendNotification("/api/notify-approval-result", data);
		}
		
		
		/**
		 * 4. 고객 알림 (보완요청/반송/완료)
		 */
		private void sendCustomerNotification(String claim_no, String customer_id, String notification_type) {
		    Map<String, Object> data = new HashMap<>();
		    data.put("claim_no", claim_no);
		    data.put("customer_id", customer_id);
		    data.put("notification_type", notification_type);
		    
		    String message = "";
		    switch (notification_type) {
		        case "보완":
		            message = "📝 보완 자료를 제출해주세요.";
		            break;
		        case "반송":
		            message = "❌ 청구가 반송되었습니다.";
		            break;
		        case "완료":
		            message = "✅ 청구 처리가 완료되었습니다.";
		            break;
		    }
		    
		    data.put("message", message);
		    sendNotification("/api/notify-customer", data);
		}
		
		/**
		 * 5. 보완 완료 알림 (담당자에게)
		*/
		private void sendSupplementCompleteNotification(String claim_no, String emp_no, String customer_name) {
		    Map<String, Object> data = new HashMap<>();
		    data.put("claim_no", claim_no);
		    data.put("emp_no", emp_no);
		    data.put("customer_name", customer_name);
		    
		    sendNotification("/api/notify-supplement-complete", data);
		}
		
		/**
		 * 관리자 번호 조회 (간단 버전)
		 */
		private String getManagerByClaimNo(String claim_no) {
		    try {
		        return claimDAO.selectManagerNo(claim_no);
		    } catch (Exception e) {
		        System.err.println("관리자 조회 실패: " + e.getMessage());
		        return null;
		    }
		}
		
	/**
	 * 현재 로그인 사용자의 직원번호 조회
	 */
	private String getCurrentUserEmpNo() {
	    try {
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	        HttpServletRequest request = attr.getRequest();
	        HttpSession session = request.getSession();
	        
	        // 세션에서 직원번호 가져오기
	        Object empNoObj = session.getAttribute("empNo");
	        return empNoObj != null ? empNoObj.toString() : null;
	        
	    } catch (Exception e) {
	        System.err.println("현재 사용자 직원번호 조회 실패: " + e.getMessage());
	        return null;
	    }
	}
	
		/**
		 * 현재 로그인 사용자 이름 조회
		 */
		private String getCurrentUserName() {
		    try {
		        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		        HttpServletRequest request = attr.getRequest();
		        HttpSession session = request.getSession();
		        
		        Object empNameObj = session.getAttribute("empName");
		        return empNameObj != null ? empNameObj.toString() : "직원"; // 기본값
		        
		    } catch (Exception e) {
		        return "직원"; // 실패 시 기본값
		    }
		}
		
		/**
		 * 현재 로그인 사용자가 관리자인지 확인 (empNo 세 번째 자리로 판단)
		 */
		private boolean isCurrentUserManager() {
		    try {
		        String empNo = getCurrentUserEmpNo();
		        
		        if (empNo != null && empNo.length() >= 3) {
		            char thirdChar = empNo.charAt(2); // 세 번째 자리
		            return thirdChar == '2'; // '2'면 관리자
		        }
		        
		        return false; // 기본값: 실무자
		    } catch (Exception e) {
		        return false;
		    }
		}
		
		/**
		 * 현재 로그인 사용자가 실무자인지 확인 (empNo 세 번째 자리로 판단)
		 */
		private boolean isCurrentUserEmployee() {
		    try {
		        String empNo = getCurrentUserEmpNo();
		        
		        if (empNo != null && empNo.length() >= 3) {
		            char thirdChar = empNo.charAt(2); // 세 번째 자리
		            return thirdChar == '1'; // '1'이면 실무자
		        }
		        
		        return true; // 기본값: 실무자
		    } catch (Exception e) {
		        return true;
		    }
		}
		
		/**
		 * 현재 로그인 사용자의 역할 문자열 반환
		 */
		private String getCurrentUserRole() {
		    return isCurrentUserManager() ? "관리자" : "실무자";
		}
		
		
		/**
		 * 고객 이름 조회
		 */
		private String getCustomerName(String customer_id) {
		    try {
		        return claimDAO.selectCustomerNameById(customer_id);
		    } catch (Exception e) {
		        System.err.println("고객 이름 조회 실패: " + e.getMessage());
		        return "고객님"; // 실패 시 기본값
		    }
		}
				
		/**
		 * 🌟 새로운 상태 변경 알림 메서드 (핵심!)
		 */
		private void sendStatusChangeNotification(ClaimVo claimVo, String oldStatus, String newStatus) {
		    try {
		      // --- ▼ 1번 로그 ▼ ---
		        System.out.println("[Java 디버깅 1] sendStatusChangeNotification 메서드 실행됨. newStatus: " + newStatus +" , oldStatus" + oldStatus);
		
		        if (newStatus != null) {
		            // --- ▼ [핵심 수정] 공백 제거 ▼ ---
		            newStatus = newStatus.trim();
		        }
		
		        // 디버깅용 상세 로그 (공백이 제거되었는지 확인 가능)
		        System.out.println("[Java 디버깅 1] sendStatusChangeNotification 메서드 실행됨. newStatus: [" + newStatus + "]");
		
		        // oldStatus도 공백을 제거하고 비교해야 안전합니다.
		        if (oldStatus != null && oldStatus.trim().equals(newStatus)) {
		        	System.out.println("동일");
		             return;
		        }
		
		        String claim_no = claimVo.getClaim_no();
		        String currentUserName = getCurrentUserName(); 
		        
		        switch (newStatus) {
		            case "보완":
		                // 고객에게 보완 요청 알림
		                sendCustomerNotification(claim_no, claimVo.getID(), "보완");
		                break;


		            case "결재중":
		                // --- ▼ 2번 로그 ▼ ---
		                System.out.println("[Java 디버깅 2] '결재중' 케이스 진입. claim_no: " + claim_no);
		
		                String manager_no = getManagerByClaimNo(claim_no);
		
		                // --- ▼ 3번 로그 ▼ ---
		                System.out.println("[Java 디버깅 3] getManagerByClaimNo 결과: " + manager_no);
		
		                if (manager_no != null) {
		                    // --- ▼ 4번 로그 ▼ ---
		                    System.out.println("[Java 디버깅 4] 관리자를 찾았으므로 알림 전송을 시작합니다.");
		                    sendApprovalRequestNotification(claim_no, manager_no, currentUserName);
		                }
		                break;
		                
		            case "결재완료":
		            case "결재반려":
		            	 // --- ▼ [디버깅 로그 추가] claimVo 객체의 전체 내용을 확인합니다. ▼ ---
		            	System.out.println("[디버깅] 결재완료/반려 시점의 claimVo 데이터: " + claimVo.toString());
		            	
		            			         
		                // 결재를 신청했던 실무자에게 결과(완료/반려)를 알립니다.
		                String approver_name = getCurrentUserName(); // 현재 사용자는 관리자
		                String result = "결재완료".equals(newStatus) ? "완료" : "반려";
				        
				        sendApprovalResultNotification(claimVo.getClaim_no(), claimVo.getEmp_no(), result, approver_name);
				        break;
						                
		            case "반송":
		                // 고객에게 반송 알림
		                sendCustomerNotification(claim_no, claimVo.getID(), "반송");
		                break;
		                
		            case "완료":
		                // 고객에게 완료 알림
		                sendCustomerNotification(claim_no, claimVo.getID(), "완료");
		                break;
		                
		            case "보완완료":
		                // 담당자에게 보완 완료 알림
		                String customer_name = getCustomerName(claimVo.getID());
		                sendSupplementCompleteNotification(claim_no, claimVo.getEmp_no(), customer_name);
		                break;
		                
		            default:
		                // 다른 상태는 알림 전송하지 않음
		                System.out.println("알림 대상이 아닌 상태: " + newStatus);
		                return;
		         }

		         System.out.println("✅ 상태 변경 알림 전송 (" + getCurrentUserRole() + ": " + currentUserName + "): " + 
                          claim_no + " (" + oldStatus + " → " + newStatus + ")");

		    } catch (Exception e) {
		    
		        System.err.println("❌ 상태 변경 알림 전송 실패: " + e.getMessage());
		         e.printStackTrace();
		    }
		}
				
		


	/**
	 * 청구를 삭제 처리 한다.
	 *
	 * @process 1. 청구를 삭제 처리 한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.deleteClaim(claimVo);
	}

	/**
	 * 청구 정보와 첨부파일 정보를 DB에 최종 저장 생성자: 이지현
	 */

	@Resource(name = "insimagefileDAO")
	private InsimagefileDAO insimagefileDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveFinalClaim(Map<String, Object> claimData) throws Exception {
		// 1. CLAIM 테이블 저장을 위한 데이터 준비 Map -> ClaimVo변환
		ClaimVo claimVo = new ClaimVo();
		claimVo.setClaim_no((String) claimData.get("claimNo"));
		claimVo.setClaim_type((String) claimData.get("claimType"));
		claimVo.setClaim_content((String) claimData.get("claimContent"));
		claimVo.setID(String.valueOf(claimData.get("userId")));
		claimVo.setStatus("대기");
		SimpleDateFormat receiptFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String receiptDateStr = receiptFormatter.format(new Date());
		claimVo.setReceipt_date(receiptDateStr);

		String accidentDateStr = (String) claimData.get("accidentDate");
		claimVo.setDate_of_accident(accidentDateStr);

		// 자동 생성된 claimDao.insertClaim 메소드 호출
//		claimDAO.insertClaim(claimVo);
		int insertResult = this.insertClaim(claimVo);

		if (insertResult <= 0) {	
			throw new Exception("청구 정보 저장에 실패했습니다.");
		}
		
		System.out.println("CLAIM 테이블 저장 완료: " + claimVo.getClaim_no());

		// --- 2. INS_IMAGE_FILE 테이블 저장을 위한 데이터 준비 ---
		String claimNo = (String) claimData.get("claimNo");
		String s3fileKeys = (String) claimData.get("s3fileKeys");
		String[] keysArray = s3fileKeys.split(",");

		for (String key : keysArray) {
			InsimagefileVo imageVo = new InsimagefileVo();
			imageVo.setClaim_no(claimNo);
			imageVo.setFile_path(key); // S3 오브젝트 키를 file_path에 저장

			// 자동 생성된 insimagefileDao.insertInsimagefile 메소드 호출
			insimagefileDao.insertInsimagefile(imageVo);
		}
		System.out.println("INS_IMAGE_FILE 테이블에 " + keysArray.length + "건 저장 완료.");
	}

	public ClaimUserEmpVo findUsernameAndEmpNameByClaimNo(ClaimNoVo claimVo) throws Exception {
		try {
			System.out.println("[SERVICE DEBUG] 처리 시작 - claimVo: " + claimVo.toString());
			ClaimUserEmpVo result = claimDAO.findUsernameAndEmpNameByClaimNo(claimVo);
			System.out.println("[SERVICE DEBUG] DAO 결과: " + (result != null ? result.toString() : "null"));
			return result;
		} catch (Exception e) {
			System.err.println("[SERVICE ERROR] findUsernameAndEmpNameByClaimNo 오류: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 청구와 직원 정보 조인 목록 조회
	 *
	 * @process 1. 청구와 직원 정보를 조인하여 목록을 조회한다. 2. 결과 List<ClaimEmployeeVo>을(를) 리턴한다.
	 * 
	 * @param claimEmployeeVo 청구-직원 VO
	 * @return 청구-직원 목록 List<ClaimEmployeeVo>
	 * @throws Exception
	 */
	public List<ClaimEmployeeVo> selectClaimEmployeeList(ClaimEmployeeVo claimEmployeeVo) throws Exception {
		List<ClaimEmployeeVo> list = claimDAO.selectClaimEmployeeList(claimEmployeeVo);
		return list;
	}

	/**
	 * 청구와 직원 정보 조인 목록 카운트 조회
	 *
	 * @process 1. 청구와 직원 정보를 조인하여 카운트를 리턴한다.
	 * 
	 * @param claimEmployeeVo 청구-직원 VO
	 * @return 청구-직원 목록 카운트
	 * @throws Exception
	 */
	public long selectClaimEmployeeListCount(ClaimEmployeeVo claimEmployeeVo) throws Exception {
		return claimDAO.selectClaimEmployeeListCount(claimEmployeeVo);
	}

	/**
	 * 청구 담당자를 업데이트한다.
	 *
	 * @process 1. 청구 존재 여부를 확인한다. 2. 담당자 정보를 업데이트한다.
	 * 
	 * @param claimVo 청구 정보
	 * @return 업데이트 결과
	 * @throws Exception
	 */
	public int updateClaimAssignee(ClaimVo claimVo) throws Exception {
		// 청구 존재 여부 확인
		ClaimVo existingClaim = claimDAO.selectClaim(claimVo);
		if (existingClaim == null) {
			throw new Exception("해당 청구를 찾을 수 없습니다.");
		}

		// 담당자 업데이트
		return claimDAO.updateClaim(claimVo);
	}

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 조회
	 *
	 * @process 1. 청구, 사용자, 직원, 결과 정보를 전체 조인하여 목록을 조회한다. 2. 결과
	 *          List<ClaimFullJoinVo>을(를) 리턴한다.
	 * 
	 * @param claimFullJoinVo 청구-전체조인 VO
	 * @return 청구-전체조인 목록 List<ClaimFullJoinVo>
	 * @throws Exception
	 */
	public List<ClaimFullJoinVo> selectClaimFullJoinList(ClaimFullJoinVo claimFullJoinVo) throws Exception {
		List<ClaimFullJoinVo> list = claimDAO.selectClaimFullJoinList(claimFullJoinVo);
		return list;
	}

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 카운트 조회
	 *
	 * @process 1. 청구, 사용자, 직원, 결과 정보를 전체 조인하여 카운트를 리턴한다.
	 * 
	 * @param claimFullJoinVo 청구-전체조인 VO
	 * @return 청구-전체조인 목록 카운트
	 * @throws Exception
	 */
	public long selectClaimFullJoinListCount(ClaimFullJoinVo claimFullJoinVo) throws Exception {
		return claimDAO.selectClaimFullJoinListCount(claimFullJoinVo);
	}



	/**
	 * 사용자 주민번호로 청구목록 조회 (사용자, 직원, 결과 정보 조인)
	 *
	 * @param claimFullJoinVo 청구-전체조인 VO (주민번호 포함)
	 * @return 사용자의 청구목록
	 * @throws Exception
	 */
	@Override
	public List<ClaimFullJoinVo> selectUserClaimsByRrn(ClaimFullJoinVo claimFullJoinVo) throws Exception {
	    List<ClaimFullJoinVo> list = claimDAO.selectUserClaimsByRrn(claimFullJoinVo);
	    return list;
	}
	/**
	 * OCR 분석 결과로 기존 청구건 업데이트
	 * OCR 서비스가 이미 분석을 완료했으므로, 단순히 DB 업데이트만 수행
	 * 
	 * @param claimNo 청구번호
	 * @param analyzedClaimTypeKor OCR 분석된 청구타입 (한글)
	 * @param claimContent 청구내용
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void updateClaimWithOcrResult(String claimNo, String analyzedClaimTypeKor, String claimContent) throws Exception {
    try {
        System.out.println("[OCR 결과 DB 업데이트] 청구번호: " + claimNo + ", 분석된 타입: " + analyzedClaimTypeKor);
        
        // 업데이트용 ClaimVo 생성
        ClaimVo updateVo = new ClaimVo();
        updateVo.setClaim_no(claimNo);
        updateVo.setClaim_type(analyzedClaimTypeKor);
        updateVo.setClaim_content(claimContent);
        
        // DB 업데이트 수행 (기존 updateClaim 메서드 활용)
        int updateResult = claimDAO.updateClaim(updateVo);
        
        if (updateResult <= 0) {
            throw new Exception("OCR 결과 DB 업데이트에 실패했습니다. 청구번호: " + claimNo);
        }
        
        System.out.println("[OCR 결과 DB 업데이트 완료] 청구번호: " + claimNo + ", 최종 타입: " + analyzedClaimTypeKor);
        
    } catch (Exception e) {
        System.err.println("[OCR 결과 DB 업데이트 실패] 청구번호: " + claimNo + ", 오류: " + e.getMessage());
        e.printStackTrace();
        throw e; // RuntimeException 대신 원본 Exception 전달
        }
        }
	
	/**
	 * 청구의 기존 배정을 해제한다 (emp_no를 null로 설정)
	 * 
	 * @param claimNo 청구번호
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void clearClaimAssignment(String claimNo) throws Exception {
	    try {
	        System.out.println("[배정 해제 시작] 청구번호: " + claimNo);
	        
	        // 배정 해제용 ClaimVo 생성
	        ClaimVo clearVo = new ClaimVo();
	        clearVo.setClaim_no(claimNo);
	        clearVo.setEmp_no(null); // 담당자를 null로 설정
	        
	        // DB 업데이트 수행
	        int updateResult = claimDAO.updateClaim(clearVo);
	        
	        if (updateResult <= 0) {
	            throw new Exception("배정 해제에 실패했습니다. 청구번호: " + claimNo);
	        }
	        
	        System.out.println("[배정 해제 완료] 청구번호: " + claimNo);
	        
	        // 트랜잭션 즐시 커밋 및 대기
	        try {
	            Thread.sleep(200); // 200ms 대기로 트랜잭션 커밋 보장
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	        
	    } catch (Exception e) {
	        System.err.println("[배정 해제 실패] 청구번호: " + claimNo + ", 오류: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	/**
	 * 배정 해제를 별도 트랜잭션으로 수행 (강제 커밋)
	 * 
	 * @param claimNo 청구번호
	 * @throws Exception
	 */
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public void clearClaimAssignmentForced(String claimNo) throws Exception {
	    try {
	        System.out.println("🔥 [강제 배정 해제 시작] 청구번호: " + claimNo);
	        
	        // 배정 해제용 ClaimVo 생성
	        ClaimVo clearVo = new ClaimVo();
	        clearVo.setClaim_no(claimNo);
	        clearVo.setEmp_no(null); // 담당자를 null로 설정
	        
	        // DB 업데이트 수행
	        int updateResult = claimDAO.updateClaim(clearVo);
	        
	        if (updateResult <= 0) {
	            throw new Exception("강제 배정 해제에 실패했습니다. 청구번호: " + claimNo);
	        }
	        
	        System.out.println("🔥 [강제 배정 해제 완료] 청구번호: " + claimNo);
	        
	    } catch (Exception e) {
	        System.err.println("🔥 [강제 배정 해제 실패] 청구번호: " + claimNo + ", 오류: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
	}

	/**
	 * 사용자의 CLAIM 상태별 갯수를 조회한다.
	 *
	 * @process 1. 청구 상태별 갯수를 조회한다. 2. 결과 ClaimStatusCountVo을(를) 리턴한다.
	 * 
	 * @param claimVo 청구 정보 (ID 포함)
	 * @return ClaimStatusCountVo 상태별 갯수 정보
	 * @throws Exception
	 */
	public ClaimStatusCountVo selectClaimStatusCount(ClaimVo claimVo) throws Exception {
		ClaimStatusCountVo resultVO = claimDAO.selectClaimStatusCount(claimVo);
		return resultVO;
	}
}

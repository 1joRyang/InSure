package com.demo.proworks.assignrule.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.vo.EmployeeAssignRuleVo;
import com.demo.proworks.assignrule.dao.AssignRuleDAO;
import com.demo.proworks.claim.dao.ClaimDAO;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.employee.dao.EmployeeDAO;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.insdept.dao.InsDeptDAO;
import com.demo.proworks.insdept.vo.InsDeptVo;

/**
 * @subject : 배정규칙 관련 처리를 담당하는 ServiceImpl
 * @description : 배정규칙 관련 처리를 담당하는 ServiceImpl
 * @author : hyunwoo
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 hyunwoo 최초 생성
 * 
 */
@Service("assignRuleServiceImpl")
public class AssignRuleServiceImpl implements AssignRuleService {

	@Resource(name = "assignRuleDAO")
	private AssignRuleDAO assignRuleDAO;

	@Resource(name = "claimDAO")
	private ClaimDAO claimDAO;

	@Resource(name = "employeeDAO")
	private EmployeeDAO employeeDAO;

	@Resource(name = "insDeptDAO")
	private InsDeptDAO insDeptDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 배정규칙 목록을 조회합니다.
	 *
	 * @process 1. 배정규칙 페이징 처리하여 목록을 조회한다. 2. 결과 List<AssignRuleVo>을(를) 리턴한다.
	 * 
	 * @param assignRuleVo 배정규칙 AssignRuleVo
	 * @return 배정규칙 목록 List<AssignRuleVo>
	 * @throws Exception
	 */
	public List<AssignRuleVo> selectListAssignRule(AssignRuleVo assignRuleVo) throws Exception {
		List<AssignRuleVo> list = assignRuleDAO.selectListAssignRule(assignRuleVo);

		return list;
	}

	/**
	 * 조회한 배정규칙 전체 카운트
	 *
	 * @process 1. 배정규칙 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param assignRuleVo 배정규칙 AssignRuleVo
	 * @return 배정규칙 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountAssignRule(AssignRuleVo assignRuleVo) throws Exception {
		return assignRuleDAO.selectListCountAssignRule(assignRuleVo);
	}

	/**
	 * 배정규칙를 상세 조회한다.
	 *
	 * @process 1. 배정규칙를 상세 조회한다. 2. 결과 AssignRuleVo을(를) 리턴한다.
	 * 
	 * @param assignRuleVo 배정규칙 AssignRuleVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public AssignRuleVo selectAssignRule(AssignRuleVo assignRuleVo) throws Exception {
		AssignRuleVo resultVO = assignRuleDAO.selectAssignRule(assignRuleVo);

		return resultVO;
	}

	/**
	 * 배정규칙를 등록 처리 한다.
	 *
	 * @process 1. 배정규칙를 등록 처리 한다.
	 * 
	 * @param assignRuleVo 배정규칙 AssignRuleVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertAssignRule(AssignRuleVo assignRuleVo) throws Exception {
		return assignRuleDAO.insertAssignRule(assignRuleVo);
	}

	/**
	 * 배정규칙를 갱신 처리 한다.
	 *
	 * @process 1. 배정규칙를 갱신 처리 한다.
	 * 
	 * @param assignRuleVo 배정규칙 AssignRuleVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateAssignRule(AssignRuleVo assignRuleVo) throws Exception {
		return assignRuleDAO.updateAssignRule(assignRuleVo);
	}

	/**
	 * 배정규칙를 삭제 처리 한다.
	 *
	 * @process 1. 배정규칙를 삭제 처리 한다.
	 * 
	 * @param assignRuleVo 배정규칙 AssignRuleVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteAssignRule(AssignRuleVo assignRuleVo) throws Exception {
		return assignRuleDAO.deleteAssignRule(assignRuleVo);
	}

	/**
	 * 키워드로 부서명을 찾고 부서 ID를 반환한다.
	 *
	 * @process 1. 키워드로 ASSIGN_RULE에서 부서명을 찾는다. 2. 부서명으로 INS_DEPT에서 부서 ID를 찾는다.
	 * 
	 * @param keyword 검색 키워드
	 * @return 부서 ID (문자열)
	 * @throws Exception
	 */
	public String findDeptIdByKeyword(String keyword) throws Exception {
		try {
			// 1. 키워드로 배정규칙에서 부서명 찾기
			AssignRuleVo assignRuleVo = new AssignRuleVo();
			assignRuleVo.setKeyword(keyword);

			// 키워드 매칭을 위한 조회 (LIKE 검색을 위해 DAO에서 처리)
			List<AssignRuleVo> assignRuleList = assignRuleDAO.selectListAssignRule(assignRuleVo);

			if (assignRuleList == null || assignRuleList.isEmpty()) {
				throw new Exception("해당 키워드에 대한 배정규칙을 찾을 수 없습니다: " + keyword);
			}

			// 첫 번째 매칭된 규칙의 부서명 사용
			String deptName = assignRuleList.get(0).getDept();

			// 2. 부서명으로 INS_DEPT에서 부서 ID 찾기
			InsDeptVo insDeptVo = new InsDeptVo();
			insDeptVo.setDept_name(deptName);

			List<InsDeptVo> deptList = insDeptDAO.selectListInsDept(insDeptVo);

			if (deptList == null || deptList.isEmpty()) {
				throw new Exception("해당 부서명에 대한 부서 정보를 찾을 수 없습니다: " + deptName);
			}

			return deptList.get(0).getDept_id();

		} catch (Exception e) {
			throw new Exception("키워드로 부서 ID 조회 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 부서 ID로 해당 부서의 실무자(직원)를 찾는다.
	 *
	 * @process 1. 부서 ID로 EMPLOYEE 테이블에서 직원 목록을 조회한다. 2. 활성 상태의 직원 중 첫 번째 직원을 반환한다.
	 * 
	 * @param deptId 부서 ID
	 * @return 직원 번호 (정수형을 문자열로 변환)
	 * @throws Exception
	 */
	public String findEmployeeByDeptId(String deptId) throws Exception {
		try {
			EmployeeVo employeeVo = new EmployeeVo();
			employeeVo.setDeptId(deptId);
			employeeVo.setStatus("재직중");
			employeeVo.setRole("실무자");

			List<EmployeeVo> empList = employeeDAO.selectListEmployeeForRule(employeeVo);

			if (empList == null || empList.isEmpty()) {
				throw new Exception("해당 부서에 '재직중' 상태의 '실무자' 직원이 없습니다. 부서 ID: " + deptId);
			}

			return String.valueOf(empList.get(0).getEmpNo());

		} catch (Exception e) {
			throw new Exception("부서 ID로 직원 조회 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 청구를 실무자에게 배정한다. (개선된 버전)
	 *
	 * @process "청구 유형을 기반으로 부서명을 찾아서, 그 부서ID의 재직중인 직원만 조회해 자동 배정한다." 1. 청구서 배정 정보
	 *          종합 조회 (한 번의 쿼리로 모든 정보 획득) 2. 해당 부서의 재직중인 직원 수 확인 3. 라운드로빈 방식으로 다음
	 *          배정할 직원 선택 4. 청구서에 직원 배정
	 * 
	 * @param claimNo 청구 번호
	 * @return 배정 결과 메시지
	 * @throws Exception
	 */
	@Transactional
	public String assignEmployeeToClaim(String claimNo) throws Exception {

		try {
			Map<String, Object> assignmentInfo = assignRuleDAO.selectClaimAssignmentInfo(claimNo);

			if (assignmentInfo == null) {
				throw new Exception("청구서 정보를 찾을 수 없습니다: " + claimNo);
			}


			String claimType = (String) assignmentInfo.get("CLAIM_TYPE");
			Object currentEmpNoObj = assignmentInfo.get("CURRENT_EMP_NO");
			String currentEmpNo = null;
			if (currentEmpNoObj != null) {
				// 모든 타입에 대해 안전하게 처리
				if (currentEmpNoObj instanceof Long) {
					currentEmpNo = String.valueOf((Long) currentEmpNoObj);
				} else if (currentEmpNoObj instanceof Integer) {
					currentEmpNo = String.valueOf((Integer) currentEmpNoObj);
				} else if (currentEmpNoObj instanceof String) {
					currentEmpNo = (String) currentEmpNoObj;
				} else {
					currentEmpNo = String.valueOf(currentEmpNoObj);
				}
			}
			String assignDeptName = (String) assignmentInfo.get("ASSIGN_DEPT_NAME");
			Object targetDeptIdObj = assignmentInfo.get("TARGET_DEPT_ID");
			String targetDeptIdStr = null;
			if (targetDeptIdObj != null) {
				if (targetDeptIdObj instanceof Long) {
					targetDeptIdStr = String.valueOf((Long) targetDeptIdObj);
				} else if (targetDeptIdObj instanceof Integer) {
					targetDeptIdStr = String.valueOf((Integer) targetDeptIdObj);
				} else if (targetDeptIdObj instanceof String) {
					targetDeptIdStr = (String) targetDeptIdObj;
				} else {
					targetDeptIdStr = String.valueOf(targetDeptIdObj);
				}
			}


			if (currentEmpNo != null && !currentEmpNo.trim().isEmpty() &&
				!"null".equals(currentEmpNo.toLowerCase()) && !"-1".equals(currentEmpNo) && !"0".equals(currentEmpNo)) {
			}

			if (assignDeptName == null || targetDeptIdStr == null) {
				String error = "청구 유형 '" + claimType + "'에 대한 배정 규칙을 찾을 수 없습니다.";
				System.err.println("[ERROR] " + error);
				throw new Exception(error);
			}


			int employeeCount = 0;
			try {
				employeeCount = assignRuleDAO.selectDeptEmployeeCount(targetDeptIdStr);
			} catch (Exception e) {
				e.printStackTrace();
				employeeCount = 0;
			}

			if (employeeCount == 0) {
				String error = "부서 '" + assignDeptName + "'(ID: " + targetDeptIdStr + ")에 재직중인 실무자 직원이 없습니다.";
				System.err.println("[ERROR] " + error);
				throw new Exception(error);
			}

			EmployeeAssignRuleVo assignedEmployee = selectNextEmployeeRoundRobin(targetDeptIdStr);

			if (assignedEmployee == null) {
				throw new Exception("배정 가능한 직원을 찾을 수 없습니다.");
			}

			String empNo = String.valueOf(assignedEmployee.getEmpNo());

			Map<String, Object> updateParams = new HashMap<String, Object>();
			updateParams.put("claimNo", claimNo);
			updateParams.put("empNo", empNo);


			int updateResult = assignRuleDAO.updateClaimAssignment(updateParams);
			if (updateResult > 0) {
            String resultMessage = String.format("자동 배정 완료 - 청구번호: %s, 청구유형: %s, 담당부서: %s, 담당자: %s(%s)", claimNo,
                    claimType, assignDeptName, assignedEmployee.getEmpName(), empNo);

            sendWebSocketAutoAssignNotification(claimNo, empNo, resultMessage, claimType, assignDeptName);

            return resultMessage;
			} else {
				throw new Exception("청구 업데이트에 실패했습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("청구 배정 중 오류 발생: " + e.getMessage(), e);
		}
		 
	}

	/**
	 * 
	 * @param deptId 부서 ID
	 * @return 다음 배정할 직원 정보
	 */
	private EmployeeAssignRuleVo selectNextEmployeeRoundRobin(String deptId) throws Exception {
		try {
			// 1. 마지막 배정된 직원 번호 조회
			Integer lastEmpNo = employeeDAO.selectLastAssignedEmployeeInDept(deptId);

			// 2. 다음 직원 조회
			EmployeeAssignRuleVo searchVo = new EmployeeAssignRuleVo();
			searchVo.setDeptId(deptId);
			if (lastEmpNo != null) {
				searchVo.setLastEmpNo(lastEmpNo.toString());
			}

			EmployeeAssignRuleVo nextEmployee = employeeDAO.selectNextEmployeeForAssignment(searchVo);

			// 3. 다음 직원이 없으면 처음부터 다시 시자
			if (nextEmployee == null) {
				nextEmployee = employeeDAO.selectFirstEmployeeInDept(deptId);
			}

			return nextEmployee;

		} catch (Exception e) {
			throw new Exception(" 직원 선택 중 오류: " + e.getMessage(), e);
		}
	}

	/**
	 * 여러 청구를 한번에 자동 배정한다.
	 *
	 * @process 1. 미배정 청구 목록을 조회한다. 2. 각 청구에 대해 claim_type 기반 자동 배정을 수행한다.
	 * 
	 * @return 배정 결과 목록
	 * @throws Exception
	 */
	@Transactional
	public List<String> assignAllUnassignedClaims() throws Exception {
		try {
			List<ClaimVo> unassignedClaims = assignRuleDAO.selectUnassignedClaims();

			List<String> results = new java.util.ArrayList<String>();

			for (ClaimVo claim : unassignedClaims) {
				if (claim.getEmp_no() != null && !claim.getEmp_no().trim().isEmpty()) {
					continue;
				}

				try {
					String result = assignEmployeeToClaim(claim.getClaim_no());
					results.add(result);
				} catch (Exception e) {
					results.add("배정 실패 - 청구번호: " + claim.getClaim_no() + ", 청구유형: " + claim.getClaim_type() + ", 오류: "
							+ e.getMessage());
				}
			}

			return results;

		} catch (Exception e) {
			throw new Exception("일괄 청구 배정 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 특정 키워드에 대한 배정 가능한 직원 목록을 조회한다.
	 *
	 * @process 1. 키워드로 부서를 찾는다. 2. 해당 부서의 모든 직원을 조회한다.
	 * 
	 * @param keyword 검색 키워드
	 * @return 배정 가능한 직원 목록
	 * @throws Exception
	 */
	public List<EmployeeVo> getAvailableEmployeesByKeyword(String keyword) throws Exception {
		try {
			// 키워드로 부서 ID 찾기
			String deptId = findDeptIdByKeyword(keyword);

			// 해당 부서의 모든 직원 조회
			EmployeeVo employeeVo = new EmployeeVo();
			employeeVo.setDeptId(deptId);

			return employeeDAO.selectListEmployee(employeeVo);

		} catch (Exception e) {
			throw new Exception("키워드별 배정 가능 직원 조회 중 오류 발생: " + e.getMessage(), e);
		}
	}






	
	
	/**
	 * 자동 배정 웹소켓 알림을 비동기로 전송합니다.
	 * 배정 로직의 성능에 영향을 주지 않기 위해 비동기로 처리합니다.
	 * @param claimNo 청구 번호
	 * @param assignedEmpNo 배정된 직원 번호
	 * @param assignResult 배정 결과 메시지
	 * @param claimType 청구 유형
	 * @param assignDeptName 배정 부서명
	 */
	private void sendWebSocketAutoAssignNotification(String claimNo, String assignedEmpNo, String assignResult, String claimType, String assignDeptName) {
	    CompletableFuture.runAsync(() -> {
	        try {
	            System.out.println("[WEBSOCKET] 자동 배정 알림 전송 시작: " + claimNo + " -> " + assignedEmpNo);
	
	            RestTemplate restTemplate = new RestTemplate();
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	
	            // Node.js 서버로 보낼 데이터 구성 (server.js의 /api/notify-auto-assign API와 일치)
	            Map<String, Object> notificationData = new HashMap<>();
	            notificationData.put("claimNo", claimNo);
	            notificationData.put("claimType", claimType);
	            notificationData.put("assignedEmpNo", assignedEmpNo);
	            notificationData.put("assignDept", assignDeptName);
	            // 필요에 따라 추가 정보 전송
	            notificationData.put("claimContent", "새로운 실손보험 청구가 배정되었습니다.");
	            notificationData.put("priority", "NORMAL");
	
	            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(notificationData, headers);
	
	            // 웹소켓 서버 API 호출 (server.js가 실행 중인 포트)
	            String webSocketApiUrl = "http://localhost:3000/api/notify-auto-assign";
	            Map<String, Object> response = restTemplate.postForObject(webSocketApiUrl, entity, Map.class);
	
	            if (response != null && Boolean.TRUE.equals(response.get("success"))) {
	                System.out.println("[WEBSOCKET] ✅ 자동 배정 알림 전송 성공: " + claimNo + " -> " + assignedEmpNo);
	            } else {
	                System.err.println("[WEBSOCKET] ⚠️ 자동 배정 알림 전송 실패: " + (response != null ? response.get("message") : "응답 없음"));
	            }
	
	        } catch (Exception e) {
	            System.err.println("[WEBSOCKET] ⚠️ 자동 배정 알림 전송 중 오류 (배정은 정상 완료됨): " + e.getMessage());
	        }
	    });
	}

	/**
	 * 🔔 웹소켓 일괄 배정 완료 알림 전송
	 */
	private void sendWebSocketBatchCompleteNotification(int totalProcessed, int successCount, int failCount) {
	    CompletableFuture.runAsync(() -> {
	        try {
	            System.out.println("[WEBSOCKET] 일괄 배정 완료 알림 전송 시작: 총 " + totalProcessed + "건");
	            
	            RestTemplate restTemplate = new RestTemplate();
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            
	            Map<String, Object> batchData = new HashMap<>();
	            batchData.put("totalProcessed", totalProcessed);
	            batchData.put("successCount", successCount);
	            batchData.put("failCount", failCount);
	            batchData.put("processedClaims", new ArrayList<>()); // 필요시 실제 처리 목록 추가
	            
	            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(batchData, headers);
	            
	            Map<String, Object> response = restTemplate.postForObject(
	                "http://localhost:3000/api/notify-batch-complete", 
	                entity, 
	                Map.class
	            );
	            
	            System.out.println("[WEBSOCKET] ✅ 일괄 배정 완료 알림 전송: " + totalProcessed + "건 처리 완료");
	            
	        } catch (Exception e) {
	            System.out.println("[WEBSOCKET] ⚠️ 일괄 배정 알림 전송 실패: " + e.getMessage());
	        }
	    });
	}
}
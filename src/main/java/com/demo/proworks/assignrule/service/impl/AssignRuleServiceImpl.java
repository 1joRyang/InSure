package com.demo.proworks.assignrule.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			employeeVo.setStatus("재직중"); // 상태 조건 추가
			employeeVo.setRole("실무자"); // 역할 조건 추가

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
	 * 🔥 청구를 실무자에게 배정한다. (개선된 버전)
	 *
	 * @process "청구 유형을 기반으로 부서명을 찾아서, 그 부서ID의 재직중인 직원만 조회해 자동 배정한다."
	 *          1. 청구서 배정 정보 종합 조회 (한 번의 쿼리로 모든 정보 획득)
	 *          2. 해당 부서의 재직중인 직원 수 확인
	 *          3. 라운드로빈 방식으로 다음 배정할 직원 선택
	 *          4. 청구서에 직원 배정
	 * 
	 * @param claimNo 청구 번호
	 * @return 배정 결과 메시지
	 * @throws Exception
	 */
	@Transactional
	public String assignEmployeeToClaim(String claimNo) throws Exception {
		try {
			System.out.println("[DEBUG] 🔥 개선된 자동 배정 시작 - 청구번호: " + claimNo);

			// ✅ ① 청구서 배정 정보 종합 조회 (한 번의 쿼리로 모든 정보 획득)
			Map<String, Object> assignmentInfo = assignRuleDAO.selectClaimAssignmentInfo(claimNo);
			
			if (assignmentInfo == null) {
				throw new Exception("청구서 정보를 찾을 수 없습니다: " + claimNo);
			}

			String claimType = (String) assignmentInfo.get("CLAIM_TYPE");
			String currentEmpNo = (String) assignmentInfo.get("CURRENT_EMP_NO");
			String assignDeptName = (String) assignmentInfo.get("ASSIGN_DEPT_NAME");
			// 🔥 Long에서 Integer로 안전하게 변환
			Object targetDeptIdObj = assignmentInfo.get("TARGET_DEPT_ID");
			Integer targetDeptId = null;
			if (targetDeptIdObj != null) {
				if (targetDeptIdObj instanceof Long) {
					targetDeptId = ((Long) targetDeptIdObj).intValue();
				} else if (targetDeptIdObj instanceof Integer) {
					targetDeptId = (Integer) targetDeptIdObj;
				} else if (targetDeptIdObj instanceof String) {
					try {
						targetDeptId = Integer.parseInt((String) targetDeptIdObj);
					} catch (NumberFormatException e) {
						// String이 숫자가 아닌 경우
					}
				}
			}

			System.out.println(String.format("[DEBUG] 종합 정보 - 청구유형: %s, 현재담당자: %s, 대상부서: %s, 부서ID: %s", 
					claimType, currentEmpNo, assignDeptName, targetDeptId));

			// 이미 배정된 청구서 체크
			if (currentEmpNo != null && !currentEmpNo.trim().isEmpty()) {
				return "이미 배정된 청구서입니다. 청구번호: " + claimNo + ", 담당자: " + currentEmpNo;
			}

			// 배정 규칙이 없는 경우
			if (assignDeptName == null || targetDeptId == null) {
				throw new Exception("청구 유형 '" + claimType + "'에 대한 배정 규칙을 찾을 수 없습니다.");
			}

			// ✅ ② 해당 부서의 재직중인 직원 수 확인
			int employeeCount = 0;
			try {
				employeeCount = assignRuleDAO.selectDeptEmployeeCount(targetDeptId.toString());
			} catch (Exception e) {
				System.err.println("[ERROR] 직원 수 조회 오류: " + e.getMessage());
				employeeCount = 0;
			}
			
			if (employeeCount == 0) {
				throw new Exception("부서 '" + assignDeptName + "'에 재직중인 실무자 직원이 없습니다.");
			}

			System.out.println("[DEBUG] 부서 " + assignDeptName + "(ID: " + targetDeptId + ")에 재직중인 직원 수: " + employeeCount);

			// ✅ ③ 라운드로빈 방식으로 다음 배정할 직원 선택
			EmployeeAssignRuleVo assignedEmployee = selectNextEmployeeRoundRobin(targetDeptId.toString());
			
			if (assignedEmployee == null) {
				throw new Exception("배정 가능한 직원을 찾을 수 없습니다.");
			}

			String empNo = String.valueOf(assignedEmployee.getEmpNo());
			System.out.println("[DEBUG] 선택된 직원: " + assignedEmployee.getEmpName() + "(" + empNo + ")");

			// ✅ ④ 청구서에 직원 배정
			Map<String, Object> updateParams = new HashMap<String, Object>();
			updateParams.put("claimNo", claimNo);
			updateParams.put("empNo", empNo);
			
			int updateResult = assignRuleDAO.updateClaimAssignment(updateParams);

			if (updateResult > 0) {
				String result = String.format("🔥 자동 배정 완료 - 청구번호: %s, 청구유형: %s, 담당부서: %s, 담당자: %s(%s)", 
						claimNo, claimType, assignDeptName, assignedEmployee.getEmpName(), empNo);
				System.out.println("[DEBUG] " + result);
				return result;
			} else {
				throw new Exception("청구 업데이트에 실패했습니다.");
			}

		} catch (Exception e) {
			System.err.println("[ERROR] 🔥 자동 배정 실패 - 청구번호: " + claimNo + ", 오류: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("청구 배정 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 🔥 라운드로빈 방식으로 다음 배정할 직원 선택
	 * 
	 * @param deptId 부서 ID
	 * @return 다음 배정할 직원 정보
	 */
	private EmployeeAssignRuleVo selectNextEmployeeRoundRobin(String deptId) throws Exception {
		try {
			// 1. 마지막 배정된 직원 번호 조회
			Integer lastEmpNo = employeeDAO.selectLastAssignedEmployeeInDept(deptId);
			System.out.println("[DEBUG] 부서 " + deptId + "의 마지막 배정 직원: " + lastEmpNo);
			
			// 2. 다음 직원 조회
			EmployeeAssignRuleVo searchVo = new EmployeeAssignRuleVo();
			searchVo.setDeptId(deptId);
			if (lastEmpNo != null) {
				searchVo.setLastEmpNo(lastEmpNo.toString());
			}
			
			EmployeeAssignRuleVo nextEmployee = employeeDAO.selectNextEmployeeForAssignment(searchVo);
			
			// 3. 다음 직원이 없으면 처음부터 다시 시작 (라운드로빈)
			if (nextEmployee == null) {
				nextEmployee = employeeDAO.selectFirstEmployeeInDept(deptId);
				System.out.println("[DEBUG] 라운드로빈 순환: 부서의 첫 번째 직원으로 이동");
			}
			
			return nextEmployee;
			
		} catch (Exception e) {
			throw new Exception("라운드로빈 직원 선택 중 오류: " + e.getMessage(), e);
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
			// 미배정 청구 조회 (emp_no가 null이거나 빈값인 청구들)
			List<ClaimVo> unassignedClaims = assignRuleDAO.selectUnassignedClaims();

			List<String> results = new java.util.ArrayList<String>();

			for (ClaimVo claim : unassignedClaims) {
				// 이미 배정된 청구는 건너뛰기
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
	 * 자동 배정 설정을 업데이트한다.
	 *
	 * @process 1. 자동 배정 설정을 데이터베이스 또는 설정 파일에 저장
	 * 
	 * @param autoAssignEnabled 자동 배정 활성화 여부
	 * @throws Exception
	 */
	@Transactional
	public void updateAutoAssignConfig(String autoAssignEnabled) throws Exception {
		try {
			// 설정 정보를 데이터베이스에 저장 (예: 시스템 설정 테이블)
			// 여기서는 간단하게 AssignRule 테이블에 설정 값을 저장하는 방식을 사용
			assignRuleDAO.updateAutoAssignConfig(autoAssignEnabled);

		} catch (Exception e) {
			throw new Exception("자동 배정 설정 업데이트 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 자동 배정 설정을 조회한다.
	 *
	 * @process 1. 데이터베이스 또는 설정 파일에서 자동 배정 설정 조회
	 * 
	 * @return 자동 배정 활성화 여부
	 * @throws Exception
	 */
	public String getAutoAssignConfig() throws Exception {
		try {
			// 데이터베이스에서 설정 조회
			String config = assignRuleDAO.getAutoAssignConfig();

			// 설정이 없으면 기본값 "false" 반환
			if (config == null || config.trim().isEmpty()) {
				return "false";
			}

			return config;

		} catch (Exception e) {
			// 오류 발생 시 기본값 반환
			return "false";
		}
	}

	/**
	 * 여러 청구를 일괄로 자동 배정한다 (배치 처리용).
	 *
	 * @process 1. 미배정 청구 목록을 조회한다. 2. 각 청구에 대해 claim_type 기반 자동 배정을 수행한다. 3. 배정 결과를
	 *          요약해서 반환한다.
	 * 
	 * @return 배정 결과 맵 (success, message, stats 포함)
	 * @throws Exception
	 */
	@Transactional
	public String runAutoAssignmentBatch() throws Exception {
		try {
			// 🔥 디버깅을 위한 직접 SQL 실행
			System.out.println("[DEBUG]  미배정 청구 조회 시작");
			
			// 🔥 1단계: 전체 청구 수 확인 (강제로 새로운 쿼리)
			try {
				// 직접 SQL로 COUNT 확인
				long totalCount = assignRuleDAO.selectListCountAssignRule(new AssignRuleVo()); // 임시로 사용
				System.out.println("[DEBUG] DB에서 직접 COUNT 조회 시도");
			} catch (Exception e) {
				System.out.println("[DEBUG] COUNT 조회 오류: " + e.getMessage());
			}
			
			List<ClaimVo> allClaims = claimDAO.selectListClaim(new ClaimVo());
			System.out.println("[DEBUG] 전체 청구 수: " + (allClaims != null ? allClaims.size() : 0));
			
			//  각 청구의 상세 정보 출력
			if (allClaims != null) {
				for (ClaimVo claim : allClaims) {
					System.out.println(String.format("[DEBUG] 청구 상세 - NO: %s, TYPE: %s, EMP_NO: %s", 
							claim.getClaim_no(), claim.getClaim_type(), claim.getEmp_no()));
				}
			}
			
			//  2단계: 미배정 청구 조회
			List<ClaimVo> unassignedClaims = assignRuleDAO.selectUnassignedClaims();
			System.out.println("[DEBUG] 미배정 청구 수: " + (unassignedClaims != null ? unassignedClaims.size() : 0));
			
			//  3단계: 각 청구의 EMP_NO 상태 확인
			if (allClaims != null && allClaims.size() > 0) {
				for (int i = 0; i < Math.min(allClaims.size(), 10); i++) { // 최대 10건만 확인
					ClaimVo claim = allClaims.get(i);
					String empNo = claim.getEmp_no();
					String status = "UNKNOWN";
					
					if (empNo == null) {
						status = "NULL";
					} else if (empNo.trim().isEmpty()) {
						status = "EMPTY_OR_WHITESPACE";
					} else {
						status = "HAS_VALUE(" + empNo + ")";
					}
					
					System.out.println(String.format("[DEBUG] 청구 %s: EMP_NO = %s", 
							claim.getClaim_no(), status));
				}
			}
			
			//  4단계: 배정규칙 확인
			List<AssignRuleVo> assignRules = assignRuleDAO.selectAllAssignRules();
			System.out.println("[DEBUG] 배정규칙 수: " + (assignRules != null ? assignRules.size() : 0));
			
			List<String> results = assignAllUnassignedClaims();

			int successCount = 0;
			int failCount = 0;

			for (String result : results) {
				if (result.contains("배정 완료") || result.contains("자동 배정 완료")) {
					successCount++;
				} else {
					failCount++;
				}
			}

			//  성공적으로 처리되었다면 정상 메시지 반환 (예외 던지지 않음)
			String resultMessage = String.format("배치 배정 완료 - 성공: %d건, 실패: %d건, 총: %d건", 
					successCount, failCount, results.size());
			
			System.out.println("[INFO] " + resultMessage);
			return resultMessage;

		} catch (Exception e) {
			System.err.println("[ERROR] 배치 자동 배정 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("배치 자동 배정 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 특정 청구 유형에 따른 배정 가능한 부서와 직원을 미리 보기한다.
	 *
	 * @process 1. claim_type과 일치하는 배정규칙을 찾는다. 2. 해당 부서와 직원 정보를 조회한다. 3. 배정 예상 정보를
	 *          반환한다.
	 * 
	 * @param claimType 청구 유형
	 * @return 배정 예상 정보 Map
	 * @throws Exception
	 */
	public java.util.Map<String, Object> previewAssignment(String claimType) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			if (claimType == null || claimType.trim().isEmpty()) {
				result.put("success", false);
				result.put("message", "청구 유형이 비어있습니다.");
				return result;
			}

			// claim_type과 일치하는 배정규칙 찾기
			List<AssignRuleVo> allRules = assignRuleDAO.selectListAssignRule(new AssignRuleVo());
			String matchedKeyword = null;
			String deptName = null;

			for (AssignRuleVo rule : allRules) {
				if (claimType.equals(rule.getKeyword())) {
					matchedKeyword = rule.getKeyword();
					deptName = rule.getDept();
					break;
				}
			}

			if (matchedKeyword == null) {
				result.put("success", false);
				result.put("message", "청구 유형 '" + claimType + "에 매칭되는 배정규칙을 찾을 수 없습니다.");
				return result;
			}

			// 부서명으로 부서 ID 찾기
			InsDeptVo insDeptVo = new InsDeptVo();
			insDeptVo.setDept_name(deptName);

			List<InsDeptVo> deptList = insDeptDAO.selectListInsDept(insDeptVo);

			if (deptList == null || deptList.isEmpty()) {
				result.put("success", false);
				result.put("message", "부서 정보를 찾을 수 없습니다: " + deptName);
				return result;
			}

			String deptId = deptList.get(0).getDept_id();

			// 해당 부서의 직원 목록 조회
			List<EmployeeVo> employees = getAvailableEmployeesByKeyword(matchedKeyword);

			// 결과 정보 구성
			result.put("success", true);
			result.put("claimType", claimType);
			result.put("matchedKeyword", matchedKeyword);
			result.put("deptName", deptName);
			result.put("deptId", deptId);
			result.put("availableEmployees", employees);
			result.put("employeeCount", employees.size());

			if (employees.size() > 0) {
				result.put("primaryAssignee", employees.get(0)); // 첫 번째 직원이 배정 대상
				result.put("message", "배정 가능한 직원이 있습니다.");
			} else {
				result.put("message", "배정 가능한 직원이 없습니다.");
			}

			return result;

		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "배정 미리보기 중 오류 발생: " + e.getMessage());
			return result;
		}
	}
}
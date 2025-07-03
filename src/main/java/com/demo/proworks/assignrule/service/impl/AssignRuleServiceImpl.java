package com.demo.proworks.assignrule.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.dao.AssignRuleDAO;
import com.demo.proworks.claim.dao.ClaimDAO;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.emp.dao.EmpDAO;
import com.demo.proworks.emp.vo.EmpVo;
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

	@Resource(name = "empDAO")
	private EmpDAO empDAO;

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
	 * @process 1. 키워드로 ASSIGN_RULE에서 부서명을 찾는다.
	 *          2. 부서명으로 INS_DEPT에서 부서 ID를 찾는다.
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
	 * @process 1. 부서 ID로 EMPLOYEE 테이블에서 직원 목록을 조회한다.
	 *          2. 활성 상태의 직원 중 첫 번째 직원을 반환한다.
	 * 
	 * @param deptId 부서 ID
	 * @return 직원 번호 (정수형을 문자열로 변환)
	 * @throws Exception
	 */
	public String findEmployeeByDeptId(String deptId) throws Exception {
		try {
			// 부서 ID로 직원 조회
			EmpVo empVo = new EmpVo();
			empVo.setDeptno(deptId); // EmpVo에서는 deptno 필드 사용
			
			List<EmpVo> empList = empDAO.selectListEmp(empVo);
			
			if (empList == null || empList.isEmpty()) {
				throw new Exception("해당 부서에 소속된 직원을 찾을 수 없습니다. 부서 ID: " + deptId);
			}
			
			// 첫 번째 직원의 번호 반환 (필요시 추가 조건으로 필터링 가능)
			// 예: 활성 상태 직원만 필터링, 특정 역할의 직원만 선택 등
			return String.valueOf(empList.get(0).getEmpno());
			
		} catch (Exception e) {
			throw new Exception("부서 ID로 직원 조회 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 청구를 실무자에게 배정한다.
	 *
	 * @process 1. 청구의 claim_type을 확인한다.
	 *          2. claim_type과 일치하는 키워드를 가진 배정규칙을 찾는다.
	 *          3. 배정규칙의 부서명으로 부서 ID를 찾는다.
	 *          4. 부서 ID로 담당 직원을 찾는다.
	 *          5. 청구에 직원 번호를 업데이트한다.
	 * 
	 * @param claimNo 청구 번호
	 * @return 배정 결과 메시지
	 * @throws Exception
	 */
	@Transactional
	public String assignEmployeeToClaim(String claimNo) throws Exception {
		try {
			// 1. 청구 정보 조회
			ClaimVo claimVo = new ClaimVo();
			claimVo.setClaim_no(claimNo);
			
			ClaimVo claimInfo = claimDAO.selectClaim(claimVo);
			
			if (claimInfo == null) {
				throw new Exception("해당 청구 번호를 찾을 수 없습니다: " + claimNo);
			}
			
			// 이미 배정된 청구인지 확인
			if (claimInfo.getEmp_no() != null && !claimInfo.getEmp_no().trim().isEmpty()) {
				return "이미 배정된 청구입니다. 청구번호: " + claimNo + ", 담당자: " + claimInfo.getEmp_no();
			}
			
			// 2. 청구 유형(claim_type)으로 배정규칙 찾기
			String claimType = claimInfo.getClaim_type();
			if (claimType == null || claimType.trim().isEmpty()) {
				throw new Exception("청구 유형(claim_type)이 없어 자동 배정을 할 수 없습니다.");
			}
			
			// 3. claim_type과 일치하는 키워드를 가진 배정규칙 찾기
			List<AssignRuleVo> allRules = assignRuleDAO.selectListAssignRule(new AssignRuleVo());
			String matchedKeyword = null;
			String deptName = null;
			
			for (AssignRuleVo rule : allRules) {
				if (claimType.equals(rule.getKeyword())) {
					matchedKeyword = rule.getKeyword();
					deptName = rule.getDept();
					break; // 첫 번째 매칭된 규칙 사용
				}
			}
			
			if (matchedKeyword == null) {
				throw new Exception("청구 유형 '" + claimType + "'에 매칭되는 배정규칙을 찾을 수 없습니다.");
			}
			
			// 4. 부서명으로 부서 ID 찾기
			InsDeptVo insDeptVo = new InsDeptVo();
			insDeptVo.setDept_name(deptName);
			
			List<InsDeptVo> deptList = insDeptDAO.selectListInsDept(insDeptVo);
			
			if (deptList == null || deptList.isEmpty()) {
				throw new Exception("부서 정보를 찾을 수 없습니다: " + deptName);
			}
			
			String deptId = deptList.get(0).getDept_id();
			
			// 5. 부서 ID로 담당 직원 찾기
			String empNo = findEmployeeByDeptId(deptId);
			
			// 6. 청구에 직원 번호 업데이트
			claimInfo.setEmp_no(empNo);
			int updateResult = claimDAO.updateClaim(claimInfo);
			
			if (updateResult > 0) {
				return String.format("청구 배정 완료 - 청구번호: %s, 청구유형: %s, 매칭규칙: %s, 담당부서: %s, 담당자번호: %s", 
					claimNo, claimType, matchedKeyword, deptName, empNo);
			} else {
				throw new Exception("청구 업데이트에 실패했습니다.");
			}
			
		} catch (Exception e) {
			throw new Exception("청구 배정 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * 여러 청구를 한번에 자동 배정한다.
	 *
	 * @process 1. 미배정 청구 목록을 조회한다.
	 *          2. 각 청구에 대해 claim_type 기반 자동 배정을 수행한다.
	 * 
	 * @return 배정 결과 목록
	 * @throws Exception
	 */
	@Transactional
	public List<String> assignAllUnassignedClaims() throws Exception {
		try {
			// 미배정 청구 조회 (emp_no가 null이거나 빈값인 청구들)
			ClaimVo searchVo = new ClaimVo();
			List<ClaimVo> unassignedClaims = claimDAO.selectListClaim(searchVo);
			
			List<String> results = new java.util.ArrayList<>();
			
			for (ClaimVo claim : unassignedClaims) {
				// 이미 배정된 청구는 건너뛰기
				if (claim.getEmp_no() != null && !claim.getEmp_no().trim().isEmpty()) {
					continue;
				}
				
				try {
					String result = assignEmployeeToClaim(claim.getClaim_no());
					results.add(result);
				} catch (Exception e) {
					results.add("배정 실패 - 청구번호: " + claim.getClaim_no() + 
						", 청구유형: " + claim.getClaim_type() + ", 오류: " + e.getMessage());
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
	 * @process 1. 키워드로 부서를 찾는다.
	 *          2. 해당 부서의 모든 직원을 조회한다.
	 * 
	 * @param keyword 검색 키워드
	 * @return 배정 가능한 직원 목록
	 * @throws Exception
	 */
	public List<EmpVo> getAvailableEmployeesByKeyword(String keyword) throws Exception {
		try {
			// 키워드로 부서 ID 찾기
			String deptId = findDeptIdByKeyword(keyword);
			
			// 해당 부서의 모든 직원 조회
			EmpVo empVo = new EmpVo();
			empVo.setDeptno(deptId);
			
			return empDAO.selectListEmp(empVo);
			
		} catch (Exception e) {
			throw new Exception("키워드별 배정 가능 직원 조회 중 오류 발생: " + e.getMessage(), e);
		}
	}
}
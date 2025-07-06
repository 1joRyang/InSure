package com.demo.proworks.assignrule.service;

import java.util.List;

import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.employee.vo.EmployeeVo;

/**  
 * @subject     : 배정규칙 관련 처리를 담당하는 인터페이스
 * @description : 배정규칙 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
public interface AssignRuleService {
	
    /**
     * 배정규칙 페이징 처리하여 목록을 조회한다.
     *
     * @param  assignRuleVo 배정규칙 AssignRuleVo
     * @return 배정규칙 목록 List<AssignRuleVo>
     * @throws Exception
     */
	public List<AssignRuleVo> selectListAssignRule(AssignRuleVo assignRuleVo) throws Exception;
	
    /**
     * 조회한 배정규칙 전체 카운트
     * 
     * @param  assignRuleVo 배정규칙 AssignRuleVo
     * @return 배정규칙 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountAssignRule(AssignRuleVo assignRuleVo) throws Exception;
	
    /**
     * 배정규칙를 상세 조회한다.
     *
     * @param  assignRuleVo 배정규칙 AssignRuleVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public AssignRuleVo selectAssignRule(AssignRuleVo assignRuleVo) throws Exception;
		
    /**
     * 배정규칙를 등록 처리 한다.
     *
     * @param  assignRuleVo 배정규칙 AssignRuleVo
     * @return 번호
     * @throws Exception
     */
	public int insertAssignRule(AssignRuleVo assignRuleVo) throws Exception;
	
    /**
     * 배정규칙를 갱신 처리 한다.
     *
     * @param  assignRuleVo 배정규칙 AssignRuleVo
     * @return 번호
     * @throws Exception
     */
	public int updateAssignRule(AssignRuleVo assignRuleVo) throws Exception;
	
    /**
    * 배정규칙를 삭제 처리 한다.
    *
    * @param  assignRuleVo 배정규칙 AssignRuleVo
    * @return 번호
    * @throws Exception
    */
    public int deleteAssignRule(AssignRuleVo assignRuleVo) throws Exception;
    
	/**
	 * 키워드로 부서명을 찾고 부서 ID를 반환한다.
	 *
	 * @param keyword 검색 키워드
	 * @return 부서 ID (문자열)
	 * @throws Exception
	 */
	public String findDeptIdByKeyword(String keyword) throws Exception;
	
	/**
	 * 부서 ID로 해당 부서의 실무자(직원)를 찾는다.
	 *
	 * @param deptId 부서 ID
	 * @return 직원 번호 (정수형을 문자열로 변환)
	 * @throws Exception
	 */
	public String findEmployeeByDeptId(String deptId) throws Exception;
	
	/**
	 * 청구를 실무자에게 배정한다.
	 *
	 * @param claimNo 청구 번호
	 * @return 배정 결과 메시지
	 * @throws Exception
	 */
	public String assignEmployeeToClaim(String claimNo) throws Exception;
	
	/**
	 * 여러 청구를 한번에 자동 배정한다.
	 *
	 * @return 배정 결과 목록
	 * @throws Exception
	 */
	public List<String> assignAllUnassignedClaims() throws Exception;
	
	/**
	 * 특정 키워드에 대한 배정 가능한 직원 목록을 조회한다.
	 *
	 * @param keyword 검색 키워드
	 * @return 배정 가능한 직원 목록
	 * @throws Exception
	 */
	public List<EmployeeVo> getAvailableEmployeesByKeyword(String keyword) throws Exception;
	
	/**
	 * 자동 배정 설정을 업데이트한다.
	 *
	 * @param autoAssignEnabled 자동 배정 활성화 여부 ("true" 또는 "false")
	 * @throws Exception
	 */
	public void updateAutoAssignConfig(String autoAssignEnabled) throws Exception;
	
	/**
	 * 자동 배정 설정을 조회한다.
	 *
	 * @return 자동 배정 활성화 여부 ("true" 또는 "false")
	 * @throws Exception
	 */
	public String getAutoAssignConfig() throws Exception;
	
	/**
	 * 여러 청구를 일괄로 자동 배정한다 (배치 처리용).
	 *
	 * @return 배정 결과 메시지
	 * @throws Exception
	 */
	public String runAutoAssignmentBatch() throws Exception;
	
	/**
	 * 특정 청구 유형에 따른 배정 가능한 부서와 직원을 미리 보기한다.
	 *
	 * @param claimType 청구 유형
	 * @return 배정 예상 정보 Map
	 * @throws Exception
	 */
	public java.util.Map<String, Object> previewAssignment(String claimType) throws Exception;
	
}

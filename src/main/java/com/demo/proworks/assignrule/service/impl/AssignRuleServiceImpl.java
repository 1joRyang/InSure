package com.demo.proworks.assignrule.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.dao.AssignRuleDAO;

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
	// 키워드로 dept name -> dept ID 찾기 
	public void findDeptIdByKeyword(String keyword) {

	}
	// 부서 번호로 실무자 찾기
	public void findEmployeeByDeptId(Long DeptId) {
	}
	// 청구 업데이트 
	public void assignEmployeeToClaim(){
	}
	
}

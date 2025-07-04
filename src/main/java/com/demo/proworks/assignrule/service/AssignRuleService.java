package com.demo.proworks.assignrule.service;

import java.util.List;

import com.demo.proworks.assignrule.vo.AssignRuleVo;

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
	
}

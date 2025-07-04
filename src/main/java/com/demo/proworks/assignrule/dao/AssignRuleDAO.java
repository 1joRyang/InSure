package com.demo.proworks.assignrule.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.dao.AssignRuleDAO;

/**  
 * @subject     : 배정규칙 관련 처리를 담당하는 DAO
 * @description : 배정규칙 관련 처리를 담당하는 DAO
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Repository("assignRuleDAO")
public class AssignRuleDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 배정규칙 상세 조회한다.
     *  
     * @param  AssignRuleVo 배정규칙
     * @return AssignRuleVo 배정규칙
     * @throws ElException
     */
    public AssignRuleVo selectAssignRule(AssignRuleVo vo) throws ElException {
        return (AssignRuleVo) selectByPk("com.demo.proworks.assignrule.selectAssignRule", vo);
    }

    /**
     * 페이징을 처리하여 배정규칙 목록조회를 한다.
     *  
     * @param  AssignRuleVo 배정규칙
     * @return List<AssignRuleVo> 배정규칙
     * @throws ElException
     */
    public List<AssignRuleVo> selectListAssignRule(AssignRuleVo vo) throws ElException {      	
        return (List<AssignRuleVo>)list("com.demo.proworks.assignrule.selectListAssignRule", vo);
    }

    /**
     * 배정규칙 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  AssignRuleVo 배정규칙
     * @return 배정규칙 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountAssignRule(AssignRuleVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.assignrule.selectListCountAssignRule", vo);
    }
        
    /**
     * 배정규칙를 등록한다.
     *  
     * @param  AssignRuleVo 배정규칙
     * @return 번호
     * @throws ElException
     */
    public int insertAssignRule(AssignRuleVo vo) throws ElException {    	
        return insert("com.demo.proworks.assignrule.insertAssignRule", vo);
    }

    /**
     * 배정규칙를 갱신한다.
     *  
     * @param  AssignRuleVo 배정규칙
     * @return 번호
     * @throws ElException
     */
    public int updateAssignRule(AssignRuleVo vo) throws ElException {
        return update("com.demo.proworks.assignrule.updateAssignRule", vo);
    }

    /**
     * 배정규칙를 삭제한다.
     *  
     * @param  AssignRuleVo 배정규칙
     * @return 번호
     * @throws ElException
     */
    public int deleteAssignRule(AssignRuleVo vo) throws ElException {
        return delete("com.demo.proworks.assignrule.deleteAssignRule", vo);
    }

}

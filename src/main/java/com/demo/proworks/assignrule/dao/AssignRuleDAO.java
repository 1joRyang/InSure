package com.demo.proworks.assignrule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.dao.AssignRuleDAO;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.employee.vo.EmployeeVo;

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
    
    // ====================== 청구 자동 배정 관련 메서드 ======================
    
    /**
     * 키워드로 배정규칙을 조회한다.
     *  
     * @param  keyword 키워드
     * @return 배정규칙
     * @throws ElException
     */
    public AssignRuleVo selectAssignRuleByKeyword(String keyword) throws ElException {
        return (AssignRuleVo) selectByPk("com.demo.proworks.assignrule.selectAssignRuleByKeyword", keyword);
    }
    
    /**
     * 부서명으로 부서 ID를 조회한다.
     *  
     * @param  deptName 부서명
     * @return 부서 ID
     * @throws ElException
     */
    public String selectDeptIdByDeptName(String deptName) throws ElException {
        return (String) selectByPk("com.demo.proworks.assignrule.selectDeptIdByDeptName", deptName);
    }
    
    /**
     * 부서 ID로 직원 번호를 조회한다.
     *  
     * @param  deptId 부서 ID
     * @return 직원 번호
     * @throws ElException
     */
    public Integer selectEmployeeByDeptId(String deptId) throws ElException {
        return (Integer) selectByPk("com.demo.proworks.assignrule.selectEmployeeByDeptId", deptId);
    }
    
    /**
     * 배정을 위한 청구 정보를 조회한다.
     *  
     * @param  claimNo 청구 번호
     * @return 청구 정보
     * @throws ElException
     */
    public ClaimVo selectClaimForAssignment(String claimNo) throws ElException {
        return (ClaimVo) selectByPk("com.demo.proworks.assignrule.selectClaimForAssignment", claimNo);
    }
    
    /**
     * 청구에 담당자를 배정한다.
     *  
     * @param  params 청구번호와 직원번호가 포함된 Map
     * @return 업데이트 건수
     * @throws ElException
     */
    public int updateClaimAssignment(Map<String, Object> params) throws ElException {
        return update("com.demo.proworks.assignrule.updateClaimAssignment", params);
    }
    
    /**
     * 미배정 청구 목록을 조회한다.
     *  
     * @return 미배정 청구 목록
     * @throws ElException
     */
    public List<ClaimVo> selectUnassignedClaims() throws ElException {
        return (List<ClaimVo>) list("com.demo.proworks.assignrule.selectUnassignedClaims", null);
    }
    
    /**
     * 모든 배정규칙을 조회한다.
     *  
     * @return 모든 배정규칙 목록
     * @throws ElException
     */
    public List<AssignRuleVo> selectAllAssignRules() throws ElException {
        return (List<AssignRuleVo>) list("com.demo.proworks.assignrule.selectAllAssignRules", null);
    }
    
    /**
     * 청구 내용에서 키워드가 매칭되는 배정규칙을 조회한다.
     *  
     * @param  claimContent 청구 내용
     * @return 매칭된 배정규칙
     * @throws ElException
     */
    public AssignRuleVo selectKeywordMatchingRules(String claimContent) throws ElException {
        return (AssignRuleVo) selectByPk("com.demo.proworks.assignrule.selectKeywordMatchingRules", claimContent);
    }
    
    /**
     * 부서 ID로 모든 직원을 조회한다.
     *  
     * @param  deptId 부서 ID
     * @return 직원 목록
     * @throws ElException
     */
     public List<EmployeeVo> selectEmployeesByDeptId(String deptId) throws ElException {
     return (List<EmployeeVo>) list("com.demo.proworks.assignrule.selectEmployeesByDeptId", deptId);
     }
    
    /**
     * 청구 배정을 위한 모든 정보를 한번에 조회한다.
     *  
     * @param  claimNo 청구 번호
     * @return 배정 정보 Map
     * @throws ElException
     */
    public Map<String, Object> selectClaimAssignmentInfo(String claimNo) throws ElException {
        return (Map<String, Object>) selectByPk("com.demo.proworks.assignrule.selectClaimAssignmentInfo", claimNo);
    }
    
    /**
     * 배정 통계를 조회한다.
     *  
     * @return 배정 통계 Map
     * @throws ElException
     */
    public Map<String, Object> selectAssignmentStatistics() throws ElException {
        return (Map<String, Object>) selectByPk("com.demo.proworks.assignrule.selectAssignmentStatistics", null);
    }
    
    /**
     * 자동 배정 설정을 업데이트한다.
     *  
     * @param  autoAssignEnabled 자동 배정 활성화 여부
     * @return 업데이트 건수
     * @throws ElException
     */
    public int updateAutoAssignConfig(String autoAssignEnabled) throws ElException {
        return update("com.demo.proworks.assignrule.updateAutoAssignConfig", autoAssignEnabled);
    }
    
    /**
     * 자동 배정 설정을 조회한다.
     *  
     * @return 자동 배정 활성화 여부
     * @throws ElException
     */
    public String getAutoAssignConfig() throws ElException {
        return (String) selectByPk("com.demo.proworks.assignrule.getAutoAssignConfig", null);
    }

    /**
     * 🔥 부서별 재직중인 직원 수 조회
     *  
     * @param deptId 부서 ID
     * @return 재직중인 직원 수
     * @throws ElException
     */
    public int selectDeptEmployeeCount(String deptId) throws ElException {
        try {
            Object countObj = selectByPk("com.demo.proworks.assignrule.selectDeptEmployeeCount", deptId);
            if (countObj == null) {
                return 0;
            }
            
            // 🔥 안전한 타입 변환
            if (countObj instanceof Long) {
                return ((Long) countObj).intValue();
            } else if (countObj instanceof Integer) {
                return (Integer) countObj;
            } else if (countObj instanceof String) {
                try {
                    return Integer.parseInt((String) countObj);
                } catch (NumberFormatException e) {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.err.println("[ERROR] 부서별 직원 수 조회 오류: " + e.getMessage());
            return 0;
        }
    }

}

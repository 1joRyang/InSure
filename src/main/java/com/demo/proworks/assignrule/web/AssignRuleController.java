package com.demo.proworks.assignrule.web;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.vo.AssignRuleListVo;
import com.demo.proworks.employee.vo.EmployeeVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 배정규칙 관련 처리를 담당하는 컨트롤러
 * @description : 배정규칙 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class AssignRuleController {
	
    /** AssignRuleService */
    @Resource(name = "assignRuleServiceImpl")
    private AssignRuleService assignRuleService;
	
    
    /**
     * 배정규칙 목록을 조회합니다.
     *
     * @param  assignRuleVo 배정규칙
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="assignrule0001List")
    @RequestMapping(value="assignrule0001List")    
    @ElDescription(sub="배정규칙 목록조회",desc="페이징을 처리하여 배정규칙 목록 조회를 한다.")               
    public AssignRuleListVo selectListAssignRule(AssignRuleVo assignRuleVo) throws Exception {    	   	

        List<AssignRuleVo> assignRuleList = assignRuleService.selectListAssignRule(assignRuleVo);                  
        long totCnt = assignRuleService.selectListCountAssignRule(assignRuleVo);
	
		AssignRuleListVo retAssignRuleList = new AssignRuleListVo();
		retAssignRuleList.setAssignRuleVoList(assignRuleList); 
		retAssignRuleList.setTotalCount(totCnt);
		retAssignRuleList.setPageSize(assignRuleVo.getPageSize());
		retAssignRuleList.setPageIndex(assignRuleVo.getPageIndex());

        return retAssignRuleList;            
    }  
        
    /**
     * 배정규칙을 단건 조회 처리 한다.
     *
     * @param  assignRuleVo 배정규칙
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "assignrule0001UpdView")    
    @RequestMapping(value="assignrule0001UpdView") 
    @ElDescription(sub = "배정규칙 갱신 폼을 위한 조회", desc = "배정규칙 갱신 폼을 위한 조회를 한다.")    
    public AssignRuleVo selectAssignRule(AssignRuleVo assignRuleVo) throws Exception {
    	AssignRuleVo selectAssignRuleVo = assignRuleService.selectAssignRule(assignRuleVo);    	    
		
        return selectAssignRuleVo;
    } 
 
    /**
     * 배정규칙를 등록 처리 한다.
     *
     * @param  assignRuleVo 배정규칙
     * @throws Exception
     */
    @ElService(key="assignrule0001Ins")    
    @RequestMapping(value="assignrule0001Ins")
    @ElDescription(sub="배정규칙 등록처리",desc="배정규칙를 등록 처리 한다.")
    public void insertAssignRule(AssignRuleVo assignRuleVo) throws Exception {    	 
    	assignRuleService.insertAssignRule(assignRuleVo);   
    }
       
    /**
     * 배정규칙를 갱신 처리 한다.
     *
     * @param  assignRuleVo 배정규칙
     * @throws Exception
     */
    @ElService(key="assignrule0001Upd")    
    @RequestMapping(value="assignrule0001Upd")    
    @ElValidator(errUrl="/assignRule/assignRuleRegister", errContinue=true)
    @ElDescription(sub="배정규칙 갱신처리",desc="배정규칙를 갱신 처리 한다.")    
    public void updateAssignRule(AssignRuleVo assignRuleVo) throws Exception {  
 
    	assignRuleService.updateAssignRule(assignRuleVo);                                            
    }

    /**
     * 배정규칙를 삭제 처리한다.
     *
     * @param  assignRuleVo 배정규칙    
     * @throws Exception
     */
    @ElService(key = "assignrule0001Del")    
    @RequestMapping(value="assignrule0001Del")
    @ElDescription(sub = "배정규칙 삭제처리", desc = "배정규칙를 삭제 처리한다.")    
    public void deleteAssignRule(AssignRuleVo assignRuleVo) throws Exception {
        assignRuleService.deleteAssignRule(assignRuleVo);
    }
    
    /**
     * 키워드로 부서 ID를 조회한다.
     *
     * @param  assignRuleVo 배정규칙 (keyword 필드 사용)
     * @return 부서 ID 조회 결과
     * @throws Exception
     */
    @ElService(key="assignrule0002FindDept")
    @RequestMapping(value="assignrule0002FindDept")
    @ElDescription(sub="키워드별 부서 조회", desc="키워드로 부서 ID를 조회한다.")
    public Map<String, Object> findDeptIdByKeyword(AssignRuleVo assignRuleVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String keyword = assignRuleVo.getKeyword();
            if (keyword == null || keyword.trim().isEmpty()) {
                result.put("success", false);
                result.put("message", "키워드를 입력해주세요.");
                return result;
            }
            
            String deptId = assignRuleService.findDeptIdByKeyword(keyword);
            result.put("success", true);
            result.put("deptId", deptId);
            result.put("message", "부서 조회 성공");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 부서 ID로 직원을 조회한다.
     *
     * @param  assignRuleVo 배정규칙 (dept 필드를 deptId로 사용)
     * @return 직원 번호 조회 결과
     * @throws Exception
     */
    @ElService(key="assignrule0003FindEmp")
    @RequestMapping(value="assignrule0003FindEmp")
    @ElDescription(sub="부서별 직원 조회", desc="부서 ID로 담당 직원을 조회한다.")
    public Map<String, Object> findEmployeeByDeptId(AssignRuleVo assignRuleVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String deptId = assignRuleVo.getDept(); // dept 필드를 deptId로 활용
            if (deptId == null || deptId.trim().isEmpty()) {
                result.put("success", false);
                result.put("message", "부서 ID를 입력해주세요.");
                return result;
            }
            
            String empNo = assignRuleService.findEmployeeByDeptId(deptId);
            result.put("success", true);
            result.put("empNo", empNo);
            result.put("message", "직원 조회 성공");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 특정 청구를 자동 배정한다.
     *
     * @param  assignRuleVo 배정규칙 (keyword 필드를 claimNo로 사용)
     * @return 청구 배정 결과
     * @throws Exception
     */
    @ElService(key="assignrule0004AssignClaim")
    @RequestMapping(value="assignrule0004AssignClaim")
    @ElDescription(sub="청구 자동 배정", desc="청구를 키워드 매칭을 통해 자동 배정한다.")
    public Map<String, Object> assignEmployeeToClaim(AssignRuleVo assignRuleVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String claimNo = assignRuleVo.getKeyword();
            if (claimNo == null || claimNo.trim().isEmpty()) {
                result.put("success", false);
                result.put("message", "청구 번호를 입력해주세요.");
                return result;
            }
            
            String assignResult = assignRuleService.assignEmployeeToClaim(claimNo);
            result.put("success", true);
            result.put("message", assignResult);
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 모든 미배정 청구를 일괄 배정한다.
     *
     * @return 일괄 배정 결과
     * @throws Exception
     */
    @ElService(key="assignrule0005AssignAllClaims")
    @RequestMapping(value="assignrule0005AssignAllClaims")
    @ElDescription(sub="청구 일괄 자동 배정", desc="미배정 청구들을 키워드 매칭을 통해 일괄 자동 배정한다.")
    public Map<String, Object> assignAllUnassignedClaims() throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<String> assignResults = assignRuleService.assignAllUnassignedClaims();
            result.put("success", true);
            result.put("results", assignResults);
            result.put("totalProcessed", assignResults.size());
            result.put("message", "일괄 배정 처리 완료");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 키워드별 배정 가능한 직원 목록을 조회한다.
     *
     * @param  assignRuleVo 배정규칙
     * @return 배정 가능한 직원 목록
     * @throws Exception
     */
    @ElService(key="assignrule0006GetAvailableEmployees")
    @RequestMapping(value="assignrule0006GetAvailableEmployees")
    @ElDescription(sub="키워드별 배정가능 직원조회", desc="키워드에 해당하는 부서의 배정 가능한 직원 목록을 조회한다.")
    public Map<String, Object> getAvailableEmployeesByKeyword(AssignRuleVo assignRuleVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        try {
            String keyword = assignRuleVo.getKeyword();
            if (keyword == null || keyword.trim().isEmpty()) {
                result.put("success", false);
                result.put("message", "키워드를 입력해주세요.");
                return result;
            }
            
            List<EmployeeVo> employees = assignRuleService.getAvailableEmployeesByKeyword(keyword);
            result.put("success", true);
            result.put("employees", employees);
            result.put("totalCount", employees.size());
            result.put("message", "직원 목록 조회 성공");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
//    /**
//     * 자동 배정 설정을 업데이트한다.
//     *
//     * @param  assignRuleVo 자동 배정 설정 정보
//     * @return 업데이트 결과
//     * @throws Exception
//     */
//    @ElService(key="assignrule0007UpdateAutoConfig")
//    @RequestMapping(value="assignrule0007UpdateAutoConfig")
//    @ElDescription(sub="자동 배정 설정 업데이트", desc="자동 배정 활성화/비활성화 설정을 업데이트한다.")
//    public Map<String, Object> updateAutoAssignConfig(AssignRuleVo assignRuleVo) throws Exception {
//        Map<String, Object> result = new HashMap<>();
//
//        try {
//            // assignRuleVo의 keyword 필드를 autoAssignEnabled로 활용
//            String autoAssignEnabled = assignRuleVo.getKeyword();
//            if (autoAssignEnabled == null) {
//                result.put("success", false);
//                result.put("message", "자동 배정 설정 값이 필요합니다.");
//                return result;
//            }
//
//            assignRuleService.updateAutoAssignConfig(autoAssignEnabled);
//            result.put("success", true);
//            result.put("message", "자동 배정 설정이 업데이트되었습니다.");
//            result.put("autoAssignEnabled", autoAssignEnabled);
//
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("message", e.getMessage());
//        }
//
//        return result;
//    }
    
//    /**
//     * 자동 배정 설정을 조회한다.
//     *
//     * @return 자동 배정 설정 정보
//     * @throws Exception
//     */
//    @ElService(key="assignrule0008GetAutoConfig")
//    @RequestMapping(value="assignrule0008GetAutoConfig")
//    @ElDescription(sub="자동 배정 설정 조회", desc="현재 자동 배정 활성화/비활성화 설정을 조회한다.")
//    public Map<String, Object> getAutoAssignConfig() throws Exception {
//        Map<String, Object> result = new HashMap<>();
//
//        try {
//            String autoAssignEnabled = assignRuleService.getAutoAssignConfig();
//            result.put("success", true);
//            result.put("autoAssignEnabled", autoAssignEnabled);
//            result.put("message", "자동 배정 설정 조회 성공");
//
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("message", e.getMessage());
//            result.put("autoAssignEnabled", "false"); // 기본값
//        }
//
//        return result;
//    }
    
//    /**
//     * 배치 자동 배정을 실행한다.
//     *
//     * @return 배치 배정 결과
//     * @throws Exception
//     */
//    @ElService(key="assignrule0009RunBatch")
//    @RequestMapping(value="assignrule0009RunBatch")
//    @ElDescription(sub="배치 자동 배정 실행", desc="모든 미배정 청구를 일괄적으로 자동 배정한다.")
//    public Map<String, Object> runAutoAssignmentBatch() throws Exception {
//        Map<String, Object> result = new HashMap<>();
//
//        try {
//            String batchResult = assignRuleService.runAutoAssignmentBatch();
//            result.put("success", true);
//            result.put("message", batchResult);
//
//        } catch (Exception e) {
//            result.put("success", false);
//            result.put("message", e.getMessage());
//        }
//
//        return result;
//    }
    
//    /**
//     * 청구 유형별 배정 미리보기를 제공한다.
//     *
//     * @param  assignRuleVo 배정규칙 (keyword 필드를 claimType으로 사용)
//     * @return 배정 미리보기 결과
//     * @throws Exception
//     */
//    @ElService(key="assignrule0010PreviewAssignment")
//    @RequestMapping(value="assignrule0010PreviewAssignment")
//    @ElDescription(sub="청구 유형별 배정 미리보기", desc="특정 청구 유형에 대한 배정 예상 정보를 조회한다.")
//    public Map<String, Object> previewAssignment(AssignRuleVo assignRuleVo) throws Exception {
//        String claimType = assignRuleVo.getKeyword(); // keyword 필드를 claimType으로 활용
//
//        if (claimType == null || claimType.trim().isEmpty()) {
//            Map<String, Object> result = new HashMap<>();
//            result.put("success", false);
//            result.put("message", "청구 유형을 입력해주세요.");
//            return result;
//        }
//
//        return assignRuleService.previewAssignment(claimType);
//    }
   
}
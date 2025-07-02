package com.demo.proworks.assignrule.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.assignrule.vo.AssignRuleVo;
import com.demo.proworks.assignrule.vo.AssignRuleListVo;

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
   
}

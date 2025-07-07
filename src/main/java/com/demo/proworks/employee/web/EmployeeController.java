package com.demo.proworks.employee.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.employee.service.EmployeeService;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.employee.vo.LoginVo;
import com.demo.proworks.employee.vo.EmployeeListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.login.LoginInfo;
import com.inswave.elfw.login.LoginProcessor;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 실무자,관리자정보 관련 처리를 담당하는 컨트롤러
 * @description : 실무자,관리자정보 관련 처리를 담당하는 컨트롤러
 * @author      : 임소희
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 임소희	 		최초 생성
 * 
 */
@Controller
public class EmployeeController {
	
    /** EmployeeService */
    @Resource(name = "employeeServiceImpl")
    private EmployeeService employeeService;
	
    @Resource(name = "loginProcess")
	protected LoginProcessor loginProcess;
    
	/**
	 * 로그인을 처리한다.
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "EmployeeLogin")
    @RequestMapping(value = "EmployeeLogin")
    @ElDescription(sub = "실무자로그인", desc = "실무자로그인을 처리한다.")
    public void login(LoginVo loginVo, HttpServletRequest request) throws Exception {
    	String id = loginVo.getId();
    	String pw = loginVo.getPw();
    	
    	LoginInfo info = loginProcess.processLogin(request, id, pw);
    	
    	AppLog.debug("- Login 정보 : " + info.toString());
    }
    /**
     * 실무자,관리자정보 목록을 조회합니다.
     *
     * @param  employeeVo 실무자,관리자정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="EmployeeList")
    @RequestMapping(value="EmployeeList")    
    @ElDescription(sub="실무자,관리자정보 목록조회",desc="페이징을 처리하여 실무자,관리자정보 목록 조회를 한다.")               
    public EmployeeListVo selectListEmployee(EmployeeVo employeeVo) throws Exception {    	   	

        List<EmployeeVo> employeeList = employeeService.selectListEmployee(employeeVo);                  
        long totCnt = employeeService.selectListCountEmployee(employeeVo);
	
		EmployeeListVo retEmployeeList = new EmployeeListVo();
		retEmployeeList.setEmployeeVoList(employeeList); 
		retEmployeeList.setTotalCount(totCnt);
		retEmployeeList.setPageSize(employeeVo.getPageSize());
		retEmployeeList.setPageIndex(employeeVo.getPageIndex());

        return retEmployeeList;            
    }  
        
    /**
     * 실무자,관리자정보을 단건 조회 처리 한다.
     *
     * @param  employeeVo 실무자,관리자정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "EmployeeUpdView")    
    @RequestMapping(value="EmployeeUpdView") 
    @ElDescription(sub = "실무자,관리자정보 갱신 폼을 위한 조회", desc = "실무자,관리자정보 갱신 폼을 위한 조회를 한다.")    
    public EmployeeVo selectEmployee(EmployeeVo employeeVo) throws Exception {
    	EmployeeVo selectEmployeeVo = employeeService.selectEmployee(employeeVo);    	    
		
        return selectEmployeeVo;
    } 
 
    /**
     * 실무자,관리자정보를 등록 처리 한다.
     *
     * @param  employeeVo 실무자,관리자정보
     * @throws Exception
     */
    @ElService(key="EmployeeIns")    
    @RequestMapping(value="EmployeeIns")
    @ElDescription(sub="실무자,관리자정보 등록처리",desc="실무자,관리자정보를 등록 처리 한다.")
    public void insertEmployee(EmployeeVo employeeVo) throws Exception {    	 
    	employeeService.insertEmployee(employeeVo);   
    }
       
    /**
     * 실무자,관리자정보를 갱신 처리 한다.
     *
     * @param  employeeVo 실무자,관리자정보
     * @throws Exception
     */
    @ElService(key="EmployeeUpd")    
    @RequestMapping(value="EmployeeUpd")    
    @ElValidator(errUrl="/employee/employeeRegister", errContinue=true)
    @ElDescription(sub="실무자,관리자정보 갱신처리",desc="실무자,관리자정보를 갱신 처리 한다.")    
    public void updateEmployee(EmployeeVo employeeVo) throws Exception {  
 
    	employeeService.updateEmployee(employeeVo);                                            
    }

    /**
     * 실무자,관리자정보를 삭제 처리한다.
     *
     * @param  employeeVo 실무자,관리자정보    
     * @throws Exception
     */
    @ElService(key = "EmployeeDel")    
    @RequestMapping(value="EmployeeDel")
    @ElDescription(sub = "실무자,관리자정보 삭제처리", desc = "실무자,관리자정보를 삭제 처리한다.")    
    public void deleteEmployee(EmployeeVo employeeVo) throws Exception {
        employeeService.deleteEmployee(employeeVo);
    }
   
}

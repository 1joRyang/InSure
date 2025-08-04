package com.demo.proworks.employee.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.employee.service.EmployeeService;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.employee.vo.LoginVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	 * 특정 조건으로 가져오기
	 */
	 @ElService(key = "EmployeeForRule")
    @RequestMapping(value = "EmployeeForRule")
    @ElDescription(sub = "특정 조건으로 실무자 조회", desc = "특정 조건으로 실무자 조회 (deptId, status, role)\r\n")
    public List<EmployeeVo> selectEmpForRule(EmployeeVo employeeVo) throws Exception {
		List<EmployeeVo> employeeList = employeeService.selectListEmployeeForRule(employeeVo);  
		return employeeList;
    }
    
	/**
	 * 직원(실무자/관리자) 로그인을 처리한다.
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "EmployeeLogin")
    @RequestMapping(value = "EmployeeLogin")
    @ElDescription(sub = "실무자/관리자 로그인", desc = "직원 로그인을 처리한다.")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 try {
        // 원시 JSON 문자열 읽기
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        
        System.out.println(">>>>> 받은 JSON 문자열: " + jsonString);
        
        // JSON 수동 파싱
        ObjectMapper jsonMapper = new ObjectMapper(); 
        JsonNode jsonNode = jsonMapper.readTree(jsonString);
        
        // 각 필드 추출
        String empNo = jsonNode.get("empNo") != null ? jsonNode.get("empNo").asText() : null;
        String empPw = jsonNode.get("empPw") != null ? jsonNode.get("empPw").asText() : null;
        String loginType = jsonNode.get("loginType") != null ? jsonNode.get("loginType").asText() : null;
        
        System.out.println(">>>>> 파싱된 데이터 - empNo: " + empNo + ", empPw: " + empPw + ", loginType: " + loginType);
        
        // 입력값 검증
        if (empNo == null || empNo.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(response, "아이디가 입력되지 않았습니다.");
            return;
        }
        
        if (empPw == null || empPw.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(response, "비밀번호가 입력되지 않았습니다.");
            return;
        }

        LoginInfo info = loginProcess.processLogin(request, empNo, "EMPLOYEE", empPw);
        
        System.out.println(">>>>> 로그인 결과: " + info.toString());

        if (info.isSuc()) {
            try {
                EmployeeVo searchEmpVo = new EmployeeVo();
                searchEmpVo.setEmpNo(empNo);
                EmployeeVo empInfo = employeeService.selectEmployee(searchEmpVo);
                
                System.out.println(">>>>> 조회된 Employee 정보: " + empInfo);

	                if (empInfo != null) {
	                    Map<String, Object> elData = new HashMap<>();
	                    Map<String, Object> responseMap = new HashMap<>();
	
	                    responseMap.put("status", "SUCCESS");
	                    
	                    responseMap.put("empNo", empInfo.getEmpNo());
	                    responseMap.put("empName", empInfo.getEmpName());
	                    responseMap.put("empStatus", empInfo.getStatus());
	                    responseMap.put("deptId", empInfo.getDeptId());
	                    responseMap.put("role", empInfo.getRole());
	                    
	
	                    elData.put("dma_login_response", responseMap);
	
	                    ObjectMapper responseMapper = new ObjectMapper(); 
	                    String jsonResponse = responseMapper.writeValueAsString(elData);
	                    
	                    System.out.println(">>>>> 전송할 응답: " + jsonResponse);
	
	                    response.setContentType("application/json");
	                    response.setCharacterEncoding("UTF-8");
	                    response.getWriter().write(jsonResponse);
	
	                } else {
	                    System.out.println(">>>>> empInfo가 null입니다.");
	                    throw new Exception("직원 정보를 조회할 수 없습니다.");
	                }

	            } catch (Exception e) {
	                System.out.println(">>>>> DB 처리 중 오류: " + e.getMessage());
	                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                sendErrorResponse(response, "로그인 처리 중 오류가 발생했습니다.");
	            }

	        } else {
	            // 로그인 실패 처리
	            System.out.println(">>>>> 로그인 실패!");
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            sendErrorResponse(response, "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
	        }
	        
	    } catch (Exception e) {
	        System.out.println(">>>>> JSON 파싱 중 오류: " + e.getMessage());
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        sendErrorResponse(response, "요청 데이터를 처리할 수 없습니다: " + e.getMessage());
	    }
	}
    

    /**
     * 에러 응답을 보내는 헬퍼 메서드
     */
    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        Map<String, Object> errorData = new HashMap<>();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMsg", errorMessage);
        errorData.put("error", errorMap);

        ObjectMapper mapper = new ObjectMapper();
        String jsonErrorResponse = mapper.writeValueAsString(errorData);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonErrorResponse);
    }
	
	
	

	/**
	 * 로그아웃을 처리한다.
	 * @param request 요청 정보 HttpServletRequest
	 * @param response 응답 정보 HttpServletResponse
	 * @throws Exception
	 */
	@ElService(key = "EmployeeLogout")
	@RequestMapping(value = "EmployeeLogout")
	@ElDescription(sub = "실무자로그아웃", desc = "실무자로그아웃을 처리한다.")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	    try {
	        // 현재 세션에서 사용자 ID 가져오기
	        HttpSession session = request.getSession(false);
	        String empNo = null;
	
	        if (session != null) {
	            ProworksUserHeader userHeader = (ProworksUserHeader) session.getAttribute("userHeader");
	            if (userHeader != null) {
	                empNo = userHeader.getUserId();
	            }
	        }
	
	        if (empNo != null) {
	            LoginInfo logoutInfo = loginProcess.processLogout(request, empNo);
	        } else {
	            // 세션이 없어도 강제로 무효화
	            if (session != null) {
	                session.invalidate();
	            }
	        }
	
	        // 성공 응답
	        Map<String, Object> elData = new HashMap<>();
	        Map<String, Object> responseMap = new HashMap<>();
	        responseMap.put("success", true);
	        responseMap.put("message", "로그아웃이 완료되었습니다.");
	        elData.put("dma_logout_response", responseMap);
	
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonResponse = mapper.writeValueAsString(elData);
	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonResponse);
	
	    } catch (Exception e) {
	        AppLog.error("로그아웃 처리 중 오류 발생", e);
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("{\"success\":false,\"error\":\"로그아웃 처리 중 오류가 발생했습니다.\"}");
	    }
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
    
/**
     * 부서별 직원 목록을 조회합니다.
     *
     * @param  employeeVo 직원정보 (deptId, status 등 포함)
     * @return 부서별 직원 목록 조회 결과
     * @throws Exception
     */
    @ElService(key="EmployeeListByDept")
    @RequestMapping(value="EmployeeListByDept")    
    @ElDescription(sub="부서별 직원 목록조회",desc="특정 부서의 직원 목록을 조회한다.")               
    public EmployeeListVo selectListEmployeeByDept(EmployeeVo employeeVo) throws Exception {    	   	
        
        // 로그 추가 (디버깅용)
        System.out.println(">>>>> 부서별 직원 조회 요청");
        System.out.println(">>>>> deptId: " + employeeVo.getDeptId());
        System.out.println(">>>>> status: " + employeeVo.getStatus());
        System.out.println(">>>>> pageSize: " + employeeVo.getPageSize());
        System.out.println(">>>>> pageIndex: " + employeeVo.getPageIndex());
        
        // 부서 ID가 없으면 빈 결과 반환
        if (employeeVo.getDeptId() == null || employeeVo.getDeptId().trim().isEmpty()) {
            System.out.println(">>>>> 부서 ID가 없어서 빈 결과 반환");
            EmployeeListVo emptyResult = new EmployeeListVo();
            emptyResult.setEmployeeVoList(new java.util.ArrayList<>());
            emptyResult.setTotalCount(0);
            emptyResult.setPageSize(employeeVo.getPageSize());
            emptyResult.setPageIndex(employeeVo.getPageIndex());
            return emptyResult;
        }
        
        // 기본값 설정
        if (employeeVo.getStatus() == null || employeeVo.getStatus().trim().isEmpty()) {
            employeeVo.setStatus("재직중");  // 기본적으로 재직중인 직원만 조회
        }
        
        // 부서별 직원 목록 조회
        List<EmployeeVo> employeeList = employeeService.selectListEmployeeByDept(employeeVo);                  
        long totCnt = employeeService.selectListCountEmployeeByDept(employeeVo);
        
        System.out.println(">>>>> 조회된 직원 수: " + employeeList.size());
        System.out.println(">>>>> 전체 건수: " + totCnt);
        
        // 결과 객체 생성
        EmployeeListVo retEmployeeList = new EmployeeListVo();
        retEmployeeList.setEmployeeVoList(employeeList); 
        retEmployeeList.setTotalCount(totCnt);
        retEmployeeList.setPageSize(employeeVo.getPageSize());
        retEmployeeList.setPageIndex(employeeVo.getPageIndex());

        return retEmployeeList;            
    }    
    
	   
}

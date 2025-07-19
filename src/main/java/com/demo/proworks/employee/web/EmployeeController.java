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
        // 1. 원시 JSON 문자열 읽기
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        
        System.out.println(">>>>> 받은 JSON 문자열: " + jsonString);
        
        // 2. JSON 수동 파싱 (ObjectMapper 사용)
        ObjectMapper jsonMapper = new ObjectMapper();  // 변수명 변경
        JsonNode jsonNode = jsonMapper.readTree(jsonString);
        
        // 3. 각 필드 추출 (null 체크 포함)
        String empNo = jsonNode.get("empNo") != null ? jsonNode.get("empNo").asText() : null;
        String empPw = jsonNode.get("empPw") != null ? jsonNode.get("empPw").asText() : null;
        String loginType = jsonNode.get("loginType") != null ? jsonNode.get("loginType").asText() : null;
        
        System.out.println(">>>>> 파싱된 데이터 - empNo: " + empNo + ", empPw: " + empPw + ", loginType: " + loginType);
        
        // 4. 입력값 검증
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

        // 5. 프레임워크의 로그인 프로세스를 호출
        LoginInfo info = loginProcess.processLogin(request, empNo, "EMPLOYEE", empPw);
        
        System.out.println(">>>>> 로그인 결과: " + info.toString());

        if (info.isSuc()) {
            try {
                // DB에서 완전한 employee 정보 조회
                EmployeeVo searchEmpVo = new EmployeeVo();
                searchEmpVo.setEmpNo(empNo);
                EmployeeVo empInfo = employeeService.selectEmployee(searchEmpVo);
                
                System.out.println(">>>>> 조회된 Employee 정보: " + empInfo);

	                if (empInfo != null) {
	                    Map<String, Object> elData = new HashMap<>();
	                    Map<String, Object> responseMap = new HashMap<>();
	
	                    // 로그인 상태
	                    responseMap.put("status", "SUCCESS");
	                    
	                    // EmployeeVo의 컬럼들만 포함 (empPw 제외)
	                    responseMap.put("empNo", empInfo.getEmpNo());
	                    responseMap.put("empName", empInfo.getEmpName());
	                    responseMap.put("empStatus", empInfo.getStatus());
	                    responseMap.put("deptId", empInfo.getDeptId());
	                    responseMap.put("role", empInfo.getRole());
	                    
	                    
	                      // 디버깅을 위한 로그 추가
					    System.out.println(">>>>> empInfo.getEmpNo(): " + empInfo.getEmpNo());
					    System.out.println(">>>>> empInfo.getEmpName(): " + empInfo.getEmpName());
					    System.out.println(">>>>> empInfo.getStatus(): " + empInfo.getStatus());
					    System.out.println(">>>>> empInfo.getDeptId(): " + empInfo.getDeptId());
					    System.out.println(">>>>> empInfo.getRole(): " + empInfo.getRole());
	
	                    elData.put("dma_login_response", responseMap);
	
	                    ObjectMapper responseMapper = new ObjectMapper();  // 변수명 변경
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
    
       /* try {
            // 1. 원시 JSON 문자열 읽기
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonString = sb.toString();
            
            AppLog.debug("받은 JSON 문자열: " + jsonString);
            
            // 2. JSON 수동 파싱 (ObjectMapper 사용)
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            
            // 3. 각 필드 추출 (null 체크 포함)
            String empNo = jsonNode.get("empNo") != null ? jsonNode.get("empNo").asText() : null;
            String empPw = jsonNode.get("empPw") != null ? jsonNode.get("empPw").asText() : null;
            String loginType = jsonNode.get("loginType") != null ? jsonNode.get("loginType").asText() : null;
            
            AppLog.debug("파싱된 데이터 - empNo: " + empNo + ", empPw: " + empPw + ", loginType: " + loginType);
            
            // 4. 입력값 검증
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


            
            // 프레임워크의 로그인 프로세스를 호출 (UserController와 동일한 순서)
            LoginInfo info = loginProcess.processLogin(request, empNo, "EMPLOYEE", empPw);
            
            System.out.println(" 로그인 결과: " + info.toString());



			            


            if (info.isSuc()) {
                // 로그인 성공 처리
                try {
                    ProworksUserHeader userHeader = (ProworksUserHeader) request.getSession().getAttribute("userHeader");

                    if (userHeader != null) {
                        Map<String, Object> elData = new HashMap<>();
                        Map<String, Object> responseMap = new HashMap<>();

                        responseMap.put("status", "SUCCESS");
                        responseMap.put("empName", userHeader.getTestUserName());
                        responseMap.put("role", userHeader.getUserRole());

                        elData.put("dma_login_response", responseMap);

                        String jsonResponse = mapper.writeValueAsString(elData);

                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(jsonResponse);

                    } else {
                        throw new Exception("로그인 후 세션 정보를 가져오는 데 실패했습니다.");
                    }

                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    AppLog.error("로그인 성공 후 세션 처리 중 에러", e);
                    sendErrorResponse(response, "로그인 처리 중 오류가 발생했습니다.");
                }

            } else {
                // 로그인 실패 처리
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                sendErrorResponse(response, "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
            }
            
        } catch (Exception e) {
            AppLog.error("JSON 파싱 중 오류 발생", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendErrorResponse(response, "요청 데이터를 처리할 수 없습니다: " + e.getMessage());
        }
    }*/

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
	
    /*public void login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//int id = loginVo.getEmpNo();
    	String id = loginVo.getUserId();
    	String pw = loginVo.getPw();
    	
    	//LoginInfo info = loginProcess.processLogin(request, id, pw);
    	
    	Object[] params = {"EMPLOYEE", pw};
    	
    	System.out.println(">>>>> 1. 화면에서 입력받은 id: " + id);
	    System.out.println(">>>>> 1. 화면에서 입력받은 pw: " + pw);
    	
    	// processLogin(request, id, pw, params[0], params[1], ...);
        LoginInfo info = loginProcess.processLogin(request, id, "EMPLOYEE", pw);
        AppLog.debug("- Login 정보 : " + info.toString()); 
    	
    	 if (info.isSuc()) {
    	 
    	 
    		 	// loginProcess가 세션을 만들지 않는 것에 대비하여, 여기서 직접 세션을 생성합니다.
		        HttpSession session = request.getSession(true);
		        
		        session.setAttribute("loginInfo", info); // info 객체나 user_id 등을 저장
		        session.setMaxInactiveInterval(30 * 60); // 예: 세션 유효 시간 30분 설정
		        
		        System.out.println(">>>>> [DEBUG] 세션 강제 생성 완료! Session ID: " + session.getId());
		        
		   try {
		     
		        ProworksUserHeader userHeader = (ProworksUserHeader) request.getSession().getAttribute("userHeader");
		        
		        
		         // DB에서 사용자 정보 조회
		        EmployeeVo searchEmpVo = new EmployeeVo();
		        searchEmpVo.setEmpNo(id);
	            EmployeeVo empInfo = employeeService.selectEmployee(searchEmpVo);
	            
	            
	             //if (empInfo != null) {
	             
	             if (userHeader != null) {
	                String employeeId = empInfo.getEmpNo(); // DB에서 가져온 실제 id 값
	                
	                
	                System.out.println(">>>>> DB에서 조회한 실무자 ID: " + employeeId);
	                
	                // 로그인 성공 및 세션 정보 조회 성공
                    Map<String, Object> elData = new HashMap<>();
                    Map<String, Object> responseMap = new HashMap<>();	              			            
		            
		            responseMap.put("status", "SUCCESS");
                    //responseMap.put("empName", empInfo.getEmpName());
                    //responseMap.put("role", empInfo.getRole());
                    responseMap.put("empName", userHeader.getTestUserName());
                    responseMap.put("role", userHeader.getUserRole());
                    
		            elData.put("dma_login_response", responseMap);
		            
            
		            ObjectMapper mapper = new ObjectMapper();
		            String jsonResponse = mapper.writeValueAsString(elData);
		
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write(jsonResponse);

			        } else {
			            //response.getWriter().write(jsonErrorResponse);
			             throw new Exception("로그인 후 세션 정보를 가져오는 데 실패했습니다.");
			        }
			        
			    } catch (Exception e) {
                // 세션 처리 중 발생한 에러에 대한 응답
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                AppLog.error("로그인 성공 후 세션 처리 중 에러", e);
                // (필요 시 에러 응답 JSON 생성)
            }

        } else {
            // 로그인 실패 시 처리
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Map<String, Object> errorData = new HashMap<>();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("errorMsg", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
            errorData.put("error", errorMap);

            ObjectMapper mapper = new ObjectMapper();
            String jsonErrorResponse = mapper.writeValueAsString(errorData);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonErrorResponse);
        }
    }*/

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

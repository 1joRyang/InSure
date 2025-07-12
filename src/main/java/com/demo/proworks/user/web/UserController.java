package com.demo.proworks.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hsqldb.SessionManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.proworks.user.vo.LoginVo;
import com.demo.proworks.user.vo.SimpleConfirmVo;
import com.demo.proworks.user.vo.SimpleLoginVo;
import com.demo.proworks.user.vo.SimplepwRegisterVo;
import com.demo.proworks.user.vo.UserListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.login.LoginException;
import com.inswave.elfw.login.LoginInfo;
import com.inswave.elfw.login.LoginProcessor;
import com.inswave.elfw.util.ElBeanUtils;

import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 컨트롤러
 * @description : 사용자정보 관련 처리를 담당하는 컨트롤러
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
public class UserController {
	
    /** UserService */
    @Resource(name = "userServiceImpl")
    private UserService userService;
	
    @Resource(name = "loginProcess")
	protected LoginProcessor loginProcess;
    
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;
    
    
	/**
	 * 로그인을 처리한다.
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "UserLogin")
    @RequestMapping(value = "UserLogin")
    @ElDescription(sub = "사용자로그인", desc = "사용자로그인을 처리한다.")
    public void login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String user_id = loginVo.getUserId();
    	String pw = loginVo.getPw();
    	
    	

    	Object[] params = {"USER", pw};
    	
    	
    	System.out.println(">>>>> 1. 화면에서 입력받은 id: " + user_id);
	    System.out.println(">>>>> 1. 화면에서 입력받은 pw: " + pw);
    	
    	LoginInfo info = loginProcess.processLogin(request, user_id, "USER" ,pw);
    	AppLog.debug("- Login 정보 : " + info.toString());  	
    	
    	
		    // 로그인 성공 시
	    if (info.isSuc()) {
	           

		        // loginProcess가 세션을 만들지 않는 것에 대비하여, 여기서 직접 세션을 생성합니다.
		        HttpSession session = request.getSession(true); // 기존 세션을 가져오거나 없으면 새로 생성
		
		        // 세션에 로그인 성공했다는 최소한의 정보라도 저장합니다.
		        // (이후 다른 인터셉터가 이 값을 확인할 수 있습니다)
		        session.setAttribute("loginInfo", info); // info 객체나 user_id 등을 저장
		        session.setMaxInactiveInterval(30 * 60); // 예: 세션 유효 시간 30분 설정
		        
		        System.out.println(">>>>> [DEBUG] 세션 강제 생성 완료! Session ID: " + session.getId());

	    
	    	try {
		     
		        ProworksUserHeader userHeader = (ProworksUserHeader) request.getSession().getAttribute("userHeader");
		        
		        // DB에서 사용자 정보 조회
		        UserVo searchUserVo = new UserVo();
		        searchUserVo.setUserId(user_id);
	            UserVo userInfo = userService.selectUser(searchUserVo);// 사용자 서비스 호출
	            
	            
	            if (userInfo != null) {
	                int customerId = userInfo.getId(); // DB에서 가져온 실제 id 값
	                String userIdString = userInfo.getUserId();
	                
	                System.out.println(">>>>> DB에서 조회한 사용자 ID: " + customerId);
	                System.out.println(">>>>> DB에서 조회한 사용자 userId: " + userIdString);
	                
	                // 간편비밀번호 등록 여부 확인
	                boolean hasSimplePassword = userService.hasSimplePassword(userInfo.getUserId());
   
	                
	                // 응답 JSON 생성
	                Map<String, Object> elData = new HashMap<>();
	                Map<String, Object> responseMap = new HashMap<>();
	                responseMap.put("id", customerId);
	                responseMap.put("userId", userIdString);
	                responseMap.put("hasSimplePassword", hasSimplePassword);
	                elData.put("dma_login_response", responseMap);
	
	                ObjectMapper mapper = new ObjectMapper();
	                String jsonResponse = mapper.writeValueAsString(elData);
	
	                response.setContentType("application/json");
	                response.setCharacterEncoding("UTF-8");
	                response.getWriter().write(jsonResponse);
            } else {
                // 사용자 정보를 찾을 수 없는 경우
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"사용자 정보를 찾을 수 없습니다.\"}");
            }
	
			} catch (Exception e) {
		            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		            response.getWriter().write("{\"error\":\"사용자 정보 조회 중 오류가 발생했습니다.\"}");
		            AppLog.error("사용자 정보 조회 실패", e);
		        }
		    } else {
		        // 로그인 실패 시 처리
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        Map<String, String> errorMap = new HashMap<>();
		        errorMap.put("error", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
		
		        ObjectMapper mapper = new ObjectMapper();
		        String jsonErrorResponse = mapper.writeValueAsString(errorMap);
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write(jsonErrorResponse);
		    }
		}
	        /*if (userHeader != null) {
	            try {
	                // 2. userHeader 객체에서 userId를 직접 가져옵니다.
	                //String customerId = userHeader.getUserId();
	                int customerId = id; 
	
	                // 3. 가져온 userId로 응답 JSON을 생성합니다.
	                Map<String, Object> elData = new HashMap<>();
	                Map<String, Integer> responseMap = new HashMap<>();
	                responseMap.put("id", customerId);
	                elData.put("dma_login_response", responseMap);
	
	                ObjectMapper mapper = new ObjectMapper();
	                String jsonResponse = mapper.writeValueAsString(elData);
	
	                response.setContentType("application/json");
	                response.setCharacterEncoding("UTF-8");
	                response.getWriter().write(jsonResponse);
	
	            } catch (Exception e) {
	                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                response.getWriter().write("{\"error\":\"응답 생성 중 오류가 발생했습니다.\"}");
	                AppLog.error("JSON 응답 생성 실패", e);
	            }
	        } else {
	             // 이 경우는 거의 발생하지 않지만, 만약을 대비한 방어 코드
	             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	             response.getWriter().write("{\"error\":\"세션에서 userHeader를 찾을 수 없습니다.\"}");
	        }
	    } else {
	        // 로그인 실패 시 처리
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        Map<String, String> errorMap = new HashMap<>();
	        errorMap.put("error", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
	        
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonErrorResponse = mapper.writeValueAsString(errorMap);
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonErrorResponse);
	    }
	}*/
    
    
    
     /**
     * 사용자정보 목록을 조회합니다.
     *
     * @param  userVo 사용자정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key = "UserList")
    @RequestMapping(value = "UserList")    
    @ElDescription(sub = "사용자 목록조회", desc = "페이징 처리하여 사용자 목록 조회를 한다.")               
    public UserListVo selectListUser(UserVo userVo) throws Exception {    	   	

        List<UserVo> userList = userService.selectListUser(userVo);                  
        long totCnt = userService.selectListCountUser(userVo);
	
		UserListVo retUserList = new UserListVo();
		retUserList.setUserVoList(userList); 
		retUserList.setTotalCount(totCnt);
		retUserList.setPageSize(userVo.getPageSize());
		retUserList.setPageIndex(userVo.getPageIndex());

        return retUserList;            
    }  
    
    
    
    /**
     * 간편비밀번호 로그인을 처리한다.
     *
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
     */
    @ElService(key = "SimpleLogin")
    @RequestMapping(value = "SimpleLogin")    
    @ElDescription(sub = "간편 비밀번호 로그인", desc = "간편 비밀번호 로그인을 한다.")               
    public void simpleLogin(SimpleLoginVo simpleLoginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String user_id = simpleLoginVo.getUserId();
	    String simple_pw = simpleLoginVo.getSimplePw();
	    
	    
	    
	    System.out.println(">>>>> 1. 화면에서 입력받은 id: " + user_id);
	    System.out.println(">>>>> 1. 화면에서 입력받은 simple_pw: " + simple_pw);
	    
    try {
        // 2. 로직 최적화: 비밀번호 검증을 먼저 수행합니다.
        // 이 메서드가 '사용자 없음'과 '비밀번호 틀림'을 모두 처리해줍니다.
        boolean isSimplePasswordValid = userService.checkSimplePassword(user_id, simple_pw);
        
        System.out.println(isSimplePasswordValid);
        

        if (isSimplePasswordValid) {
            // 비밀번호 검증 성공!
            AppLog.debug("간편 비밀번호 인증 성공 - 사용자 ID: " + user_id);

            // 2. 로직 최적화: 인증 성공 후에만 DB에서 사용자 정보를 조회하여 응답값을 만듭니다.
            UserVo searchUserVo = new UserVo();
            searchUserVo.setUserId(user_id);
            UserVo userInfo = userService.selectUser(searchUserVo);

            if (userInfo == null) {
                // 이 경우는 거의 발생하지 않지만 (checkSimplePassword 직후라), 안전을 위해 추가
                throw new Exception("인증 후 사용자 정보를 조회하는데 실패했습니다.");
            }

            int customerId = userInfo.getId();
            System.out.println(">>>>> DB에서 조회한 사용자 ID: " + customerId);

            // 세션에 로그인 정보 저장 (필요시)
            request.getSession().setAttribute("connectedUserId", customerId);

            // 응답 JSON 생성
            Map<String, Object> elData = new HashMap<>();
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("id", customerId);
            // 약속했던 대로 dma_login_response 키를 사용합니다.
            elData.put("dma_login_response", responseMap);

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(elData);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);

        } else {
            // 비밀번호 검증 실패 (사용자가 없거나, 비밀번호가 틀림)
            AppLog.warn("간편 비밀번호 인증 실패 - 사용자 ID: " + user_id);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401: 인증 실패
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", "아이디 또는 간편 비밀번호가 올바르지 않습니다.");
            errorMap.put("status", "failed");

            ObjectMapper mapper = new ObjectMapper();
            String jsonErrorResponse = mapper.writeValueAsString(errorMap);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonErrorResponse);
        }

    } catch (Exception e) {
        AppLog.error("간편 비밀번호 로그인 처리 중 오류 발생", e);

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", "로그인 처리 중 오류가 발생했습니다.");
        errorMap.put("status", "server_error");

        ObjectMapper mapper = new ObjectMapper();
        String jsonErrorResponse = mapper.writeValueAsString(errorMap);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonErrorResponse);
    }
}

    /**
     * 간편비밀번호를 등록(임시저장)한다. (1단계)
     *
	 * @param registerVo 간편비밀번호 등록 정보 registerVo
	 * @param request    요청 정보 HttpServletRequest
	 * @param response   응답 정보 HttpServletResponse
	 * @throws Exception
	 */
    @ElService(key = "SimplePwRegister")
    @RequestMapping(value = "SimplePwRegister")    
    @ElDescription(sub = "간편 비밀번호 등록", desc = "간편 비밀번호를 등록한다.")   
	public void registerSimplePassword(SimplepwRegisterVo registerVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
	    Map<String, Object> responseData = new HashMap<>();
	    try {
	        // 서비스 호출
	        userService.temporarilyStorePin(registerVo.getUserId(), registerVo.getSimplePw(), request.getSession());
	
	        // 성공 시 응답 데이터 구성
	        responseData.put("resultCode", "OK");
	        responseData.put("resultMessage", "확인 단계로 진행합니다.");
	
	    } catch (Exception e) {
	        // 서비스에서 Exception 발생 시 실패 응답 구성
	        responseData.put("resultCode", "FAIL");
	        responseData.put("resultMessage", e.getMessage());
	    }
	    
	    Map<String, Object> elData = new HashMap<>();
	    elData.put("dma_login_response", responseData);
	    
	    // JSON으로 변환하여 응답 작성
	    ObjectMapper objectMapper = new ObjectMapper();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    response.getWriter().write(objectMapper.writeValueAsString(elData));
	}
	

	
	
	 /**
     * 간편비밀번호 확인 및 최종 등록을 처리한다.(2단계)
     *
	 * @param confirmVo 간편비밀번호 확인 정보 confirmVo
	 * @param request   요청 정보 HttpServletRequest
	 * @param response  응답 정보 HttpServletResponse
	 * @throws Exception
	 */
    @ElService(key = "SimplePwRegisterCheck")
    @RequestMapping(value = "SimplePwRegisterCheck")    
    @ElDescription(sub = "간편 비밀번호 최종등록", desc = "간편 비밀번호 최종등록을 한다.")    	 
	public void confirmSimplePassword(SimpleConfirmVo confirmVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Map<String, Object> responseData = new HashMap<>();
	    
	    // 서비스 호출하여 성공 여부(boolean) 받기
	    boolean isSuccess = userService.confirmAndSavePin(confirmVo.getUserId(), confirmVo.getSimplePw(), request.getSession());
	
	    if (isSuccess) {
	        // 성공 시 응답
	        responseData.put("resultCode", "OK");
	        responseData.put("resultMessage", "간편비밀번호가 등록되었습니다.");
	    } else {
	        // 실패 시 응답
	        responseData.put("resultCode", "FAIL");
	        responseData.put("resultMessage", "비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
	    }
	    
	    // dma_login_response 키로 한번 더 감싸주는 로직 추가
	    Map<String, Object> elData = new HashMap<>();
	    elData.put("dma_login_response", responseData);
	
	    // JSON으로 변환하여 응답 작성
	    ObjectMapper objectMapper = new ObjectMapper();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(objectMapper.writeValueAsString(elData));
	}
		
        
    /**
     * 사용자정보을 단건 조회 처리 한다.
     *
     * @param  userVo 사용자정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "UserUpdView")    
    @RequestMapping(value="UserUpdView") 
    @ElDescription(sub = "사용자정보 갱신 폼을 위한 조회", desc = "사용자정보 갱신 폼을 위한 조회를 한다.")    
    public UserVo selectUser(UserVo userVo) throws Exception {
    	UserVo selectUserVo = userService.selectUser(userVo);    	    
		
        return selectUserVo;
    } 
 
    /**
     * 사용자정보를 등록 처리 한다.
     *
     * @param  userVo 사용자정보
     * @throws Exception
     */
    @ElService(key="UserIns")    
    @RequestMapping(value="UserIns")
    @ElDescription(sub="사용자정보 등록처리",desc="사용자정보를 등록 처리 한다.")
    public void insertUser(UserVo userVo) throws Exception {    	 
    	userService.insertUser(userVo);   
    }
       
    /**
     * 사용자정보를 갱신 처리 한다.
     *
     * @param  userVo 사용자정보
     * @throws Exception
     */
    @ElService(key="UserUpd")    
    @RequestMapping(value="UserUpd")    
    @ElValidator(errUrl="/user/userRegister", errContinue=true)
    @ElDescription(sub="사용자정보 갱신처리",desc="사용자정보를 갱신 처리 한다.")    
    public void updateUser(UserVo userVo) throws Exception {  
 
    	userService.updateUser(userVo);                                            
    }

    /**
     * 사용자정보를 삭제 처리한다.
     *
     * @param  userVo 사용자정보    
     * @throws Exception
     */
    @ElService(key = "UserDel")    
    @RequestMapping(value="UserDel")
    @ElDescription(sub = "사용자정보 삭제처리", desc = "사용자정보를 삭제 처리한다.")    
    public void deleteUser(UserVo userVo) throws Exception {
        userService.deleteUser(userVo);
    }
   
}

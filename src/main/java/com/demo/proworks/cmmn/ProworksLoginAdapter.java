package com.demo.proworks.cmmn;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.login.LoginAdapter;
import com.inswave.elfw.login.LoginException;
import com.inswave.elfw.login.LoginInfo;
import com.inswave.elfw.util.ElBeanUtils;

import com.demo.proworks.emp.service.EmpService;
import com.demo.proworks.emp.vo.EmpVo;
import com.demo.proworks.employee.service.EmployeeService;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.user.dao.UserDAO;
import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;

/**
 * @subject		: ProworksLoginAdapter.java 
 * @description : 프로젝트 로그인 어댑터
 * @author		: 개발팀
 * @since 		: 2025/05/19
 * @modification
 * ===========================================================
 * DATE				AUTHOR				NOTE
 * ===========================================================
 * 2025/05/19		샘플개발팀			최초 생성
 * 
 */
public class ProworksLoginAdapter extends LoginAdapter {

    
	/**
	 * 데모용 로그인 어댑터의 생성자
	 * @param adapterInfoMap Adapter 정보
	 */
	public ProworksLoginAdapter(Map<String, Object> adapterInfoMap){
		super(adapterInfoMap);
	}

	/**
	 * 로그인 처리를 담당하는 구현체 메소드.
	 * 프레임워크 DefaultLoginAdapter 추상클래스의 로그인 구현체 메소드
	 * @param request
	 * @param id
	 * @param params 기타 동적 파라미터에 추가할 수 있다.(ex. 서비스 구현체 )
	 * @return LoginInfo
	 * @throws LoginException
	 */
	@Override
	public LoginInfo login(HttpServletRequest request, String id, Object... params) throws LoginException {
	    String loginType = (String) params[0];
	    String rawPassword  = (String) params[1];
	
	    try {
	        if ("USER".equals(loginType)) {	            	            
	            
	            UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
	            PasswordEncoder passwordEncoder = (PasswordEncoder) ElBeanUtils.getBean("passwordEncoder");

			    
			    AppLog.debug("로그인 어댑터 인코더 객체: " + passwordEncoder.toString());		    
			    
			    
			    UserVo userVoParam = new UserVo();
			
			    userVoParam.setUserId(id);

			    UserVo storedUser = userService.selectUser(userVoParam);			    
			
			    if (storedUser == null || !passwordEncoder.matches(rawPassword, storedUser.getPw())) {
			        //실패 시 에러 처리
			        throw new LoginException("아이디 또는 비밀번호가 일치하지 않습니다.");
			    }
	            
	            

	        } else if ("SIMPLE".equals(loginType)) {
	            UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
	            PasswordEncoder passwordEncoder = (PasswordEncoder) ElBeanUtils.getBean("passwordEncoder");

			    AppLog.debug("간편 로그인 어댑터 인코더 객체: " + passwordEncoder.toString());
			
			    UserVo userVoParam = new UserVo();
			
			    userVoParam.setUserId(id);
			
			    UserVo storedUser = userService.selectUser(userVoParam);
			
			    // 간편 비밀번호 검증
			    if (storedUser == null || !userService.checkSimplePassword(id, rawPassword) || 
			        !passwordEncoder.matches(rawPassword, storedUser.getSimplePw())) {
			        throw new LoginException("간편 비밀번호가 일치하지 않습니다.");
			    }

 
	            
	            /*if (!userService.checkSimplePassword(id, rawPassword)) {
	                throw new LoginException("간편 비밀번호가 일치하지 않습니다.");
	            }*/
	        } else if ("EMPLOYEE".equals(loginType)) {
	            EmployeeService empService = (EmployeeService) ElBeanUtils.getBean("employeeServiceImpl");
	            PasswordEncoder passwordEncoder = (PasswordEncoder) ElBeanUtils.getBean("passwordEncoder");
	            
	            
	            EmployeeVo empVoParam = new EmployeeVo();
	            empVoParam.setEmpNo(id);
	            System.out.println("empVoParam: "+ empVoParam);
	            EmployeeVo storedEmp = empService.selectEmployee(empVoParam);
	            
	            System.out.println("storedEmp " + storedEmp);
	          
	             // 직원 존재 여부 및 암호화된 비밀번호를 비교
			    if (storedEmp == null || !passwordEncoder.matches(rawPassword, storedEmp.getEmpPw())) {
			        throw new LoginException("실무자 아이디 또는 비밀번호가 일치하지 않습니다.");
			    }
	            
	        

	        } else {
	            throw new LoginException("지원하지 않는 로그인 방식입니다.");
	        }
	    
			     // 로그
		        AppLog.debug("LoginAdapter: 인증 성공. 세션에 loginType 저장 시도. Type: " + loginType);
		        request.getSession().setAttribute("loginTypeForSession", loginType);
		        AppLog.debug("LoginAdapter: 세션에 loginType 저장 완료.");
	        	
	    } catch (LoginException e) {
	    	 // 실패 로그
	    	AppLog.error("LoginAdapter: 인증 실패", e);	    
	        throw e; 
	    } catch (Exception e) {
	        AppLog.error("Login-Adapter Error", e);
	        throw new LoginException("로그인 처리 중 시스템 오류가 발생했습니다.");
	    }
	
	    // 모든 검증이 성공 시
	    LoginInfo info = new LoginInfo();
	    info.setSuc(true);
	    return info;
	}
	
	
	
	

	/**
	 * 로그아웃 처리를 담당하는 구현체 메소드.
	 * 프레임워크 DefaultLoginAdapter 추상클래스의 로그아웃 구현체 메소드
	 * @param request
	 * @param id
	 * @param params 기타 동적 파라미터에 추가할 수 있다.
	 * @return LoginInfo
	 * @throws LoginException
	 */
	@Override
	public LoginInfo logout(HttpServletRequest request, String id, Object... params) throws LoginException {
		LoginInfo info = new LoginInfo();
		try{			
			// 세션 무효화
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            AppLog.debug("로그아웃 처리 - 세션 무효화 시작");
	            session.invalidate();
	        }
	        
	        // 로그아웃 성공 설정 
	        info.setSuc(true);
	        AppLog.debug("[Logout] Proworks Logout 성공.....");
	        
	    } catch(Exception e) {
	        AppLog.error("로그아웃 처리 중 오류 발생", e);
	        throw new LoginException(e);
	    }
	    return info;
	}

}

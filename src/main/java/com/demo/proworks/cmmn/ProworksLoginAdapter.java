package com.demo.proworks.cmmn;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	 * 데모용 로그인 처리를 담당하는 구현체 메소드.
	 * 프레임워크 DefaultLoginAdapter 추상클래스의 로그인 구현체 메소드
	 * @param request
	 * @param id
	 * @param params 기타 동적 파라미터에 추가할 수 있다.(ex. 서비스 구현체 )
	 * @return LoginInfo
	 * @throws LoginException
	 */
	@Override
	public LoginInfo login(HttpServletRequest request, String id, Object... params) throws LoginException {


/*
		// 로그인 체크를 수행  (샘플 예제)
		try{
			String pw = (String)params[0];
			EmpService empService = (EmpService)ElBeanUtils.getBean("empServiceImpl");
			EmpVo empVo = new EmpVo();

			empVo.setEmpno(Integer.parseInt(id) );
			EmpVo resEmpVo = empService.selectEmp(empVo);

			if( resEmpVo == null ) {
				throw new LoginException("EL.ERROR.LOGIN.0001");
			}
			
			String resPw = String.valueOf(resEmpVo.getMgr());
			if(pw == null || !pw.equals(resPw)){
				throw new LoginException("EL.ERROR.LOGIN.0002");
			}
		}catch(NumberFormatException e){
			AppLog.error("login Error1",e);
			throw new LoginException("EL.ERROR.LOGIN.0001");
		}catch(ElException e){
			AppLog.error("login Error2",e);
			throw e;		
		}catch(Exception e){
			AppLog.error("login Error3",e);
			throw new LoginException("EL.ERROR.LOGIN.0003");
		}

		
		// 3. 로그인 성공 설정 
		LoginInfo info = new LoginInfo();		
		info.setSuc(true);
		AppLog.debug("[Login] Proworks Login 성공.....");
			
		return info;
	*/	
		
		
		// Controller에서 보낸 '신호'와 '비밀번호'를 꺼냅니다.
	    String loginType = (String) params[0];
	    String rawPassword  = (String) params[1];
	    System.out.println("id 입니다 "+id);
//	    System.out.println("pw"+rawPassword);
	    System.out.println("로그 " + Arrays.toString(params));
	    System.out.println((String)params[0]);
	    System.out.println((String)params[1]);
	
	    try {
	        if ("USER".equals(loginType)) {	            	            
	            
	            
	            // --- 일반 사용자 비밀번호 검증 ---
	            UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
	            PasswordEncoder passwordEncoder = (PasswordEncoder) ElBeanUtils.getBean("passwordEncoder");

			    
			    AppLog.debug("[확인] 로그인 어댑터 인코더 객체: " + passwordEncoder.toString());		    
			    
			    
			    // 1. ID를 담을 새로운 UserVo 객체를 생성합니다.
			    UserVo userVoParam = new UserVo();
			
			    // 2. 컨트롤러에서 전달받은 id 값을 UserVo에 담아줍니다.
			    userVoParam.setUserId(id);
			    //userVoParam.setPw(rawPassword);
			
			    // 3. ID가 담긴 UserVo를 파라미터로 넘겨서 사용자 정보를 조회합니다.
			    UserVo storedUser = userService.selectUser(userVoParam);			    
			
			
			    // 이제 storedUser는 DB에서 가져온 사용자 정보를 담고 있습니다.
			    if (storedUser == null || !passwordEncoder.matches(rawPassword, storedUser.getPw())) {
			        // ... 실패 시 에러 처리 ...
			        throw new LoginException("아이디 또는 비밀번호가 일치하지 않습니다.");
			    }
	            
	            
	            /*UserVo resUserVo = userService.selectUser(new UserVo());
	            if (resUserVo == null || !password.equals(resUserVo.getPw())) {
	                throw new LoginException("아이디 또는 비밀번호가 일치하지 않습니다.");
	            }*/
	        } else if ("SIMPLE".equals(loginType)) {
	            // --- 간편 비밀번호 검증 ---
	            UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
	            PasswordEncoder passwordEncoder = (PasswordEncoder) ElBeanUtils.getBean("passwordEncoder");

			    AppLog.debug("[확인] 간편 로그인 어댑터 인코더 객체: " + passwordEncoder.toString());
			
			    // 1. ID를 담을 새로운 UserVo 객체를 생성합니다.
			    UserVo userVoParam = new UserVo();
			
			    // 2. 컨트롤러에서 전달받은 id 값을 UserVo에 담아줍니다.
			    userVoParam.setUserId(id);
			
			    // 3. ID가 담긴 UserVo를 파라미터로 넘겨서 사용자 정보를 조회합니다.
			    UserVo storedUser = userService.selectUser(userVoParam);
			
			    // 간편 비밀번호 검증 (간편 비밀번호도 암호화되어 저장되어 있다고 가정)
			    if (storedUser == null || !userService.checkSimplePassword(id, rawPassword) || 
			        !passwordEncoder.matches(rawPassword, storedUser.getSimplePw())) {
			        throw new LoginException("간편 비밀번호가 일치하지 않습니다.");
			    }

 
	            
	            /*if (!userService.checkSimplePassword(id, rawPassword)) {
	                throw new LoginException("간편 비밀번호가 일치하지 않습니다.");
	            }*/
	        } else if ("EMPLOYEE".equals(loginType)) {
	            // --- 실무자 비밀번호 검증 ---
	            EmployeeService empService = (EmployeeService) ElBeanUtils.getBean("employeeServiceImpl");
	            PasswordEncoder passwordEncoder = (PasswordEncoder) ElBeanUtils.getBean("passwordEncoder");
	            
	            
	            EmployeeVo empVoParam = new EmployeeVo();
	            empVoParam.setEmpNo(id);
	            System.out.println("empVoParam: "+ empVoParam);
	            EmployeeVo storedEmp = empService.selectEmployee(empVoParam);
	            
	            System.out.println("storedEmp " + storedEmp);
	          
	             // 3. 직원 존재 여부 및 암호화된 비밀번호를 비교합니다.
			    // ※ EmpVo에 DB의 암호화된 비밀번호를 반환하는 getPassword() 메소드가 있다고 가정합니다.
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
	        throw e; // LoginException은 그대로 전달
	    } catch (Exception e) {
	        AppLog.error("Login-Adapter Error", e);
	        throw new LoginException("로그인 처리 중 시스템 오류가 발생했습니다.");
	    }
	
	    // 모든 검증이 성공하면, 다음 단계로 넘어갈 수 있도록 성공 정보만 담아서 반환
	    LoginInfo info = new LoginInfo();
	    info.setSuc(true);
	    return info;
	}
	
	
	
	

	/**
	 * 데모용 로그아웃 처리를 담당하는 구현체 메소드.
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
			// 1. 로그아웃 처리로직 추가
			
			// 2. 로그아웃 성공 설정 
			info.setSuc(true);
			AppLog.debug("[Logout] Proworks Logout 성공.....");
			
		}catch(Exception e){
			throw new LoginException(e);
		}		
		return info;
	}

}

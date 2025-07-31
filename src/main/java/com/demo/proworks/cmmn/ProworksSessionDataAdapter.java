package com.demo.proworks.cmmn;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.inswave.elfw.adapter.AdapterException;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.session.SessionDataAdapter;
import com.inswave.elfw.util.ElBeanUtils;

import com.demo.proworks.emp.service.EmpService;
import com.demo.proworks.emp.vo.EmpVo;
import com.demo.proworks.employee.service.EmployeeService;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;

/**  
 * @Class Name : ProworksSessionDataAdapter.java
 * @Description : 프로젝트 세션 데이터 어댑터 - 로그인 후 사용자 헤더 정보를 Setting 한다. 
 * @Modification Information  
 * @
 * @  수정일                  수정자                  수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.08.01   EL개발팀             최초생성
 * 
 * @author EL개발팀
 * @since 2013.08.01
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Inswave All right reserved.
 */
public class ProworksSessionDataAdapter extends SessionDataAdapter {
	/**
	 * SessionAdapter 생성자이다.
	 * 
	 * @param adapterInfoMap Adapter 정보
	 */
	public ProworksSessionDataAdapter(Map<String, Object> adapterInfoMap) {
		super(adapterInfoMap);
	}

	/**
	 * 데모용 세션 터이터의 로드를 담당하는 구현체 메소드.
	 * - 프레임워크 SessionDataAdapter 추상클래스의 세션 데이터를 Set 하는 구현체 메소드
	 * - 프로젝트에 필요한 헤더 정보를 세팅한다. 
	 * -  해당 헤더 정보는 로그인 후에 사용가능하다. 
	 * 
	 * @param request HttpServletRequest
	 * @param id
	 * @param obj 기타 동적 파라미터에 추가할 수 있다.(ex. 서비스 구현체 )
	 * @return ProworksUserHeader
	 * @throws AdapterException
	 */
	@Override
	public ProworksUserHeader setSessionData(HttpServletRequest request, String id, Object... obj) throws AdapterException{
		
		/*
		// 로그인 후에 id 기반으로 세션 정보를 세팅하여 반환한다.		
		ProworksUserHeader userHeader = new ProworksUserHeader();
		userHeader.setUserId( id );
		
		

		// 사용자 세션을 UserHeader 에 설정 (샘플 예제)
		try{
			EmpService empService = (EmpService)ElBeanUtils.getBean("empServiceImpl");
			EmpVo empVo = new EmpVo();

			empVo.setEmpno(Integer.parseInt(id));
			EmpVo resEmpVo = empService.selectEmp(empVo);

			if( resEmpVo == null ) {
				throw new AdapterException("EL.ERROR.LOGIN.0004", new String[]{id});
			}
			
			// 사용자 세션 설정
			userHeader.setTestDeptNo(resEmpVo.getDeptno());
			userHeader.setTestDeptName(resEmpVo.getDname());
		}catch(ElException e){
			AppLog.error("setSessionData Error1",e);
			throw e;
		}catch(Exception e){
			AppLog.error("setSessionData Error2",e);
			throw new AdapterException("EL.ERROR.LOGIN.0005");
		}
		
		return userHeader;
		*/
		AppLog.debug("=== [SessionDataAdapter] 세션 생성 시작 ===");
	    AppLog.debug("- 로그인 ID: " + id);
	    
	    // 로그인 후에 id 기반으로 세션 정보를 세팅하여 반환한다.		
	    ProworksUserHeader userHeader = new ProworksUserHeader();
	    userHeader.setUserId(id);
	    
	    // 로그인 시 사용했던 '로그인 타입'을 가져옵니다.
	    String loginType = (String) request.getSession().getAttribute("loginTypeForSession");
	    AppLog.debug("- 세션에서 가져온 loginType: " + loginType);
	    
	    try {
	        if ("USER".equals(loginType)) {
	            AppLog.debug("- USER 타입으로 세션 생성 중...");
	            
	            UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
	            UserVo userVoParam = new UserVo();
	            userVoParam.setUserId(id);
	            UserVo resUserVo = userService.selectUser(userVoParam);
	            
	            if (resUserVo == null) throw new AdapterException("세션 정보를 생성할 사용자가 없습니다. ID: " + id);
	
	            userHeader.setTestUserName(resUserVo.getUserName());
	            userHeader.setUserRole("USER");
	            
	            AppLog.debug("- USER 세션 생성 완료: " + resUserVo.getUserName());
	
	        } else if ("EMPLOYEE".equals(loginType)) {
	            AppLog.debug("- EMPLOYEE 타입으로 세션 생성 중...");
	            
	            EmployeeService empService = (EmployeeService) ElBeanUtils.getBean("employeeServiceImpl");
	            EmployeeVo empVoParam = new EmployeeVo();
	            
	            empVoParam.setEmpNo(id);
	            EmployeeVo resEmpVo = empService.selectEmployee(empVoParam);
	            
	            if (resEmpVo == null) {
	                AppLog.error("- EMPLOYEE 정보 조회 실패: ID=" + id);
	                throw new AdapterException("세션 정보를 생성할 관리자가 없습니다. ID: " + id);
	            }
	
	            userHeader.setTestUserName(resEmpVo.getEmpName());
	            userHeader.setUserRole(resEmpVo.getRole());
	            
	            AppLog.debug("- EMPLOYEE 세션 생성 완료: " + resEmpVo.getEmpName() + ", 역할: " + resEmpVo.getRole());
	            
	        } else {
	            AppLog.error("- 알 수 없는 로그인 타입: " + loginType);
	            throw new AdapterException("알 수 없는 로그인 타입입니다: " + loginType);
	        }
	        
	        AppLog.debug("- 최종 UserHeader 정보:");
	        AppLog.debug("  * UserId: " + userHeader.getUserId());
	        AppLog.debug("  * UserName: " + userHeader.getTestUserName());
	        AppLog.debug("  * UserRole: " + userHeader.getUserRole());
	        
	        AppLog.debug("=== [SessionDataAdapter] 세션 생성 완료 ===");
	
	    } catch (Exception e) {
	        AppLog.error("setSessionData Error", e);
	        throw new AdapterException("세션 데이터 생성 중 오류가 발생했습니다: " + e.getMessage());
	    } finally {
	        request.getSession().removeAttribute("loginTypeForSession");
	    }
	
	    return userHeader;
	}
		/*
		
		// 로그인 후에 id 기반으로 세션 정보를 세팅하여 반환한다.		
		ProworksUserHeader userHeader = new ProworksUserHeader();
		userHeader.setUserId( id );
		
		
		
		// 로그인 시 사용했던 '로그인 타입'을 가져옵니다.
		// ※ 이 값을 LoginAdapter에서 SessionDataAdapter로 넘기는 방법이 필요할 수 있습니다.
		// 가장 쉬운 방법은 로그인 성공 직후 세션에 임시로 저장했다가 여기서 꺼내 쓰는 것입니다.
		String loginType = (String) request.getSession().getAttribute("loginTypeForSession");
		
		
		try {
        if ("USER".equals(loginType)) {
            UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
            UserVo userVoParam = new UserVo();
            userVoParam.setUserId(id);
            UserVo resUserVo = userService.selectUser(userVoParam);
            
            if (resUserVo == null) throw new AdapterException("세션 정보를 생성할 사용자가 없습니다. ID: " + id);

            userHeader.setTestUserName(resUserVo.getUserName());
            userHeader.setUserRole("USER"); 

        } else if ("EMPLOYEE".equals(loginType)) { // 관리자 로그인 타입
            EmployeeService empService = (EmployeeService) ElBeanUtils.getBean("employeeServiceImpl");
            EmployeeVo empVoParam = new EmployeeVo();
            
            empVoParam.setEmpNo(id);
            EmployeeVo resEmpVo = empService.selectEmployee(empVoParam);
            
            if (resEmpVo == null) throw new AdapterException("세션 정보를 생성할 관리자가 없습니다. ID: " + id);

            userHeader.setTestUserName(resEmpVo.getEmpName()); // 관리자 이름 설정
            userHeader.setUserRole(resEmpVo.getRole());
        }
        
        AppLog.debug("세션 생성 완료. 사용자: " + userHeader.getTestUserName() + ", 역할: " + userHeader.getUserRole());

		} catch (Exception e) {
	        AppLog.error("setSessionData Error", e);
	        throw new AdapterException("세션 데이터 생성 중 오류가 발생했습니다.");
		} finally {
        
			request.getSession().removeAttribute("loginTypeForSession");
		}

		return userHeader;
		
		}*/

}

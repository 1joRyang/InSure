package com.demo.proworks.cmmn;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inswave.elfw.core.UserHeader;
import com.inswave.elfw.exception.UserException;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.util.ControllerContextUtil;



public class ProworksAuthProcess {
    
    // USER 전용 서비스 목록 (일반 사용자만 접근 가능)
    private static final List<String> USER_ONLY_SERVICES = Arrays.asList(
        /*
        "UserLogin",           // 사용자 로그인
        "SimpleLogin",         // 간편 비밀번호 로그인  
        "SimplePwRegister",    // 간편 비밀번호 등록
        "SimplePwRegisterCheck" // 간편 비밀번호 확인*/

    );
    
    // EMPLOYEE 전용 서비스 목록 (실무자/관리자만 접근 가능)  
    private static final List<String> EMPLOYEE_ONLY_SERVICES = Arrays.asList(
        /*"EmployeeLogin",       // 실무자 로그인
        "EmployeeForRule",  // 특정 조건으로 실무자 조회
        "EmployeeList",      // 실무자,관리자정보 목록 조회
        "EmployeeUpdView",        // 실무자,관리자정보을 단건 조회 처리
        "EmployeeIns",       // 실무자,관리자정보를 등록 처리
        "EmployeeUpd",       // 실무자,관리자정보를 갱신 처리
        "EmployeeDel",		 // 실무자,관리자정보를 삭제 처리
        "UserList",			 //사용자 목록조회
        "UserUpdView",		 //사용자정보를 단건 조회 처리
        "UserIns",			 //사용자정보를 등록 처리
        "UserUpd",			 //사용자정보를 갱신 처리
        "UserDel",			 //사용자정보를 삭제 처리
        "getUserInfo",		//로그인한 사용자의 기본 정보를 조회
        "UserInfoByRrn",		//주민번호로 사용자 기본정보 조회
        "getUserInfo",		//로그인한 사용자의 기본 정보를 조회
        "CLAIMManagerwStatusList",		//관리자 청구 목록 조회
        "CLAIMwStatusList",		//내 청구 목록 조회
        "CLAIMwStatusListWait",		//내 청구 목록 조회
        "CLAIMnCResultList",		//기지급이력 조회
        "CLAIMList",		//청구 목록 조회
        "CLAIMUpdView",		//청구를 단건 조회 처리
        "CLAIMFindUserEmpInfo",		//청구 번호로 사용자,실무자 찾기
        "CLAIMUpd",				//청구를 갱신 처리
        "CLAIMDel",				//청구를 삭제 처리
        "CLAIMEmployeeList",				//청구와 직원 정보 조인 목록 조회
        "CLAIMUpdAssignee",				//청구 담당자 업데이트
        "CLAIMFullJoinList",				//청구와 사용자, 직원, 결과 정보 전체 조인 목록 조회
        "CLAIMFullJoinListByRrn"		//주민번호로 청구와 사용자, 직원, 결과 정보 전체 조인 목록 조회	*/
           
    );
    
    // 공통 서비스 목록 (모든 로그인 사용자가 접근 가능)
    private static final List<String> COMMON_SERVICES = Arrays.asList(
        "Logout"              // 로그아웃

    );
    
    // 로그인 불필요 서비스 목록 (인증 없이 접근 가능)
    private static final List<String> PUBLIC_SERVICES = Arrays.asList(
        "UserLogin",
        "SimpleLogin", 
        "EmployeeLogin",
        "websocket0004SendTestNotification"


        // 필요에 따라 추가...
    );

    public ProworksAuthProcess() {
    }

    public void checkAuth(HttpServletRequest request, String svcId, String inputData) throws UserException {
        
        AppLog.debug("[ProworksAuthProcess] 권한 체크 시작 - svcId: " + svcId);
        
        try {
        
            // 1. 로그인 불필요 서비스인지 체크
            if (isPublicService(svcId)) {
                AppLog.debug("[ProworksAuthProcess] 공개 서비스 - 권한 체크 생략: " + svcId);
                return;
            }
            
            // 2. 세션에서 사용자 헤더 정보 가져오기
            UserHeader userHeader = ControllerContextUtil.getUserHeader();
            
            if (userHeader == null) {
                AppLog.warn("[ProworksAuthProcess] 세션이 존재하지 않음 - svcId: " + svcId);
                throw new UserException("ERR.USER.0002"); // 세션이 존재하지 않습니다.
            }
            
            if (!(userHeader instanceof ProworksUserHeader)) {
                AppLog.warn("[ProworksAuthProcess] 잘못된 사용자 헤더 타입");
                throw new UserException("ERR.USER.0002"); // 세션이 존재하지 않습니다.
            }
            
            ProworksUserHeader siteUserHeader = (ProworksUserHeader) userHeader;
            String userId = siteUserHeader.getUserId();
            String userRole = siteUserHeader.getUserRole();
            
            AppLog.debug("[ProworksAuthProcess] 사용자 정보 - ID: " + userId + ", Role: " + userRole);
            
            // 3. 역할별 서비스 접근 권한 체크
            if (!hasServiceAccess(svcId, userRole)) {
                AppLog.warn("[ProworksAuthProcess] 서비스 접근 권한 없음 - User: " + userId + 
                           ", Role: " + userRole + ", Service: " + svcId);
                throw new UserException("ERR.USER.0003"); // 권한이 존재하지 않습니다.
            }
            
            AppLog.debug("[ProworksAuthProcess] 권한 체크 성공 - User: " + userId + ", Service: " + svcId);
            
        } catch (UserException ue) {
            AppLog.error("ProworksAuthProcess-UserException", ue);
            throw ue;
        } catch (Exception e) {
            AppLog.error("ProworksAuthProcess-Exception", e);
            throw new UserException("ERR.USER.0004"); // 권한 체크 중 오류가 발생했습니다.
        }
    }
    
    /**
     * 로그인 불필요 서비스인지 확인
     */
    private boolean isPublicService(String svcId) {
        return PUBLIC_SERVICES.contains(svcId);
    }
    
    /**
     * 사용자 역할에 따른 서비스 접근 권한 체크
     */
    private boolean hasServiceAccess(String svcId, String userRole) {
        
        // 공통 서비스는 모든 로그인 사용자 접근 가능
        if (COMMON_SERVICES.contains(svcId)) {
            return true;
        }
        
        // USER 역할 체크
        if ("USER".equals(userRole)) {
            // USER 전용 서비스에만 접근 가능
            if (USER_ONLY_SERVICES.contains(svcId)) {
                return true;
            }
            // EMPLOYEE 전용 서비스는 접근 불가
            if (EMPLOYEE_ONLY_SERVICES.contains(svcId)) {
                return false;
            }
            // 그 외 서비스는 기본적으로 접근 가능 (필요시 수정)
            return true;
        }
        

        // EMPLOYEE 역할 체크 (ADMIN, MANAGER 등 포함)

        if ("ADMIN".equals(userRole) || "MANAGER".equals(userRole) || "EMPLOYEE".equals(userRole)||
        "실무자".equals(userRole) || "관리자".equals(userRole)) {
            // EMPLOYEE는 USER 전용 서비스 제외하고 모든 서비스 접근 가능
            if (USER_ONLY_SERVICES.contains(svcId)) {
                return false;
            }
            return true;
        }
        
        
        // 정의되지 않은 역할은 접근 불가
        AppLog.warn("[ProworksAuthProcess] 정의되지 않은 사용자 역할: " + userRole);
        return false;
    }
}	
		
		
		
		
		
		
		/*public class ProworksAuthProcess {


	public ProworksAuthProcess(){
	
	}
	
	public void checkAuth(HttpServletRequest request, String svcId, String inputData) throws UserException{
	
		UserHeader userHeader = ControllerContextUtil.getUserHeader();
		
		
		
//		try{
//			if( userHeader != null ) {  // 세션이 존재함 
//				if( userHeader instanceof ProworksUserHeader ) {
//					ProworksUserHeader siteUserHeader = (ProworksUserHeader)userHeader;
//					String userId = siteUserHeader.getUserId();
//					
//					// userId 기반으로   ServiceImple 을 사용하여 권한등 체크로직 수행 -> 아래는 ElBeanUtils 를 사용해서 권한 체크 하라는 예시 
//					EmpService empService = (EmpService)ElBeanUtils.getBean("empServiceImpl");  
//					boolean bCheck = false;
//					//..... 권한 체크 로직 수행 
//					
//					if( false == bCheck) { // 권한 체크 결과 
//					    throw new UserException("ERR.USER.0003");  // 권한이 존재하지 않습니다.
//					}
//				} else {
//					throw new UserException("ERR.USER.0002");  // 세션이 존재하지 않습니다.
//				}
//			} else { /// 세션이 존재하지 않으면 
//				throw new UserException("ERR.USER.0002");  // 세션이 존재하지 않습니다.
//			}
//		}catch(UserException ue){
//			AppLog.error("CommAuthProcess-UserException", ue);
//			throw ue;
//		}catch(Exception e){
//			AppLog.error("CommAuthProcess-Exception", e);
//			throw new UserException("ERR.USER.0003");  // 기타 에러 메시지....
//		}
	
	}
	
	
}*/

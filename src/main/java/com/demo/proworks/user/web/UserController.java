package com.demo.proworks.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hsqldb.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;
import com.demo.proworks.user.vo.LoginVo;
import com.demo.proworks.user.vo.SimpleLoginVo;
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
    
	/**
	 * 로그인을 처리한다.
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "UserLogin")
    @RequestMapping(value = "UserLogin")
    @ElDescription(sub = "사용자로그인", desc = "사용자로그인을 처리한다.")
    public void login(LoginVo loginVo, HttpServletRequest request) throws Exception {
    	String id = loginVo.getUserId();
    	String pw = loginVo.getPw();
    	
    	System.out.println(">>>>> 1. 화면에서 입력받은 PIN: " + id);
	    System.out.println(">>>>> 1. 화면에서 입력받은 ID: " + pw);
    	
    	LoginInfo info = loginProcess.processLogin(request, id, pw);
    	
    	AppLog.debug("- Login 정보 : " + info.toString());
    }
    
    
    /**
     * 간편 비밀번호 로그인을 처리한다.
     * @param userVo 간편 로그인 정보 UserVo
     * @param request 요청 정보 HttpServletRequest
     * @throws Exception
     */
    @ElService(key = "SimpleLogin")
    @RequestMapping(value = "SimpleLogin")
    @ElDescription(sub = "간편비밀번호로그인", desc = "간편비밀번호로그인을 처리한다.")
    public void simpleLogin(SimpleLoginVo simpleloginVo, HttpServletRequest request) throws Exception {

    	String sp_pw = simpleloginVo.getSimplePw();
    	
    	System.out.println(">>>>> 1. 화면에서 입력받은 PIN: " + sp_pw);
    	
    	LoginInfo info = loginProcess.processLogin(request, sp_pw);
    	
    	AppLog.debug("- Login 정보 : " + info.toString());
    	
    	
    }
    
    
    
    
    /*public ProworksUserHeader simpleLogin(UserVo userVo) throws Exception {

        // 1. 화면에서 보낸 사용자 ID와 간편 비밀번호를 가져옵니다.
        String userId = userVo.getUserId();
        String simplePin = userVo.getSimplePw();
        
         // ▼▼▼ 디버깅 코드 추가 ▼▼▼
	    System.out.println(">>>>> 1. 화면에서 입력받은 PIN: " + simplePin);
	    System.out.println(">>>>> 1. 화면에서 입력받은 ID: " + userId);

        // 2. Service의 비밀번호 확인 로직을 호출합니다.
        //boolean isSuccess = userService.checkSimplePassword(userId, simplePin);
        boolean isSuccess = userService.checkSimplePassword("1", simplePin);

        if (isSuccess) {
            // 3. [성공 시] 세션 정보를 수동으로 생성하고 저장합니다.
            UserVo userInfo = userService.selectUser(userVo);

            ProworksUserHeader userHeader = new ProworksUserHeader();
            userHeader.setUserId(userInfo.getUserId());
            userHeader.setTestUserName(userInfo.getUserName());
            userHeader.setTestDeptName("일반사용자");

            //SessionManager sessionManager = (SessionManager) ElBeanUtils.getBean("sessionManager");
            //sessionManager.setLogin(request, userHeader);
            //AppLog.debug("- 간편 로그인 성공 / 사용자: " + userHeader.getTestUserName());
            
            
            // 5. sessionManager를 직접 호출하는 대신, userHeader 객체를 리턴합니다.
            return userHeader;

        } else {
            // 4. [실패 시] 로그인 실패 예외를 발생시킵니다.
            throw new LoginException("EL.ERROR.LOGIN.0002");
        }
    }*/
    
    /**
     * 사용자정보 목록을 조회합니다.
     *
     * @param  userVo 사용자정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="UserList")
    @RequestMapping(value="UserList")    
    @ElDescription(sub="사용자정보 목록조회",desc="페이징을 처리하여 사용자정보 목록 조회를 한다.")               
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

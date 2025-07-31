package com.demo.proworks.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;
import com.inswave.elfw.login.LoginInfo;
import com.demo.proworks.emp.vo.LoginVo;
import com.demo.proworks.user.dao.UserDAO;

import org.springframework.security.crypto.password.PasswordEncoder;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 사용자정보 관련 처리를 담당하는 ServiceImpl
 * @author      : 임소희
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 임소희	 		최초 생성
 * 
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource(name="userDAO")
    private UserDAO userDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * 사용자정보 목록을 조회합니다.
     *
     * @process
     * 1. 사용자정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<UserVo>을(를) 리턴한다.
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 사용자정보 목록 List<UserVo>
     * @throws Exception
     */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception {
		List<UserVo> list = userDAO.selectListUser(userVo);	
	
		return list;
	}

    /**
     * 조회한 사용자정보 전체 카운트
     *
     * @process
     * 1. 사용자정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 사용자정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountUser(UserVo userVo) throws Exception {
		return userDAO.selectListCountUser(userVo);
	}

    /**
     * 사용자정보를 상세 조회한다.
     *
     * @process
     * 1. 사용자정보를 상세 조회한다.
     * 2. 결과 UserVo을(를) 리턴한다.
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UserVo selectUser(UserVo userVo) throws Exception {
		UserVo resultVO = userDAO.selectUser(userVo);			
        System.out.println(resultVO);
        return resultVO;
	}
	
	/**
     * 간편 비밀번호를 확인한다.
     *
     * @process
     * 1. 사용자정보를 상세 조회한다.
     * 2. 조회된 사용자의 저장된 간편 비밀번호를 DB에서 가져온다.
     * 3. 화면에서 입력된 비밀번호와 DB의 비밀번호가 일치하는지 비교한다.
     * 4. 비교 결과를 boolean 타입으로 리턴한다.
     *
     * @param userId   로그인할 사용자 ID
     * @param inputPin 화면에서 사용자가 입력한 비밀번호 6자리
     * @return 성공하면 true, 실패하면 false
     */
    public boolean checkSimplePassword(String userId, String inputPin) throws Exception {
        
	
	    UserVo param = new UserVo();
	    param.setUserId(userId);
	

	    UserVo storedUser = this.selectUser(param);
	
	
	    if (storedUser == null) {
	        System.out.println("❌ [Service] 사용자를 찾을 수 없음");
	        return false;
	    }
	
	    String dbPin = storedUser.getSimplePw();
	
	    if (inputPin != null && inputPin.equals(dbPin)) {
	        return true;
	    } else {
	        return false;
	    }
	        
    }
    
    

     /**
     * 간편비밀번호 등록 여부를 확인한다.
	 *
	 * @process
     * 1. 간편비밀번호 등록 여부를 확인한다.
     * 2. 등록 여부 결과를 boolean 타입으로 리턴한다.
	 *
     * @param userId 사용자 ID
     * @return 간편비밀번호 등록 여부
	 * @throws Exception
     */
	public boolean hasSimplePassword(String userId) throws Exception {
		
		UserVo param = new UserVo();
	    param.setUserId(userId);
	    
	    UserVo storedUser = this.selectUser(param);
	    
	    if (storedUser == null) {
        return false;
	    }
	
		if (storedUser.getSimplePw() == null || storedUser.getSimplePw().trim().isEmpty()) {
            return false;
        }
        return true;
	}
	
    
    
     /**
     * 간편비밀번호 등록한다.(1단계)
	 *
	 * @process
     * 1. 간편비밀번호를 등록한다.
     * 2. 간편비밀번호를 암호화하여 세션에 임시 저장한다.( 1단계 )
	 *
	 * @param userId 사용자 ID
	 * @param rawSimplePw 사용자가 입력한 원본 PIN
	 * @param session 현재 HttpSession
     * @return 간편비밀번호 등록 여부
	 * @throws Exception
     */   	
	public void temporarilyStorePin(String userId, String rawSimplePw, HttpSession session) throws Exception {
	    
	    // 1. 세션의 로그인 정보와 요청 사용자가 일치하는지 검증	    
	    //LoginInfo loggedInUser = (LoginInfo) session.getAttribute("loginInfo");
	    		   
	    
	    /*if (loggedInUser == null || !loggedInUser.getUserId().equals(userId)) {
	        throw new Exception("인증 정보가 올바르지 않습니다.");
	    }
	
	    // 2. PIN 암호화
	    String hashedSimplePw = passwordEncoder.encode(rawSimplePw);
	
	    // 3. 암호화된 PIN을 세션에 임시 저장
	    session.setAttribute("tempSimplePw", hashedSimplePw);
	     */
	     
	     
        String hashedSimplePw = passwordEncoder.encode(rawSimplePw);


	    session.setAttribute("tempSimplePw", hashedSimplePw);
	
	    System.out.println("### PIN 임시 저장 성공! UserId: " + userId);
	}
	
	
	
	/**
	 * 간편비밀번호 등록한다.(2단계)
	 * @process
     * 1. 간편비밀번호를 등록한다.
     * 2. 입력된 PIN과 세션의 임시 PIN을 비교하고, 일치하면 DB에 최종 저장한다.( 2단계 )
	 *
	 * @param userId 사용자 ID
	 * @param confirmationPin 사용자가 확인을 위해 입력한 PIN
	 * @param session 현재 HttpSession
	 * @return 성공 시 true, 실패 시 false
	 * @throws Exception
	 */
	public boolean confirmAndSavePin(String userId, String confirmationPin, HttpSession session) throws Exception {
	    // 1. 세션에서 임시 저장된 암호화 PIN 가져오기
	    String tempHashedPin = (String) session.getAttribute("tempSimplePw");
	    //UserVo loggedInUser = (UserVo) session.getAttribute("loginInfo");
	  
	
	    /* 2. 유효성 검증
	    if (loggedInUser == null || !loggedInUser.getUserId().equals(userId) || tempHashedPin == null) {
	        return false; // 비정상 접근
	    }*/
	
	    // 3. 입력된 확인 PIN과 임시 PIN 비교
	    if (passwordEncoder.matches(confirmationPin, tempHashedPin)) {
	    
	        UserVo updateUserVo = new UserVo();
		    updateUserVo.setUserId(userId);
		    updateUserVo.setSimplePw(tempHashedPin); 
		    
	        // 4. 일치하면 DB에 최종 저장
	        userDAO.updateSimplePassword(updateUserVo);
	        
	        // 5. 세션의 임시 비밀번호 제거
	        session.removeAttribute("tempSimplePw");
	        
	        return true; // 성공
	    }
	
	    return false; // 불일치
	}


	/**
	 * 주민번호로 사용자 기본정보를 조회한다.
	 *
	 * @param rrn 주민등록번호
	 * @return 사용자 기본정보
	 * @throws Exception
	 */
	@Override
	public UserVo selectUserByRrn(String rrn) throws Exception {
	    UserVo userVo = userDAO.selectUserByRrn(rrn);
	    return userVo;
	}


    /**
     * 사용자정보를 등록 처리 한다.
     *
     * @process
     * 1. 사용자정보를 등록 처리 한다.
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int insertUser(UserVo userVo) throws Exception {
        // DB에 저장하기 전, 비밀번호를 암호화합니다.
        String encodedPassword = passwordEncoder.encode(userVo.getPw());
        userVo.setPw(encodedPassword);
        return userDAO.insertUser(userVo);
	}
	
    /**
     * 사용자정보를 갱신 처리 한다.
     *
     * @process
     * 1. 사용자정보를 갱신 처리 한다.
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int updateUser(UserVo userVo) throws Exception {		
	
		// DB에 저장하기 전, 비밀번호를 암호화합니다.
        String encodedPassword = passwordEncoder.encode(userVo.getPw());
        userVo.setPw(encodedPassword);
		return userDAO.updateUser(userVo);	   		
	}

    /**
     * 사용자정보를 삭제 처리 한다.
     *
     * @process
     * 1. 사용자정보를 삭제 처리 한다.
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int deleteUser(UserVo userVo) throws Exception {
		return userDAO.deleteUser(userVo);
	}
	
	/**
     * 숫자 ID로 사용자의 상세 정보를 조회 한다.
	 * 생성자 : J
     *
     * @process
     * 1. 사용자정보를 삭제 처리 한다.
     * 
     * @param  userId 사용자의 숫자 ID (Primary Key)
     * @return 조회된 사용자 정보
     * @throws Exception
     */
    @Override
	public UserVo getUserInfo(long userId) throws Exception {
    	
    	UserVo paramVo = new UserVo();
    	paramVo.setId((int) userId);
    	return userDAO.selectUserById(paramVo);
	}
	
}

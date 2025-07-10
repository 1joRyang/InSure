package com.demo.proworks.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;
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
    

/*
	    System.out.println("[Service] 전달받은 ID: " + userId);
	    System.out.println("[Service] 전달받은 PIN: " + inputPin);
    

        // 1. DB에서 사용자 정보를 가져오기 위해 파라미터 준비
        UserVo param = new UserVo();
        param.setUserId(userId);

        // 2. 기존의 selectUser 메소드를 호출해서 DB로부터 사용자 정보를 가져옴
        UserVo storedUser = this.selectUser(param);

        // 3. 비밀번호 비교 로직
        if (storedUser == null) {
            // DB에 해당 ID의 사용자가 아예 없음
            System.out.println("[Service] DB 조회 실패: " + userId + " 사용자를 찾을 수 없음");
            
            return false;
        }

        // DB에 저장된 비밀번호를 꺼냄
        String dbPin = storedUser.getSimplePw();

        // 사용자가 입력한 비밀번호(inputPin)와 DB 비밀번호(dbPin)가 같은지 비교
        if (inputPin != null && inputPin.equals(dbPin)) {
            // 일치하면 성공!
            return true;
        } else {
            // 일치하지 않으면 실패!
            return false;
        }*/
        
	
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
	
}

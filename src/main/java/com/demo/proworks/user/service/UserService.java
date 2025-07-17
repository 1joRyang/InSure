package com.demo.proworks.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.demo.proworks.user.vo.UserVo;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 인터페이스
 * @description : 사용자정보 관련 처리를 담당하는 인터페이스
 * @author      : 임소희
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 임소희	 		최초 생성
 * 
 */
public interface UserService {
	
    /**
     * 사용자정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  userVo 사용자정보 UserVo
     * @return 사용자정보 목록 List<UserVo>
     * @throws Exception
     */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception;
	
    /**
     * 조회한 사용자정보 전체 카운트
     * 
     * @param  userVo 사용자정보 UserVo
     * @return 사용자정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountUser(UserVo userVo) throws Exception;
	
    /**
     * 사용자정보를 상세 조회한다.
     *
     * @param  userVo 사용자정보 UserVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UserVo selectUser(UserVo userVo) throws Exception;
	
	/**
     * 간편 비밀번호를 확인한다.
     *
     * @param userId   로그인할 사용자 ID
     * @param inputPin 화면에서 사용자가 입력한 비밀번호 6자리
     * @return 성공하면 true, 실패하면 false
	 * @throws Exception
     */
    public boolean checkSimplePassword(String userId, String inputPin) throws Exception;
		
	/**
     * 간편비밀번호 등록 여부를 확인한다.
	 *
     * @param userId 사용자 ID
     * @return 간편비밀번호 등록 여부
	 * @throws Exception
     */
	public boolean hasSimplePassword(String userId) throws Exception;
		
	 /**
     * 간편비밀번호 등록한다.(1단계)
	 *
	 * @param userId 사용자 ID
	 * @param rawSimplePw 사용자가 입력한 원본 PIN
	 * @param session 현재 HttpSession
     * @return 간편비밀번호 등록 여부
	 * @throws Exception
     */   
	public void temporarilyStorePin(String userId, String rawSimplePw, HttpSession session) throws Exception;
	
	
	/**
	 * 간편비밀번호 등록한다.(2단계)
	 *
	 * @param userId 사용자 ID
	 * @param confirmationPin 사용자가 확인을 위해 입력한 PIN
	 * @param session 현재 HttpSession
	 * @return 성공 시 true, 실패 시 false
	 * @throws Exception
	 */
	public boolean confirmAndSavePin(String userId, String confirmationPin, HttpSession session) throws Exception;
		
	/**
	 * 주민번호로 사용자 기본정보를 조회한다.
	 *
	 * @param rrn 주민등록번호
	 * @return 사용자 기본정보
	 * @throws Exception
	 */
	public UserVo selectUserByRrn(String rrn) throws Exception;
			
    /**
     * 사용자정보를 등록 처리 한다.
     *
     * @param  userVo 사용자정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int insertUser(UserVo userVo) throws Exception;
	
    /**
     * 사용자정보를 갱신 처리 한다.
     *
     * @param  userVo 사용자정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int updateUser(UserVo userVo) throws Exception;
	
    /**
     * 사용자정보를 삭제 처리 한다.
     *
     * @param  userVo 사용자정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int deleteUser(UserVo userVo) throws Exception;
	
	/**
     * 숫자 ID로 사용자의 상세 정보를 조회합니다.
     * 생성자 : J
     * @param  userId 사용자의 숫자 ID (Primary Key)
     * @return 조회된 사용자 정보
     * @throws Exception
     */
	public UserVo getUserInfo(long userId) throws Exception;
	
	
	
}

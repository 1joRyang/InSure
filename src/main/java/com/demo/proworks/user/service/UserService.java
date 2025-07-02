package com.demo.proworks.user.service;

import java.util.List;

import com.demo.proworks.user.vo.UserVo;

/**  
 * @subject     : 유저정보 관련 처리를 담당하는 인터페이스
 * @description : 유저정보 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
public interface UserService {
	
    /**
     * 유저정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  userVo 유저정보 UserVo
     * @return 유저정보 목록 List<UserVo>
     * @throws Exception
     */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception;
	
    /**
     * 조회한 유저정보 전체 카운트
     * 
     * @param  userVo 유저정보 UserVo
     * @return 유저정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountUser(UserVo userVo) throws Exception;
	
    /**
     * 유저정보를 상세 조회한다.
     *
     * @param  userVo 유저정보 UserVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UserVo selectUser(UserVo userVo) throws Exception;
		
    /**
     * 유저정보를 등록 처리 한다.
     *
     * @param  userVo 유저정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int insertUser(UserVo userVo) throws Exception;
	
    /**
     * 유저정보를 갱신 처리 한다.
     *
     * @param  userVo 유저정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int updateUser(UserVo userVo) throws Exception;
	
    /**
     * 유저정보를 삭제 처리 한다.
     *
     * @param  userVo 유저정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int deleteUser(UserVo userVo) throws Exception;
	
}

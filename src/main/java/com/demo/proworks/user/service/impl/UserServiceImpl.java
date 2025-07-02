package com.demo.proworks.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;
import com.demo.proworks.user.dao.UserDAO;

/**  
 * @subject     : 유저정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 유저정보 관련 처리를 담당하는 ServiceImpl
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource(name="userDAO")
    private UserDAO userDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 유저정보 목록을 조회합니다.
     *
     * @process
     * 1. 유저정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<UserVo>을(를) 리턴한다.
     * 
     * @param  userVo 유저정보 UserVo
     * @return 유저정보 목록 List<UserVo>
     * @throws Exception
     */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception {
		List<UserVo> list = userDAO.selectListUser(userVo);	
	
		return list;
	}

    /**
     * 조회한 유저정보 전체 카운트
     *
     * @process
     * 1. 유저정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  userVo 유저정보 UserVo
     * @return 유저정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountUser(UserVo userVo) throws Exception {
		return userDAO.selectListCountUser(userVo);
	}

    /**
     * 유저정보를 상세 조회한다.
     *
     * @process
     * 1. 유저정보를 상세 조회한다.
     * 2. 결과 UserVo을(를) 리턴한다.
     * 
     * @param  userVo 유저정보 UserVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UserVo selectUser(UserVo userVo) throws Exception {
		UserVo resultVO = userDAO.selectUser(userVo);			
        
        return resultVO;
	}

    /**
     * 유저정보를 등록 처리 한다.
     *
     * @process
     * 1. 유저정보를 등록 처리 한다.
     * 
     * @param  userVo 유저정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int insertUser(UserVo userVo) throws Exception {
		return userDAO.insertUser(userVo);	
	}
	
    /**
     * 유저정보를 갱신 처리 한다.
     *
     * @process
     * 1. 유저정보를 갱신 처리 한다.
     * 
     * @param  userVo 유저정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int updateUser(UserVo userVo) throws Exception {				
		return userDAO.updateUser(userVo);	   		
	}

    /**
     * 유저정보를 삭제 처리 한다.
     *
     * @process
     * 1. 유저정보를 삭제 처리 한다.
     * 
     * @param  userVo 유저정보 UserVo
     * @return 번호
     * @throws Exception
     */
	public int deleteUser(UserVo userVo) throws Exception {
		return userDAO.deleteUser(userVo);
	}
	
}

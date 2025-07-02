package com.demo.proworks.user.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.user.service.UserService;
import com.demo.proworks.user.vo.UserVo;
import com.demo.proworks.user.vo.UserListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 유저정보 관련 처리를 담당하는 컨트롤러
 * @description : 유저정보 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class UserController {
	
    /** UserService */
    @Resource(name = "userServiceImpl")
    private UserService userService;
	
    
    /**
     * 유저정보 목록을 조회합니다.
     *
     * @param  userVo 유저정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="user0001List")
    @RequestMapping(value="user0001List")    
    @ElDescription(sub="유저정보 목록조회",desc="페이징을 처리하여 유저정보 목록 조회를 한다.")               
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
     * 유저정보을 단건 조회 처리 한다.
     *
     * @param  userVo 유저정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "user0001UpdView")    
    @RequestMapping(value="user0001UpdView") 
    @ElDescription(sub = "유저정보 갱신 폼을 위한 조회", desc = "유저정보 갱신 폼을 위한 조회를 한다.")    
    public UserVo selectUser(UserVo userVo) throws Exception {
    	UserVo selectUserVo = userService.selectUser(userVo);    	    
		
        return selectUserVo;
    } 
 
    /**
     * 유저정보를 등록 처리 한다.
     *
     * @param  userVo 유저정보
     * @throws Exception
     */
    @ElService(key="user0001Ins")    
    @RequestMapping(value="user0001Ins")
    @ElDescription(sub="유저정보 등록처리",desc="유저정보를 등록 처리 한다.")
    public void insertUser(UserVo userVo) throws Exception {    	 
    	userService.insertUser(userVo);   
    }
       
    /**
     * 유저정보를 갱신 처리 한다.
     *
     * @param  userVo 유저정보
     * @throws Exception
     */
    @ElService(key="user0001Upd")    
    @RequestMapping(value="user0001Upd")    
    @ElValidator(errUrl="/user/userRegister", errContinue=true)
    @ElDescription(sub="유저정보 갱신처리",desc="유저정보를 갱신 처리 한다.")    
    public void updateUser(UserVo userVo) throws Exception {  
 
    	userService.updateUser(userVo);                                            
    }

    /**
     * 유저정보를 삭제 처리한다.
     *
     * @param  userVo 유저정보    
     * @throws Exception
     */
    @ElService(key = "user0001Del")    
    @RequestMapping(value="user0001Del")
    @ElDescription(sub = "유저정보 삭제처리", desc = "유저정보를 삭제 처리한다.")    
    public void deleteUser(UserVo userVo) throws Exception {
        userService.deleteUser(userVo);
    }
   
}

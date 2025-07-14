package com.demo.proworks.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.user.vo.UserVo;
import com.demo.proworks.user.dao.UserDAO;

/**  
 * @subject     : 사용자정보 관련 처리를 담당하는 DAO
 * @description : 사용자정보 관련 처리를 담당하는 DAO
 * @author      : 임소희
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 임소희	 		최초 생성
 * 
 */
@Repository("userDAO")
public class UserDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
     * 사용자 ID로 암호화된 비밀번호를 직접 조회합니다.
     * @param userId
     * @return String 암호화된 비밀번호
     * @throws ElException
     */
	public String selectPasswordByUserId(String userId) {
        // selectByPk 메소드를 사용하고, 결과를 String으로 형변환(casting)합니다.
        return (String) selectByPk("com.demo.proworks.user.selectPasswordByUserId", userId);
    }
    
    
	/**
	 * 사용자 ID로 간편 비밀번호를 업데이트합니다.
	 * @param userVo 사용자 ID와 암호화된 간편비밀번호가 담긴 Vo
	 * @return int 업데이트된 행의 수 (보통 성공 시 1)
	 * @throws ElException
	 */
	public int updateSimplePassword(UserVo userVo) {
	    // 프레임워크의 update 메소드를 사용하고, 쿼리 ID와 파라미터 Vo를 전달합니다.
	    return update("com.demo.proworks.user.updateSimplePassword", userVo);
	}
	    
    
    /**
     * 사용자정보 상세 조회한다.
     *  
     * @param  UserVo 사용자정보
     * @return UserVo 사용자정보
     * @throws ElException
     */
    public UserVo selectUser(UserVo vo) throws ElException {
        return (UserVo) selectByPk("com.demo.proworks.user.selectUser", vo);
    }

    /**
     * 페이징을 처리하여 사용자정보 목록조회를 한다.
     *  
     * @param  UserVo 사용자정보
     * @return List<UserVo> 사용자정보
     * @throws ElException
     */
    public List<UserVo> selectListUser(UserVo vo) throws ElException {      	
        return (List<UserVo>)list("com.demo.proworks.user.selectListUser", vo);
    }

    /**
     * 사용자정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  UserVo 사용자정보
     * @return 사용자정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountUser(UserVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.user.selectListCountUser", vo);
    }
        
    /**
     * 사용자정보를 등록한다.
     *  
     * @param  UserVo 사용자정보
     * @return 번호
     * @throws ElException
     */
    public int insertUser(UserVo vo) throws ElException {    	
        return insert("com.demo.proworks.user.insertUser", vo);
    }

    /**
     * 사용자정보를 갱신한다.
     *  
     * @param  UserVo 사용자정보
     * @return 번호
     * @throws ElException
     */
    public int updateUser(UserVo vo) throws ElException {
        return update("com.demo.proworks.user.updateUser", vo);
    }

    /**
     * 사용자정보를 삭제한다.
     *  
     * @param  UserVo 사용자정보
     * @return 번호
     * @throws ElException
     */
    public int deleteUser(UserVo vo) throws ElException {
        return delete("com.demo.proworks.user.deleteUser", vo);
    }

}

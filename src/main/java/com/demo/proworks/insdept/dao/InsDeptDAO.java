package com.demo.proworks.insdept.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.insdept.vo.InsDeptVo;
import com.demo.proworks.insdept.dao.InsDeptDAO;

/**  
 * @subject     : 부서정보 관련 처리를 담당하는 DAO
 * @description : 부서정보 관련 처리를 담당하는 DAO
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Repository("insDeptDAO")
public class InsDeptDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 부서정보 상세 조회한다.
     *  
     * @param  InsDeptVo 부서정보
     * @return InsDeptVo 부서정보
     * @throws ElException
     */
    public InsDeptVo selectInsDept(InsDeptVo vo) throws ElException {
        return (InsDeptVo) selectByPk("com.demo.proworks.insdept.selectInsDept", vo);
    }

    /**
     * 페이징을 처리하여 부서정보 목록조회를 한다.
     *  
     * @param  InsDeptVo 부서정보
     * @return List<InsDeptVo> 부서정보
     * @throws ElException
     */
    public List<InsDeptVo> selectListInsDept(InsDeptVo vo) throws ElException {      	
        return (List<InsDeptVo>)list("com.demo.proworks.insdept.selectListInsDept", vo);
    }

    /**
     * 부서정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  InsDeptVo 부서정보
     * @return 부서정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountInsDept(InsDeptVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.insdept.selectListCountInsDept", vo);
    }
        
    /**
     * 부서정보를 등록한다.
     *  
     * @param  InsDeptVo 부서정보
     * @return 번호
     * @throws ElException
     */
    public int insertInsDept(InsDeptVo vo) throws ElException {    	
        return insert("com.demo.proworks.insdept.insertInsDept", vo);
    }

    /**
     * 부서정보를 갱신한다.
     *  
     * @param  InsDeptVo 부서정보
     * @return 번호
     * @throws ElException
     */
    public int updateInsDept(InsDeptVo vo) throws ElException {
        return update("com.demo.proworks.insdept.updateInsDept", vo);
    }

    /**
     * 부서정보를 삭제한다.
     *  
     * @param  InsDeptVo 부서정보
     * @return 번호
     * @throws ElException
     */
    public int deleteInsDept(InsDeptVo vo) throws ElException {
        return delete("com.demo.proworks.insdept.deleteInsDept", vo);
    }

}

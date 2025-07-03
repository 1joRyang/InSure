package com.demo.proworks.employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.employee.dao.EmployeeDAO;

/**  
 * @subject     : 실무자,관리자정보 관련 처리를 담당하는 DAO
 * @description : 실무자,관리자정보 관련 처리를 담당하는 DAO
 * @author      : 임소희
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 임소희	 		최초 생성
 * 
 */
@Repository("employeeDAO")
public class EmployeeDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 실무자,관리자정보 상세 조회한다.
     *  
     * @param  EmployeeVo 실무자,관리자정보
     * @return EmployeeVo 실무자,관리자정보
     * @throws ElException
     */
    public EmployeeVo selectEmployee(EmployeeVo vo) throws ElException {
        return (EmployeeVo) selectByPk("com.demo.proworks.employee.selectEmployee", vo);
    }

    /**
     * 페이징을 처리하여 실무자,관리자정보 목록조회를 한다.
     *  
     * @param  EmployeeVo 실무자,관리자정보
     * @return List<EmployeeVo> 실무자,관리자정보
     * @throws ElException
     */
    public List<EmployeeVo> selectListEmployee(EmployeeVo vo) throws ElException {      	
        return (List<EmployeeVo>)list("com.demo.proworks.employee.selectListEmployee", vo);
    }

    /**
     * 실무자,관리자정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  EmployeeVo 실무자,관리자정보
     * @return 실무자,관리자정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountEmployee(EmployeeVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.employee.selectListCountEmployee", vo);
    }
        
    /**
     * 실무자,관리자정보를 등록한다.
     *  
     * @param  EmployeeVo 실무자,관리자정보
     * @return 번호
     * @throws ElException
     */
    public int insertEmployee(EmployeeVo vo) throws ElException {    	
        return insert("com.demo.proworks.employee.insertEmployee", vo);
    }

    /**
     * 실무자,관리자정보를 갱신한다.
     *  
     * @param  EmployeeVo 실무자,관리자정보
     * @return 번호
     * @throws ElException
     */
    public int updateEmployee(EmployeeVo vo) throws ElException {
        return update("com.demo.proworks.employee.updateEmployee", vo);
    }

    /**
     * 실무자,관리자정보를 삭제한다.
     *  
     * @param  EmployeeVo 실무자,관리자정보
     * @return 번호
     * @throws ElException
     */
    public int deleteEmployee(EmployeeVo vo) throws ElException {
        return delete("com.demo.proworks.employee.deleteEmployee", vo);
    }

}

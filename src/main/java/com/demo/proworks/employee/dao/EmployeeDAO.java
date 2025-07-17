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
//==============================================================================

	public List<EmployeeVo> selectListEmployeeForRule(EmployeeVo employeeVo) {
		return (List<EmployeeVo>) list("com.demo.proworks.employee.selectListEmployeeForRule", employeeVo);
	}

	/**
	 * @param deptId 부서 ID
	 * @param status 재직 상태
	 * @return 해당 조건의 직원 목록
	 * @throws ElException
	 */
	public List<EmployeeVo> selectEmployeesByDeptAndStatus(String deptId, String status) throws ElException {
		EmployeeVo searchVo = new EmployeeVo();
		searchVo.setDeptId(deptId);
		searchVo.setStatus(status);
		return (List<EmployeeVo>) list("com.demo.proworks.employee.selectEmployeesByDeptAndStatus", searchVo);
	}

	/**
	 * 
	 * @param deptId 부서 ID
	 * @return 마지막 배정된 직원 번호
	 * @throws ElException
	 */
	public Integer selectLastAssignedEmployeeInDept(String deptId) throws ElException {
		try {
			Object empNoObj = selectByPk("com.demo.proworks.employee.selectLastAssignedEmployeeInDept", deptId);
			if (empNoObj == null) {
				return null;
			}
			

			if (empNoObj instanceof Long) {
				return ((Long) empNoObj).intValue();
			} else if (empNoObj instanceof Integer) {
				return (Integer) empNoObj;
			} else if (empNoObj instanceof String) {
				try {
					return Integer.parseInt((String) empNoObj);
				} catch (NumberFormatException e) {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("[ERROR] 마지막 배정 직원 조회 오류: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param employeeVo 검색 조건 (deptId, lastEmpNo 포함)
	 * @return 다음 배정할 직원
	 * @throws ElException
	 */
	public EmployeeVo selectNextEmployeeForAssignment(EmployeeVo employeeVo) throws ElException {
		return (EmployeeVo) selectByPk("com.demo.proworks.employee.selectNextEmployeeForAssignment", employeeVo);
	}

	/**
	 * 
	 * @param deptId 부서 ID
	 * @return 부서의 첫 번째 직원
	 * @throws ElException
	 */
	public EmployeeVo selectFirstEmployeeInDept(String deptId) throws ElException {
		return (EmployeeVo) selectByPk("com.demo.proworks.employee.selectFirstEmployeeInDept", deptId);
	}
	
	/*=============================================================================================*/
	
	/**
     * 사원번호로 직원 이름을 조회한다.
	 * 생성자 J
     * @param empNo 사원번호
     * @return String 직원 이름
     * @throws Exception
     */
    public String selectEmpNameByNo(String empNo) throws Exception {
        // "employee.selectEmpNameByNo" ID를 가진 SQL 쿼리를 실행하고
        // 결과를 String 타입으로 반환합니다.
        return (String) selectByPk("employee.selectEmpNameByNo", empNo);
    }


}
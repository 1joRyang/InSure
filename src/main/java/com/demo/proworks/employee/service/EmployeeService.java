package com.demo.proworks.employee.service;

import java.util.List;

import com.demo.proworks.employee.vo.EmployeeVo;

/**  
 * @subject     : 실무자,관리자정보 관련 처리를 담당하는 인터페이스
 * @description : 실무자,관리자정보 관련 처리를 담당하는 인터페이스
 * @author      : 임소희
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 임소희	 		최초 생성
 * 
 */
public interface EmployeeService {

	public List<EmployeeVo> selectListEmployeeForRule(EmployeeVo employeeVo) throws Exception;
	
    /**
     * 실무자,관리자정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 실무자,관리자정보 목록 List<EmployeeVo>
     * @throws Exception
     */
	public List<EmployeeVo> selectListEmployee(EmployeeVo employeeVo) throws Exception;
	
    /**
     * 조회한 실무자,관리자정보 전체 카운트
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 실무자,관리자정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountEmployee(EmployeeVo employeeVo) throws Exception;
	
    /**
     * 실무자,관리자정보를 상세 조회한다.
     *
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public EmployeeVo selectEmployee(EmployeeVo employeeVo) throws Exception;
		
    /**
     * 실무자,관리자정보를 등록 처리 한다.
     *
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 번호
     * @throws Exception
     */
	public int insertEmployee(EmployeeVo employeeVo) throws Exception;
	
    /**
     * 실무자,관리자정보를 갱신 처리 한다.
     *
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 번호
     * @throws Exception
     */
	public int updateEmployee(EmployeeVo employeeVo) throws Exception;
	
    /**
     * 실무자,관리자정보를 삭제 처리 한다.
     *
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 번호
     * @throws Exception
     */
	public int deleteEmployee(EmployeeVo employeeVo) throws Exception;
	
	/**
     * 사원 번호로 이름을 조회한다.
     * 생성자 : J
     */
	public String getEmpNameByNo(String empNo) throws Exception;
	
}

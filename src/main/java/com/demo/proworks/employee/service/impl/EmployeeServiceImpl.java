package com.demo.proworks.employee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.employee.service.EmployeeService;
import com.demo.proworks.employee.vo.EmployeeVo;
import com.demo.proworks.employee.dao.EmployeeDAO;

/**  
 * @subject     : 실무자,관리자정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 실무자,관리자정보 관련 처리를 담당하는 ServiceImpl
 * @author      : 임소희
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 임소희	 		최초 생성
 * 
 */
@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource(name="employeeDAO")
    private EmployeeDAO employeeDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 실무자,관리자정보 목록을 조회합니다.
     *
     * @process
     * 1. 실무자,관리자정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<EmployeeVo>을(를) 리턴한다.
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 실무자,관리자정보 목록 List<EmployeeVo>
     * @throws Exception
     */
	public List<EmployeeVo> selectListEmployee(EmployeeVo employeeVo) throws Exception {
		List<EmployeeVo> list = employeeDAO.selectListEmployee(employeeVo);	
	
		return list;
	}

    /**
     * 조회한 실무자,관리자정보 전체 카운트
     *
     * @process
     * 1. 실무자,관리자정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 실무자,관리자정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountEmployee(EmployeeVo employeeVo) throws Exception {
		return employeeDAO.selectListCountEmployee(employeeVo);
	}

    /**
     * 실무자,관리자정보를 상세 조회한다.
     *
     * @process
     * 1. 실무자,관리자정보를 상세 조회한다.
     * 2. 결과 EmployeeVo을(를) 리턴한다.
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public EmployeeVo selectEmployee(EmployeeVo employeeVo) throws Exception {
		EmployeeVo resultVO = employeeDAO.selectEmployee(employeeVo);			
        
        return resultVO;
	}

    /**
     * 실무자,관리자정보를 등록 처리 한다.
     *
     * @process
     * 1. 실무자,관리자정보를 등록 처리 한다.
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 번호
     * @throws Exception
     */
	public int insertEmployee(EmployeeVo employeeVo) throws Exception {
		return employeeDAO.insertEmployee(employeeVo);	
	}
	
    /**
     * 실무자,관리자정보를 갱신 처리 한다.
     *
     * @process
     * 1. 실무자,관리자정보를 갱신 처리 한다.
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 번호
     * @throws Exception
     */
	public int updateEmployee(EmployeeVo employeeVo) throws Exception {				
		return employeeDAO.updateEmployee(employeeVo);	   		
	}

    /**
     * 실무자,관리자정보를 삭제 처리 한다.
     *
     * @process
     * 1. 실무자,관리자정보를 삭제 처리 한다.
     * 
     * @param  employeeVo 실무자,관리자정보 EmployeeVo
     * @return 번호
     * @throws Exception
     */
	public int deleteEmployee(EmployeeVo employeeVo) throws Exception {
		return employeeDAO.deleteEmployee(employeeVo);
	}
	
}

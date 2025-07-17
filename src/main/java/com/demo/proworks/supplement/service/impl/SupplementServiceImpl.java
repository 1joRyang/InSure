package com.demo.proworks.supplement.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.proworks.employee.service.EmployeeService;
import com.demo.proworks.supplement.dao.SupplementDAO;
import com.demo.proworks.supplement.service.SupplementService;
import com.demo.proworks.supplement.vo.SuppVo;

@Service("supplementService")
public class SupplementServiceImpl implements SupplementService {

	@Resource(name="supplementDAO")
	private SupplementDAO supplementDAO;
	
	//@Resource(name="employeeService")
	//private EmployeeService employeeService;
	
	/**
	 * 보완 요청 상세 정보 조회 로직
	 */
	 @Override
	 public SuppVo selectSupplementInfo(SuppVo suppVo) throws Exception {
		 // 1. DAO를 통해 DB에서 보완정보 조회
		 SuppVo resultVO = supplementDAO.selectSupplementInfo(suppVo);
		 
		 //if (resultVO != null && resultVO.getEmpNo() != null) {
			 //String empName = employeeService.getEmpNameByNo(resultVO.getEmpNo());
			 //resultVO.setEmpName(empName);
		 //}
		 
		 return resultVO;
	 }
	 
}

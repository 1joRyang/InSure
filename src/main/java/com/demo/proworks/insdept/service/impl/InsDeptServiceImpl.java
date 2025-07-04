package com.demo.proworks.insdept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.insdept.service.InsDeptService;
import com.demo.proworks.insdept.vo.InsDeptVo;
import com.demo.proworks.insdept.dao.InsDeptDAO;

/**  
 * @subject     : 부서정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 부서정보 관련 처리를 담당하는 ServiceImpl
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Service("insDeptServiceImpl")
public class InsDeptServiceImpl implements InsDeptService {

    @Resource(name="insDeptDAO")
    private InsDeptDAO insDeptDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 부서정보 목록을 조회합니다.
     *
     * @process
     * 1. 부서정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<InsDeptVo>을(를) 리턴한다.
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 부서정보 목록 List<InsDeptVo>
     * @throws Exception
     */
	public List<InsDeptVo> selectListInsDept(InsDeptVo insDeptVo) throws Exception {
		List<InsDeptVo> list = insDeptDAO.selectListInsDept(insDeptVo);	
	
		return list;
	}

    /**
     * 조회한 부서정보 전체 카운트
     *
     * @process
     * 1. 부서정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 부서정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountInsDept(InsDeptVo insDeptVo) throws Exception {
		return insDeptDAO.selectListCountInsDept(insDeptVo);
	}

    /**
     * 부서정보를 상세 조회한다.
     *
     * @process
     * 1. 부서정보를 상세 조회한다.
     * 2. 결과 InsDeptVo을(를) 리턴한다.
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public InsDeptVo selectInsDept(InsDeptVo insDeptVo) throws Exception {
		InsDeptVo resultVO = insDeptDAO.selectInsDept(insDeptVo);			
        
        return resultVO;
	}

    /**
     * 부서정보를 등록 처리 한다.
     *
     * @process
     * 1. 부서정보를 등록 처리 한다.
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 번호
     * @throws Exception
     */
	public int insertInsDept(InsDeptVo insDeptVo) throws Exception {
		return insDeptDAO.insertInsDept(insDeptVo);	
	}
	
    /**
     * 부서정보를 갱신 처리 한다.
     *
     * @process
     * 1. 부서정보를 갱신 처리 한다.
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 번호
     * @throws Exception
     */
	public int updateInsDept(InsDeptVo insDeptVo) throws Exception {				
		return insDeptDAO.updateInsDept(insDeptVo);	   		
	}

    /**
     * 부서정보를 삭제 처리 한다.
     *
     * @process
     * 1. 부서정보를 삭제 처리 한다.
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 번호
     * @throws Exception
     */
	public int deleteInsDept(InsDeptVo insDeptVo) throws Exception {
		return insDeptDAO.deleteInsDept(insDeptVo);
	}
	
}

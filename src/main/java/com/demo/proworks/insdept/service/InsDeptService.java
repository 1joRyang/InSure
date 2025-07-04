package com.demo.proworks.insdept.service;

import java.util.List;

import com.demo.proworks.insdept.vo.InsDeptVo;

/**  
 * @subject     : 부서정보 관련 처리를 담당하는 인터페이스
 * @description : 부서정보 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
public interface InsDeptService {
	
    /**
     * 부서정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 부서정보 목록 List<InsDeptVo>
     * @throws Exception
     */
	public List<InsDeptVo> selectListInsDept(InsDeptVo insDeptVo) throws Exception;
	
    /**
     * 조회한 부서정보 전체 카운트
     * 
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 부서정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountInsDept(InsDeptVo insDeptVo) throws Exception;
	
    /**
     * 부서정보를 상세 조회한다.
     *
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public InsDeptVo selectInsDept(InsDeptVo insDeptVo) throws Exception;
		
    /**
     * 부서정보를 등록 처리 한다.
     *
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 번호
     * @throws Exception
     */
	public int insertInsDept(InsDeptVo insDeptVo) throws Exception;
	
    /**
     * 부서정보를 갱신 처리 한다.
     *
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 번호
     * @throws Exception
     */
	public int updateInsDept(InsDeptVo insDeptVo) throws Exception;
	
    /**
     * 부서정보를 삭제 처리 한다.
     *
     * @param  insDeptVo 부서정보 InsDeptVo
     * @return 번호
     * @throws Exception
     */
	public int deleteInsDept(InsDeptVo insDeptVo) throws Exception;
	
}

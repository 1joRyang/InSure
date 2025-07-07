package com.demo.proworks.excitem.service;

import java.util.List;

import com.demo.proworks.excitem.vo.ExcItemVo;

/**  
 * @subject     : 제외항목 관련 처리를 담당하는 인터페이스
 * @description : 제외항목 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
public interface ExcItemService {
	
    /**
     * 제외항목 페이징 처리하여 목록을 조회한다.
     *
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 제외항목 목록 List<ExcItemVo>
     * @throws Exception
     */
	public List<ExcItemVo> selectListExcItem(ExcItemVo excItemVo) throws Exception;
	
    /**
     * 조회한 제외항목 전체 카운트
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 제외항목 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountExcItem(ExcItemVo excItemVo) throws Exception;
	
    /**
     * 제외항목를 상세 조회한다.
     *
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ExcItemVo selectExcItem(ExcItemVo excItemVo) throws Exception;
		
    /**
     * 제외항목를 등록 처리 한다.
     *
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 번호
     * @throws Exception
     */
	public int insertExcItem(ExcItemVo excItemVo) throws Exception;
	
    /**
     * 제외항목를 갱신 처리 한다.
     *
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 번호
     * @throws Exception
     */
	public int updateExcItem(ExcItemVo excItemVo) throws Exception;
	
    /**
     * 제외항목를 삭제 처리 한다.
     *
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 번호
     * @throws Exception
     */
	public int deleteExcItem(ExcItemVo excItemVo) throws Exception;
	
}

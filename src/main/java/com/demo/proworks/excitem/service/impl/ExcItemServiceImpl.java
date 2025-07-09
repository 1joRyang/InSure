package com.demo.proworks.excitem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.excitem.service.ExcItemService;
import com.demo.proworks.excitem.vo.ExcItemVo;
import com.demo.proworks.excitem.dao.ExcItemDAO;

/**  
 * @subject     : 제외항목 관련 처리를 담당하는 ServiceImpl
 * @description	: 제외항목 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Service("excItemServiceImpl")
public class ExcItemServiceImpl implements ExcItemService {

    @Resource(name="excItemDAO")
    private ExcItemDAO excItemDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	/**
	 * 하나의 영수증에 대한 제외항목 조회
	 */
	public List<ExcItemVo> selectListExcItemR(ExcItemVo excItemVo) throws Exception {
		List<ExcItemVo> list = excItemDAO.selectListExcItemR(excItemVo);	
	
		return list;
	}

    /**
     * 제외항목 목록을 조회합니다.
     *
     * @process
     * 1. 제외항목 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ExcItemVo>을(를) 리턴한다.
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 제외항목 목록 List<ExcItemVo>
     * @throws Exception
     */
	public List<ExcItemVo> selectListExcItem(ExcItemVo excItemVo) throws Exception {
		List<ExcItemVo> list = excItemDAO.selectListExcItem(excItemVo);	
	
		return list;
	}

    /**
     * 조회한 제외항목 전체 카운트
     *
     * @process
     * 1. 제외항목 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 제외항목 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountExcItem(ExcItemVo excItemVo) throws Exception {
		return excItemDAO.selectListCountExcItem(excItemVo);
	}

    /**
     * 제외항목를 상세 조회한다.
     *
     * @process
     * 1. 제외항목를 상세 조회한다.
     * 2. 결과 ExcItemVo을(를) 리턴한다.
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ExcItemVo selectExcItem(ExcItemVo excItemVo) throws Exception {
		ExcItemVo resultVO = excItemDAO.selectExcItem(excItemVo);			
        
        return resultVO;
	}

    /**
     * 제외항목를 등록 처리 한다.
     *
     * @process
     * 1. 제외항목를 등록 처리 한다.
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 번호
     * @throws Exception
     */
	public int insertExcItem(ExcItemVo excItemVo) throws Exception {
		return excItemDAO.insertExcItem(excItemVo);	
	}
	
    /**
     * 제외항목를 갱신 처리 한다.
     *
     * @process
     * 1. 제외항목를 갱신 처리 한다.
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 번호
     * @throws Exception
     */
	public int updateExcItem(ExcItemVo excItemVo) throws Exception {				
		return excItemDAO.updateExcItem(excItemVo);	   		
	}

    /**
     * 제외항목를 삭제 처리 한다.
     *
     * @process
     * 1. 제외항목를 삭제 처리 한다.
     * 
     * @param  excItemVo 제외항목 ExcItemVo
     * @return 번호
     * @throws Exception
     */
	public int deleteExcItem(ExcItemVo excItemVo) throws Exception {
		return excItemDAO.deleteExcItem(excItemVo);
	}
	
}

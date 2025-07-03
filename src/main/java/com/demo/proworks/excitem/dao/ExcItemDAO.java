package com.demo.proworks.excitem.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.excitem.vo.ExcItemVo;
import com.demo.proworks.excitem.dao.ExcItemDAO;

/**  
 * @subject     : 제외항목 관련 처리를 담당하는 DAO
 * @description : 제외항목 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Repository("excItemDAO")
public class ExcItemDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 제외항목 상세 조회한다.
     *  
     * @param  ExcItemVo 제외항목
     * @return ExcItemVo 제외항목
     * @throws ElException
     */
    public ExcItemVo selectExcItem(ExcItemVo vo) throws ElException {
        return (ExcItemVo) selectByPk("com.demo.proworks.excitem.selectExcItem", vo);
    }

    /**
     * 페이징을 처리하여 제외항목 목록조회를 한다.
     *  
     * @param  ExcItemVo 제외항목
     * @return List<ExcItemVo> 제외항목
     * @throws ElException
     */
    public List<ExcItemVo> selectListExcItem(ExcItemVo vo) throws ElException {      	
        return (List<ExcItemVo>)list("com.demo.proworks.excitem.selectListExcItem", vo);
    }

    /**
     * 제외항목 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ExcItemVo 제외항목
     * @return 제외항목 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountExcItem(ExcItemVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.excitem.selectListCountExcItem", vo);
    }
        
    /**
     * 제외항목를 등록한다.
     *  
     * @param  ExcItemVo 제외항목
     * @return 번호
     * @throws ElException
     */
    public int insertExcItem(ExcItemVo vo) throws ElException {    	
        return insert("com.demo.proworks.excitem.insertExcItem", vo);
    }

    /**
     * 제외항목를 갱신한다.
     *  
     * @param  ExcItemVo 제외항목
     * @return 번호
     * @throws ElException
     */
    public int updateExcItem(ExcItemVo vo) throws ElException {
        return update("com.demo.proworks.excitem.updateExcItem", vo);
    }

    /**
     * 제외항목를 삭제한다.
     *  
     * @param  ExcItemVo 제외항목
     * @return 번호
     * @throws ElException
     */
    public int deleteExcItem(ExcItemVo vo) throws ElException {
        return delete("com.demo.proworks.excitem.deleteExcItem", vo);
    }

}

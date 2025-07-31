package com.demo.proworks.insimagefile.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.insimagefile.dao.InsimagefileDAO;

/**  
 * @subject     : 이미지파일테이블 관련 처리를 담당하는 DAO
 * @description : 이미지파일테이블 관련 처리를 담당하는 DAO
 * @author      : 이지현
 * @since       : 2025/07/04
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/04			 이지현	 		최초 생성
 * 
 */
@Repository("insimagefileDAO")
public class InsimagefileDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 이미지파일테이블 상세 조회한다.
     *  
     * @param  InsimagefileVo 이미지파일테이블
     * @return InsimagefileVo 이미지파일테이블
     * @throws ElException
     */
    public InsimagefileVo selectInsimagefile(InsimagefileVo vo) throws ElException {
        return (InsimagefileVo) selectByPk("com.demo.proworks.insimagefile.selectInsimagefile", vo);
    }

    /**
     * 페이징을 처리하여 이미지파일테이블 목록조회를 한다.
     *  
     * @param  InsimagefileVo 이미지파일테이블
     * @return List<InsimagefileVo> 이미지파일테이블
     * @throws ElException
     */
    public List<InsimagefileVo> selectListInsimagefile(InsimagefileVo vo) throws ElException {      	
        return (List<InsimagefileVo>)list("com.demo.proworks.insimagefile.selectListInsimagefile", vo);
    }

    /**
     * 이미지파일테이블 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  InsimagefileVo 이미지파일테이블
     * @return 이미지파일테이블 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountInsimagefile(InsimagefileVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.insimagefile.selectListCountInsimagefile", vo);
    }
        
    /**
     * 이미지파일테이블를 등록한다.
     *  
     * @param  InsimagefileVo 이미지파일테이블
     * @return 번호
     * @throws ElException
     */
    public int insertInsimagefile(InsimagefileVo vo) throws ElException {    	
        return insert("com.demo.proworks.insimagefile.insertInsimagefile", vo);
    }

    /**
     * 이미지파일테이블를 갱신한다.
     *  
     * @param  InsimagefileVo 이미지파일테이블
     * @return 번호
     * @throws ElException
     */
    public int updateInsimagefile(InsimagefileVo vo) throws ElException {
        return update("com.demo.proworks.insimagefile.updateInsimagefile", vo);
    }

    /**
     * 이미지파일테이블를 삭제한다.
     *  
     * @param  InsimagefileVo 이미지파일테이블
     * @return 번호
     * @throws ElException
     */
    public int deleteInsimagefile(InsimagefileVo vo) throws ElException {
        return delete("com.demo.proworks.insimagefile.deleteInsimagefile", vo);
    }

}

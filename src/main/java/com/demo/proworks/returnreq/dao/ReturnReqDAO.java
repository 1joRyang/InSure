package com.demo.proworks.returnreq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.returnreq.vo.ReturnReqVo;
import com.demo.proworks.returnreq.dao.ReturnReqDAO;

/**  
 * @subject     : 반송요청정보 관련 처리를 담당하는 DAO
 * @description : 반송요청정보 관련 처리를 담당하는 DAO
 * @author      : hyunwoo
 * @since       : 2025/07/22
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/22			 hyunwoo	 		최초 생성
 * 
 */
@Repository("returnReqDAO")
public class ReturnReqDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 반송요청정보 상세 조회한다.
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return ReturnReqVo 반송요청정보
     * @throws ElException
     */
    public ReturnReqVo selectReturnReq(ReturnReqVo vo) throws ElException {
        return (ReturnReqVo) selectByPk("com.demo.proworks.returnreq.selectReturnReq", vo);
    }

    /**
     * 페이징을 처리하여 반송요청정보 목록조회를 한다.
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return List<ReturnReqVo> 반송요청정보
     * @throws ElException
     */
    public List<ReturnReqVo> selectListReturnReq(ReturnReqVo vo) throws ElException {      	
        return (List<ReturnReqVo>)list("com.demo.proworks.returnreq.selectListReturnReq", vo);
    }

    /**
     * 반송요청정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return 반송요청정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountReturnReq(ReturnReqVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.returnreq.selectListCountReturnReq", vo);
    }
        
    /**
     * 반송요청정보를 등록한다. (INSERT만)
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return 번호
     * @throws ElException
     */
    public int insertReturnReq(ReturnReqVo vo) throws ElException { 
        return insert("com.demo.proworks.returnreq.insertReturnReqOnly", vo); 
    }

    /**
     * 반송요청정보를 갱신한다. (UPDATE만)
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return 번호
     * @throws ElException
     */
    public int updateReturnReq(ReturnReqVo vo) throws ElException {
        return update("com.demo.proworks.returnreq.updateReturnReqOnly", vo);
    }

    /**
     * 반송요청정보를 삭제한다.
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return 번호
     * @throws ElException
     */
    public int deleteReturnReq(ReturnReqVo vo) throws ElException {
        return delete("com.demo.proworks.returnreq.deleteReturnReq", vo);
    }

    /**
     * 반송요청 존재 여부 확인
     *  
     * @param  ReturnReqVo 반송요청정보
     * @return boolean 존재 여부
     */
    public boolean existsReturnReq(ReturnReqVo vo) {
        try {
            Integer count = (Integer) selectByPk("com.demo.proworks.returnreq.countReturnReq", vo);
            return count != null && count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int updateClaimStatus(ReturnReqVo vo) {
        return update("com.demo.proworks.returnreq.updateClaimStatusReturn", vo);
    }
}
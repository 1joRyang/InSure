package com.demo.proworks.claimitem.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claimitem.vo.ClaimItemVo;
import com.demo.proworks.claimitem.dao.ClaimItemDAO;

/**  
 * @subject     : 청구 아이템들 관련 처리를 담당하는 DAO
 * @description : 청구 아이템들 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Repository("claimItemDAO")
public class ClaimItemDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 청구 아이템들 상세 조회한다.
     *  
     * @param  ClaimItemVo 청구 아이템들
     * @return ClaimItemVo 청구 아이템들
     * @throws ElException
     */
    public ClaimItemVo selectClaimItem(ClaimItemVo vo) throws ElException {
        return (ClaimItemVo) selectByPk("com.demo.proworks.claimitem.selectClaimItem", vo);
    }

    /**
     * 페이징을 처리하여 청구 아이템들 목록조회를 한다.
     *  
     * @param  ClaimItemVo 청구 아이템들
     * @return List<ClaimItemVo> 청구 아이템들
     * @throws ElException
     */
    public List<ClaimItemVo> selectListClaimItem(ClaimItemVo vo) throws ElException {      	
        return (List<ClaimItemVo>)list("com.demo.proworks.claimitem.selectListClaimItem", vo);
    }

    /**
     * 청구 아이템들 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ClaimItemVo 청구 아이템들
     * @return 청구 아이템들 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountClaimItem(ClaimItemVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.claimitem.selectListCountClaimItem", vo);
    }
        
    /**
     * 청구 아이템들를 등록한다.
     *  
     * @param  ClaimItemVo 청구 아이템들
     * @return 번호
     * @throws ElException
     */
    public int insertClaimItem(ClaimItemVo vo) throws ElException {    	
        return insert("com.demo.proworks.claimitem.insertClaimItem", vo);
    }

    /**
     * 청구 아이템들를 갱신한다.
     *  
     * @param  ClaimItemVo 청구 아이템들
     * @return 번호
     * @throws ElException
     */
    public int updateClaimItem(ClaimItemVo vo) throws ElException {
        return update("com.demo.proworks.claimitem.updateClaimItem", vo);
    }

    /**
     * 청구 아이템들를 삭제한다.
     *  
     * @param  ClaimItemVo 청구 아이템들
     * @return 번호
     * @throws ElException
     */
    public int deleteClaimItem(ClaimItemVo vo) throws ElException {
        return delete("com.demo.proworks.claimitem.deleteClaimItem", vo);
    }

}

package com.demo.proworks.claimoffset.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claimoffset.vo.ClaimOffsetVo;
import com.demo.proworks.claimoffset.dao.ClaimOffsetDAO;

/**  
 * @subject     : 상계 관련 처리를 담당하는 DAO
 * @description : 상계 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Repository("claimOffsetDAO")
public class ClaimOffsetDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 상계 상세 조회한다.
     *  
     * @param  ClaimOffsetVo 상계
     * @return ClaimOffsetVo 상계
     * @throws ElException
     */
    public ClaimOffsetVo selectClaimOffset(ClaimOffsetVo vo) throws ElException {
        return (ClaimOffsetVo) selectByPk("com.demo.proworks.claimoffset.selectClaimOffset", vo);
    }

    /**
     * 페이징을 처리하여 상계 목록조회를 한다.
     *  
     * @param  ClaimOffsetVo 상계
     * @return List<ClaimOffsetVo> 상계
     * @throws ElException
     */
    public List<ClaimOffsetVo> selectListClaimOffset(ClaimOffsetVo vo) throws ElException {      	
        return (List<ClaimOffsetVo>)list("com.demo.proworks.claimoffset.selectListClaimOffset", vo);
    }

    /**
     * 상계 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ClaimOffsetVo 상계
     * @return 상계 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountClaimOffset(ClaimOffsetVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.claimoffset.selectListCountClaimOffset", vo);
    }
        
    /**
     * 상계를 등록한다.
     *  
     * @param  ClaimOffsetVo 상계
     * @return 번호
     * @throws ElException
     */
    public int insertClaimOffset(ClaimOffsetVo vo) throws ElException {    	
        return insert("com.demo.proworks.claimoffset.insertClaimOffset", vo);
    }

    /**
     * 상계를 갱신한다.
     *  
     * @param  ClaimOffsetVo 상계
     * @return 번호
     * @throws ElException
     */
    public int updateClaimOffset(ClaimOffsetVo vo) throws ElException {
        return update("com.demo.proworks.claimoffset.updateClaimOffset", vo);
    }

    /**
     * 상계를 삭제한다.
     *  
     * @param  ClaimOffsetVo 상계
     * @return 번호
     * @throws ElException
     */
    public int deleteClaimOffset(ClaimOffsetVo vo) throws ElException {
        return delete("com.demo.proworks.claimoffset.deleteClaimOffset", vo);
    }

}

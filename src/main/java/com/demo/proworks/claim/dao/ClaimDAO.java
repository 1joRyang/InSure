package com.demo.proworks.claim.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.claim.dao.ClaimDAO;

/**  
 * @subject     : 청구 관련 처리를 담당하는 DAO
 * @description : 청구 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
@Repository("claimDAO")
public class ClaimDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 기지급이력 조회 (claim, claim_result join)
	 */
	public List<ClaimNClaimResultVo> selectClaimNClaimResult(ClaimUserVo vo) throws ElException {
		return (List<ClaimNClaimResultVo>)list("com.demo.proworks.claim.selectClaimJoinClaimResult", vo);
	}

    /**
     * 청구 상세 조회한다.
     *  
     * @param  ClaimVo 청구
     * @return ClaimVo 청구
     * @throws ElException
     */
    public ClaimVo selectClaim(ClaimVo vo) throws ElException {
        return (ClaimVo) selectByPk("com.demo.proworks.claim.selectClaim", vo);
    }

    /**
     * 페이징을 처리하여 청구 목록조회를 한다.
     *  
     * @param  ClaimVo 청구
     * @return List<ClaimVo> 청구
     * @throws ElException
     */
    public List<ClaimVo> selectListClaim(ClaimVo vo) throws ElException {      	
        return (List<ClaimVo>)list("com.demo.proworks.claim.selectListClaim", vo);
    }

    /**
     * 청구 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ClaimVo 청구
     * @return 청구 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountClaim(ClaimVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.claim.selectListCountClaim", vo);
    }
        
    /**
     * 청구를 등록한다.
     *  
     * @param  ClaimVo 청구
     * @return 번호
     * @throws ElException
     */
    public int insertClaim(ClaimVo vo) throws ElException {    	
        return insert("com.demo.proworks.claim.insertClaim", vo);
    }

    /**
     * 청구를 갱신한다.
     *  
     * @param  ClaimVo 청구
     * @return 번호
     * @throws ElException
     */
    public int updateClaim(ClaimVo vo) throws ElException {
        return update("com.demo.proworks.claim.updateClaim", vo);
    }

    /**
     * 청구를 삭제한다.
     *  
     * @param  ClaimVo 청구
     * @return 번호
     * @throws ElException
     */
    public int deleteClaim(ClaimVo vo) throws ElException {
        return delete("com.demo.proworks.claim.deleteClaim", vo);
    }

	public ClaimVo findUsernameAndEmpNameByClaimNo(ClaimVo claimVo) throws ElException {
	    return (ClaimVo) selectByPk("com.demo.proworks.claim.findUsernameAndEmpNameByClaimNo", claimVo);
	}


}

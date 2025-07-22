package com.demo.proworks.claimresult.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claimresult.vo.ClaimResultVo;
import com.demo.proworks.claimresult.dao.ClaimResultDAO;

/**  
 * @subject     : 심사결과 관련 처리를 담당하는 DAO
 * @description : 심사결과 관련 처리를 담당하는 DAO
 * @author      : hyunwoo
 * @since       : 2025/07/13
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/13			 hyunwoo	 		최초 생성
 * 
 */
@Repository("claimResultDAO")
public class ClaimResultDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 심사결과 상세 조회한다.
     *  
     * @param  ClaimResultVo 심사결과
     * @return ClaimResultVo 심사결과
     * @throws ElException
     */
    public ClaimResultVo selectClaimResult(ClaimResultVo vo) throws ElException {
        return (ClaimResultVo) selectByPk("com.demo.proworks.claimresult.selectClaimResult", vo);
    }

    /**
     * 페이징을 처리하여 심사결과 목록조회를 한다.
     *  
     * @param  ClaimResultVo 심사결과
     * @return List<ClaimResultVo> 심사결과
     * @throws ElException
     */
    public List<ClaimResultVo> selectListClaimResult(ClaimResultVo vo) throws ElException {      	
        return (List<ClaimResultVo>)list("com.demo.proworks.claimresult.selectListClaimResult", vo);
    }

    /**
     * 심사결과 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  ClaimResultVo 심사결과
     * @return 심사결과 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountClaimResult(ClaimResultVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.claimresult.selectListCountClaimResult", vo);
    }
        
    /**
     * 심사결과를 등록한다.
     *  
     * @param  ClaimResultVo 심사결과
     * @return 번호
     * @throws ElException
     */
    public int insertClaimResult(ClaimResultVo vo) throws ElException {    	
    
    	update("com.demo.proworks.claimresult.updateClaimStatus",vo);
        return insert("com.demo.proworks.claimresult.insertClaimResult", vo);
    }

    /**
     * 심사결과를 갱신한다.
     *  
     * @param  ClaimResultVo 심사결과
     * @return 번호
     * @throws ElException
     */

	public int updateClaimResult(ClaimResultVo vo) throws ElException {
	    // CLAIM_RESULT 업데이트 전에 CLAIM 상태도 업데이트
	    update("com.demo.proworks.claimresult.updateClaimStatus", vo);
	    return update("com.demo.proworks.claimresult.updateClaimResult", vo);
	}

    /**
     * 심사결과를 삭제한다.
     *  
     * @param  ClaimResultVo 심사결과
     * @return 번호
     * @throws ElException
     */
    public int deleteClaimResult(ClaimResultVo vo) throws ElException {
        return delete("com.demo.proworks.claimresult.deleteClaimResult", vo);
    }



}

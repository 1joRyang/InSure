package com.demo.proworks.additionalreq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.additionalreq.vo.AdditionalReqCusVo;
import com.demo.proworks.additionalreq.vo.AdditionalReqVo;
import com.demo.proworks.additionalreq.dao.AdditionalReqDAO;

/**  
 * @subject     : 추가요청 정보 관련 처리를 담당하는 DAO
 * @description : 추가요청 정보 관련 처리를 담당하는 DAO
 * @author      : hyunwoo
 * @since       : 2025/07/16
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/16			 hyunwoo	 		최초 생성
 * 
 */
@Repository("additionalReqDAO")
public class AdditionalReqDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 추가요청 정보 상세 조회한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return AdditionalReqVo 추가요청 정보
     * @throws ElException
     */
    public AdditionalReqVo selectAdditionalReq(AdditionalReqVo vo) throws ElException {
        return (AdditionalReqVo) selectByPk("com.demo.proworks.additionalreq.selectAdditionalReq", vo);
    }

    /**
     * 페이징을 처리하여 추가요청 정보 목록조회를 한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return List<AdditionalReqVo> 추가요청 정보
     * @throws ElException
     */
    public List<AdditionalReqVo> selectListAdditionalReq(AdditionalReqVo vo) throws ElException {      	
        return (List<AdditionalReqVo>)list("com.demo.proworks.additionalreq.selectListAdditionalReq", vo);
    }

    /**
     * 추가요청 정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return 추가요청 정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountAdditionalReq(AdditionalReqVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.additionalreq.selectListCountAdditionalReq", vo);
    }
        
    /**
     * 추가요청 정보를 등록한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return 번호
     * @throws ElException
     */
    public int insertAdditionalReq(AdditionalReqVo vo) throws ElException {    	
        return insert("com.demo.proworks.additionalreq.insertAdditionalReq", vo);
    }

    /**
     * 추가요청 정보를 갱신한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return 번호
     * @throws ElException
     */
    public int updateAdditionalReq(AdditionalReqVo vo) throws ElException {
        return update("com.demo.proworks.additionalreq.updateAdditionalReq", vo);
    }

    /**
     * 추가요청 정보를 삭제한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return 번호
     * @throws ElException
     */
    public int deleteAdditionalReq(AdditionalReqVo vo) throws ElException {
    return delete("com.demo.proworks.additionalreq.deleteAdditionalReq", vo);
    }

    /**
     * 추가요청 정보를 등록하고 청구 상태를 업데이트한다.
     *  
     * @param  AdditionalReqVo 추가요청 정보
     * @return 번호
     * @throws ElException
     */
    public int insertAdditionalReqAndUpdateClaimStatus(AdditionalReqVo vo) throws ElException {
        // 1. 추가요청 정보 등록
        int result1 = insert("com.demo.proworks.additionalreq.insertAdditionalReqOnly", vo);
        
        // 2. 청구 상태 업데이트
        int result2 = update("com.demo.proworks.additionalreq.updateClaimStatus", vo);
        
        // 두 작업 모두 성공하면 첫 번째 insert 결과 반환
        return result1;
    }
    
    public int upsertAdditionalReqAndUpdateClaimStatus(AdditionalReqCusVo vo) throws ElException {
    // 1. UPSERT 실행 (MySQL ON DUPLICATE KEY UPDATE 사용)
    int result = insert("com.demo.proworks.additionalreq.upsertAdditionalReqOnly", vo);
    
    // 2. 청구 상태 업데이트
    update("com.demo.proworks.additionalreq.updateClaimStatus", vo);
    
    return result;
}

}

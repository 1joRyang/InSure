package com.demo.proworks.claim.service;

import java.util.List;

import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;

/**  
 * @subject     : 청구 관련 처리를 담당하는 인터페이스
 * @description : 청구 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
public interface ClaimService {
	
	/**
	 * 기지급이력 조회
	 */
	public List<ClaimNClaimResultVo> selectClaimNClaimResult(ClaimUserVo claimVo) throws Exception;
	
	
    /**
     * 청구 페이징 처리하여 목록을 조회한다.
     *
     * @param  claimVo 청구 ClaimVo
     * @return 청구 목록 List<ClaimVo>
     * @throws Exception
     */
	public List<ClaimVo> selectListClaim(ClaimVo claimVo) throws Exception;
	
    /**
     * 조회한 청구 전체 카운트
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 청구 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaim(ClaimVo claimVo) throws Exception;
	
    /**
     * 청구를 상세 조회한다.
     *
     * @param  claimVo 청구 ClaimVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimVo selectClaim(ClaimVo claimVo) throws Exception;
		
    /**
     * 청구를 등록 처리 한다.
     *
     * @param  claimVo 청구 ClaimVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaim(ClaimVo claimVo) throws Exception;
	
    /**
     * 청구를 갱신 처리 한다.
     *
     * @param  claimVo 청구 ClaimVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaim(ClaimVo claimVo) throws Exception;
	
    /**
     * 청구를 삭제 처리 한다.
     *
     * @param  claimVo 청구 ClaimVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaim(ClaimVo claimVo) throws Exception;
	
}

package com.demo.proworks.claimresult.service;

import java.util.List;

import com.demo.proworks.claimresult.vo.ClaimResultVo;

/**  
 * @subject     : 심사결과 관련 처리를 담당하는 인터페이스
 * @description : 심사결과 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/13
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/13			 hyunwoo	 		최초 생성
 * 
 */
public interface ClaimResultService {
	
    /**
     * 심사결과 페이징 처리하여 목록을 조회한다.
     *
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 심사결과 목록 List<ClaimResultVo>
     * @throws Exception
     */
	public List<ClaimResultVo> selectListClaimResult(ClaimResultVo claimResultVo) throws Exception;
	
    /**
     * 조회한 심사결과 전체 카운트
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 심사결과 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaimResult(ClaimResultVo claimResultVo) throws Exception;
	
    /**
     * 심사결과를 상세 조회한다.
     *
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimResultVo selectClaimResult(ClaimResultVo claimResultVo) throws Exception;
		
    /**
     * 심사결과를 등록 처리 한다.
     *
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaimResult(ClaimResultVo claimResultVo) throws Exception;
	
    /**
     * 심사결과를 갱신 처리 한다.
     *
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaimResult(ClaimResultVo claimResultVo) throws Exception;
	
    /**
     * 심사결과를 삭제 처리 한다.
     *
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaimResult(ClaimResultVo claimResultVo) throws Exception;
	
}

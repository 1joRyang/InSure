package com.demo.proworks.claimoffset.service;

import java.util.List;

import com.demo.proworks.claimoffset.vo.ClaimOffsetVo;

/**  
 * @subject     : 상계 관련 처리를 담당하는 인터페이스
 * @description : 상계 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
public interface ClaimOffsetService {
	
    /**
     * 상계 페이징 처리하여 목록을 조회한다.
     *
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 상계 목록 List<ClaimOffsetVo>
     * @throws Exception
     */
	public List<ClaimOffsetVo> selectListClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception;
	
    /**
     * 조회한 상계 전체 카운트
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 상계 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception;
	
    /**
     * 상계를 상세 조회한다.
     *
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimOffsetVo selectClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception;
		
    /**
     * 상계를 등록 처리 한다.
     *
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception;
	
    /**
     * 상계를 갱신 처리 한다.
     *
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception;
	
    /**
     * 상계를 삭제 처리 한다.
     *
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception;
	
}

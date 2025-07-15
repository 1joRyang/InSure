package com.demo.proworks.claimitem.service;

import java.util.List;

import com.demo.proworks.claimitem.vo.ClaimItemVo;

/**  
 * @subject     : 청구 아이템들 관련 처리를 담당하는 인터페이스
 * @description : 청구 아이템들 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
public interface ClaimItemService {
	
    /**
     * 청구 아이템들 페이징 처리하여 목록을 조회한다.
     *
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 청구 아이템들 목록 List<ClaimItemVo>
     * @throws Exception
     */
	public List<ClaimItemVo> selectListClaimItem(ClaimItemVo claimItemVo) throws Exception;
	
    /**
     * 조회한 청구 아이템들 전체 카운트
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 청구 아이템들 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaimItem(ClaimItemVo claimItemVo) throws Exception;
	
    /**
     * 청구 아이템들를 상세 조회한다.
     *
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimItemVo selectClaimItem(ClaimItemVo claimItemVo) throws Exception;
		
    /**
     * 청구 아이템들를 등록 처리 한다.
     *
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaimItem(ClaimItemVo claimItemVo) throws Exception;
	
    /**
     * 청구 아이템들를 갱신 처리 한다.
     *
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaimItem(ClaimItemVo claimItemVo) throws Exception;
	
    /**
     * 청구 아이템들를 삭제 처리 한다.
     *
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaimItem(ClaimItemVo claimItemVo) throws Exception;
	
}

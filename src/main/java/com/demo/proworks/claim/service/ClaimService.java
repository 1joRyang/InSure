package com.demo.proworks.claim.service;

import java.util.List;
import java.util.Map;

import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimNoVo;
import com.demo.proworks.claim.vo.ClaimUserEmpVo;
import com.demo.proworks.claim.vo.ClaimEmployeeVo;
import com.demo.proworks.claim.vo.ClaimFullJoinVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;

/**
 * @subject : 청구 관련 처리를 담당하는 인터페이스
 * @description : 청구 관련 처리를 담당하는 인터페이스
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
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
	 * @param claimVo 청구 ClaimVo
	 * @return 청구 목록 List<ClaimVo>
	 * @throws Exception
	 */
	public List<ClaimVo> selectListClaim(ClaimVo claimVo) throws Exception;

	/**
	 * 조회한 청구 전체 카운트
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 청구 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountClaim(ClaimVo claimVo) throws Exception;

	/**
	 * 청구를 상세 조회한다.
	 *
	 * @param claimVo 청구 ClaimVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public ClaimVo selectClaim(ClaimVo claimVo) throws Exception;

	/**
	 * 청구를 등록 처리 한다.
	 *
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertClaim(ClaimVo claimVo) throws Exception;

	/**
	 * 청구를 갱신 처리 한다.
	 *
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateClaim(ClaimVo claimVo) throws Exception;

	/**
	 * 청구를 삭제 처리 한다.
	 *
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteClaim(ClaimVo claimVo) throws Exception;

	
	/**
     * 청구 정보와 첨부파일 정보를 DB에 최종 저장
     * 생성자: 이지현
     * @param  claimData 세션에 저장된 모든 청구 정보
     * @throws Exception
     */
	public void saveFinalClaim(Map<String, Object> claimData) throws Exception;
	
	


	public ClaimUserEmpVo findUsernameAndEmpNameByClaimNo(ClaimNoVo claimVo) throws Exception;

	/**
	 * 청구와 직원 정보 조인 목록 조회
	 *
	 * @param claimEmployeeVo 청구-직원 VO
	 * @return 청구-직원 목록 List<ClaimEmployeeVo>
	 * @throws Exception
	 */
	public List<ClaimEmployeeVo> selectClaimEmployeeList(ClaimEmployeeVo claimEmployeeVo) throws Exception;

	/**
	 * 청구와 직원 정보 조인 목록 카운트 조회
	 * 
	 * @param claimEmployeeVo 청구-직원 VO
	 * @return 청구-직원 목록 카운트
	 * @throws Exception
	 */
	public long selectClaimEmployeeListCount(ClaimEmployeeVo claimEmployeeVo) throws Exception;

	/**
	 * 청구 담당자를 업데이트한다.
	 *
	 * @param claimVo 청구 정보
	 * @return 업데이트 결과
	 * @throws Exception
	 */
	public int updateClaimAssignee(ClaimVo claimVo) throws Exception;

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 조회
	 *
	 * @param claimFullJoinVo 청구-전체조인 VO
	 * @return 청구-전체조인 목록 List<ClaimFullJoinVo>
	 * @throws Exception
	 */
	public List<ClaimFullJoinVo> selectClaimFullJoinList(ClaimFullJoinVo claimFullJoinVo) throws Exception;

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 카운트 조회
	 * 
	 * @param claimFullJoinVo 청구-전체조인 VO
	 * @return 청구-전체조인 목록 카운트
	 * @throws Exception
	 */
	public long selectClaimFullJoinListCount(ClaimFullJoinVo claimFullJoinVo) throws Exception;
	
	/**
	 * 사용자 주민번호로 청구목록 조회 (사용자, 직원, 결과 정보 조인)
	 *
	 * @param claimFullJoinVo 청구-전체조인 VO (주민번호 포함)
	 * @return 사용자의 청구목록
	 * @throws Exception
	 */
	public List<ClaimFullJoinVo> selectUserClaimsByRrn(ClaimFullJoinVo claimFullJoinVo) throws Exception;


}

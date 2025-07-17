package com.demo.proworks.claim.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimNoVo;
import com.demo.proworks.claim.vo.ClaimUserEmpVo;
import com.demo.proworks.claim.vo.ClaimEmployeeVo;
import com.demo.proworks.claim.vo.ClaimFullJoinVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.claim.dao.ClaimDAO;

/**
 * @subject : 청구 관련 처리를 담당하는 DAO
 * @description : 청구 관련 처리를 담당하는 DAO
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Repository("claimDAO")
public class ClaimDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 기지급이력 조회 (claim, claim_result join)
	 */
	public List<ClaimNClaimResultVo> selectClaimNClaimResult(ClaimUserVo vo) throws ElException {
		return (List<ClaimNClaimResultVo>) list("com.demo.proworks.claim.selectClaimJoinClaimResult", vo);
	}

	/**
	 * 청구 상세 조회한다.
	 * 
	 * @param ClaimVo 청구
	 * @return ClaimVo 청구
	 * @throws ElException
	 */
	public ClaimVo selectClaim(ClaimVo vo) throws ElException {
		return (ClaimVo) selectByPk("com.demo.proworks.claim.selectClaim", vo);
	}

	/**
	 * 페이징을 처리하여 청구 목록조회를 한다.
	 * 
	 * @param ClaimVo 청구
	 * @return List<ClaimVo> 청구
	 * @throws ElException
	 */
	public List<ClaimVo> selectListClaim(ClaimVo vo) throws ElException {
		return (List<ClaimVo>) list("com.demo.proworks.claim.selectListClaim", vo);
	}

	/**
	 * 청구 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param ClaimVo 청구
	 * @return 청구 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountClaim(ClaimVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.claim.selectListCountClaim", vo);
	}

	/**
	 * 청구를 등록한다.
	 * 
	 * @param ClaimVo 청구
	 * @return 번호
	 * @throws ElException
	 */
	public int insertClaim(ClaimVo vo) throws ElException {
		return insert("com.demo.proworks.claim.insertClaim", vo);
	}

	/**
	 * 청구를 갱신한다.
	 * 
	 * @param ClaimVo 청구
	 * @return 번호
	 * @throws ElException
	 */
	public int updateClaim(ClaimVo vo) throws ElException {
		return update("com.demo.proworks.claim.updateClaim", vo);
	}

	/**
	 * 청구를 삭제한다.
	 * 
	 * @param ClaimVo 청구
	 * @return 번호
	 * @throws ElException
	 */
	public int deleteClaim(ClaimVo vo) throws ElException {
		return delete("com.demo.proworks.claim.deleteClaim", vo);
	}

    public ClaimUserEmpVo findUsernameAndEmpNameByClaimNo(ClaimNoVo claimVo) throws ElException {
        return (ClaimUserEmpVo) selectByPk("com.demo.proworks.claim.findUsernameAndEmpNameByClaimNo", claimVo);
    }

	/**
	 * 청구와 직원 정보 조인 목록 조회
	 * 
	 * @param ClaimEmployeeVo 청구-직원 VO
	 * @return List<ClaimEmployeeVo> 청구-직원 목록
	 * @throws ElException
	 */
	public List<ClaimEmployeeVo> selectClaimEmployeeList(ClaimEmployeeVo vo) throws ElException {
		return (List<ClaimEmployeeVo>) list("com.demo.proworks.claim.selectClaimEmployeeList", vo);
	}

	/**
	 * 청구와 직원 정보 조인 목록 카운트 조회
	 * 
	 * @param ClaimEmployeeVo 청구-직원 VO
	 * @return long 전체 카운트
	 * @throws ElException
	 */
	public long selectClaimEmployeeListCount(ClaimEmployeeVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.claim.selectClaimEmployeeListCount", vo);
	}

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 조회
	 * 
	 * @param ClaimFullJoinVo 청구-전체조인 VO
	 * @return List<ClaimFullJoinVo> 청구-전체조인 목록
	 * @throws ElException
	 */
	public List<ClaimFullJoinVo> selectClaimFullJoinList(ClaimFullJoinVo vo) throws ElException {
		return (List<ClaimFullJoinVo>) list("com.demo.proworks.claim.selectClaimFullJoinList", vo);
	}

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 카운트 조회
	 * 
	 * @param ClaimFullJoinVo 청구-전체조인 VO
	 * @return long 전체 카운트
	 * @throws ElException
	 */
	public long selectClaimFullJoinListCount(ClaimFullJoinVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.claim.selectClaimFullJoinListCount", vo);
	}
	
		
	/**
	 * 사용자 주민번호로 청구목록 조회 (사용자, 직원, 결과 정보 조인)
	 *
	 * @param claimFullJoinVo 청구-전체조인 VO (주민번호 포함)
	 * @return 사용자의 청구목록
	 * @throws Exception
	 */
	public List<ClaimFullJoinVo> selectUserClaimsByRrn(ClaimFullJoinVo vo) throws ElException {
		return (List<ClaimFullJoinVo>) list("com.demo.proworks.claim.selectUserClaimsByRrn", vo);
	}

}

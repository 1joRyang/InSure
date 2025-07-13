package com.demo.proworks.claim.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.claim.dao.ClaimDAO;
import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimNoVo;
import com.demo.proworks.claim.vo.ClaimUserEmpVo;
import com.demo.proworks.claim.vo.ClaimEmployeeVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;

/**
 * @subject : 청구 관련 처리를 담당하는 ServiceImpl
 * @description : 청구 관련 처리를 담당하는 ServiceImpl
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Service("claimServiceImpl")
public class ClaimServiceImpl implements ClaimService {

	@Resource(name = "claimDAO")
	private ClaimDAO claimDAO;

	@Resource(name = "assignRuleServiceImpl")
	private AssignRuleService assignRuleService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	public List<ClaimNClaimResultVo> selectClaimNClaimResult(ClaimUserVo claimVo) throws Exception {
		return claimDAO.selectClaimNClaimResult(claimVo);
	}

	/**
	 * 청구 목록을 조회합니다.
	 *
	 * @process 1. 청구 페이징 처리하여 목록을 조회한다. 2. 결과 List<ClaimVo>을(를) 리턴한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 청구 목록 List<ClaimVo>
	 * @throws Exception
	 */
	public List<ClaimVo> selectListClaim(ClaimVo claimVo) throws Exception {
		List<ClaimVo> list = claimDAO.selectListClaim(claimVo);

		return list;
	}

	/**
	 * 조회한 청구 전체 카운트
	 *
	 * @process 1. 청구 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 청구 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.selectListCountClaim(claimVo);
	}

	/**
	 * 청구를 상세 조회한다.
	 *
	 * @process 1. 청구를 상세 조회한다. 2. 결과 ClaimVo을(를) 리턴한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public ClaimVo selectClaim(ClaimVo claimVo) throws Exception {
		ClaimVo resultVO = claimDAO.selectClaim(claimVo);

		return resultVO;
	}

	/**
	 * 청구를 등록 처리 한다.
	 *
	 * @process 1. 청구를 등록 처리 한다. 2. 자동 배정 설정이 활성화된 경우 자동으로 배정을 실행한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	@Transactional
	public int insertClaim(ClaimVo claimVo) throws Exception {
		// 1. 청구 데이터 등록
		int result = claimDAO.insertClaim(claimVo);

		// 2. 등록이 성공한 경우 자동 배정 실행
		if (result > 0 && claimVo.getClaim_no() != null) {
			try {
				// 자동 배정 설정 확인
				String autoAssignEnabled = assignRuleService.getAutoAssignConfig();

				// 자동 배정이 활성화된 경우에만 실행
				if ("true".equals(autoAssignEnabled)) {
					// 신규 청구에 대해 자동 배정 실행
					String assignResult = assignRuleService.assignEmployeeToClaim(claimVo.getClaim_no());

					// 배정 결과 로그 (필요시 추가 처리)
					System.out.println("[자동 배정] " + assignResult);
				}
			} catch (Exception e) {
				// 자동 배정 실패 시에도 청구 등록은 유지하고 로그만 남김
				System.err.println("[자동 배정 실패] 청구번호: " + claimVo.getClaim_no() + ", 오류: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 청구를 갱신 처리 한다.
	 *
	 * @process 1. 청구를 갱신 처리 한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.updateClaim(claimVo);
	}

	/**
	 * 청구를 삭제 처리 한다.
	 *
	 * @process 1. 청구를 삭제 처리 한다.
	 * 
	 * @param claimVo 청구 ClaimVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.deleteClaim(claimVo);
	}

    public ClaimUserEmpVo findUsernameAndEmpNameByClaimNo(ClaimNoVo claimVo) throws Exception {
		    try {
		    	System.out.println("[SERVICE DEBUG] 처리 시작 - claimVo: " + claimVo.toString());
		    	ClaimUserEmpVo result = claimDAO.findUsernameAndEmpNameByClaimNo(claimVo);
		    	System.out.println("[SERVICE DEBUG] DAO 결과: " + (result != null ? result.toString() : "null"));
		    	return result;
		    } catch (Exception e) {
		    	System.err.println("[SERVICE ERROR] findUsernameAndEmpNameByClaimNo 오류: " + e.getMessage());
		    	e.printStackTrace();
		    	throw e;
		    }
    }

	/**
	 * 청구와 직원 정보 조인 목록 조회
	 *
	 * @process 1. 청구와 직원 정보를 조인하여 목록을 조회한다. 2. 결과 List<ClaimEmployeeVo>을(를) 리턴한다.
	 * 
	 * @param claimEmployeeVo 청구-직원 VO
	 * @return 청구-직원 목록 List<ClaimEmployeeVo>
	 * @throws Exception
	 */
	public List<ClaimEmployeeVo> selectClaimEmployeeList(ClaimEmployeeVo claimEmployeeVo) throws Exception {
		List<ClaimEmployeeVo> list = claimDAO.selectClaimEmployeeList(claimEmployeeVo);
		return list;
	}

	/**
	 * 청구와 직원 정보 조인 목록 카운트 조회
	 *
	 * @process 1. 청구와 직원 정보를 조인하여 카운트를 리턴한다.
	 * 
	 * @param claimEmployeeVo 청구-직원 VO
	 * @return 청구-직원 목록 카운트
	 * @throws Exception
	 */
	public long selectClaimEmployeeListCount(ClaimEmployeeVo claimEmployeeVo) throws Exception {
		return claimDAO.selectClaimEmployeeListCount(claimEmployeeVo);
	}

}

package com.demo.proworks.claim.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.claim.vo.ClaimListVo;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimNoVo;
import com.demo.proworks.claim.vo.ClaimUserEmpVo;
import com.demo.proworks.claim.vo.ClaimEmployeeVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**
 * @subject : 청구 관련 처리를 담당하는 컨트롤러
 * @description : 청구 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Controller
public class ClaimController {

	/** ClaimService */
	@Resource(name = "claimServiceImpl")
	private ClaimService claimService;

	/**
	 * 기지급이력 조회 (insure-second)
	 */
	@ElService(key = "CLAIMnCResultList")
	@RequestMapping(value = "CLAIMnCResultList")
	@ElDescription(sub = "기지급이력 조회 (CLAIM, CLAIM RESULT 조인)", desc = "기지급이력 조회 (페이징없음)")
	public List<ClaimNClaimResultVo> selectListClaimNClaimResult(ClaimUserVo claimVo) throws Exception {
		List<ClaimNClaimResultVo> claimList = claimService.selectClaimNClaimResult(claimVo);

		return claimList;
	}

	/**
	 * 청구 목록을 조회합니다.
	 *
	 * @param claimVo 청구
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CLAIMList")
	@RequestMapping(value = "CLAIMList")
	@ElDescription(sub = "청구 목록조회", desc = "페이징을 처리하여 청구 목록 조회를 한다.")
	public ClaimListVo selectListClaim(ClaimVo claimVo) throws Exception {

		List<ClaimVo> claimList = claimService.selectListClaim(claimVo);
		long totCnt = claimService.selectListCountClaim(claimVo);

		ClaimListVo retClaimList = new ClaimListVo();
		retClaimList.setClaimVoList(claimList);
		retClaimList.setTotalCount(totCnt);
		retClaimList.setPageSize(claimVo.getPageSize());
		retClaimList.setPageIndex(claimVo.getPageIndex());

		return retClaimList;
	}

	/**
	 * 청구을 단건 조회 처리 한다.
	 *
	 * @param claimVo 청구
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CLAIMUpdView")
	@RequestMapping(value = "CLAIMUpdView")
	@ElDescription(sub = "청구 갱신 폼을 위한 조회", desc = "청구 갱신 폼을 위한 조회를 한다.")
	public ClaimVo selectClaim(ClaimVo claimVo) throws Exception {
		ClaimVo selectClaimVo = claimService.selectClaim(claimVo);

		return selectClaimVo;
	}

	/**
	 * 청구를 등록 처리 한다.
	 *
	 * @param claimVo 청구
	 * @throws Exception
	 */
	@ElService(key = "CLAIMIns")
	@RequestMapping(value = "CLAIMIns")
	@ElDescription(sub = "청구 등록처리", desc = "청구를 등록 처리 한다.")
	public void insertClaim(ClaimVo claimVo) throws Exception {
		claimService.insertClaim(claimVo);
	}

	/**
	 * 청구를 갱신 처리 한다.
	 *
	 * @param claimVo 청구
	 * @throws Exception
	 */
	@ElService(key = "CLAIMUpd")
	@RequestMapping(value = "CLAIMUpd")
	@ElValidator(errUrl = "/claim/claimRegister", errContinue = true)
	@ElDescription(sub = "청구 갱신처리", desc = "청구를 갱신 처리 한다.")
	public void updateClaim(ClaimVo claimVo) throws Exception {

		claimService.updateClaim(claimVo);
	}

	/**
	 * 청구를 삭제 처리한다.
	 *
	 * @param claimVo 청구
	 * @throws Exception
	 */
	@ElService(key = "CLAIMDel")
	@RequestMapping(value = "CLAIMDel")
	@ElDescription(sub = "청구 삭제처리", desc = "청구를 삭제 처리한다.")
	public void deleteClaim(ClaimVo claimVo) throws Exception {
		claimService.deleteClaim(claimVo);
	}

	/**
	 * 청구 번호로 사용자,실무자 찾기
	 *
	 * @param claimVo 청구
	 * @return ClaimUserEmpVo
	 * @throws Exception
	 */
	@ElService(key = "CLAIMFindUserEmpInfo")
	@RequestMapping(value = "CLAIMFindUserEmpInfo")
	@ElDescription(sub = "청구 번호로 사용자,실무자 찾기", desc = "청구 번호로 사용자,실무자 찾기")
	public ClaimUserEmpVo findUsernameAndEmpNameByClaimNo(ClaimNoVo claimNoVo) throws Exception {
		System.out.println("[DEBUG] 받은 ClaimNoVo: " + claimNoVo.toString());
		System.out.println("[DEBUG] claim_no 값: " + claimNoVo.getClaim_no());
		
		ClaimUserEmpVo result = claimService.findUsernameAndEmpNameByClaimNo(claimNoVo);
		System.out.println("[DEBUG] 반환 결과: " + (result != null ? result.toString() : "null"));
		
		return result;
	}

	/**
	 * 청구와 직원 정보 조인 목록 조회
	 *
	 * @param claimEmployeeVo 청구-직원
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CLAIMEmployeeList")
	@RequestMapping(value = "CLAIMEmployeeList")
	@ElDescription(sub = "청구와 직원 조인 목록조회", desc = "청구와 직원 정보를 조인하여 목록을 조회한다.")
	public List<ClaimEmployeeVo> selectClaimEmployeeList(ClaimEmployeeVo claimEmployeeVo) throws Exception {
		
		List<ClaimEmployeeVo> claimEmployeeList = claimService.selectClaimEmployeeList(claimEmployeeVo);
		
		return claimEmployeeList;
	}

	/**
	 * 청구 담당자를 업데이트한다.
	 *
	 * @param claimVo 청구 정보 (claim_no, emp_no 포함)
	 * @return 업데이트 결과
	 * @throws Exception
	 */
	@ElService(key = "CLAIMUpdAssignee")
	@RequestMapping(value = "CLAIMUpdAssignee")
	@ElDescription(sub = "청구 담당자 갱신", desc = "청구의 담당자를 갱신한다.")
	public Map<String, Object> updateClaimAssignee(ClaimVo claimVo) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			if (claimVo.getClaim_no() == null || claimVo.getClaim_no().trim().isEmpty()) {
				result.put("success", false);
				result.put("message", "청구 번호를 입력해주세요.");
				return result;
			}
			
			if (claimVo.getEmp_no() == null || claimVo.getEmp_no().trim().isEmpty()) {
				result.put("success", false);
				result.put("message", "담당자를 선택해주세요.");
				return result;
			}
			
			// 청구 담당자 업데이트
			claimService.updateClaimAssignee(claimVo);
			
			result.put("success", true);
			result.put("message", "담당자가 성공적으로 변경되었습니다.");
			
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		
		return result;
	}

}

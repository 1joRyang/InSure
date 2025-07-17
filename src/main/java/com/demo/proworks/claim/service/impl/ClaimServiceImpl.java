package com.demo.proworks.claim.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
import com.demo.proworks.claim.vo.ClaimFullJoinVo;
import com.demo.proworks.claim.vo.ClaimListwStatusVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;

import com.demo.proworks.insimagefile.dao.InsimagefileDAO;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claim.dao.ClaimDAO;

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
	
	/**
	 * 관리자 청구 목록 조회
	 */
	public List<ClaimListwStatusVo> selectClaimWithStatusManager(ClaimVo claimVo) throws Exception {
		return claimDAO.selectClaimWithStatusManager(claimVo);
	}
	
	/**
	 *  내 청구 목록 조회
	 */
	public List<ClaimListwStatusVo> selectClaimWithStatus(ClaimVo claimVo) throws Exception {
		System.out.println("service" + claimDAO.selectClaimWithStatus(claimVo));
		return claimDAO.selectClaimWithStatus(claimVo);
	}
	
	
	public List<ClaimListwStatusVo> selectClaimWithStatusWait(ClaimVo claimVo) throws Exception {
		System.out.println("service" + claimDAO.selectClaimWithStatusWait(claimVo));
		return claimDAO.selectClaimWithStatusWait(claimVo);
	}

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
		System.out.println("[청구 등록 완료] 청구번호: " + claimVo.getClaim_no() + ", claim_type: " + claimVo.getClaim_type());

		// 2. 등록이 성공한 경우 자동 배정 실행 (실패해도 트랜잭션 롤백 안함)
	    if (result > 0 && claimVo.getClaim_no() != null) {
	        try {
	            // claim_type이 기본 배정 규칙에 없는 경우 "질병"으로 임시 변경하여 배정
	            String originalClaimType = claimVo.getClaim_type();
	            if ("other".equals(originalClaimType)) {
	                System.out.println("[자동 배정] claim_type 'other'를 'disease'로 변경하여 배정 진행");
	                claimVo.setClaim_type("disease");
	            }
	            
	            String assignResult = assignRuleService.assignEmployeeToClaim(claimVo.getClaim_no());
	            System.out.println("[자동 배정 완료] " + assignResult);
	            
	            // 원래 claim_type으로 복구 (DB에는 이미 저장되었으므로 VO만 복구)
	            claimVo.setClaim_type(originalClaimType);
	            
	        } catch (Exception e) {
	            // 자동 배정 실패 시에도 청구 등록은 유지하고 로그만 남김 (트랜잭션 롤백 방지)
	            System.err.println("[자동 배정 실패] 청구번호: " + claimVo.getClaim_no() + ", 오류: " + e.getMessage());
	            System.err.println("[알림] 청구 등록은 완료되었으나 자동 배정만 실패함. 수동 배정 필요.");
	            e.printStackTrace();
	            // 🔥 중요: 자동배정 실패시 예외를 던지지 않음으로써 트랜잭션 롤백 방지
	            // throw new Exception("자동 배정 실패: " + e.getMessage(), e);
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

	/**
	 * 청구 정보와 첨부파일 정보를 DB에 최종 저장 생성자: 이지현
	 */

	@Resource(name = "insimagefileDAO")
	private InsimagefileDAO insimagefileDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveFinalClaim(Map<String, Object> claimData) throws Exception {
		// 1. CLAIM 테이블 저장을 위한 데이터 준비 Map -> ClaimVo변환
		ClaimVo claimVo = new ClaimVo();
		claimVo.setClaim_no((String) claimData.get("claimNo"));
		claimVo.setClaim_type((String) claimData.get("claimType"));
		claimVo.setClaim_content((String) claimData.get("claimContent"));
		claimVo.setID(String.valueOf(claimData.get("userId")));
		claimVo.setStatus("대기");
		SimpleDateFormat receiptFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String receiptDateStr = receiptFormatter.format(new Date());
		claimVo.setReceipt_date(receiptDateStr);

		String accidentDateStr = (String) claimData.get("accidentDate");
		claimVo.setDate_of_accident(accidentDateStr);

		// 자동 생성된 claimDao.insertClaim 메소드 호출
//		claimDAO.insertClaim(claimVo);
		int insertResult = this.insertClaim(claimVo);

		if (insertResult <= 0) {	
			throw new Exception("청구 정보 저장에 실패했습니다.");
		}
		
		System.out.println("CLAIM 테이블 저장 완료: " + claimVo.getClaim_no());

		// --- 2. INS_IMAGE_FILE 테이블 저장을 위한 데이터 준비 ---
		String claimNo = (String) claimData.get("claimNo");
		String s3fileKeys = (String) claimData.get("s3fileKeys");
		String[] keysArray = s3fileKeys.split(",");

		for (String key : keysArray) {
			InsimagefileVo imageVo = new InsimagefileVo();
			imageVo.setClaim_no(claimNo);
			imageVo.setFile_path(key); // S3 오브젝트 키를 file_path에 저장

			// 자동 생성된 insimagefileDao.insertInsimagefile 메소드 호출
			insimagefileDao.insertInsimagefile(imageVo);
		}
		System.out.println("INS_IMAGE_FILE 테이블에 " + keysArray.length + "건 저장 완료.");
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

	/**
	 * 청구 담당자를 업데이트한다.
	 *
	 * @process 1. 청구 존재 여부를 확인한다. 2. 담당자 정보를 업데이트한다.
	 * 
	 * @param claimVo 청구 정보
	 * @return 업데이트 결과
	 * @throws Exception
	 */
	public int updateClaimAssignee(ClaimVo claimVo) throws Exception {
		// 청구 존재 여부 확인
		ClaimVo existingClaim = claimDAO.selectClaim(claimVo);
		if (existingClaim == null) {
			throw new Exception("해당 청구를 찾을 수 없습니다.");
		}

		// 담당자 업데이트
		return claimDAO.updateClaim(claimVo);
	}

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 조회
	 *
	 * @process 1. 청구, 사용자, 직원, 결과 정보를 전체 조인하여 목록을 조회한다. 2. 결과
	 *          List<ClaimFullJoinVo>을(를) 리턴한다.
	 * 
	 * @param claimFullJoinVo 청구-전체조인 VO
	 * @return 청구-전체조인 목록 List<ClaimFullJoinVo>
	 * @throws Exception
	 */
	public List<ClaimFullJoinVo> selectClaimFullJoinList(ClaimFullJoinVo claimFullJoinVo) throws Exception {
		List<ClaimFullJoinVo> list = claimDAO.selectClaimFullJoinList(claimFullJoinVo);
		return list;
	}

	/**
	 * 청구와 사용자, 직원, 결과 정보 전체 조인 목록 카운트 조회
	 *
	 * @process 1. 청구, 사용자, 직원, 결과 정보를 전체 조인하여 카운트를 리턴한다.
	 * 
	 * @param claimFullJoinVo 청구-전체조인 VO
	 * @return 청구-전체조인 목록 카운트
	 * @throws Exception
	 */
	public long selectClaimFullJoinListCount(ClaimFullJoinVo claimFullJoinVo) throws Exception {
		return claimDAO.selectClaimFullJoinListCount(claimFullJoinVo);
	}



	/**
	 * 사용자 주민번호로 청구목록 조회 (사용자, 직원, 결과 정보 조인)
	 *
	 * @param claimFullJoinVo 청구-전체조인 VO (주민번호 포함)
	 * @return 사용자의 청구목록
	 * @throws Exception
	 */
	@Override
	public List<ClaimFullJoinVo> selectUserClaimsByRrn(ClaimFullJoinVo claimFullJoinVo) throws Exception {
	    List<ClaimFullJoinVo> list = claimDAO.selectUserClaimsByRrn(claimFullJoinVo);
	    return list;
	}


}

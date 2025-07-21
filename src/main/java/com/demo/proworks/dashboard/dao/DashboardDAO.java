package com.demo.proworks.dashboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.claim.vo.ClaimFullJoinVo;
import com.demo.proworks.dashboard.vo.TodayStatusVo;

/**
 * @subject : 대시보드 처리를 담당하는 DAO
 * @description : 대시보드 처리를 담당하는 DAO
 * @author : Inswave
 * @since : 2025/07/01
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/01 Inswave 최초 생성
 * 
 */
@Repository("dashboardDAO")
public class DashboardDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
	
	/**
	 * 오늘의 처리 현황 (상태별 건수)을 조회한다.
	 * @return List<TodayStatusVo> 오늘의 처리 현황 결과
	 * @throws ElException
	 */
	 @SuppressWarnings("unchecked")
	 public List<TodayStatusVo> selectTodayStatusCounts() throws ElException {
		 return (List<TodayStatusVo>) list("com.demo.proworks.dashboard.selectTodayStatusCounts");
	 }
	
	
		
	/**
	 * 사용자 주민번호로 청구목록 조회 (사용자, 직원, 결과 정보 조인)
	 *
	 * @param claimFullJoinVo 청구-전체조인 VO (주민번호 포함)
	 * @return 사용자의 청구목록
	 * @throws Exception
	 
	public List<ClaimFullJoinVo> selectUserClaimsByRrn(ClaimFullJoinVo vo) throws ElException {
		return (List<ClaimFullJoinVo>) list("com.demo.proworks.claim.selectUserClaimsByRrn", vo);
	}
*/

}

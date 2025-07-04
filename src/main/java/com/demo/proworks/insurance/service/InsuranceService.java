package com.demo.proworks.insurance.service;

import java.util.List;

import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.insurance.vo.InsuranceVo;
import com.demo.proworks.insurance.vo.SubInsuranceListVo;

/**  
 * @subject     : 보험 관련 처리를 담당하는 인터페이스
 * @description : 보험 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
public interface InsuranceService {

	/**
	 * 고객이 가입한 보험 조회
	 */
	 public List<SubInsuranceListVo> selectListSubInsurance(ClaimUserVo insuranceVo) throws Exception;
	
    /**
     * 보험 페이징 처리하여 목록을 조회한다.
     *
     * @param  insuranceVo 보험 InsuranceVo
     * @return 보험 목록 List<InsuranceVo>
     * @throws Exception
     */
	public List<InsuranceVo> selectListInsurance(InsuranceVo insuranceVo) throws Exception;
	
    /**
     * 조회한 보험 전체 카운트
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 보험 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountInsurance(InsuranceVo insuranceVo) throws Exception;
	
    /**
     * 보험를 상세 조회한다.
     *
     * @param  insuranceVo 보험 InsuranceVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public InsuranceVo selectInsurance(InsuranceVo insuranceVo) throws Exception;
		
    /**
     * 보험를 등록 처리 한다.
     *
     * @param  insuranceVo 보험 InsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int insertInsurance(InsuranceVo insuranceVo) throws Exception;
	
    /**
     * 보험를 갱신 처리 한다.
     *
     * @param  insuranceVo 보험 InsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int updateInsurance(InsuranceVo insuranceVo) throws Exception;
	
    /**
     * 보험를 삭제 처리 한다.
     *
     * @param  insuranceVo 보험 InsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int deleteInsurance(InsuranceVo insuranceVo) throws Exception;
	
}

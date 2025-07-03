package com.demo.proworks.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.insurance.service.InsuranceService;
import com.demo.proworks.insurance.vo.InsuranceVo;
import com.demo.proworks.insurance.vo.SubInsuranceListVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.insurance.dao.InsuranceDAO;

/**  
 * @subject     : 보험 관련 처리를 담당하는 ServiceImpl
 * @description	: 보험 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
@Service("insuranceServiceImpl")
public class InsuranceServiceImpl implements InsuranceService {

    @Resource(name="insuranceDAO")
    private InsuranceDAO insuranceDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	/**
	 * 고객이 가입한 보험 목록을 조회
	 */
	 public List<SubInsuranceListVo> selectListSubInsurance(ClaimUserVo insuranceVo) throws Exception {
		List<SubInsuranceListVo> list = insuranceDAO.selectListSubInsurance(insuranceVo);	
	
		return list;
	 }
	

    /**
     * 보험 목록을 조회합니다.
     *
     * @process
     * 1. 보험 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<InsuranceVo>을(를) 리턴한다.
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 보험 목록 List<InsuranceVo>
     * @throws Exception
     */
	public List<InsuranceVo> selectListInsurance(InsuranceVo insuranceVo) throws Exception {
		List<InsuranceVo> list = insuranceDAO.selectListInsurance(insuranceVo);	
	
		return list;
	}

    /**
     * 조회한 보험 전체 카운트
     *
     * @process
     * 1. 보험 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 보험 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountInsurance(InsuranceVo insuranceVo) throws Exception {
		return insuranceDAO.selectListCountInsurance(insuranceVo);
	}

    /**
     * 보험를 상세 조회한다.
     *
     * @process
     * 1. 보험를 상세 조회한다.
     * 2. 결과 InsuranceVo을(를) 리턴한다.
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public InsuranceVo selectInsurance(InsuranceVo insuranceVo) throws Exception {
		InsuranceVo resultVO = insuranceDAO.selectInsurance(insuranceVo);			
        
        return resultVO;
	}

    /**
     * 보험를 등록 처리 한다.
     *
     * @process
     * 1. 보험를 등록 처리 한다.
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int insertInsurance(InsuranceVo insuranceVo) throws Exception {
		return insuranceDAO.insertInsurance(insuranceVo);	
	}
	
    /**
     * 보험를 갱신 처리 한다.
     *
     * @process
     * 1. 보험를 갱신 처리 한다.
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int updateInsurance(InsuranceVo insuranceVo) throws Exception {				
		return insuranceDAO.updateInsurance(insuranceVo);	   		
	}

    /**
     * 보험를 삭제 처리 한다.
     *
     * @process
     * 1. 보험를 삭제 처리 한다.
     * 
     * @param  insuranceVo 보험 InsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int deleteInsurance(InsuranceVo insuranceVo) throws Exception {
		return insuranceDAO.deleteInsurance(insuranceVo);
	}
	
}

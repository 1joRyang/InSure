package com.demo.proworks.subinsurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.subinsurance.service.SubInsuranceService;
import com.demo.proworks.subinsurance.vo.SubInsuranceVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceProductVo;
import com.demo.proworks.subinsurance.dao.SubInsuranceDAO;

/**  
 * @subject     : 가입보험정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 가입보험정보 관련 처리를 담당하는 ServiceImpl
 * @author      : hyunwoo
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 hyunwoo	 		최초 생성
 * 
 */
@Service("subInsuranceServiceImpl")
public class SubInsuranceServiceImpl implements SubInsuranceService {

    @Resource(name="subInsuranceDAO")
    private SubInsuranceDAO subInsuranceDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 가입보험정보 목록을 조회합니다.
     *
     * @process
     * 1. 가입보험정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<SubInsuranceVo>을(를) 리턴한다.
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 가입보험정보 목록 List<SubInsuranceVo>
     * @throws Exception
     */
	public List<SubInsuranceVo> selectListSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
		List<SubInsuranceVo> list = subInsuranceDAO.selectListSubInsurance(subInsuranceVo);	
	
		return list;
	}

    /**
     * 조회한 가입보험정보 전체 카운트
     *
     * @process
     * 1. 가입보험정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 가입보험정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
		return subInsuranceDAO.selectListCountSubInsurance(subInsuranceVo);
	}

    /**
     * 가입보험정보를 상세 조회한다.
     *
     * @process
     * 1. 가입보험정보를 상세 조회한다.
     * 2. 결과 SubInsuranceVo을(를) 리턴한다.
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public SubInsuranceVo selectSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
		SubInsuranceVo resultVO = subInsuranceDAO.selectSubInsurance(subInsuranceVo);			
        
        return resultVO;
	}

    /**
     * 가입보험정보를 등록 처리 한다.
     *
     * @process
     * 1. 가입보험정보를 등록 처리 한다.
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int insertSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
		return subInsuranceDAO.insertSubInsurance(subInsuranceVo);	
	}
	
    /**
     * 가입보험정보를 갱신 처리 한다.
     *
     * @process
     * 1. 가입보험정보를 갱신 처리 한다.
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int updateSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {				
		return subInsuranceDAO.updateSubInsurance(subInsuranceVo);	   		
	}

    /**
     * 가입보험정보를 삭제 처리 한다.
     *
     * @process
     * 1. 가입보험정보를 삭제 처리 한다.
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int deleteSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
		return subInsuranceDAO.deleteSubInsurance(subInsuranceVo);
	}

    /**
     * 사용자 ID로 보험상품 목록을 조회한다.
     *
     * @process
     * 1. 사용자 ID로 보험상품 목록을 조회한다.
     * 2. 결과 List<SubInsuranceProductVo>를 리턴한다.
     * 
     * @param  subInsuranceProductVo 보험상품조회정보 SubInsuranceProductVo
     * @return 보험상품 목록 List<SubInsuranceProductVo>
     * @throws Exception
     */
	public List<SubInsuranceProductVo> selectInsuranceProductsByUserId(SubInsuranceProductVo subInsuranceProductVo) throws Exception {
		List<SubInsuranceProductVo> list = subInsuranceDAO.selectInsuranceProductsByUserId(subInsuranceProductVo);
		return list;
	}
	
}

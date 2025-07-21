package com.demo.proworks.subinsurance.service;

import java.util.List;

import com.demo.proworks.subinsurance.vo.SubInsuranceVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceProductVo;

/**  
 * @subject     : 가입보험정보 관련 처리를 담당하는 인터페이스
 * @description : 가입보험정보 관련 처리를 담당하는 인터페이스
 * @author      : hyunwoo
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 hyunwoo	 		최초 생성
 * 
 */
public interface SubInsuranceService {
	
    /**
     * 가입보험정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 가입보험정보 목록 List<SubInsuranceVo>
     * @throws Exception
     */
	public List<SubInsuranceVo> selectListSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception;
	
    /**
     * 조회한 가입보험정보 전체 카운트
     * 
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 가입보험정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception;
	
    /**
     * 가입보험정보를 상세 조회한다.
     *
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public SubInsuranceVo selectSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception;
		
    /**
     * 가입보험정보를 등록 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int insertSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception;
	
    /**
     * 가입보험정보를 갱신 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int updateSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception;
	
    /**
     * 가입보험정보를 삭제 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보 SubInsuranceVo
     * @return 번호
     * @throws Exception
     */
	public int deleteSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception;

    /**
     * 사용자 ID로 보험상품 목록을 조회한다.
     *
     * @param  subInsuranceProductVo 보험상품조회정보 SubInsuranceProductVo
     * @return 보험상품 목록 List<SubInsuranceProductVo>
     * @throws Exception
     */
	public List<SubInsuranceProductVo> selectInsuranceProductsByUserId(SubInsuranceProductVo subInsuranceProductVo) throws Exception;
	
}

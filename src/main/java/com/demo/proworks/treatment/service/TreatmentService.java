package com.demo.proworks.treatment.service;

import java.util.List;

import com.demo.proworks.treatment.vo.TreatmentVo;

/**  
 * @subject     : 진료정보 관련 처리를 담당하는 인터페이스
 * @description : 진료정보 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
public interface TreatmentService {
	
    /**
     * 진료정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 진료정보 목록 List<TreatmentVo>
     * @throws Exception
     */
	public List<TreatmentVo> selectListTreatment(TreatmentVo treatmentVo) throws Exception;
	
    /**
     * 조회한 진료정보 전체 카운트
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 진료정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountTreatment(TreatmentVo treatmentVo) throws Exception;
	
    /**
     * 진료정보를 상세 조회한다.
     *
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public TreatmentVo selectTreatment(TreatmentVo treatmentVo) throws Exception;
		
    /**
     * 진료정보를 등록 처리 한다.
     *
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 번호
     * @throws Exception
     */
	public int insertTreatment(TreatmentVo treatmentVo) throws Exception;
	
    /**
     * 진료정보를 갱신 처리 한다.
     *
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 번호
     * @throws Exception
     */
	public int updateTreatment(TreatmentVo treatmentVo) throws Exception;
	
    /**
     * 진료정보를 삭제 처리 한다.
     *
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 번호
     * @throws Exception
     */
	public int deleteTreatment(TreatmentVo treatmentVo) throws Exception;
	
}

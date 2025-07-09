package com.demo.proworks.treatment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.treatment.service.TreatmentService;
import com.demo.proworks.treatment.vo.TreatmentVo;
import com.demo.proworks.treatment.dao.TreatmentDAO;

/**  
 * @subject     : 진료정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 진료정보 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Service("treatmentServiceImpl")
public class TreatmentServiceImpl implements TreatmentService {

    @Resource(name="treatmentDAO")
    private TreatmentDAO treatmentDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 진료정보 목록을 조회합니다.
     *
     * @process
     * 1. 진료정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<TreatmentVo>을(를) 리턴한다.
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 진료정보 목록 List<TreatmentVo>
     * @throws Exception
     */
	public List<TreatmentVo> selectListTreatment(TreatmentVo treatmentVo) throws Exception {
		List<TreatmentVo> list = treatmentDAO.selectListTreatment(treatmentVo);	
	
		return list;
	}

    /**
     * 조회한 진료정보 전체 카운트
     *
     * @process
     * 1. 진료정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 진료정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountTreatment(TreatmentVo treatmentVo) throws Exception {
		return treatmentDAO.selectListCountTreatment(treatmentVo);
	}

    /**
     * 진료정보를 상세 조회한다.
     *
     * @process
     * 1. 진료정보를 상세 조회한다.
     * 2. 결과 TreatmentVo을(를) 리턴한다.
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public TreatmentVo selectTreatment(TreatmentVo treatmentVo) throws Exception {
		TreatmentVo resultVO = treatmentDAO.selectTreatment(treatmentVo);			
        
        return resultVO;
	}

    /**
     * 진료정보를 등록 처리 한다.
     *
     * @process
     * 1. 진료정보를 등록 처리 한다.
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 번호
     * @throws Exception
     */
	public int insertTreatment(TreatmentVo treatmentVo) throws Exception {
		return treatmentDAO.insertTreatment(treatmentVo);	
	}
	
    /**
     * 진료정보를 갱신 처리 한다.
     *
     * @process
     * 1. 진료정보를 갱신 처리 한다.
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 번호
     * @throws Exception
     */
	public int updateTreatment(TreatmentVo treatmentVo) throws Exception {				
		return treatmentDAO.updateTreatment(treatmentVo);	   		
	}

    /**
     * 진료정보를 삭제 처리 한다.
     *
     * @process
     * 1. 진료정보를 삭제 처리 한다.
     * 
     * @param  treatmentVo 진료정보 TreatmentVo
     * @return 번호
     * @throws Exception
     */
	public int deleteTreatment(TreatmentVo treatmentVo) throws Exception {
		return treatmentDAO.deleteTreatment(treatmentVo);
	}
	
}

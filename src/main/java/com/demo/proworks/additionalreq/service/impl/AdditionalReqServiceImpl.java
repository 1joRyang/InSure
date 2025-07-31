package com.demo.proworks.additionalreq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.proworks.additionalreq.service.AdditionalReqService;
import com.demo.proworks.additionalreq.vo.AdditionalReqCusVo;
import com.demo.proworks.additionalreq.vo.AdditionalReqVo;
import com.demo.proworks.additionalreq.dao.AdditionalReqDAO;

/**
 * @subject : 추가요청 정보 관련 처리를 담당하는 ServiceImpl
 * @description : 추가요청 정보 관련 처리를 담당하는 ServiceImpl
 * @author : hyunwoo
 * @since : 2025/07/16
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/16 hyunwoo 최초 생성
 * 
 */
@Service("additionalReqServiceImpl")
public class AdditionalReqServiceImpl implements AdditionalReqService {

	@Resource(name = "additionalReqDAO")
	private AdditionalReqDAO additionalReqDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 추가요청 정보 목록을 조회합니다.
	 *
	 * @process 1. 추가요청 정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<AdditionalReqVo>을(를) 리턴한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 추가요청 정보 목록 List<AdditionalReqVo>
	 * @throws Exception
	 */
	public List<AdditionalReqVo> selectListAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		List<AdditionalReqVo> list = additionalReqDAO.selectListAdditionalReq(additionalReqVo);

		return list;
	}

	/**
	 * 조회한 추가요청 정보 전체 카운트
	 *
	 * @process 1. 추가요청 정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 추가요청 정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		return additionalReqDAO.selectListCountAdditionalReq(additionalReqVo);
	}

	/**
	 * 추가요청 정보를 상세 조회한다.
	 *
	 * @process 1. 추가요청 정보를 상세 조회한다. 2. 결과 AdditionalReqVo을(를) 리턴한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public AdditionalReqVo selectAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		AdditionalReqVo resultVO = additionalReqDAO.selectAdditionalReq(additionalReqVo);

		return resultVO;
	}

	/**
	 * 추가요청 존재 여부를 확인한다.
	 *
	 * @process 1. 추가요청 존재 여부를 확인한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 존재 여부
	 * @throws Exception
	 */
	public boolean existsAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		return additionalReqDAO.existsAdditionalReq(additionalReqVo);
	}

	/**
	 * 추가요청 정보를 등록 처리 한다. (INSERT만)
	 *
	 * @process 1. 추가요청 정보를 등록 처리 한다.
	 *          2. 청구 상태를 업데이트 한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 번호
	 * @throws Exception
	 */
	@Transactional
	public int insertAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		int result = additionalReqDAO.insertAdditionalReq(additionalReqVo);
		if (result > 0) {
			additionalReqDAO.updateClaimStatus(additionalReqVo);
		}
		
		return result;
	}

	/**
	 * 추가요청 정보를 갱신 처리 한다. (UPDATE만)
	 *
	 * @process 1. 추가요청 정보를 갱신 처리 한다.
	 *          2. 청구 상태를 업데이트 한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 번호
	 * @throws Exception
	 */
	@Transactional
	public int updateAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		int result = additionalReqDAO.updateAdditionalReq(additionalReqVo);
		if (result > 0) {
			additionalReqDAO.updateClaimStatus(additionalReqVo);
		}
		
		return result;
	}

	/**
	 * 추가요청 정보를 삭제 처리 한다.
	 *
	 * @process 1. 추가요청 정보를 삭제 처리 한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteAdditionalReq(AdditionalReqVo additionalReqVo) throws Exception {
		return additionalReqDAO.deleteAdditionalReq(additionalReqVo);
	}

	/**
	 * 추가요청 정보를 등록하고 청구 상태를 업데이트 한다. (기존 호환성 유지)
	 *
	 * @process 1. 추가요청 정보를 등록 처리 한다. 2. 청구 상태를 업데이트 한다.
	 * 
	 * @param additionalReqVo 추가요청 정보 AdditionalReqVo
	 * @return 번호
	 * @throws Exception
	 */
	@Transactional
	public int insertAdditionalReqAndUpdateClaimStatus(AdditionalReqVo additionalReqVo) throws Exception {
		return additionalReqDAO.insertAdditionalReqAndUpdateClaimStatus(additionalReqVo);
	}

	/**
	 * 추가요청 정보를 Upsert하고 청구 상태를 업데이트 한다. (기존 호환성 유지)
	 *
	 * @param vo 추가요청 정보 AdditionalReqCusVo
	 * @return 번호
	 * @throws Exception
	 */
	@Transactional
	public int upsertAdditionalReqAndUpdateClaimStatus(AdditionalReqCusVo vo) throws Exception {
		return additionalReqDAO.upsertAdditionalReqAndUpdateClaimStatus(vo);
	}
}
package com.demo.proworks.returnreq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.returnreq.service.ReturnReqService;
import com.demo.proworks.returnreq.vo.ReturnReqVo;
import com.demo.proworks.returnreq.dao.ReturnReqDAO;

/**  
 * @subject     : 반송요청정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 반송요청정보 관련 처리를 담당하는 ServiceImpl
 * @author      : hyunwoo
 * @since       : 2025/07/22
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/22			 hyunwoo	 		최초 생성
 * 
 */
@Service("returnReqServiceImpl")
public class ReturnReqServiceImpl implements ReturnReqService {

    @Resource(name="returnReqDAO")
    private ReturnReqDAO returnReqDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 반송요청정보 목록을 조회합니다.
     *
     * @process
     * 1. 반송요청정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ReturnReqVo>을(를) 리턴한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 반송요청정보 목록 List<ReturnReqVo>
     * @throws Exception
     */
	public List<ReturnReqVo> selectListReturnReq(ReturnReqVo returnReqVo) throws Exception {
		List<ReturnReqVo> list = returnReqDAO.selectListReturnReq(returnReqVo);	
	
		return list;
	}

    /**
     * 조회한 반송요청정보 전체 카운트
     *
     * @process
     * 1. 반송요청정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 반송요청정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountReturnReq(ReturnReqVo returnReqVo) throws Exception {
		return returnReqDAO.selectListCountReturnReq(returnReqVo);
	}

    /**
     * 반송요청정보를 상세 조회한다.
     *
     * @process
     * 1. 반송요청정보를 상세 조회한다.
     * 2. 결과 ReturnReqVo을(를) 리턴한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ReturnReqVo selectReturnReq(ReturnReqVo returnReqVo) throws Exception {
		ReturnReqVo resultVO = returnReqDAO.selectReturnReq(returnReqVo);			
        
        return resultVO;
	}

    /**
     * 반송요청 존재 여부를 확인한다.
     *
     * @process
     * 1. 반송요청 존재 여부를 확인한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 존재 여부
     * @throws Exception
     */
	public boolean existsReturnReq(ReturnReqVo returnReqVo) throws Exception {
		return returnReqDAO.existsReturnReq(returnReqVo);
	}

    /**
     * 반송요청정보를 등록 처리 한다. (INSERT만)
     *
     * @process
     * 1. 반송요청정보를 등록 처리 한다.
     * 2. 청구 상태를 '반송'으로 업데이트 한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 처리 건수
     * @throws Exception
     */
	public int insertReturnReq(ReturnReqVo returnReqVo) throws Exception {
		int result = returnReqDAO.insertReturnReq(returnReqVo);
		if (result > 0) {
			returnReqDAO.updateClaimStatus(returnReqVo);
		}
		
		return result;	
	}
	
    /**
     * 반송요청정보를 갱신 처리 한다. (UPDATE만)
     *
     * @process
     * 1. 반송요청정보를 갱신 처리 한다.
     * 2. 청구 상태를 '반송'으로 업데이트 한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 처리 건수
     * @throws Exception
     */
	public int updateReturnReq(ReturnReqVo returnReqVo) throws Exception {
		int result = returnReqDAO.updateReturnReq(returnReqVo);
		if (result > 0) {
			returnReqDAO.updateClaimStatus(returnReqVo);
		}
		return result;	   		
	}

    /**
     * 반송요청정보를 삭제 처리 한다.
     *
     * @process
     * 1. 반송요청정보를 삭제 처리 한다.
     * 
     * @param  returnReqVo 반송요청정보 ReturnReqVo
     * @return 처리 건수
     * @throws Exception
     */
	public int deleteReturnReq(ReturnReqVo returnReqVo) throws Exception {
		return returnReqDAO.deleteReturnReq(returnReqVo);
	}
}
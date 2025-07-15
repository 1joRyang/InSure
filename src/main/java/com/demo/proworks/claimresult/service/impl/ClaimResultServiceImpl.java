package com.demo.proworks.claimresult.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.claimresult.service.ClaimResultService;
import com.demo.proworks.claimresult.vo.ClaimResultVo;
import com.demo.proworks.claimresult.dao.ClaimResultDAO;

/**  
 * @subject     : 심사결과 관련 처리를 담당하는 ServiceImpl
 * @description	: 심사결과 관련 처리를 담당하는 ServiceImpl
 * @author      : hyunwoo
 * @since       : 2025/07/13
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/13			 hyunwoo	 		최초 생성
 * 
 */
@Service("claimResultServiceImpl")
public class ClaimResultServiceImpl implements ClaimResultService {

    @Resource(name="claimResultDAO")
    private ClaimResultDAO claimResultDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 심사결과 목록을 조회합니다.
     *
     * @process
     * 1. 심사결과 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ClaimResultVo>을(를) 리턴한다.
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 심사결과 목록 List<ClaimResultVo>
     * @throws Exception
     */
	public List<ClaimResultVo> selectListClaimResult(ClaimResultVo claimResultVo) throws Exception {
		List<ClaimResultVo> list = claimResultDAO.selectListClaimResult(claimResultVo);	
	
		return list;
	}

    /**
     * 조회한 심사결과 전체 카운트
     *
     * @process
     * 1. 심사결과 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 심사결과 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaimResult(ClaimResultVo claimResultVo) throws Exception {
		return claimResultDAO.selectListCountClaimResult(claimResultVo);
	}

    /**
     * 심사결과를 상세 조회한다.
     *
     * @process
     * 1. 심사결과를 상세 조회한다.
     * 2. 결과 ClaimResultVo을(를) 리턴한다.
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimResultVo selectClaimResult(ClaimResultVo claimResultVo) throws Exception {
		ClaimResultVo resultVO = claimResultDAO.selectClaimResult(claimResultVo);			
        
        return resultVO;
	}

    /**
     * 심사결과를 등록 처리 한다.
     *
     * @process
     * 1. 심사결과를 등록 처리 한다.
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaimResult(ClaimResultVo claimResultVo) throws Exception {
		return claimResultDAO.insertClaimResult(claimResultVo);	
	}
	
    /**
     * 심사결과를 갱신 처리 한다.
     *
     * @process
     * 1. 심사결과를 갱신 처리 한다.
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaimResult(ClaimResultVo claimResultVo) throws Exception {				
		return claimResultDAO.updateClaimResult(claimResultVo);	   		
	}

    /**
     * 심사결과를 삭제 처리 한다.
     *
     * @process
     * 1. 심사결과를 삭제 처리 한다.
     * 
     * @param  claimResultVo 심사결과 ClaimResultVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaimResult(ClaimResultVo claimResultVo) throws Exception {
		return claimResultDAO.deleteClaimResult(claimResultVo);
	}
	
}

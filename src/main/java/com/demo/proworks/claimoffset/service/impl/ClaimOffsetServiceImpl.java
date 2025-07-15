package com.demo.proworks.claimoffset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.claimoffset.service.ClaimOffsetService;
import com.demo.proworks.claimoffset.vo.ClaimOffsetVo;
import com.demo.proworks.claimoffset.dao.ClaimOffsetDAO;

/**  
 * @subject     : 상계 관련 처리를 담당하는 ServiceImpl
 * @description	: 상계 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Service("claimOffsetServiceImpl")
public class ClaimOffsetServiceImpl implements ClaimOffsetService {

    @Resource(name="claimOffsetDAO")
    private ClaimOffsetDAO claimOffsetDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 상계 목록을 조회합니다.
     *
     * @process
     * 1. 상계 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ClaimOffsetVo>을(를) 리턴한다.
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 상계 목록 List<ClaimOffsetVo>
     * @throws Exception
     */
	public List<ClaimOffsetVo> selectListClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
		List<ClaimOffsetVo> list = claimOffsetDAO.selectListClaimOffset(claimOffsetVo);	
	
		return list;
	}

    /**
     * 조회한 상계 전체 카운트
     *
     * @process
     * 1. 상계 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 상계 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
		return claimOffsetDAO.selectListCountClaimOffset(claimOffsetVo);
	}

    /**
     * 상계를 상세 조회한다.
     *
     * @process
     * 1. 상계를 상세 조회한다.
     * 2. 결과 ClaimOffsetVo을(를) 리턴한다.
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimOffsetVo selectClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
		ClaimOffsetVo resultVO = claimOffsetDAO.selectClaimOffset(claimOffsetVo);			
        
        return resultVO;
	}

    /**
     * 상계를 등록 처리 한다.
     *
     * @process
     * 1. 상계를 등록 처리 한다.
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
		return claimOffsetDAO.insertClaimOffset(claimOffsetVo);	
	}
	
    /**
     * 상계를 갱신 처리 한다.
     *
     * @process
     * 1. 상계를 갱신 처리 한다.
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {				
		return claimOffsetDAO.updateClaimOffset(claimOffsetVo);	   		
	}

    /**
     * 상계를 삭제 처리 한다.
     *
     * @process
     * 1. 상계를 삭제 처리 한다.
     * 
     * @param  claimOffsetVo 상계 ClaimOffsetVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaimOffset(ClaimOffsetVo claimOffsetVo) throws Exception {
		return claimOffsetDAO.deleteClaimOffset(claimOffsetVo);
	}
	
}

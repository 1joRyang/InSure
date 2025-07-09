package com.demo.proworks.claimitem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.claimitem.service.ClaimItemService;
import com.demo.proworks.claimitem.vo.ClaimItemVo;
import com.demo.proworks.claimitem.dao.ClaimItemDAO;

/**  
 * @subject     : 청구 아이템들 관련 처리를 담당하는 ServiceImpl
 * @description	: 청구 아이템들 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Service("claimItemServiceImpl")
public class ClaimItemServiceImpl implements ClaimItemService {

    @Resource(name="claimItemDAO")
    private ClaimItemDAO claimItemDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 청구 아이템들 목록을 조회합니다.
     *
     * @process
     * 1. 청구 아이템들 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ClaimItemVo>을(를) 리턴한다.
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 청구 아이템들 목록 List<ClaimItemVo>
     * @throws Exception
     */
	public List<ClaimItemVo> selectListClaimItem(ClaimItemVo claimItemVo) throws Exception {
		List<ClaimItemVo> list = claimItemDAO.selectListClaimItem(claimItemVo);	
	
		return list;
	}

    /**
     * 조회한 청구 아이템들 전체 카운트
     *
     * @process
     * 1. 청구 아이템들 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 청구 아이템들 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaimItem(ClaimItemVo claimItemVo) throws Exception {
		return claimItemDAO.selectListCountClaimItem(claimItemVo);
	}

    /**
     * 청구 아이템들를 상세 조회한다.
     *
     * @process
     * 1. 청구 아이템들를 상세 조회한다.
     * 2. 결과 ClaimItemVo을(를) 리턴한다.
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimItemVo selectClaimItem(ClaimItemVo claimItemVo) throws Exception {
		ClaimItemVo resultVO = claimItemDAO.selectClaimItem(claimItemVo);			
        
        return resultVO;
	}

    /**
     * 청구 아이템들를 등록 처리 한다.
     *
     * @process
     * 1. 청구 아이템들를 등록 처리 한다.
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaimItem(ClaimItemVo claimItemVo) throws Exception {
		return claimItemDAO.insertClaimItem(claimItemVo);	
	}
	
    /**
     * 청구 아이템들를 갱신 처리 한다.
     *
     * @process
     * 1. 청구 아이템들를 갱신 처리 한다.
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaimItem(ClaimItemVo claimItemVo) throws Exception {				
		return claimItemDAO.updateClaimItem(claimItemVo);	   		
	}

    /**
     * 청구 아이템들를 삭제 처리 한다.
     *
     * @process
     * 1. 청구 아이템들를 삭제 처리 한다.
     * 
     * @param  claimItemVo 청구 아이템들 ClaimItemVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaimItem(ClaimItemVo claimItemVo) throws Exception {
		return claimItemDAO.deleteClaimItem(claimItemVo);
	}
	
}

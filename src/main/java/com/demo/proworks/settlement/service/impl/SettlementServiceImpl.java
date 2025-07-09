package com.demo.proworks.settlement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.settlement.service.SettlementService;
import com.demo.proworks.settlement.vo.SettlementVo;
import com.demo.proworks.settlement.dao.SettlementDAO;

/**  
 * @subject     : 정산정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 정산정보 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
@Service("settlementServiceImpl")
public class SettlementServiceImpl implements SettlementService {

    @Resource(name="settlementDAO")
    private SettlementDAO settlementDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 정산정보 목록을 조회합니다.
     *
     * @process
     * 1. 정산정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<SettlementVo>을(를) 리턴한다.
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 정산정보 목록 List<SettlementVo>
     * @throws Exception
     */
	public List<SettlementVo> selectListSettlement(SettlementVo settlementVo) throws Exception {
		List<SettlementVo> list = settlementDAO.selectListSettlement(settlementVo);	
	
		return list;
	}

    /**
     * 조회한 정산정보 전체 카운트
     *
     * @process
     * 1. 정산정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 정산정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountSettlement(SettlementVo settlementVo) throws Exception {
		return settlementDAO.selectListCountSettlement(settlementVo);
	}

    /**
     * 정산정보를 상세 조회한다.
     *
     * @process
     * 1. 정산정보를 상세 조회한다.
     * 2. 결과 SettlementVo을(를) 리턴한다.
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public SettlementVo selectSettlement(SettlementVo settlementVo) throws Exception {
		SettlementVo resultVO = settlementDAO.selectSettlement(settlementVo);			
        
        return resultVO;
	}

    /**
     * 정산정보를 등록 처리 한다.
     *
     * @process
     * 1. 정산정보를 등록 처리 한다.
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 번호
     * @throws Exception
     */
	public int insertSettlement(SettlementVo settlementVo) throws Exception {
		return settlementDAO.insertSettlement(settlementVo);	
	}
	
    /**
     * 정산정보를 갱신 처리 한다.
     *
     * @process
     * 1. 정산정보를 갱신 처리 한다.
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 번호
     * @throws Exception
     */
	public int updateSettlement(SettlementVo settlementVo) throws Exception {				
		return settlementDAO.updateSettlement(settlementVo);	   		
	}

    /**
     * 정산정보를 삭제 처리 한다.
     *
     * @process
     * 1. 정산정보를 삭제 처리 한다.
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 번호
     * @throws Exception
     */
	public int deleteSettlement(SettlementVo settlementVo) throws Exception {
		return settlementDAO.deleteSettlement(settlementVo);
	}
	
}

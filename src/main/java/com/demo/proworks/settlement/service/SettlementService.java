package com.demo.proworks.settlement.service;

import java.util.List;

import com.demo.proworks.settlement.vo.SettlementVo;

/**  
 * @subject     : 정산정보 관련 처리를 담당하는 인터페이스
 * @description : 정산정보 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/08
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/08			 Inswave	 		최초 생성
 * 
 */
public interface SettlementService {
	
    /**
     * 정산정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  settlementVo 정산정보 SettlementVo
     * @return 정산정보 목록 List<SettlementVo>
     * @throws Exception
     */
	public List<SettlementVo> selectListSettlement(SettlementVo settlementVo) throws Exception;
	
    /**
     * 조회한 정산정보 전체 카운트
     * 
     * @param  settlementVo 정산정보 SettlementVo
     * @return 정산정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountSettlement(SettlementVo settlementVo) throws Exception;
	
    /**
     * 정산정보를 상세 조회한다.
     *
     * @param  settlementVo 정산정보 SettlementVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public SettlementVo selectSettlement(SettlementVo settlementVo) throws Exception;
		
    /**
     * 정산정보를 등록 처리 한다.
     *
     * @param  settlementVo 정산정보 SettlementVo
     * @return 번호
     * @throws Exception
     */
	public int insertSettlement(SettlementVo settlementVo) throws Exception;
	
    /**
     * 정산정보를 갱신 처리 한다.
     *
     * @param  settlementVo 정산정보 SettlementVo
     * @return 번호
     * @throws Exception
     */
	public int updateSettlement(SettlementVo settlementVo) throws Exception;
	
    /**
     * 정산정보를 삭제 처리 한다.
     *
     * @param  settlementVo 정산정보 SettlementVo
     * @return 번호
     * @throws Exception
     */
	public int deleteSettlement(SettlementVo settlementVo) throws Exception;
	
}

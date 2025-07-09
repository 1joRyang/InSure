package com.demo.proworks.settlement.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.demo.proworks.settlement.service.SettlementService;
import com.demo.proworks.settlement.vo.SettlementVo;
import com.demo.proworks.settlement.vo.SettlementListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**
 * @subject : 정산정보 관련 처리를 담당하는 컨트롤러
 * @description : 정산정보 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/08
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/08 Inswave 최초 생성
 * 
 */
@Controller
public class SettlementController {

	/** SettlementService */
	@Resource(name = "settlementServiceImpl")
	private SettlementService settlementService;

	/**
	 * 정산정보 목록을 조회합니다.
	 *
	 * @param settlementVo 정산정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "SETTLEMENTList")
	@RequestMapping(value = "SETTLEMENTList")
	@ElDescription(sub = "정산정보 목록조회", desc = "페이징을 처리하여 정산정보 목록 조회를 한다.")
	public SettlementListVo selectListSettlement(SettlementVo settlementVo) throws Exception {

		List<SettlementVo> settlementList = settlementService.selectListSettlement(settlementVo);
		long totCnt = settlementService.selectListCountSettlement(settlementVo);

		SettlementListVo retSettlementList = new SettlementListVo();
		retSettlementList.setSettlementVoList(settlementList);
		retSettlementList.setTotalCount(totCnt);
		retSettlementList.setPageSize(settlementVo.getPageSize());
		retSettlementList.setPageIndex(settlementVo.getPageIndex());

		return retSettlementList;
	}

	/**
	 * 정산정보을 단건 조회 처리 한다.
	 *
	 * @param settlementVo 정산정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "SETTLEMENTUpdView")
	@RequestMapping(value = "SETTLEMENTUpdView")
	@ElDescription(sub = "정산정보 갱신 폼을 위한 조회", desc = "정산정보 갱신 폼을 위한 조회를 한다.")
	public SettlementVo selectSettlement(SettlementVo settlementVo) throws Exception {
		SettlementVo selectSettlementVo = settlementService.selectSettlement(settlementVo);

		return selectSettlementVo;
	}

	/**
	 * 정산정보를 등록 처리 한다.
	 *
	 * @param settlementVo 정산정보
	 * @throws Exception
	 */
	@ElService(key = "SETTLEMENTIns")
	@RequestMapping(value = "SETTLEMENTIns")
	@ElDescription(sub = "정산정보 등록처리", desc = "정산정보를 등록 처리 한다.")
	public void insertSettlement(SettlementVo settlementVo) throws Exception {
		settlementService.insertSettlement(settlementVo);
	}

	/**
	 * 정산정보를 갱신 처리 한다.
	 *
	 * @param settlementVo 정산정보
	 * @throws Exception
	 */
	@ElService(key = "SETTLEMENTUpd")
	@RequestMapping(value = "SETTLEMENTUpd")
	@ElValidator(errUrl = "/settlement/settlementRegister", errContinue = true)
	@ElDescription(sub = "정산정보 갱신처리", desc = "정산정보를 갱신 처리 한다.")
	public void updateSettlement(SettlementVo settlementVo) throws Exception {

		settlementService.updateSettlement(settlementVo);
	}

	/**
	 * 정산정보를 삭제 처리한다.
	 *
	 * @param settlementVo 정산정보
	 * @throws Exception
	 */
	@ElService(key = "SETTLEMENTDel")
	@RequestMapping(value = "SETTLEMENTDel")
	@ElDescription(sub = "정산정보 삭제처리", desc = "정산정보를 삭제 처리한다.")
	public void deleteSettlement(SettlementVo settlementVo) throws Exception {
		settlementService.deleteSettlement(settlementVo);
	}

	/// =====================================================
	@ElService(key = "SETTLEMENTMemoGenerate")
	@RequestMapping(value = "SETTLEMENTMemoGenerate")
	@ElDescription(sub = "FastAPI 심사메모 생성", desc = "FastAPI에 settlementId로 요청하여 심사메모 생성 결과를 받는다.")
	public Map<String, Object> generateMemoFromFastAPI(SettlementVo settlementVo) throws Exception {

		String treatmentId = settlementVo.getTreatment_id();
		String claimNo = settlementVo.getClaim_no();

		// FastAPI 호출
		String fastApiUrl = "http://127.0.0.1:8000/api/v1/generate-memo" + "?treatment_id=" + treatmentId + "&claim_no="
				+ claimNo;

		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> result = restTemplate.getForObject(fastApiUrl, Map.class);

		// 원하는 데이터 추출
		String memo = (String) result.get("memo");
		String receiptNo = (String) result.get("receipt_no");

		Map<String, Object> response = new HashMap<>();
		response.put("memo", memo);
		response.put("receiptNo", receiptNo);
		response.put("status", "success");

		return response;
	}

}

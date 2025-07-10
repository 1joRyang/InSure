package com.demo.proworks.excitem.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.excitem.service.ExcItemService;
import com.demo.proworks.excitem.vo.ExcItemVo;
import com.demo.proworks.excitem.vo.ExcItemListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @subject : 제외항목 관련 처리를 담당하는 컨트롤러
 * @description : 제외항목 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Controller
public class ExcItemController {

	/** ExcItemService */
	@Resource(name = "excItemServiceImpl")
	private ExcItemService excItemService;

	/**
	 * 하나의 영수증에 대한 제외항목 조회
	 *
	 * @param excItemVo 제외항목
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "EXCITEMListR")
	@RequestMapping(value = "EXCITEMListR")
	@ElDescription(sub = "하나의 영수증에 대한 제외항목 조회", desc = "페이징처리없음")
	public List<ExcItemVo> selectListExcItemR(ExcItemVo excItemVo) throws Exception {
		List<ExcItemVo> excItemList = excItemService.selectListExcItemR(excItemVo);
		return excItemList;
	}

	/**
	 * 제외항목 목록을 조회합니다.
	 *
	 * @param excItemVo 제외항목
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "EXCITEMList")
	@RequestMapping(value = "EXCITEMList")
	@ElDescription(sub = "제외항목 목록조회", desc = "페이징을 처리하여 제외항목 목록 조회를 한다.")
	public ExcItemListVo selectListExcItem(ExcItemVo excItemVo) throws Exception {

		List<ExcItemVo> excItemList = excItemService.selectListExcItem(excItemVo);
		long totCnt = excItemService.selectListCountExcItem(excItemVo);

		ExcItemListVo retExcItemList = new ExcItemListVo();
		retExcItemList.setExcItemVoList(excItemList);
		retExcItemList.setTotalCount(totCnt);
		retExcItemList.setPageSize(excItemVo.getPageSize());
		retExcItemList.setPageIndex(excItemVo.getPageIndex());

		return retExcItemList;
	}

	/**
	 * 제외항목을 단건 조회 처리 한다.
	 *
	 * @param excItemVo 제외항목
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "EXCITEMUpdView")
	@RequestMapping(value = "EXCITEMUpdView")
	@ElDescription(sub = "제외항목 갱신 폼을 위한 조회", desc = "제외항목 갱신 폼을 위한 조회를 한다.")
	public ExcItemVo selectExcItem(ExcItemVo excItemVo) throws Exception {
		ExcItemVo selectExcItemVo = excItemService.selectExcItem(excItemVo);

		return selectExcItemVo;
	}

	/**
	 * 제외항목를 등록 처리 한다.
	 *
	 * @param excItemVo 제외항목
	 * @throws Exception
	 */
	@ElService(key = "EXCITEMIns")
	@RequestMapping(value = "EXCITEMIns")
	@ElDescription(sub = "제외항목 등록처리", desc = "제외항목를 등록 처리 한다.")
	public void insertExcItem(ExcItemVo excItemVo) throws Exception {
		excItemService.insertExcItem(excItemVo);
	}

	/**
	 * 제외항목를 갱신 처리 한다.
	 *
	 * @param excItemVo 제외항목
	 * @throws Exception
	 */
	@ElService(key = "EXCITEMUpd")
	@RequestMapping(value = "EXCITEMUpd")
	@ElValidator(errUrl = "/excItem/excItemRegister", errContinue = true)
	@ElDescription(sub = "제외항목 갱신처리", desc = "제외항목를 갱신 처리 한다.")
	public void updateExcItem(ExcItemVo excItemVo) throws Exception {

		excItemService.updateExcItem(excItemVo);
	}

	/**
	 * 제외항목를 삭제 처리한다.
	 *
	 * @param excItemVo 제외항목
	 * @throws Exception
	 */
	@ElService(key = "EXCITEMDel")
	@RequestMapping(value = "EXCITEMDel")
	@ElDescription(sub = "제외항목 삭제처리", desc = "제외항목를 삭제 처리한다.")
	public void deleteExcItem(ExcItemVo excItemVo) throws Exception {
		excItemService.deleteExcItem(excItemVo);
	}

}

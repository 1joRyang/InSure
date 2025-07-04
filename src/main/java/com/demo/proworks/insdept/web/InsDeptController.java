package com.demo.proworks.insdept.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.insdept.service.InsDeptService;
import com.demo.proworks.insdept.vo.InsDeptVo;
import com.demo.proworks.insdept.vo.InsDeptListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 부서정보 관련 처리를 담당하는 컨트롤러
 * @description : 부서정보 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class InsDeptController {
	
    /** InsDeptService */
    @Resource(name = "insDeptServiceImpl")
    private InsDeptService insDeptService;
	
    
    /**
     * 부서정보 목록을 조회합니다.
     *
     * @param  insDeptVo 부서정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="insdept0001List")
    @RequestMapping(value="insdept0001List")    
    @ElDescription(sub="부서정보 목록조회",desc="페이징을 처리하여 부서정보 목록 조회를 한다.")               
    public InsDeptListVo selectListInsDept(InsDeptVo insDeptVo) throws Exception {    	   	

        List<InsDeptVo> insDeptList = insDeptService.selectListInsDept(insDeptVo);                  
        long totCnt = insDeptService.selectListCountInsDept(insDeptVo);
	
		InsDeptListVo retInsDeptList = new InsDeptListVo();
		retInsDeptList.setInsDeptVoList(insDeptList); 
		retInsDeptList.setTotalCount(totCnt);
		retInsDeptList.setPageSize(insDeptVo.getPageSize());
		retInsDeptList.setPageIndex(insDeptVo.getPageIndex());

        return retInsDeptList;            
    }  
        
    /**
     * 부서정보을 단건 조회 처리 한다.
     *
     * @param  insDeptVo 부서정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "insdept0001UpdView")    
    @RequestMapping(value="insdept0001UpdView") 
    @ElDescription(sub = "부서정보 갱신 폼을 위한 조회", desc = "부서정보 갱신 폼을 위한 조회를 한다.")    
    public InsDeptVo selectInsDept(InsDeptVo insDeptVo) throws Exception {
    	InsDeptVo selectInsDeptVo = insDeptService.selectInsDept(insDeptVo);    	    
		
        return selectInsDeptVo;
    } 
 
    /**
     * 부서정보를 등록 처리 한다.
     *
     * @param  insDeptVo 부서정보
     * @throws Exception
     */
    @ElService(key="insdept0001Ins")    
    @RequestMapping(value="insdept0001Ins")
    @ElDescription(sub="부서정보 등록처리",desc="부서정보를 등록 처리 한다.")
    public void insertInsDept(InsDeptVo insDeptVo) throws Exception {    	 
    	insDeptService.insertInsDept(insDeptVo);   
    }
       
    /**
     * 부서정보를 갱신 처리 한다.
     *
     * @param  insDeptVo 부서정보
     * @throws Exception
     */
    @ElService(key="insdept0001Upd")    
    @RequestMapping(value="insdept0001Upd")    
    @ElValidator(errUrl="/insDept/insDeptRegister", errContinue=true)
    @ElDescription(sub="부서정보 갱신처리",desc="부서정보를 갱신 처리 한다.")    
    public void updateInsDept(InsDeptVo insDeptVo) throws Exception {  
 
    	insDeptService.updateInsDept(insDeptVo);                                            
    }

    /**
     * 부서정보를 삭제 처리한다.
     *
     * @param  insDeptVo 부서정보    
     * @throws Exception
     */
    @ElService(key = "insdept0001Del")    
    @RequestMapping(value="insdept0001Del")
    @ElDescription(sub = "부서정보 삭제처리", desc = "부서정보를 삭제 처리한다.")    
    public void deleteInsDept(InsDeptVo insDeptVo) throws Exception {
        insDeptService.deleteInsDept(insDeptVo);
    }
   
}

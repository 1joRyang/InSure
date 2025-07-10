package com.demo.proworks.insimagefile.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.insimagefile.service.InsimagefileService;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.insimagefile.vo.InsimagefileListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**  
 * @subject     : 이미지파일테이블 관련 처리를 담당하는 컨트롤러
 * @description : 이미지파일테이블 관련 처리를 담당하는 컨트롤러
 * @author      : 이지현
 * @since       : 2025/07/04
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/04			 이지현	 		최초 생성
 * 
 */
@Controller
public class InsimagefileController {
	
    /** InsimagefileService */
    @Resource(name = "insimagefileServiceImpl")
    private InsimagefileService insimagefileService;
	
    
    /**
     * 이미지파일테이블 목록을 조회합니다.
     *
     * @param  insimagefileVo 이미지파일테이블
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key = "InsimagefileList")
    @RequestMapping(value = "InsimagefileList")    
    @ElDescription(sub = "이미지파일테이블 목록조회", desc = "페이징을 처리하여 이미지파일테이블 목록 조회를 한다.")               
    public InsimagefileListVo selectListInsimagefile(InsimagefileVo insimagefileVo) throws Exception {    	   	

        List<InsimagefileVo> insimagefileList = insimagefileService.selectListInsimagefile(insimagefileVo);                  
        long totCnt = insimagefileService.selectListCountInsimagefile(insimagefileVo);
	
		InsimagefileListVo retInsimagefileList = new InsimagefileListVo();
		retInsimagefileList.setInsimagefileVoList(insimagefileList); 
		retInsimagefileList.setTotalCount(totCnt);
		retInsimagefileList.setPageSize(insimagefileVo.getPageSize());
		retInsimagefileList.setPageIndex(insimagefileVo.getPageIndex());

        return retInsimagefileList;            
    }  
        
    /**
     * 이미지파일테이블을 단건 조회 처리 한다.
     *
     * @param  insimagefileVo 이미지파일테이블
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "InsimagefileUpdView")    
    @RequestMapping(value="InsimagefileUpdView") 
    @ElDescription(sub = "이미지파일테이블 갱신 폼을 위한 조회", desc = "이미지파일테이블 갱신 폼을 위한 조회를 한다.")    
    public InsimagefileVo selectInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
    	InsimagefileVo selectInsimagefileVo = insimagefileService.selectInsimagefile(insimagefileVo);    	    
		
        return selectInsimagefileVo;
    } 
 
    /**
     * 이미지파일테이블를 등록 처리 한다.
     *
     * @param  insimagefileVo 이미지파일테이블
     * @throws Exception
     */
    @ElService(key="InsimagefileIns")    
    @RequestMapping(value="InsimagefileIns")
    @ElDescription(sub="이미지파일테이블 등록처리",desc="이미지파일테이블를 등록 처리 한다.")
    public void insertInsimagefile(InsimagefileVo insimagefileVo) throws Exception {    	 
    	insimagefileService.insertInsimagefile(insimagefileVo);   
    }
       
    /**
     * 이미지파일테이블를 갱신 처리 한다.
     *
     * @param  insimagefileVo 이미지파일테이블
     * @throws Exception
     */
    @ElService(key="InsimagefileUpd")    
    @RequestMapping(value="InsimagefileUpd")    
    @ElValidator(errUrl="/insimagefile/insimagefileRegister", errContinue=true)
    @ElDescription(sub="이미지파일테이블 갱신처리",desc="이미지파일테이블를 갱신 처리 한다.")    
    public void updateInsimagefile(InsimagefileVo insimagefileVo) throws Exception {  
 
    	insimagefileService.updateInsimagefile(insimagefileVo);                                            
    }

    /**
     * 이미지파일테이블를 삭제 처리한다.
     *
     * @param  insimagefileVo 이미지파일테이블    
     * @throws Exception
     */
    @ElService(key = "InsimagefileDel")    
    @RequestMapping(value="InsimagefileDel")
    @ElDescription(sub = "이미지파일테이블 삭제처리", desc = "이미지파일테이블를 삭제 처리한다.")    
    public String deleteInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
        //insimagefileService.deleteInsimagefile(insimagefileVo);
        //테스트용 void => String
        return "Delete Method OK";
    }
    
    /**
     * 보험금 청구 서류(이미지)를 접수하여 저장
     *
     * @param  payload 클라이언트에서 전송한 JSON 데이터
	 * @return 처리 결과
     * @throws Exception
     */
    @ElService(key = "submitClaim")    
    @RequestMapping(value = "submitClaim")
    @ElDescription(sub = "보험금 청구 이미지 접수 저장처리", desc = "보험금 청구 이미지 접수 저장 한다.")
    //@ResponseBody
    public void submitClaim(
    		@RequestParam("files") List<MultipartFile> files,
    		@RequestParam("claim_type") String claimType) throws Exception {
    	
    	insimagefileService.saveImageFiles(files, claimType); 	

    }
    
    
    
    
    
    
    

   
}

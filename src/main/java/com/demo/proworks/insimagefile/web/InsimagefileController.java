package com.demo.proworks.insimagefile.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.insimagefile.service.InsimagefileService;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.insimagefile.vo.Step1Vo;
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
    @ElService(key = "/api/saveStep1")    
    @RequestMapping(value = "/api/saveStep1")
    @ElDescription(sub = "모바일 청구 단계별 서버세션 저장", desc = "모바일 청구 단계별 서버세션 저장 한다.")
    //@ResponseBody
    public void submitClaim(
    		@RequestParam("files") List<MultipartFile> files,
    		@RequestParam("claim_type") String claimType) throws Exception {
    	
    	insimagefileService.saveImageFiles(files, claimType); 	

    }
    
    /**
     * 청구단계별 서버세션 저장
     */
    @ElService(key = "saveStep1")    
    @RequestMapping(value = "saveStep1")
    @ElDescription(sub = "모바일 청구 1단계 청구유형 저장", desc = "모바일 청구유형을 서버 세션에 저장 한다.")
    public void saveStep1(Step1Vo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================콘트롤러진입");
    	System.out.println("================전달받은 claimType: " + param.getClaimType());
    	HttpSession session = request.getSession();
    	
    	// 2. 모든 청구정보 담을 객체 세션에 만들기
    	Map<String, Object> claimData = new HashMap<>();
    	
    	// 3. 클라이언트가 보낸 claimType을 claimData에 저장
    	claimData.put("claimType", param.getClaimType());
    	
    	// 4. 이 claimData를 "claim_data"라는 이름으로 세션에 저장
    	session.setAttribute("claim_data", claimData);
    	
    	// 세션에 데이터 정상적으로 저장되었는 확인
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    	System.out.println("================controller:saveStep1 메소드 종료");

    }
    
    
    
    
    
    
    

   
}

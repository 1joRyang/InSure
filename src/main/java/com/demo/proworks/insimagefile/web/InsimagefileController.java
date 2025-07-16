package com.demo.proworks.insimagefile.web;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.insimagefile.service.InsimagefileService;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.insimagefile.vo.Step1Vo;
import com.demo.proworks.insimagefile.vo.Step2Vo;
import com.demo.proworks.insimagefile.vo.Step3Vo;
import com.demo.proworks.insimagefile.vo.Step4Vo;
import com.demo.proworks.insimagefile.vo.Step5Vo;
import com.demo.proworks.s3.service.S3Service;
import com.demo.proworks.insimagefile.vo.ConsentVo;
import com.demo.proworks.insimagefile.vo.InsimagefileListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.exception.ElException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    
    /** S3Service 주입*/
    @Resource(name = "s3Service")
    private S3Service s3Service;
    
    /** ClaimService 주입 */
    @Resource(name = "claimServiceImpl")
    private ClaimService claimService;
    
	
    
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
    
    @ElService(key = "/api/saveStep1")    
    @RequestMapping(value = "/api/saveStep1")
    @ElDescription(sub = "모바일 청구 단계별 서버세션 저장", desc = "모바일 청구 단계별 서버세션 저장 한다.")
    //@ResponseBody
    public void submitClaim(
    		@RequestParam("files") List<MultipartFile> files,
    		@RequestParam("claim_type") String claimType) throws Exception {
    	
    	insimagefileService.saveImageFiles(files, claimType); 	

    } */
    
    /**
     * (청구사유)청구단계별 서버세션 저장
     */
    @ElService(key = "saveStep1")    
    @RequestMapping(value = "saveStep1")
    @ElDescription(sub = "모바일 청구 1단계 청구사유 저장", desc = "청구사유를 서버 세션에 저장 한다.")
    public void saveStep1(Step1Vo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================콘트롤러진입");
    	System.out.println("================전달받은 claimType: " + param.getClaimType());
    	HttpSession session = request.getSession();
    	
    	// 2. (새로 생성 혹은 수정) 세션에서 기존 claim_data 가져오기
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	if(claimData == null) {
    		claimData = new HashMap<>();
    	}
    	
    	
    	// 3. 클라이언트가 보낸 claimType을 claimData에 저장
    	claimData.put("claimType", param.getClaimType());
    	
    	// 4. 이 claimData를 "claim_data"라는 이름으로 세션에 저장
    	session.setAttribute("claim_data", claimData);
    	
    	// 세션에 데이터 정상적으로 저장되었는 확인
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    	System.out.println("================controller:saveStep1 메소드 종료");

    }
    
    /**
     * (개인정보동의)청구단계별 서버세션 저장
     */
    @ElService(key = "saveConsent")    
    @RequestMapping(value = "saveConsent")
    @ElDescription(sub = "모바일 청구 1단계 팝업 개인정보동의저장", desc = "모바일 청구 1단계 팝업 개인정보동의를 서버세션에 저장 한다.")
    public void saveConsent(ConsentVo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================동의 콘트롤러진입");
    	HttpSession session = request.getSession();
    	
    	// 2. 세션에 저장된 기존 claim_data 를 불러오기
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	if(claimData == null) {
    		claimData = new HashMap<>();
    	}
    	
    	claimData.put("agreed", param.getAgreed());
    	session.setAttribute("claim_data", claimData);
    	
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    	System.out.println("================controller:saveConsent 메소드 종료");

    }
    
    
    /**
     * (사고일자)청구단계별 서버세션 저장
     */
    @ElService(key = "saveStep2")    
    @RequestMapping(value = "saveStep2")
    @ElDescription(sub = "모바일 청구 2단계 사고일자 저장", desc = "사고일자를 서버 세션에 저장 한다.")
    public void saveStep2(Step2Vo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================사고일자 콘트롤러진입");
    	HttpSession session = request.getSession();
    	
    	// 2. 세션에 저장된 기존 claim_data 를 불러오기
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	if(claimData == null) {
    		claimData = new HashMap<>();
    	}
    	// 3. 불러온 데이터에 새로운 정보 추가
    	claimData.put("accidentDate", param.getAccidentDate());
    		
    	// 4. 변경된 내용으로 세션 업데이트
    	session.setAttribute("claim_data", claimData);
    		
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));

    }
    
    /**
     * (진단내용)청구단계별 서버세션 저장
     */
    @ElService(key = "saveStep3")    
    @RequestMapping(value = "saveStep3")
    @ElDescription(sub = "모바일 청구 3단계 진단내용 저장", desc = "진단내용을 서버 세션에 저장 한다.")
    public void saveStep3(Step3Vo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================진단내용 콘트롤러진입");
    	HttpSession session = request.getSession();
    	
    	// 2. 세션에 저장된 기존 claim_data 를 불러오기
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	if(claimData == null) {
    		claimData = new HashMap<>();
    	}
    	
    	claimData.put("symptom", param.getSymptom());
    	session.setAttribute("claim_data", claimData);
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    

    }
    
    
    /**
     * (계좌정보)청구단계별 서버세션 저장
     */
    @ElService(key = "saveStep4")    
    @RequestMapping(value = "saveStep4")
    @ElDescription(sub = "모바일 청구 4단계 계좌저장", desc = "계좌를 서버 세션에 저장 한다.")
    public void saveStep4(Step4Vo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================계좌정보 콘트롤러진입");
    	HttpSession session = request.getSession();
    	
    	// 2. 세션에 저장된 기존 claim_data 를 불러오기
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	if(claimData == null) {
    		claimData = new HashMap<>();
    	}
    	
    	// 3. 불러온 데이터에 새로운 정보 추가
    	claimData.put("accountOption",param.getAccountOption());
    	claimData.put("bankCode",param.getBankCode());
    	claimData.put("bankName",param.getBankName());
    	claimData.put("accountNo",param.getAccountNo());
    	claimData.put("accountHolder",param.getAccountHolder());
    		
    	// 4. 변경된 내용으로 세션 업데이트
    	session.setAttribute("claim_data", claimData);
    		
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    }
    
    /**
     * (서류이미지)청구단계별 서버세션 저장
     */
    @ElService(key = "saveStep5")    
    @RequestMapping(value = "saveStep5")
    @ElDescription(sub = "모바일 청구 5단계 청구서류저장", desc = "청구서류를 서버 세션에 저장 한다.")
    public void saveStep5(Step5Vo param, HttpServletRequest request) throws Exception {
    	// 1. 표준적인 방식으로 현재 요청 세션 가져오기
    	System.out.println("================청구서류 콘트롤러진입");
    	HttpSession session = request.getSession();
    	
    	// 2. 세션에 저장된 기존 claim_data 를 불러오기
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	if(claimData == null) {
    		claimData = new HashMap<>();
    	}
    	
    	String s3KeysAsString = param.getS3FileKeys();
    	
    	if (s3KeysAsString != null && !s3KeysAsString.isEmpty()) {
    			String[] keysArray = s3KeysAsString.split(",");
    		}
    	
    	claimData.put("s3fileKeys", s3KeysAsString);  		
    		
    	// 4. 변경된 내용으로 세션 업데이트
    	session.setAttribute("claim_data", claimData);
    		
    	System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    	
    }
    
    /**
     * (최종확인)모든 청구 데이터 서버세션에서 조회
     */
    @ElService(key = "getClaimData")    
    @RequestMapping(value = "getClaimData")
    @ElDescription(sub = "모든 청구 데이터를 세션에서 조회", desc = "모든 청구 데이터를 세션에서 조회 한다.")
    public Map<String, Object> getClaimData(HttpServletRequest request) throws Exception {
    	System.out.println("================최종확인 콘트롤러진입");
    	HttpSession session = request.getSession();
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
        System.out.println("================ getClaimData 진입 ================");
        if (claimData == null) {
            System.out.println("### 오류: 세션에서 'claim_data'를 찾을 수 없습니다! ###");
            // 1. 데이터가 없으면 빈 Map을 생성하여 반환 (null 반환 방지)
            claimData = new HashMap<>();
        } else {
            System.out.println("세션에서 찾은 데이터: " + claimData.toString());
        }
        
        // s3FileKeys를 실제 접근 가능한 URL 리스트로 변환
        String s3KeysAsString = (String) claimData.get("s3fileKeys");
        if (s3KeysAsString != null && !s3KeysAsString.isEmpty()) {
    			String[] keysArray = s3KeysAsString.split(",");
    			List<String> s3FileUrls = new ArrayList<>();
    			for (String key : keysArray) {
    			URL presignedUrl = s3Service.generatePresignedGetUrl("insure-claim-docs-final-project", key);
    			s3FileUrls.add(presignedUrl.toString());
    			}
    		// 기존 s3fileKeys 제거하고 URL 리스트인 s3fileUrls 를 추가
    		//claimData.remove("s3fileKeys");
    		claimData.put("s3fileUrls", s3FileUrls);
    		
    		System.out.println("================세션에 저장된 claimType: " + session.getAttribute("claim_data"));
    		}
    	// 1. 최종 응답을 담을 새로운 Map 생성
    		Map<String, Object> responseWrapper = new HashMap<>();
    		responseWrapper.put("elData", claimData);
    		
    		// 3. 최종응답 반환
    		return responseWrapper; 
    
    }
    
    /**
     * (최종제출) 세션에 저장된 모든 데이터를 취합하여 DB에 저장(청구 접수)
     */
    @ElService(key = "submitFinalClaim")    
    @RequestMapping(value = "submitFinalClaim")
    @ElDescription(sub = "최종 보험금 청구 제출", desc = "세션의 모든 정보를 취합하여 최종 청구를 접수한다.")
    public void submitFinalClaim(HttpServletRequest request) throws Exception {
    	System.out.println("================최종 청구 제출 컨트롤러 진입=======================");
    	HttpSession session = request.getSession();
    	Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    	
    	// 1. 데이터 유효성 검사
    	if (claimData == null ||
    		claimData.get("claimType") == null ||
    		claimData.get("agreed") == null ||
    		claimData.get("accidentDate") == null ||
    		claimData.get("symptom") == null ||
    		claimData.get("accountNo") == null ||
    		claimData.get("s3fileKeys") == null ) {
    		
    		// 누락된 항목 있으면 에러 메시지 전달 후 다시 진행
    		throw new ElException("ERROR.BIZ.001", new String[]{"필수 청구 정보가 누락되었습니다. 처음부터 다시 진행해주세요."});   		
    		}
    		
    	System.out.println("========최종 데이터 유효성 검사 통과=====================");
    	
    	// 데이터 가공 -> 청구사유 한글변환
    	String claimTypeEng = (String) claimData.get("claimType");
    	String claimTypeKor = "기타";
    	if ("disease".equals(claimTypeEng)) {
    		claimTypeKor = "질병";
    	} else if ("injury".equals(claimTypeEng)) {
    		claimTypeKor = "재해";
    	}
    	claimData.put("claimType", claimTypeKor);
    	
    	// claim_content 데이터 조합
    	String symptom = (String) claimData.get("symptom");
    	String claimContent = String.format("[%s] %s", claimTypeKor, symptom);
    	claimData.put("claimContent", claimContent);
    	
    	//Long loginUserId = (Long) session.getAttribute("loginUserId");
    	//claimData.put("userId", loginUserId);
    	// (임시) 세션에서 로그인 사용자 ID를 가져와 claimData에 추가
    	claimData.put("userId", 1L);
    	// 로그인 처리 로직 성공 부분
    	//session.setAttribute("userId", "1111"); // "1111"은 실제 로그인한 사용자의 ID
    		
    	// 2. 고유번호 생성
    	String claimNo = generateClaimNumber();
    	claimData.put("claimNo", claimNo);
    	
    	// 3. Service를 호출하여 DB 저장 (트랜잭션 처리)
    	claimService.saveFinalClaim(claimData);
    	
    	System.out.println("최종 청구 프로세스 완료.");
    	
    	
    }
    
    
    /**
     * (청구번호 생성) 청구연도-영문4자리-숫자4자리 형식
     */
    @ElService(key = "generateClaimNumber")    
    @RequestMapping(value = "generateClaimNumber")
    @ElDescription(sub = "고유한 청구번호 생성", desc = "고유한 청구번호 생성한다.")
    public String generateClaimNumber() {
    	String year = String.valueOf(LocalDate.now().getYear());
    	
    	Random random = new Random();
    	String randomChars = random.ints(4, 'A', 'Z' + 1)
    							.collect(StringBuilder::new,
    									 StringBuilder::appendCodePoint,
    									 StringBuilder::append)
    							.toString();
    							
    	int randomNumber = 1000 + random.nextInt(9000);
    	
    	return String.format("%s-%s-%d", year, randomChars, randomNumber);
    }
    
    

   
}

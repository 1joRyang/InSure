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

import com.demo.proworks.assignrule.service.AssignRuleService;
import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.insimagefile.service.InsimagefileService;
import com.demo.proworks.insimagefile.vo.ConsentVo;
import com.demo.proworks.insimagefile.vo.InsimagefileListVo;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.insimagefile.vo.Step1Vo;
import com.demo.proworks.insimagefile.vo.Step2Vo;
import com.demo.proworks.insimagefile.vo.Step3Vo;
import com.demo.proworks.insimagefile.vo.Step4Vo;
import com.demo.proworks.insimagefile.vo.Step5Vo;
import com.demo.proworks.ocr.service.OcrService;
import com.demo.proworks.s3.service.S3Service;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.exception.ElException;
import org.springframework.web.bind.annotation.RequestMethod;

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

	/** OcrService 주입 */
	@Resource(name = "ocrService")
	private OcrService ocrService;
	
	/** AssignRuleService 주입 */
	@Resource(name = "assignRuleServiceImpl")
	private AssignRuleService assignRuleService;
    
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
            claimData.get("accidentDate") == null||
            claimData.get("symptom") == null ||
            claimData.get("accountNo") == null||
            claimData.get("s3fileKeys") == null) {

        throw new ElException("ERROR.BIZ.001", new String[] { "필수 청구 정보가 누락되었습니다. 처음부터 다시 진행해주세요." });
    }

    System.out.println("========최종 데이터 유효성 검사 통과=====================");

    // 2. 고유번호 생성
    String claimNo = generateClaimNumber();
    claimData.put("claimNo", claimNo);
    
    // 3. 사용자 ID 설정 (임시)
    claimData.put("userId", 1L);

    //  4. 영문 claimType을 한글로 변환하여 DB 저장용으로 설정
    String originalClaimTypeEng = (String) claimData.get("claimType");
    String claimTypeKor = convertEngToKoreanClaimType(originalClaimTypeEng);
    claimData.put("claimType", claimTypeKor); // 한글로 변경
    
    System.out.println("DB 저장용 claimType 변환: " + originalClaimTypeEng + " -> " + claimTypeKor);

    // 5. DB 저장 (한글 claimType으로 저장)
    claimService.saveFinalClaim(claimData);
    
    System.out.println("청구 저장 완료 - claim_no: " + claimNo + ", claim_type: " + claimTypeKor);

	}
	
	 @ElService(key = "ClaimSaveDone")    
    @RequestMapping(value = "ClaimSaveDone")
    @ElDescription(sub = "OCR 과 자동배정 ", desc = "OCR로 분류하고 자동배정 한다.")
    public void ClaimSaveDone(HttpServletRequest request) throws Exception {
    
    System.out.println("================청구 후속처리 (OCR 분석 및 자동배정) 컨트롤러 진입=======================");
    HttpSession session = request.getSession();
    Map<String, Object> claimData = (Map<String, Object>) session.getAttribute("claim_data");
    
    // claimNo만 확인 (submitFinalClaim에서 생성되어 세션에 저장됨)
    if (claimData == null || claimData.get("claimNo") == null) {
        throw new ElException("ERROR.BIZ.001", new String[] { "청구번호를 찾을 수 없습니다. submitFinalClaim이 먼저 실행되어야 합니다." });
    }
    
    String claimNo = (String) claimData.get("claimNo");
    System.out.println("========후속처리 대상 청구번호: " + claimNo + " =====================");
    
    // ===== DB 조회 제거, 세션 데이터만 사용 =====
    
    // 2. OCR 처리에 필요한 데이터는 모두 세션에서 가져오기
    // 사용자가 처음 입력한 claimType (세션에서)
    String originalClaimTypeEng = (String) claimData.get("claimType");
    String originalClaimTypeKor = convertClaimTypeToKorean(originalClaimTypeEng);

    // symptom과 s3fileKeys는 세션에서
    String symptom = (String) claimData.get("symptom");
    String s3KeysAsString = (String) claimData.get("s3fileKeys");
    
    // 3. claim_content 생성 (사용자 입력값 기준)
    String claimContent = String.format("[%s] %s", originalClaimTypeKor, symptom);
    System.out.println("사용자 입력 기준 claim_content 생성: " + claimContent);

    // 4. OCR 분석을 통한 자동 claim_type 결정
    String analyzedClaimTypeEng = "disease"; // 기본값 (영문 코드)

    if (s3KeysAsString != null && !s3KeysAsString.isEmpty()) {
        try {
            // S3 키 목록 파싱
            String[] keysArray = s3KeysAsString.split(",");
            List<String> s3ObjectKeys = new ArrayList<String>();
            for (String key : keysArray) {
                s3ObjectKeys.add(key.trim());
            }

            System.out.println("OCR 분석 시작 - 파일 수: " + s3ObjectKeys.size());

            // OCR 분석 수행 (영문 코드 반환)
            analyzedClaimTypeEng = ocrService.analyzeDocumentType(s3ObjectKeys);
            System.out.println("OCR 분석 완료 - 영문 코드: " + analyzedClaimTypeEng);

            // 유효한 claim_type인지 검증
            if (!isValidClaimType(analyzedClaimTypeEng)) {
                System.out.println("유효하지 않은 문서 타입, 기본값 사용: " + analyzedClaimTypeEng);
                analyzedClaimTypeEng = "disease";
            }

        } catch (Exception e) {
            System.err.println("OCR 분석 실패: " + e.getMessage());
            System.out.println("OCR 실패로 기본값 사용: " + analyzedClaimTypeEng);
            e.printStackTrace();
            // OCR 실패시에도 처리는 계속 진행 (기본값 사용)
        }
    }

    // 5. DB 저장용으로 한글 코드로 변환
    String analyzedClaimTypeKor = convertEngToKoreanClaimType(analyzedClaimTypeEng);
    System.out.println("OCR 분석 결과 - 한글 코드: " + analyzedClaimTypeKor);

    System.out.println("=== OCR 분석 및 자동배정 결과 요약 ===");
    System.out.println("사용자 입력 claimType (원본): " + originalClaimTypeEng + " -> " + originalClaimTypeKor);
    System.out.println("OCR 분석 결과: " + analyzedClaimTypeEng + " -> " + analyzedClaimTypeKor);
    System.out.println("claim_content (사용자 입력 기준): " + claimContent);
    System.out.println("최종 자동배정 claimType: " + analyzedClaimTypeKor);
    System.out.println("==================");

    // 6. 기존 저장된 청구건에 OCR 분석 결과를 업데이트
    claimService.updateClaimWithOcrResult(claimNo, analyzedClaimTypeKor, claimContent);
    
    System.out.println("[OCR 결과 DB 업데이트 완료] 청구번호: " + claimNo + ", 최종 타입: " + analyzedClaimTypeKor);
    
    // 6.5. *** 강제 배정 해제 - OCR 분석 후 무조건 수행 ***
    // 🔥 중요: originalClaimTypeKor ≠ analyzedClaimTypeKor 일 때만 배정 해제
    System.out.println("[DEBUG - 배정 해제 체크] originalClaimTypeKor: " + originalClaimTypeKor + ", analyzedClaimTypeKor: " + analyzedClaimTypeKor);
    
    if (!originalClaimTypeKor.equals(analyzedClaimTypeKor)) {
        System.out.println("🔥 [배정 해제] 청구 타입 변경됨: " + originalClaimTypeKor + " -> " + analyzedClaimTypeKor);
        try {
            // 🔥 별도 트랜잭션으로 강제 배정 해제 수행
            claimService.clearClaimAssignmentForced(claimNo);
            System.out.println("🔥 [배정 해제 완료] 청구번호: " + claimNo);
            
            // 트랜잭션 강제 커밋을 위해 잠시 대기
            Thread.sleep(300); // 300ms 대기
            
        } catch (Exception e) {
            System.err.println("🔥 [배정 해제 실패] 청구번호: " + claimNo + ", 오류: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        System.out.println("[배정 해제 생략] 청구 타입이 동일함: " + originalClaimTypeKor);
    }
    
    // 7. *** 자동 배정 수행 *** 
    System.out.println("[디버그] 자동 배정 로직 시작 전 - 청구번호: " + claimNo);
    try {
        System.out.println("[자동 배정 시작] 청구번호: " + claimNo + ", 청구타입: " + analyzedClaimTypeKor);
        
        // null 체크 추가
        if (assignRuleService == null) {
            System.err.println("[오류] assignRuleService가 null입니다!");
            throw new Exception("assignRuleService가 주입되지 않았습니다.");
        }
        
        String assignResult = assignRuleService.assignEmployeeToClaim(claimNo);
        System.out.println("[자동 배정 완료] " + assignResult);
    } catch (Exception e) {
        System.err.println("[자동 배정 실패] 청구번호: " + claimNo + ", 오류: " + e.getMessage());
        e.printStackTrace();
        // 자동 배정 실패해도 전체 프로세스는 계속 진행
    }
    System.out.println("[디버그] 자동 배정 로직 완료 후");
    
    System.out.println("청구 후속처리 완료 - claim_no: " + claimNo + ", 최종 claim_type: " + analyzedClaimTypeKor);
    
    // 8. 후속처리 완료 후 세션 정리
    session.removeAttribute("claim_data");    
    
    }
	/**
	 * 유효한 claim_type인지 검증
	 */
	private boolean isValidClaimType(String claimType) {
		if (claimType == null)
			return false;

		switch (claimType) {
			case "death":
			case "disability":
			case "surgery":
			case "disease":
			case "injury":
			case "other":
				return true;
			default:
				return false;
		}
	}

	/**
	 * claim_type을 한글로 변환 (화면 표시용)
	 */
	private String convertClaimTypeToKorean(String claimType) {
		switch (claimType) {
			case "death":
				return "사망";
			case "disability":
				return "장해";
			case "surgery":
				return "수술";
			case "disease":
				return "질병";
			case "injury":
				return "재해";
			case "other":
				return "기타";
			default:
				return "질병"; // 기본값은 질병
		}
	}

	private String convertEngToKoreanClaimType(String engClaimType) {
		if (engClaimType == null)
			return "질병";
		switch (engClaimType) {
			case "death":
				return "사망";
			case "disability":
				return "장해";
			case "surgery":
				return "수술";
			case "disease":
				return "질병";
			case "injury":
				return "재해";
			case "other":
				return "기타";
			default:
				return "질병"; // 기본값은 질병
		}
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
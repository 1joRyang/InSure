package com.demo.proworks.subinsurance.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.subinsurance.service.SubInsuranceService;
import com.demo.proworks.subinsurance.vo.SubInsuranceVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceListVo;
import com.demo.proworks.subinsurance.vo.SubInsuranceProductVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 가입보험정보 관련 처리를 담당하는 컨트롤러
 * @description : 가입보험정보 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/21
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/21			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class SubInsuranceController {
	
    /** SubInsuranceService */
    @Resource(name = "subInsuranceServiceImpl")
    private SubInsuranceService subInsuranceService;
	
    
    /**
    * 가입보험정보 목록을 조회합니다.
    *
    * @param  subInsuranceVo 가입보험정보
    * @return 목록조회 결과
    * @throws Exception
    */
    @ElService(key="SUBINSURANCEList")
    @RequestMapping(value="SUBINSURANCEList")    
    @ElDescription(sub="가입보험정보 목록조회",desc="페이징을 처리하여 가입보험정보 목록 조회를 한다.")               
    public SubInsuranceListVo selectListSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {    	   	
        
    System.out.println("=== SUBINSURANCEList 컴트롤러 호출 ===");
    System.out.println("요청 데이터: " + subInsuranceVo.toString());
        System.out.println("사용자 ID: " + subInsuranceVo.getID());
          
          // 사용자 ID가 있으면 보험상품 조회로 전환
          if (subInsuranceVo.getID() != null && !subInsuranceVo.getID().isEmpty()) {
          System.out.println("보험상품 조회 모드로 전환");
          
          // SubInsuranceProductVo로 변환
              SubInsuranceProductVo productVo = new SubInsuranceProductVo();
          productVo.setID(subInsuranceVo.getID());
          
          List<SubInsuranceProductVo> productList = subInsuranceService.selectInsuranceProductsByUserId(productVo);
          
          System.out.println("보험상품 조회 결과: " + (productList != null ? productList.size() : 0) + "건");
          
          // SubInsuranceListVo로 반환하지 말고 직접 List<SubInsuranceProductVo> 반환
          // 이렇게 하면 elData에 직접 배열이 들어감
          if (productList != null && !productList.isEmpty()) {
              System.out.println("첫 번째 데이터: " + productList.get(0).toString());
              
              // 임시로 SubInsuranceListVo 형태로 래핑하지만 elData에 직접 설정
          SubInsuranceListVo result = new SubInsuranceListVo();
          
          // productList를 그대로 반환하기 위해 setJSON 방식 사용
          // elData를 직접 설정할 방법을 찾아야 함
          
          return result; // 임시로 빈 객체 반환
          }
          
          return new SubInsuranceListVo(); // 비어있으면 빈 객체
          }
        
        // 기존 로직
        List<SubInsuranceVo> subInsuranceList = subInsuranceService.selectListSubInsurance(subInsuranceVo);                  
        long totCnt = subInsuranceService.selectListCountSubInsurance(subInsuranceVo);

		SubInsuranceListVo retSubInsuranceList = new SubInsuranceListVo();
		retSubInsuranceList.setSubInsuranceVoList(subInsuranceList); 
		retSubInsuranceList.setTotalCount(totCnt);
		retSubInsuranceList.setPageSize(subInsuranceVo.getPageSize());
		retSubInsuranceList.setPageIndex(subInsuranceVo.getPageIndex());

        return retSubInsuranceList;            
    }
        
    /**
     * 가입보험정보을 단건 조회 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "SUBINSURANCEUpdView")    
    @RequestMapping(value="SUBINSURANCEUpdView") 
    @ElDescription(sub = "가입보험정보 갱신 폼을 위한 조회", desc = "가입보험정보 갱신 폼을 위한 조회를 한다.")    
    public SubInsuranceVo selectSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
    	SubInsuranceVo selectSubInsuranceVo = subInsuranceService.selectSubInsurance(subInsuranceVo);    	    
		
        return selectSubInsuranceVo;
    } 
 
    /**
     * 가입보험정보를 등록 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEIns")    
    @RequestMapping(value="SUBINSURANCEIns")
    @ElDescription(sub="가입보험정보 등록처리",desc="가입보험정보를 등록 처리 한다.")
    public void insertSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {    	 
    	subInsuranceService.insertSubInsurance(subInsuranceVo);   
    }
       
    /**
     * 가입보험정보를 갱신 처리 한다.
     *
     * @param  subInsuranceVo 가입보험정보
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEUpd")    
    @RequestMapping(value="SUBINSURANCEUpd")    
    @ElValidator(errUrl="/subInsurance/subInsuranceRegister", errContinue=true)
    @ElDescription(sub="가입보험정보 갱신처리",desc="가입보험정보를 갱신 처리 한다.")    
    public void updateSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {  
 
    	subInsuranceService.updateSubInsurance(subInsuranceVo);                                            
    }

    /**
     * 가입보험정보를 삭제 처리한다.
     *
     * @param  subInsuranceVo 가입보험정보    
     * @throws Exception
     */
    @ElService(key = "SUBINSURANCEDel")    
    @RequestMapping(value="SUBINSURANCEDel")
    @ElDescription(sub = "가입보험정보 삭제처리", desc = "가입보험정보를 삭제 처리한다.")    
    public void deleteSubInsurance(SubInsuranceVo subInsuranceVo) throws Exception {
        subInsuranceService.deleteSubInsurance(subInsuranceVo);
    }

    /**
     * 사용자 ID로 보험상품 목록을 조회한다.
     *
     * @param  subInsuranceProductVo 보험상품조회정보
     * @return 보험상품 목록 조회 결과
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEProductsList")
    @RequestMapping(value="SUBINSURANCEProductsList")
    @ElDescription(sub="보험상품 목록조회",desc="사용자 ID로 보험상품 목록을 조회한다.")
    public List<SubInsuranceProductVo> selectInsuranceProductsByUserId(SubInsuranceProductVo subInsuranceProductVo) throws Exception {
        System.out.println("=== SUBINSURANCEProductsList 컸트롤러 호출 ===");
        System.out.println("요청 데이터: " + subInsuranceProductVo.toString());
        System.out.println("사용자 ID: " + subInsuranceProductVo.getID());
        
        List<SubInsuranceProductVo> productList = subInsuranceService.selectInsuranceProductsByUserId(subInsuranceProductVo);
        
        System.out.println("조회 결과: " + (productList != null ? productList.size() : 0) + "건");
        if (productList != null && !productList.isEmpty()) {
            System.out.println("첫 번째 데이터: " + productList.get(0).toString());
        }
        
        return productList;
    }

    /**
     * 사용자 ID로 보험상품 목록을 직접 조회한다.
     *
     * @param  subInsuranceVo 가입보험정보 (ID 필드 사용)
     * @return 보험상품 목록
     * @throws Exception
     */
    @ElService(key="SUBINSURANCEProductsListDirect")
    @RequestMapping(value="SUBINSURANCEProductsListDirect")
    @ElDescription(sub="보험상품 목록직접조회",desc="사용자 ID로 보험상품 목록을 직접 조회한다.")
    public List<SubInsuranceProductVo> selectInsuranceProductsListDirect(SubInsuranceVo subInsuranceVo) throws Exception {
        System.out.println("=== SUBINSURANCEProductsListDirect 컸트롤러 호출 ===");
        System.out.println("요청 데이터: " + subInsuranceVo.toString());
        System.out.println("사용자 ID: " + subInsuranceVo.getID());
        
        // SubInsuranceProductVo로 변환
        SubInsuranceProductVo productVo = new SubInsuranceProductVo();
        productVo.setID(subInsuranceVo.getID());
        
        List<SubInsuranceProductVo> productList = subInsuranceService.selectInsuranceProductsByUserId(productVo);
        
        System.out.println("보험상품 조회 결과: " + (productList != null ? productList.size() : 0) + "건");
        if (productList != null && !productList.isEmpty()) {
            System.out.println("첫 번째 데이터: " + productList.get(0).toString());
        }
        
        // 직접 List<SubInsuranceProductVo> 반환 - 이렇게 하면 elData에 직접 배열이 들어감
        return productList != null ? productList : new java.util.ArrayList<>();
    }
   
}

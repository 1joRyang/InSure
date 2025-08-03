package com.demo.proworks.claimresult.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.claimresult.service.ClaimResultService;
import com.demo.proworks.claimresult.vo.ClaimResultVo;
import com.demo.proworks.claimresult.vo.ClaimResultListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 심사결과 관련 처리를 담당하는 컨트롤러
 * @description : 심사결과 관련 처리를 담당하는 컨트롤러
 * @author      : hyunwoo
 * @since       : 2025/07/13
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/13			 hyunwoo	 		최초 생성
 * 
 */
@Controller
public class ClaimResultController {
	
    /** ClaimResultService */
    @Resource(name = "claimResultServiceImpl")
    private ClaimResultService claimResultService;
	
    
    /**
     * 심사결과 목록을 조회합니다.
     *
     * @param  claimResultVo 심사결과
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="CLAIMRESULTList")
    @RequestMapping(value="CLAIMRESULTList")    
    @ElDescription(sub="심사결과 목록조회",desc="페이징을 처리하여 심사결과 목록 조회를 한다.")               
    public ClaimResultListVo selectListClaimResult(ClaimResultVo claimResultVo) throws Exception {    	   	

        List<ClaimResultVo> claimResultList = claimResultService.selectListClaimResult(claimResultVo);                  
        long totCnt = claimResultService.selectListCountClaimResult(claimResultVo);
	
		ClaimResultListVo retClaimResultList = new ClaimResultListVo();
		retClaimResultList.setClaimResultVoList(claimResultList); 
		retClaimResultList.setTotalCount(totCnt);
		retClaimResultList.setPageSize(claimResultVo.getPageSize());
		retClaimResultList.setPageIndex(claimResultVo.getPageIndex());

        return retClaimResultList;            
    }  
        
    /**
     * 심사결과을 단건 조회 처리 한다.
     *
     * @param  claimResultVo 심사결과
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMRESULTUpdView")    
    @RequestMapping(value="CLAIMRESULTUpdView") 
    @ElDescription(sub = "심사결과 갱신 폼을 위한 조회", desc = "심사결과 갱신 폼을 위한 조회를 한다.")    
    public ClaimResultVo selectClaimResult(ClaimResultVo claimResultVo) throws Exception {
    	ClaimResultVo selectClaimResultVo = claimResultService.selectClaimResult(claimResultVo);    	    
		
        return selectClaimResultVo;
    } 
 
    /**
     * 심사결과를 등록 처리 한다.
     *
     * @param  claimResultVo 심사결과
     * @return 등록 결과
     * @throws Exception
     */
    @ElService(key="CLAIMRESULTIns")    
    @RequestMapping(value="CLAIMRESULTIns")
    @ElDescription(sub="심사결과 등록처리",desc="심사결과를 등록 처리 한다.")
    public ClaimResultVo insertClaimResult(ClaimResultVo claimResultVo) throws Exception {
        // 입력값 검증
        validateClaimResult(claimResultVo);
        
        int result = claimResultService.insertClaimResult(claimResultVo);
        
        ClaimResultVo response = new ClaimResultVo();
        response.setClaim_no(claimResultVo.getClaim_no());
        if (result > 0) {
            response.setClaim_memo("CLAIM_RESULT가 성공적으로 등록되었습니다.");
        } else {
            throw new Exception("CLAIM_RESULT 등록에 실패했습니다.");
        }
        
        return response;
    }
       
    /**
     * 심사결과를 갱신 처리 한다.
     *
     * @param  claimResultVo 심사결과
     * @return 갱신 결과
     * @throws Exception
     */
    @ElService(key="CLAIMRESULTUpd")    
    @RequestMapping(value="CLAIMRESULTUpd")    
    @ElValidator(errUrl="/claimResult/claimResultRegister", errContinue=true)
    @ElDescription(sub="심사결과 갱신처리",desc="심사결과를 갱신 처리 한다.")    
    public ClaimResultVo updateClaimResult(ClaimResultVo claimResultVo) throws Exception {
        // 입력값 검증
        validateClaimResult(claimResultVo);
        
        // 기존 데이터 존재 여부 확인
        ClaimResultVo existingData = claimResultService.selectClaimResult(claimResultVo);
        if (existingData == null) {
            throw new Exception("수정할 CLAIM_RESULT 데이터를 찾을 수 없습니다.");
        }
        
        int result = claimResultService.updateClaimResult(claimResultVo);
        
        ClaimResultVo response = new ClaimResultVo();
        response.setClaim_no(claimResultVo.getClaim_no());
        if (result > 0) {
            response.setClaim_memo("CLAIM_RESULT가 성공적으로 업데이트되었습니다.");
        } else {
            throw new Exception("CLAIM_RESULT 업데이트에 실패했습니다.");
        }
        
        return response;
    }

    /**
     * 심사결과를 삭제 처리한다.
     *
     * @param  claimResultVo 심사결과    
     * @return 삭제 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMRESULTDel")    
    @RequestMapping(value="CLAIMRESULTDel")
    @ElDescription(sub = "심사결과 삭제처리", desc = "심사결과를 삭제 처리한다.")    
    public ClaimResultVo deleteClaimResult(ClaimResultVo claimResultVo) throws Exception {
        if (claimResultVo.getClaim_no() == null || claimResultVo.getClaim_no().trim().isEmpty()) {
            throw new Exception("청구번호는 필수입니다.");
        }
        
        int result = claimResultService.deleteClaimResult(claimResultVo);
        
        ClaimResultVo response = new ClaimResultVo();
        response.setClaim_no(claimResultVo.getClaim_no());
        if (result > 0) {
            response.setClaim_memo("CLAIM_RESULT가 성공적으로 삭제되었습니다.");
        } else {
            throw new Exception("CLAIM_RESULT 삭제에 실패했습니다.");
        }
        
        return response;
    }
    /**
     * 심사결과를 저장 처리한다. (INSERT/UPDATE)
     *
     * @param  claimResultVo 심사결과    
     * @return 저장 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMRESULTSave")    
    @RequestMapping(value="CLAIMRESULTSave")
    @ElDescription(sub = "심사결과 저장처리", desc = "심사결과를 저장 처리한다.")
    public ClaimResultVo saveClaimResult(ClaimResultVo claimResultVo) throws Exception {
        

        // 입력값 검증
        if (claimResultVo.getClaim_no() == null || claimResultVo.getClaim_no().trim().isEmpty()) {
            System.err.println("[ERROR] 청구번호가 null 또는 빈 문자열입니다.");
            throw new Exception("청구번호는 필수입니다.");
        }
        
        if (claimResultVo.getClaim_memo() == null || claimResultVo.getClaim_memo().trim().isEmpty()) {
            System.err.println("[ERROR] 청구메모가 null 또는 빈 문자열입니다.");
            throw new Exception("청구메모는 필수입니다.");
        }
        
        // 기존 데이터 존재 여부 확인
        ClaimResultVo existingData = claimResultService.selectClaimResult(claimResultVo);
        
        ClaimResultVo result = new ClaimResultVo();
        result.setClaim_no(claimResultVo.getClaim_no());
        
        if (existingData != null) {
            // 기존 데이터가 있으면 업데이트
            System.out.println("기존 데이터 발견 - 업데이트 실행");
            claimResultService.updateClaimResult(claimResultVo);
            result.setClaim_memo("CLAIM_RESULT가 성공적으로 업데이트되었습니다.");
        } else {
            // 기존 데이터가 없으면 신규 저장
            System.out.println("신규 데이터 - 저장 실행");
            claimResultService.insertClaimResult(claimResultVo);
            result.setClaim_memo("CLAIM_RESULT가 성공적으로 저장되었습니다.");
        }
        
        System.out.println("ClaimResult 저장/업데이트 완료");
        return result;
    }
    
    /**
     * CLAIM_RESULT 조회
     *
     * @param  claimResultVo 심사결과    
     * @return 조회 결과
     * @throws Exception
     */
    @ElService(key = "CLAIMRESULTView")    
    @RequestMapping(value="CLAIMRESULTView")
    @ElDescription(sub = "심사결과 조회", desc = "심사결과를 조회한다.")
    public ClaimResultVo viewClaimResult(ClaimResultVo claimResultVo) throws Exception {
        
        if (claimResultVo.getClaim_no() == null || claimResultVo.getClaim_no().trim().isEmpty()) {
            throw new Exception("청구번호는 필수입니다.");
        }
        
        ClaimResultVo result = claimResultService.selectClaimResult(claimResultVo);
        
        if (result == null) {
            throw new Exception("해당 청구번호의 CLAIM_RESULT 데이터를 찾을 수 없습니다.");
        }
        
        return result;
    }
    
    /**
     * 입력값 검증 공통 메서드
     */
    private void validateClaimResult(ClaimResultVo claimResultVo) throws Exception {
        if (claimResultVo.getClaim_no() == null || claimResultVo.getClaim_no().trim().isEmpty()) {
            throw new Exception("청구번호는 필수입니다.");
        }
        
        if (claimResultVo.getClaim_memo() == null || claimResultVo.getClaim_memo().trim().isEmpty()) {
            throw new Exception("청구메모는 필수입니다.");
        }
    }
}
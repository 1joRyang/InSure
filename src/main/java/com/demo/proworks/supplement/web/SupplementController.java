package com.demo.proworks.supplement.web;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.demo.proworks.supplement.service.SupplementService;
import com.demo.proworks.supplement.vo.SuppVo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.exception.ElException;
import org.springframework.web.bind.annotation.RequestMethod;
import com.inswave.elfw.annotation.ElValidator;



@Controller
public class SupplementController {
	
	@Resource(name = "supplementService")
	private SupplementService supplementService;
	
	/**
     * 보완 서류 제출에 필요한 청구 및 보완 요청 정보를 조회합니다.
     * @param paramVo 클라이언트에서 보낸 claimNo가 담겨있는 VO
     * @return DB에서 조회한 상세 정보가 채워진 VO
     * @throws Exception
     */
    @ElService(key = "getSupplementInfo")
    @RequestMapping(value = "getSupplementInfo")    
    @ElDescription(sub = "보완 정보 조회", desc = "보완 서류 제출 화면에 필요한 상세 정보를 조회한다.")               
    public Map<String, Object> getSupplementInfo(SuppVo paramVo, HttpServletRequest request) throws Exception {
        
        System.out.println("3. 컨트롤러 진입 성공");
        System.out.println("4. 컨트롤러가 받은 claimNo: " + paramVo.getClaimNo()); 

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("connectedUserId") == null) {
            throw new ElException("ERROR.LOGIN.004", new String[]{"로그인 정보가 없습니다."});
        }
        Number userIdObj = (Number) session.getAttribute("connectedUserId");
        long loginUserId = userIdObj.longValue();
        paramVo.setID(loginUserId);
        
        SuppVo resultVo = supplementService.selectSupplementInfo(paramVo);
        System.out.println("5. 클라이언트로 보낼 최종 데이터: " + resultVo);

        // ▼▼▼▼▼ 최종 결과를 "elData" 키로 감싸서 Map 형태로 반환합니다. ▼▼▼▼▼
        Map<String, Object> response = new HashMap<>();
        response.put("elData", resultVo);
        
        return response;
    }
    
    
    /**
     * [클라이언트가 호출하는 메소드] 
	 * 보완 서류 제출에 필요한 청구 및 보완 요청 정보를 조회합니다.
     * @param paramVo 클라이언트에서 보낸 claimNo가 담겨있는 VO
     * @return DB에서 조회한 상세 정보가 채워진 VO
     * @throws Exception
     */
    @ElService(key = "selectSupplementInfoLogic")
    @RequestMapping(value = "selectSupplementInfoLogic")    
    @ElDescription(sub = "보완 정보 조회 내부 로직용", desc = "보완 서류 제출 화면에 필요한 상세 정보를 조회한다.")               
    private SuppVo selectSupplementInfoLogic(String claimNo, long loginUserId) throws Exception {
        
        // 서비스에 전달할 VO를 생성하고 값을 설정합니다.
        SuppVo paramVo = new SuppVo();
        paramVo.setClaimNo(claimNo);
        paramVo.setID(loginUserId);
        
        // 서비스를 호출하여 결과를 반환합니다.
        return supplementService.selectSupplementInfo(paramVo);
    }
    	
    	 
   

    
    
}
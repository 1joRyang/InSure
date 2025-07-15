package com.demo.proworks.claim.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.proworks.claim.service.ClaimService;
import com.demo.proworks.claim.vo.ClaimNClaimResultVo;
import com.demo.proworks.claim.vo.ClaimUserVo;
import com.demo.proworks.claim.vo.ClaimVo;
import com.demo.proworks.insimagefile.dao.InsimagefileDAO;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.claim.dao.ClaimDAO;

/**  
 * @subject     : 청구 관련 처리를 담당하는 ServiceImpl
 * @description	: 청구 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/01
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/01			 Inswave	 		최초 생성
 * 
 */
@Service("claimServiceImpl")
public class ClaimServiceImpl implements ClaimService {

    @Resource(name="claimDAO")
    private ClaimDAO claimDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	public List<ClaimNClaimResultVo> selectClaimNClaimResult(ClaimUserVo claimVo) throws Exception {
		return claimDAO.selectClaimNClaimResult(claimVo);
	}

    /**
     * 청구 목록을 조회합니다.
     *
     * @process
     * 1. 청구 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<ClaimVo>을(를) 리턴한다.
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 청구 목록 List<ClaimVo>
     * @throws Exception
     */
	public List<ClaimVo> selectListClaim(ClaimVo claimVo) throws Exception {
		List<ClaimVo> list = claimDAO.selectListClaim(claimVo);	
	
		return list;
	}

    /**
     * 조회한 청구 전체 카운트
     *
     * @process
     * 1. 청구 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 청구 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.selectListCountClaim(claimVo);
	}

    /**
     * 청구를 상세 조회한다.
     *
     * @process
     * 1. 청구를 상세 조회한다.
     * 2. 결과 ClaimVo을(를) 리턴한다.
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public ClaimVo selectClaim(ClaimVo claimVo) throws Exception {
		ClaimVo resultVO = claimDAO.selectClaim(claimVo);			
        
        return resultVO;
	}

    /**
     * 청구를 등록 처리 한다.
     *
     * @process
     * 1. 청구를 등록 처리 한다.
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 번호
     * @throws Exception
     */
	public int insertClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.insertClaim(claimVo);	
	}
	
    /**
     * 청구를 갱신 처리 한다.
     *
     * @process
     * 1. 청구를 갱신 처리 한다.
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 번호
     * @throws Exception
     */
	public int updateClaim(ClaimVo claimVo) throws Exception {				
		return claimDAO.updateClaim(claimVo);	   		
	}

    /**
     * 청구를 삭제 처리 한다.
     *
     * @process
     * 1. 청구를 삭제 처리 한다.
     * 
     * @param  claimVo 청구 ClaimVo
     * @return 번호
     * @throws Exception
     */
	public int deleteClaim(ClaimVo claimVo) throws Exception {
		return claimDAO.deleteClaim(claimVo);
	}
	
	/**
     * 청구 정보와 첨부파일 정보를 DB에 최종 저장
     * 생성자: 이지현
     */
     
     @Resource(name = "insimagefileDAO")
     private InsimagefileDAO insimagefileDao;
     
    @Override
    @Transactional(rollbackFor = Exception.class)
	public void saveFinalClaim(Map<String, Object> claimData) throws Exception {
		// 1. CLAIM 테이블 저장을 위한 데이터 준비 Map -> ClaimVo변환
		ClaimVo claimVo = new ClaimVo();
        claimVo.setClaim_no((String) claimData.get("claimNo"));
        claimVo.setClaim_type((String) claimData.get("claimType"));
        claimVo.setClaim_content((String) claimData.get("claimContent"));
        claimVo.setID(String.valueOf(claimData.get("userId")));
        claimVo.setStatus("접수");
        SimpleDateFormat receiptFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String receiptDateStr = receiptFormatter.format(new Date());
        claimVo.setReceipt_date(receiptDateStr);
        
        String accidentDateStr = (String) claimData.get("accidentDate");
        claimVo.setDate_of_accident(accidentDateStr);
        
        // 자동 생성된 claimDao.insertClaim 메소드 호출
        claimDAO.insertClaim(claimVo);
        System.out.println("CLAIM 테이블 저장 완료: " + claimVo.getClaim_no());
        
        // --- 2. INS_IMAGE_FILE 테이블 저장을 위한 데이터 준비 ---
        String claimNo = (String) claimData.get("claimNo");
        String s3fileKeys = (String) claimData.get("s3fileKeys");
        String[] keysArray = s3fileKeys.split(",");

        for (String key : keysArray) {
            InsimagefileVo imageVo = new InsimagefileVo();
            imageVo.setClaim_no(claimNo);
            imageVo.setFile_path(key); // S3 오브젝트 키를 file_path에 저장
            
            // 자동 생성된 insimagefileDao.insertInsimagefile 메소드 호출
            insimagefileDao.insertInsimagefile(imageVo);
        }
        System.out.println("INS_IMAGE_FILE 테이블에 " + keysArray.length + "건 저장 완료.");
	}
	
}

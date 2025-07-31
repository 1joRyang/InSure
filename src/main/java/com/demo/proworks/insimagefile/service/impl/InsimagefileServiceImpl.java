package com.demo.proworks.insimagefile.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.proworks.insimagefile.service.InsimagefileService;
import com.demo.proworks.insimagefile.vo.InsimagefileListVo;
import com.demo.proworks.insimagefile.vo.InsimagefileVo;
import com.demo.proworks.insimagefile.dao.InsimagefileDAO;

/**  
 * @subject     : 이미지파일테이블 관련 처리를 담당하는 ServiceImpl
 * @description	: 이미지파일테이블 관련 처리를 담당하는 ServiceImpl
 * @author      : 이지현
 * @since       : 2025/07/04
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/04			 이지현	 		최초 생성
 * 
 */
@Service("insimagefileServiceImpl")
public class InsimagefileServiceImpl implements InsimagefileService {

    @Resource(name="insimagefileDAO")
    private InsimagefileDAO insimagefileDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 이미지파일테이블 목록을 조회합니다.
     *
     * @process
     * 1. 이미지파일테이블 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<InsimagefileVo>을(를) 리턴한다.
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 이미지파일테이블 목록 List<InsimagefileVo>
     * @throws Exception
     */
	public List<InsimagefileVo> selectListInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
		List<InsimagefileVo> list = insimagefileDAO.selectListInsimagefile(insimagefileVo);	
	
		return list;
	}

    /**
     * 조회한 이미지파일테이블 전체 카운트
     *
     * @process
     * 1. 이미지파일테이블 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 이미지파일테이블 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
		return insimagefileDAO.selectListCountInsimagefile(insimagefileVo);
	}

    /**
     * 이미지파일테이블를 상세 조회한다.
     *
     * @process
     * 1. 이미지파일테이블를 상세 조회한다.
     * 2. 결과 InsimagefileVo을(를) 리턴한다.
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public InsimagefileVo selectInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
		InsimagefileVo resultVO = insimagefileDAO.selectInsimagefile(insimagefileVo);			
        
        return resultVO;
	}

    /**
     * 이미지파일테이블를 등록 처리 한다.
     *
     * @process
     * 1. 이미지파일테이블를 등록 처리 한다.
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 번호
     * @throws Exception
     */
	public int insertInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
		return insimagefileDAO.insertInsimagefile(insimagefileVo);	
	}
	
    /**
     * 이미지파일테이블를 갱신 처리 한다.
     *
     * @process
     * 1. 이미지파일테이블를 갱신 처리 한다.
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 번호
     * @throws Exception
     */
	public int updateInsimagefile(InsimagefileVo insimagefileVo) throws Exception {				
		return insimagefileDAO.updateInsimagefile(insimagefileVo);	   		
	}

    /**
     * 이미지파일테이블를 삭제 처리 한다.
     *
     * @process
     * 1. 이미지파일테이블를 삭제 처리 한다.
     * 
     * @param  insimagefileVo 이미지파일테이블 InsimagefileVo
     * @return 번호
     * @throws Exception
     */
	public int deleteInsimagefile(InsimagefileVo insimagefileVo) throws Exception {
		return insimagefileDAO.deleteInsimagefile(insimagefileVo);
	}
	
	/**
     * 보험금 청구 서류(이미지)를 파일로 저장하고 DB에 기록 한다.
     
    @Override
	public void saveImageFiles(List<MultipartFile> files, String claimType) throws Exception {
		String uploadPath = "C:/inswave/uploads/";
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		for (MultipartFile file : files){
			if(file.isEmpty()) continue;
			
			String storedFileName = UUID.randomUUID().toString() + ".jpg";
			File newFile = new File(uploadPath + storedFileName);
			
			// MultipartFile을 바로 파일로 저장
			file.transferTo(newFile);
			
			// DB에 기록할 Vo만들기
			InsimagefileVo dbVo = new InsimagefileVo();
			dbVo.setStored_file_name(storedFileName);
			dbVo.setOriginal_file_name(file.getOriginalFilename());
			dbVo.setFile_path(uploadPath);
			dbVo.setFile_size(String.valueOf(file.getSize()));
			dbVo.setFile_type(file.getContentType());
			
			insimagefileDAO.insertInsimagefile(dbVo);
		
		}
   
	}*/
	
}

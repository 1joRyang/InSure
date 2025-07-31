package com.demo.proworks.s3.web;

import java.net.URL;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.demo.proworks.s3.service.S3Service;
import com.demo.proworks.s3.vo.S3UrlVo;

import org.springframework.web.bind.annotation.RequestMapping;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import org.springframework.web.bind.annotation.RequestMethod;
import com.inswave.elfw.annotation.ElValidator;

@Controller
public class S3Controller {
	
	@Resource(name = "s3Service")
	private S3Service s3Service;
	
	//@Value("${aws.s3.bucket}")
	private String bucketName="insure-claim-docs-final-project";

	/**
     * 청구이미지를 s3에 업로드합니다
     * @return DB에 저장할 URL과 파일 키
     */
    @ElService(key = "getS3UploadUrl")
    @RequestMapping(value = "getS3UploadUrl")    
    @ElDescription(sub = "S3 Pre-signed URL 발급", desc = "S3 업로드용 Pre-signed URL을 발급한다.")               
    public S3UrlVo getS3UploadUrl(S3UrlVo param) throws Exception {    
    
    	System.out.println("================s3 콘트롤러진입======================");
    	String filename = param.getFilename();
    	System.out.println("=============filename : "+ filename);
    	// 고유한 파일 키 생성
    	String extension = filename.substring(filename.lastIndexOf("."));
    	String objectKey = "uploads/" + UUID.randomUUID().toString() + extension;
    	System.out.println("=============extension : "+ extension);
    	System.out.println("=============objectKey : "+ objectKey);
    	
    	// Pre-signed URL 생성
    	URL presignedUrl = s3Service.generatePresignedPutUrl(bucketName, objectKey);
    	System.out.println("=============presignedUrl : "+ presignedUrl);
    	param.setPresignedUrl(presignedUrl.toString());
    	param.setObjectKey(objectKey);
    	System.out.println("================s3 콘트롤러 메소드 끝======================");
    	return param;
    	
    	 
    }
    
    /**
     * 청구이미지를 s3에서 조회합니다.
	 * @param param objectKey에 S3 파일 키를 담아 요청
     * @return presignedUrl에 임시 조회 URL을 담아 반환
	 * =>DB의 INS_IMAGE_FILE테이블에서 s3_object_key 값 조회
	 * =>s3_object_key 값을 파라미터로 /getS3ViewUrl.pwkjson API호출
	 * =>API로 응답받은 JSON에서 presingedUrl 값 꺼내서 <img>태그의 src에 넣거나 링크로 사용하여 이미지 조회
     */
    @ElService(key = "getS3ViewUrl")
    @RequestMapping(value = "getS3ViewUrl")    
    @ElDescription(sub = "S3 Pre-signed URL 발급", desc = "S3 조회용 Pre-signed URL을 발급한다.")               
    public S3UrlVo getS3ViewUrl(S3UrlVo param) throws Exception {    
    
    	 String objectKey = param.getObjectKey();
    	 System.out.println("========조회 요청된 S3 키: " + objectKey);
    	 
    	 URL presignedUrl = s3Service.generatePresignedGetUrl(bucketName, objectKey);
    	 
    	 S3UrlVo resultVo = new S3UrlVo();
    	 resultVo.setPresignedUrl(presignedUrl.toString());
    	 
    	 return resultVo;
    }
    
    
    
}
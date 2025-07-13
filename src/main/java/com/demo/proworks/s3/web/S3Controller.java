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
}
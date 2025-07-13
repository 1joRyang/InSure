package com.demo.proworks.s3.service.impl;

import java.net.URL;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.demo.proworks.s3.service.S3Service;

@Service("s3Service")
public class S3ServiceImpl implements S3Service {

	@Resource(name = "amazonS3Client")
	private AmazonS3 amazonS3Client;
	
	@Override
	public URL generatePresignedPutUrl(String bucketName, String objectKey) {
	
		// URL 유효시간 설정
		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 10;	// 10분
		expiration.setTime(expTimeMillis);
		
		GeneratePresignedUrlRequest request =
				new GeneratePresignedUrlRequest(bucketName, objectKey)
					.withMethod(HttpMethod.PUT)
					.withExpiration(expiration);
		
		request.setContentType("image/jpeg");

		return amazonS3Client.generatePresignedUrl(request);
	}
	

}

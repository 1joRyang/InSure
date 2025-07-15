package com.demo.proworks.s3.service;

import java.net.URL;

public interface S3Service {
	//	이미지 업로드 URL 생성
	URL generatePresignedPutUrl(String bucketName, String objectKey);
	
	// 이미지 조회 URL 생성
	URL generatePresignedGetUrl(String bucketName, String objectKet);
}

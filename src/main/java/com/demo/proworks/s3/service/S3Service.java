package com.demo.proworks.s3.service;

import java.net.URL;

public interface S3Service {
	URL generatePresignedPutUrl(String bucketName, String objectKey);
}

package com.demo.proworks.batch.service;

public interface BatchService {
	// 월별 승인률 및 일별처리시간
	void executeMonthlyRateBatch();
	
	// 주간 처리 현황
	void executeWeeklyTrendBatch();
	
	// 청구 유형 분포
	void executeClaimTypeDistributionBatch();
}

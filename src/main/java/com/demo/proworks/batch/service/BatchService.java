package com.demo.proworks.batch.service;

public interface BatchService {
	void executeMonthlyRateBatch();
	
	void executeWeeklyTrendBatch();
	
	void executeClaimTypeDistributionBatch();
}

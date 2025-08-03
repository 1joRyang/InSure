package com.demo.proworks.batch.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.proworks.batch.dao.BatchDAO;
import com.demo.proworks.batch.service.BatchService;

@Service("batchService")
public class BatchServiceImpl implements BatchService {

		@Resource
	    private BatchDAO batchDAO;
	
	    /**
	     * 매일 새벽 2시에 월별 승인율 집계 배치를 실행
	     */
	    @Scheduled(cron = "0 0 2 * * *")
	    @Override
	    public void executeMonthlyRateBatch() {
	    
	        System.out.println("====== [배치 시작] 월별 승인율 집계 ======");
	        try {
	            int updatedRows = batchDAO.updateMonthlyApprovalRate();
	            System.out.println("====== [배치 종료] 업데이트 된 월: " + updatedRows + "건 ======");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("====== [배치 오류] 월별 승인율 집계 실패 ======");
	        }
	        
	        
	        // --- 신규 일별 처리 시간 집계 추가 ---
	        System.out.println("====== [배치 시작] 일별 처리 시간 집계 ======");
	        try {
	            int updatedRows = batchDAO.updateDailyProcessingTime();
	            System.out.println("====== [배치 종료] 업데이트 된 일: " + updatedRows + "건 ======");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("====== [배치 오류] 일별 처리 시간 집계 실패 ======");
	        }
	    }
	    
	    @Scheduled(cron = "0 5 2 * * *") // 매일 새벽 2시 5분
		public void executeWeeklyTrendBatch() {
		    System.out.println("====== [배치 시작] 주간 처리 현황 집계 ======");
		    try {
		        batchDAO.updateWeeklyTrend();
		        System.out.println("====== [배치 종료] 주간 처리 현황 집계 완료 ======");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("====== [배치 오류] 주간 처리 현황 집계 실패 ======");
		    }
		}
		
		@Scheduled(cron = "0 10 2 * * *")
		@Override
		public void executeClaimTypeDistributionBatch() {
		    System.out.println("====== [배치 시작] 청구 유형 분포 집계 ======");
		    try {
		        batchDAO.updateClaimTypeDistribution();
		        System.out.println("====== [배치 종료] 청구 유형 분포 집계 완료 ======");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("====== [배치 오류] 청구 유형 분포 집계 실패 ======");
		    }
		}
	    
	    
}

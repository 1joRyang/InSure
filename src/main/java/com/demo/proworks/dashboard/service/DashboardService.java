package com.demo.proworks.dashboard.service;

import com.demo.proworks.dashboard.vo.TodayStatusVo;
import com.inswave.elfw.exception.ElException;

/**
 * @subject : 대시보드 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 * @author  : Inswave
 * @since   : 2025/07/21
 */
public interface DashboardService {

	/**
	 * 오늘의 처리 현황을 조회한다.
	 * @return TodayStatusVo 오늘의 처리 현황 결과 VO
	 * @throws ElException
	 */
	 public TodayStatusVo selectTodayStatusCounts() throws ElException;

}

package com.demo.proworks.supplement.service;

import com.demo.proworks.supplement.vo.SuppVo;

public interface SupplementService {
	
	/*보완 요청 상세 정보 조회 로직*/
	public SuppVo selectSupplementInfo(SuppVo suppVo) throws Exception;
	
	/*보완 서류 추가*/
	public void  addSupplementDocs(SuppVo vo) throws Exception;
	
	/*보완 완료 후 상태 변경: 보완완료*/
	public void updateSupplementCompleted(SuppVo vo) throws Exception;
}

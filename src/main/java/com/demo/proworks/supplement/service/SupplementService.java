package com.demo.proworks.supplement.service;

import com.demo.proworks.supplement.vo.SuppVo;

public interface SupplementService {
	
	public SuppVo selectSupplementInfo(SuppVo suppVo) throws Exception;
	
	public void  addSupplementDocs(SuppVo vo) throws Exception;
	
	public void updateSupplementCompleted(SuppVo vo) throws Exception;
}

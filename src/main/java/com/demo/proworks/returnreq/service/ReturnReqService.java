package com.demo.proworks.returnreq.service;

import java.util.List;

import com.demo.proworks.returnreq.vo.ReturnReqVo;

/**
 * @subject     : 반송요청정보 관련 처리를 담당하는 Service Interface
 * @description : 반송요청정보 관련 처리를 담당하는 Service Interface
 * @author      : hyunwoo
 * @since       : 2025/07/22
 * @modification
 * ===========================================================
 * DATE             AUTHOR              DESC
 * ===========================================================
 * 2025/07/22       hyunwoo            최초 생성
 */
public interface ReturnReqService {

    /**
     * 반송요청정보 목록을 조회합니다.
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 반송요청정보 목록
     * @throws Exception
     */
    public List<ReturnReqVo> selectListReturnReq(ReturnReqVo returnReqVo) throws Exception;

    /**
     * 반송요청정보 전체 건수를 조회합니다.
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 전체 건수
     * @throws Exception
     */
    public long selectListCountReturnReq(ReturnReqVo returnReqVo) throws Exception;

    /**
     * 반송요청정보를 상세 조회합니다.
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 반송요청정보 상세
     * @throws Exception
     */
    public ReturnReqVo selectReturnReq(ReturnReqVo returnReqVo) throws Exception;

    /**
     * 반송요청 존재 여부를 확인합니다.
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 존재 여부
     * @throws Exception
     */
    public boolean existsReturnReq(ReturnReqVo returnReqVo) throws Exception;

    /**
     * 반송요청정보를 등록합니다. (INSERT만)
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 처리 건수
     * @throws Exception
     */
    public int insertReturnReq(ReturnReqVo returnReqVo) throws Exception;

    /**
     * 반송요청정보를 수정합니다. (UPDATE만)
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 처리 건수
     * @throws Exception
     */
    public int updateReturnReq(ReturnReqVo returnReqVo) throws Exception;

    /**
     * 반송요청정보를 삭제합니다.
     * 
     * @param returnReqVo 반송요청정보 VO
     * @return 처리 건수
     * @throws Exception
     */
    public int deleteReturnReq(ReturnReqVo returnReqVo) throws Exception;
}
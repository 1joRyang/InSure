package com.demo.proworks.returnreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "반송요청정보")
public class ReturnReqListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "반송요청정보List", physicalName = "returnReqVoList", type = "com.demo.proworks.returnreq.ReturnReqVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.returnreq.vo.ReturnReqVo> returnReqVoList;

    public java.util.List<com.demo.proworks.returnreq.vo.ReturnReqVo> getReturnReqVoList(){
        return returnReqVoList;
    }

    public void setReturnReqVoList(java.util.List<com.demo.proworks.returnreq.vo.ReturnReqVo> returnReqVoList){
        this.returnReqVoList = returnReqVoList;
    }

    @Override
    public String toString() {
        return "ReturnReqListVo [returnReqVoList=" + returnReqVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

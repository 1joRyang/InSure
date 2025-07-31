package com.demo.proworks.additionalreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "추가요청 정보")
public class AdditionalReqListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "추가요청 정보List", physicalName = "additionalReqVoList", type = "com.demo.proworks.additionalreq.AdditionalReqVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.additionalreq.vo.AdditionalReqVo> additionalReqVoList;

    public java.util.List<com.demo.proworks.additionalreq.vo.AdditionalReqVo> getAdditionalReqVoList(){
        return additionalReqVoList;
    }

    public void setAdditionalReqVoList(java.util.List<com.demo.proworks.additionalreq.vo.AdditionalReqVo> additionalReqVoList){
        this.additionalReqVoList = additionalReqVoList;
    }

    @Override
    public String toString() {
        return "AdditionalReqListVo [additionalReqVoList=" + additionalReqVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

package com.demo.proworks.claimresult.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "심사결과")
public class ClaimResultListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "심사결과List", physicalName = "claimResultVoList", type = "com.demo.proworks.claimresult.ClaimResultVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.claimresult.vo.ClaimResultVo> claimResultVoList;

    public java.util.List<com.demo.proworks.claimresult.vo.ClaimResultVo> getClaimResultVoList(){
        return claimResultVoList;
    }

    public void setClaimResultVoList(java.util.List<com.demo.proworks.claimresult.vo.ClaimResultVo> claimResultVoList){
        this.claimResultVoList = claimResultVoList;
    }

    @Override
    public String toString() {
        return "ClaimResultListVo [claimResultVoList=" + claimResultVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

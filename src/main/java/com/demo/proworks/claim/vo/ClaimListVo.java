package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "청구")
public class ClaimListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "청구List", physicalName = "claimVoList", type = "com.demo.proworks.claim.ClaimVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.claim.vo.ClaimVo> claimVoList;

    public java.util.List<com.demo.proworks.claim.vo.ClaimVo> getClaimVoList(){
        return claimVoList;
    }

    public void setClaimVoList(java.util.List<com.demo.proworks.claim.vo.ClaimVo> claimVoList){
        this.claimVoList = claimVoList;
    }

    @Override
    public String toString() {
        return "ClaimListVo [claimVoList=" + claimVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

package com.demo.proworks.claimoffset.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "상계")
public class ClaimOffsetListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "상계List", physicalName = "claimOffsetVoList", type = "com.demo.proworks.claimoffset.ClaimOffsetVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.claimoffset.vo.ClaimOffsetVo> claimOffsetVoList;

    public java.util.List<com.demo.proworks.claimoffset.vo.ClaimOffsetVo> getClaimOffsetVoList(){
        return claimOffsetVoList;
    }

    public void setClaimOffsetVoList(java.util.List<com.demo.proworks.claimoffset.vo.ClaimOffsetVo> claimOffsetVoList){
        this.claimOffsetVoList = claimOffsetVoList;
    }

    @Override
    public String toString() {
        return "ClaimOffsetListVo [claimOffsetVoList=" + claimOffsetVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

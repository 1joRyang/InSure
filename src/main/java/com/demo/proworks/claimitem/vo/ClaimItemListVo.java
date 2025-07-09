package com.demo.proworks.claimitem.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "청구 아이템들")
public class ClaimItemListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "청구 아이템들List", physicalName = "claimItemVoList", type = "com.demo.proworks.claimitem.ClaimItemVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.claimitem.vo.ClaimItemVo> claimItemVoList;

    public java.util.List<com.demo.proworks.claimitem.vo.ClaimItemVo> getClaimItemVoList(){
        return claimItemVoList;
    }

    public void setClaimItemVoList(java.util.List<com.demo.proworks.claimitem.vo.ClaimItemVo> claimItemVoList){
        this.claimItemVoList = claimItemVoList;
    }

    @Override
    public String toString() {
        return "ClaimItemListVo [claimItemVoList=" + claimItemVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

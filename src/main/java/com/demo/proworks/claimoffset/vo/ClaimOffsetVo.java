package com.demo.proworks.claimoffset.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "상계")
public class ClaimOffsetVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "reason", physicalName = "reason", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String reason;

    @ElDtoField(logicalName = "charge", physicalName = "charge", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String charge;

    @ElDtoField(logicalName = "claim_no2", physicalName = "claim_no2", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no2;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "reason")
    public String getReason(){
        return reason;
    }

    @ElVoField(physicalName = "reason")
    public void setReason(String reason){
        this.reason = reason;
    }

    @ElVoField(physicalName = "charge")
    public String getCharge(){
        return charge;
    }

    @ElVoField(physicalName = "charge")
    public void setCharge(String charge){
        this.charge = charge;
    }

    @ElVoField(physicalName = "claim_no2")
    public String getClaim_no2(){
        return claim_no2;
    }

    @ElVoField(physicalName = "claim_no2")
    public void setClaim_no2(String claim_no2){
        this.claim_no2 = claim_no2;
    }

    @Override
    public String toString() {
        return "ClaimOffsetVo [claim_no=" + claim_no + ",reason=" + reason + ",charge=" + charge + ",claim_no2=" + claim_no2 + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

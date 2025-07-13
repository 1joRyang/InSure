package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "")
public class ClaimNoVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimNoVo(){
    }

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimNoVo [");
        sb.append("claim_no").append("=").append(claim_no);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
    }


    @Override
    public void _xStreamDec() {
    }


}

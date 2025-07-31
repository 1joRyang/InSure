package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class ClaimMonitorVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimMonitorVo(){
    }

    @ElDtoField(logicalName = "countUnder1M", physicalName = "countUnder1M", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String countUnder1M;

    @ElDtoField(logicalName = "countOver1M", physicalName = "countOver1M", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String countOver1M;

    @ElVoField(physicalName = "countUnder1M")
    public String getCountUnder1M(){
        String ret = this.countUnder1M;
        return ret;
    }

    @ElVoField(physicalName = "countUnder1M")
    public void setCountUnder1M(String countUnder1M){
        this.countUnder1M = countUnder1M;
    }

    @ElVoField(physicalName = "countOver1M")
    public String getCountOver1M(){
        String ret = this.countOver1M;
        return ret;
    }

    @ElVoField(physicalName = "countOver1M")
    public void setCountOver1M(String countOver1M){
        this.countOver1M = countOver1M;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimMonitorVo [");
        sb.append("countUnder1M").append("=").append(countUnder1M).append(",");
        sb.append("countOver1M").append("=").append(countOver1M);
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

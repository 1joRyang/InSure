package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class PaymentVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PaymentVo(){
    }

    @ElDtoField(logicalName = "approvalRate", physicalName = "approvalRate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approvalRate;

    @ElVoField(physicalName = "approvalRate")
    public String getApprovalRate(){
        String ret = this.approvalRate;
        return ret;
    }

    @ElVoField(physicalName = "approvalRate")
    public void setApprovalRate(String approvalRate){
        this.approvalRate = approvalRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApprovalRateVo [");
        sb.append("approvalRate").append("=").append(approvalRate);
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

package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "")
public class ClaimUserCalcVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimUserCalcVo(){
    }

    @ElDtoField(logicalName = "completed_count", physicalName = "completed_count", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String completed_count;

    @ElDtoField(logicalName = "total_count", physicalName = "total_count", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String total_count;

    @ElDtoField(logicalName = "total_payment", physicalName = "total_payment", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String total_payment;

    @ElDtoField(logicalName = "approval_rate", physicalName = "approval_rate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approval_rate;

    @ElVoField(physicalName = "completed_count")
    public String getCompleted_count(){
        String ret = this.completed_count;
        return ret;
    }

    @ElVoField(physicalName = "completed_count")
    public void setCompleted_count(String completed_count){
        this.completed_count = completed_count;
    }

    @ElVoField(physicalName = "total_count")
    public String getTotal_count(){
        String ret = this.total_count;
        return ret;
    }

    @ElVoField(physicalName = "total_count")
    public void setTotal_count(String total_count){
        this.total_count = total_count;
    }

    @ElVoField(physicalName = "total_payment")
    public String getTotal_payment(){
        String ret = this.total_payment;
        return ret;
    }

    @ElVoField(physicalName = "total_payment")
    public void setTotal_payment(String total_payment){
        this.total_payment = total_payment;
    }

    @ElVoField(physicalName = "approval_rate")
    public String getApproval_rate(){
        String ret = this.approval_rate;
        return ret;
    }

    @ElVoField(physicalName = "approval_rate")
    public void setApproval_rate(String approval_rate){
        this.approval_rate = approval_rate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimUserCalcVo [");
        sb.append("completed_count").append("=").append(completed_count).append(",");
        sb.append("total_count").append("=").append(total_count).append(",");
        sb.append("total_payment").append("=").append(total_payment).append(",");
        sb.append("approval_rate").append("=").append(approval_rate);
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

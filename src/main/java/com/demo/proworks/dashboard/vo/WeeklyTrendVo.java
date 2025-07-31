package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class WeeklyTrendVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public WeeklyTrendVo(){
    }

    @ElDtoField(logicalName = "reportDate", physicalName = "reportDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String reportDate;

    @ElDtoField(logicalName = "approvalCount", physicalName = "approvalCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approvalCount;

    @ElDtoField(logicalName = "simplePayCount", physicalName = "simplePayCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String simplePayCount;

    @ElVoField(physicalName = "reportDate")
    public String getReportDate(){
        String ret = this.reportDate;
        return ret;
    }

    @ElVoField(physicalName = "reportDate")
    public void setReportDate(String reportDate){
        this.reportDate = reportDate;
    }

    @ElVoField(physicalName = "approvalCount")
    public String getApprovalCount(){
        String ret = this.approvalCount;
        return ret;
    }

    @ElVoField(physicalName = "approvalCount")
    public void setApprovalCount(String approvalCount){
        this.approvalCount = approvalCount;
    }

    @ElVoField(physicalName = "simplePayCount")
    public String getSimplePayCount(){
        String ret = this.simplePayCount;
        return ret;
    }

    @ElVoField(physicalName = "simplePayCount")
    public void setSimplePayCount(String simplePayCount){
        this.simplePayCount = simplePayCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeeklyTrendVo [");
        sb.append("reportDate").append("=").append(reportDate).append(",");
        sb.append("approvalCount").append("=").append(approvalCount).append(",");
        sb.append("simplePayCount").append("=").append(simplePayCount);
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

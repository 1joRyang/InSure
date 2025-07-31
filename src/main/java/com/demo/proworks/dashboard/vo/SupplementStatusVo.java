package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class SupplementStatusVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SupplementStatusVo(){
    }

    @ElDtoField(logicalName = "requestCount", physicalName = "requestCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String requestCount;

    @ElDtoField(logicalName = "completedCount", physicalName = "completedCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String completedCount;

    @ElDtoField(logicalName = "avgProcessingTime", physicalName = "avgProcessingTime", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String avgProcessingTime;

    @ElVoField(physicalName = "requestCount")
    public String getRequestCount(){
        String ret = this.requestCount;
        return ret;
    }

    @ElVoField(physicalName = "requestCount")
    public void setRequestCount(String requestCount){
        this.requestCount = requestCount;
    }

    @ElVoField(physicalName = "completedCount")
    public String getCompletedCount(){
        String ret = this.completedCount;
        return ret;
    }

    @ElVoField(physicalName = "completedCount")
    public void setCompletedCount(String completedCount){
        this.completedCount = completedCount;
    }

    @ElVoField(physicalName = "avgProcessingTime")
    public String getAvgProcessingTime(){
        String ret = this.avgProcessingTime;
        return ret;
    }

    @ElVoField(physicalName = "avgProcessingTime")
    public void setAvgProcessingTime(String avgProcessingTime){
        this.avgProcessingTime = avgProcessingTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SupplementStatusVo [");
        sb.append("requestCount").append("=").append(requestCount).append(",");
        sb.append("completedCount").append("=").append(completedCount).append(",");
        sb.append("avgProcessingTime").append("=").append(avgProcessingTime);
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

package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class TodayStatusVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public TodayStatusVo(){
    }

    @ElDtoField(logicalName = "waitingCount", physicalName = "waitingCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String waitingCount;

    @ElDtoField(logicalName = "processingCount", physicalName = "processingCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String processingCount;

    @ElDtoField(logicalName = "completedCount", physicalName = "completedCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String completedCount;

    @ElDtoField(logicalName = "returnedCount", physicalName = "returnedCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String returnedCount;

    @ElVoField(physicalName = "waitingCount")
    public String getWaitingCount(){
        String ret = this.waitingCount;
        return ret;
    }

    @ElVoField(physicalName = "waitingCount")
    public void setWaitingCount(String waitingCount){
        this.waitingCount = waitingCount;
    }

    @ElVoField(physicalName = "processingCount")
    public String getProcessingCount(){
        String ret = this.processingCount;
        return ret;
    }

    @ElVoField(physicalName = "processingCount")
    public void setProcessingCount(String processingCount){
        this.processingCount = processingCount;
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

    @ElVoField(physicalName = "returnedCount")
    public String getReturnedCount(){
        String ret = this.returnedCount;
        return ret;
    }

    @ElVoField(physicalName = "returnedCount")
    public void setReturnedCount(String returnedCount){
        this.returnedCount = returnedCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TodayStatusVo [");
        sb.append("waitingCount").append("=").append(waitingCount).append(",");
        sb.append("processingCount").append("=").append(processingCount).append(",");
        sb.append("completedCount").append("=").append(completedCount).append(",");
        sb.append("returnedCount").append("=").append(returnedCount);
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

package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "CLAIM상태별갯수")
public class ClaimStatusCountVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimStatusCountVo(){
    }

    @ElDtoField(logicalName = "사용자ID", physicalName = "ID", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String ID;

    @ElDtoField(logicalName = "대기상태갯수", physicalName = "waitingCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String waitingCount;

    @ElDtoField(logicalName = "보완상태갯수", physicalName = "supplementCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String supplementCount;

    @ElDtoField(logicalName = "완료상태갯수", physicalName = "completedCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String completedCount;

    @ElDtoField(logicalName = "전체갯수", physicalName = "allCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String allCount;

    @ElVoField(physicalName = "ID")
    public String getID(){
        String ret = this.ID;
        return ret;
    }

    @ElVoField(physicalName = "ID")
    public void setID(String ID){
        this.ID = ID;
    }

    @ElVoField(physicalName = "waitingCount")
    public String getWaitingCount(){
        String ret = this.waitingCount;
        return ret;
    }

    @ElVoField(physicalName = "waitingCount")
    public void setWaitingCount(String waitingCount){
        this.waitingCount = waitingCount;
    }

    @ElVoField(physicalName = "supplementCount")
    public String getSupplementCount(){
        String ret = this.supplementCount;
        return ret;
    }

    @ElVoField(physicalName = "supplementCount")
    public void setSupplementCount(String supplementCount){
        this.supplementCount = supplementCount;
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

    @ElVoField(physicalName = "allCount")
    public String getAllCount(){
        String ret = this.allCount;
        return ret;
    }

    @ElVoField(physicalName = "allCount")
    public void setAllCount(String allCount){
        this.allCount = allCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimStatusCountVo [");
        sb.append("ID").append("=").append(ID).append(",");
        sb.append("waitingCount").append("=").append(waitingCount).append(",");
        sb.append("supplementCount").append("=").append(supplementCount).append(",");
        sb.append("completedCount").append("=").append(completedCount).append(",");
        sb.append("allCount").append("=").append(allCount);
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

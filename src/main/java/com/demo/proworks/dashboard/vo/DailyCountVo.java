package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class DailyCountVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public DailyCountVo(){
    }

    @ElDtoField(logicalName = "yesterdayProcessedCount", physicalName = "yesterdayProcessedCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String yesterdayProcessedCount;

    @ElVoField(physicalName = "yesterdayProcessedCount")
    public String getYesterdayProcessedCount(){
        String ret = this.yesterdayProcessedCount;
        return ret;
    }

    @ElVoField(physicalName = "yesterdayProcessedCount")
    public void setYesterdayProcessedCount(String yesterdayProcessedCount){
        this.yesterdayProcessedCount = yesterdayProcessedCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DailyCountVo [");
        sb.append("yesterdayProcessedCount").append("=").append(yesterdayProcessedCount);
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

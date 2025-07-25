package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class ChartVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ChartVo(){
    }

    @ElDtoField(logicalName = "label", physicalName = "label", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String label;

    @ElDtoField(logicalName = "value", physicalName = "value", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int value;

    @ElVoField(physicalName = "label")
    public String getLabel(){
        String ret = this.label;
        return ret;
    }

    @ElVoField(physicalName = "label")
    public void setLabel(String label){
        this.label = label;
    }

    @ElVoField(physicalName = "value")
    public int getValue(){
        return value;
    }

    @ElVoField(physicalName = "value")
    public void setValue(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChartVo [");
        sb.append("label").append("=").append(label).append(",");
        sb.append("value").append("=").append(value);
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

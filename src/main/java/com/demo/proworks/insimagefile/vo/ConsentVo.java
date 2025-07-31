package com.demo.proworks.insimagefile.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이미지파일테이블")
public class ConsentVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ConsentVo(){
    }

    @ElDtoField(logicalName = "agreed", physicalName = "agreed", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String agreed;

    @ElVoField(physicalName = "agreed")
    public String getAgreed(){
        String ret = this.agreed;
        return ret;
    }

    @ElVoField(physicalName = "agreed")
    public void setAgreed(String agreed){
        this.agreed = agreed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConsentVo [");
        sb.append("agreed").append("=").append(agreed);
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

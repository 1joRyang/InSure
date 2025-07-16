package com.demo.proworks.insimagefile.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이미지파일테이블")
public class Step2Vo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public Step2Vo(){
    }

    @ElDtoField(logicalName = "accidentDate", physicalName = "accidentDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accidentDate;

    @ElVoField(physicalName = "accidentDate")
    public String getAccidentDate(){
        String ret = this.accidentDate;
        return ret;
    }

    @ElVoField(physicalName = "accidentDate")
    public void setAccidentDate(String accidentDate){
        this.accidentDate = accidentDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Step2Vo [");
        sb.append("accidentDate").append("=").append(accidentDate);
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

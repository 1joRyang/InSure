package com.demo.proworks.insimagefile.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이미지파일테이블")
public class Step5Vo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public Step5Vo(){
    }

    @ElDtoField(logicalName = "s3FileKeys", physicalName = "s3FileKeys", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String s3FileKeys;

    @ElVoField(physicalName = "s3FileKeys")
    public String getS3FileKeys(){
        String ret = this.s3FileKeys;
        return ret;
    }

    @ElVoField(physicalName = "s3FileKeys")
    public void setS3FileKeys(String s3FileKeys){
        this.s3FileKeys = s3FileKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Step5Vo [");
        sb.append("s3FileKeys").append("=").append(s3FileKeys);
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

package com.demo.proworks.s3.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "S3Url테이블")
public class S3UrlReqVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public S3UrlReqVo(){
    }

    @ElDtoField(logicalName = "elData", physicalName = "elData", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String elData;

    @ElVoField(physicalName = "elData")
    public String getElData(){
        String ret = this.elData;
        return ret;
    }

    @ElVoField(physicalName = "elData")
    public void setElData(String elData){
        this.elData = elData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("S3UrlReqVo [");
        sb.append("elData").append("=").append(elData);
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

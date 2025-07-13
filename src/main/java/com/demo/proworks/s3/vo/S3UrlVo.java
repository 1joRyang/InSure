package com.demo.proworks.s3.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "S3Url테이블")
public class S3UrlVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public S3UrlVo(){
    }

    @ElDtoField(logicalName = "presignedUrl", physicalName = "presignedUrl", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String presignedUrl;

    @ElDtoField(logicalName = "objectKey", physicalName = "objectKey", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String objectKey;

    @ElDtoField(logicalName = "filename", physicalName = "filename", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String filename;

    @ElVoField(physicalName = "presignedUrl")
    public String getPresignedUrl(){
        String ret = this.presignedUrl;
        return ret;
    }

    @ElVoField(physicalName = "presignedUrl")
    public void setPresignedUrl(String presignedUrl){
        this.presignedUrl = presignedUrl;
    }

    @ElVoField(physicalName = "objectKey")
    public String getObjectKey(){
        String ret = this.objectKey;
        return ret;
    }

    @ElVoField(physicalName = "objectKey")
    public void setObjectKey(String objectKey){
        this.objectKey = objectKey;
    }

    @ElVoField(physicalName = "filename")
    public String getFilename(){
        String ret = this.filename;
        return ret;
    }

    @ElVoField(physicalName = "filename")
    public void setFilename(String filename){
        this.filename = filename;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("S3UrlVo [");
        sb.append("presignedUrl").append("=").append(presignedUrl).append(",");
        sb.append("objectKey").append("=").append(objectKey).append(",");
        sb.append("filename").append("=").append(filename);
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

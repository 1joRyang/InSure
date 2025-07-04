package com.demo.proworks.insurance.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "")
public class SubInsuranceListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SubInsuranceListVo(){
    }

    @ElDtoField(logicalName = "insu_id", physicalName = "insu_id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String insu_id;

    @ElDtoField(logicalName = "insu_name", physicalName = "insu_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String insu_name;

    @ElDtoField(logicalName = "user_name", physicalName = "user_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String user_name;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElVoField(physicalName = "insu_id")
    public String getInsu_id(){
        String ret = this.insu_id;
        return ret;
    }

    @ElVoField(physicalName = "insu_id")
    public void setInsu_id(String insu_id){
        this.insu_id = insu_id;
    }

    @ElVoField(physicalName = "insu_name")
    public String getInsu_name(){
        String ret = this.insu_name;
        return ret;
    }

    @ElVoField(physicalName = "insu_name")
    public void setInsu_name(String insu_name){
        this.insu_name = insu_name;
    }

    @ElVoField(physicalName = "user_name")
    public String getUser_name(){
        String ret = this.user_name;
        return ret;
    }

    @ElVoField(physicalName = "user_name")
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        String ret = this.status;
        return ret;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SubInsuranceListVo [");
        sb.append("insu_id").append("=").append(insu_id).append(",");
        sb.append("insu_name").append("=").append(insu_name).append(",");
        sb.append("user_name").append("=").append(user_name).append(",");
        sb.append("status").append("=").append(status);
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

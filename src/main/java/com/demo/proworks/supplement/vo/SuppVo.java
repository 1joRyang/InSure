package com.demo.proworks.supplement.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "서류보완 vo")
public class SuppVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SuppVo(){
    }

    @ElDtoField(logicalName = "claimNo", physicalName = "claimNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claimNo;

    @ElDtoField(logicalName = "claimType", physicalName = "claimType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claimType;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "additionalMemo", physicalName = "additionalMemo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String additionalMemo;

    @ElDtoField(logicalName = "empNo", physicalName = "empNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empNo;

    @ElDtoField(logicalName = "ID", physicalName = "ID", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long ID;

    @ElDtoField(logicalName = "empName", physicalName = "empName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empName;

    @ElDtoField(logicalName = "s3fileKeys", physicalName = "s3fileKeys", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String s3fileKeys;

    @ElVoField(physicalName = "claimNo")
    public String getClaimNo(){
        String ret = this.claimNo;
        return ret;
    }

    @ElVoField(physicalName = "claimNo")
    public void setClaimNo(String claimNo){
        this.claimNo = claimNo;
    }

    @ElVoField(physicalName = "claimType")
    public String getClaimType(){
        String ret = this.claimType;
        return ret;
    }

    @ElVoField(physicalName = "claimType")
    public void setClaimType(String claimType){
        this.claimType = claimType;
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

    @ElVoField(physicalName = "additionalMemo")
    public String getAdditionalMemo(){
        String ret = this.additionalMemo;
        return ret;
    }

    @ElVoField(physicalName = "additionalMemo")
    public void setAdditionalMemo(String additionalMemo){
        this.additionalMemo = additionalMemo;
    }

    @ElVoField(physicalName = "empNo")
    public String getEmpNo(){
        String ret = this.empNo;
        return ret;
    }

    @ElVoField(physicalName = "empNo")
    public void setEmpNo(String empNo){
        this.empNo = empNo;
    }

    @ElVoField(physicalName = "ID")
    public long getID(){
        return ID;
    }

    @ElVoField(physicalName = "ID")
    public void setID(long ID){
        this.ID = ID;
    }

    @ElVoField(physicalName = "empName")
    public String getEmpName(){
        String ret = this.empName;
        return ret;
    }

    @ElVoField(physicalName = "empName")
    public void setEmpName(String empName){
        this.empName = empName;
    }

    @ElVoField(physicalName = "s3fileKeys")
    public String getS3fileKeys(){
        String ret = this.s3fileKeys;
        return ret;
    }

    @ElVoField(physicalName = "s3fileKeys")
    public void setS3fileKeys(String s3fileKeys){
        this.s3fileKeys = s3fileKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SuppVo [");
        sb.append("claimNo").append("=").append(claimNo).append(",");
        sb.append("claimType").append("=").append(claimType).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("additionalMemo").append("=").append(additionalMemo).append(",");
        sb.append("empNo").append("=").append(empNo).append(",");
        sb.append("ID").append("=").append(ID).append(",");
        sb.append("empName").append("=").append(empName).append(",");
        sb.append("s3fileKeys").append("=").append(s3fileKeys);
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

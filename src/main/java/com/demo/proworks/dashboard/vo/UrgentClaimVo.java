package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class UrgentClaimVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UrgentClaimVo(){
    }

    @ElDtoField(logicalName = "claimNo", physicalName = "claimNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claimNo;

    @ElDtoField(logicalName = "userName", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElDtoField(logicalName = "claimContent", physicalName = "claimContent", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claimContent;

    @ElDtoField(logicalName = "claimType", physicalName = "claimType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claimType;

    @ElDtoField(logicalName = "deadline", physicalName = "deadline", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String deadline;

    @ElDtoField(logicalName = "empName", physicalName = "empName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empName;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "timeRemaining", physicalName = "timeRemaining", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String timeRemaining;

    @ElVoField(physicalName = "claimNo")
    public String getClaimNo(){
        String ret = this.claimNo;
        return ret;
    }

    @ElVoField(physicalName = "claimNo")
    public void setClaimNo(String claimNo){
        this.claimNo = claimNo;
    }

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        String ret = this.userName;
        return ret;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
    }

    @ElVoField(physicalName = "claimContent")
    public String getClaimContent(){
        String ret = this.claimContent;
        return ret;
    }

    @ElVoField(physicalName = "claimContent")
    public void setClaimContent(String claimContent){
        this.claimContent = claimContent;
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

    @ElVoField(physicalName = "deadline")
    public String getDeadline(){
        String ret = this.deadline;
        return ret;
    }

    @ElVoField(physicalName = "deadline")
    public void setDeadline(String deadline){
        this.deadline = deadline;
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

    @ElVoField(physicalName = "status")
    public String getStatus(){
        String ret = this.status;
        return ret;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "timeRemaining")
    public String getTimeRemaining(){
        String ret = this.timeRemaining;
        return ret;
    }

    @ElVoField(physicalName = "timeRemaining")
    public void setTimeRemaining(String timeRemaining){
        this.timeRemaining = timeRemaining;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UrgentClaimVo [");
        sb.append("claimNo").append("=").append(claimNo).append(",");
        sb.append("userName").append("=").append(userName).append(",");
        sb.append("claimContent").append("=").append(claimContent).append(",");
        sb.append("claimType").append("=").append(claimType).append(",");
        sb.append("deadline").append("=").append(deadline).append(",");
        sb.append("empName").append("=").append(empName).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("timeRemaining").append("=").append(timeRemaining);
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

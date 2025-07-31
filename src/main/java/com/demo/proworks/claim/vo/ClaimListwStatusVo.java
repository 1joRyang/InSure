package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "")
public class ClaimListwStatusVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimListwStatusVo(){
    }

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "user_name", physicalName = "user_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String user_name;

    @ElDtoField(logicalName = "receipt_date", physicalName = "receipt_date", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String receipt_date;

    @ElDtoField(logicalName = "deadline", physicalName = "deadline", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String deadline;

    @ElDtoField(logicalName = "emp_name", physicalName = "emp_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emp_name;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "ID", physicalName = "ID", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String ID;

    @ElDtoField(logicalName = "emp_no", physicalName = "emp_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emp_no;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
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

    @ElVoField(physicalName = "receipt_date")
    public String getReceipt_date(){
        String ret = this.receipt_date;
        return ret;
    }

    @ElVoField(physicalName = "receipt_date")
    public void setReceipt_date(String receipt_date){
        this.receipt_date = receipt_date;
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

    @ElVoField(physicalName = "emp_name")
    public String getEmp_name(){
        String ret = this.emp_name;
        return ret;
    }

    @ElVoField(physicalName = "emp_name")
    public void setEmp_name(String emp_name){
        this.emp_name = emp_name;
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

    @ElVoField(physicalName = "ID")
    public String getID(){
        String ret = this.ID;
        return ret;
    }

    @ElVoField(physicalName = "ID")
    public void setID(String ID){
        this.ID = ID;
    }

    @ElVoField(physicalName = "emp_no")
    public String getEmp_no(){
        String ret = this.emp_no;
        return ret;
    }

    @ElVoField(physicalName = "emp_no")
    public void setEmp_no(String emp_no){
        this.emp_no = emp_no;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimListwStatusVo [");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("user_name").append("=").append(user_name).append(",");
        sb.append("receipt_date").append("=").append(receipt_date).append(",");
        sb.append("deadline").append("=").append(deadline).append(",");
        sb.append("emp_name").append("=").append(emp_name).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("ID").append("=").append(ID).append(",");
        sb.append("emp_no").append("=").append(emp_no);
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

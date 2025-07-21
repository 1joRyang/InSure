package com.demo.proworks.subinsurance.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "보험상품조회정보")
public class SubInsuranceProductVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "insu_id", physicalName = "insu_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "보험ID")
    private String insu_id;

    @ElDtoField(logicalName = "insu_name", physicalName = "insu_name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "보험상품명")
    private String insu_name;

    @ElDtoField(logicalName = "user_name", physicalName = "user_name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자명")
    private String user_name;

    @ElDtoField(logicalName = "ID", physicalName = "ID", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자ID")
    private String ID;

    @ElDtoField(logicalName = "insu_amt", physicalName = "insu_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "보험금액")
    private String insu_amt;

    @ElDtoField(logicalName = "claim_amt", physicalName = "claim_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "청구금액")
    private String claim_amt;

    @ElDtoField(logicalName = "payment_amt", physicalName = "payment_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "지급금액")
    private String payment_amt;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "상태")
    private String status;

    @ElDtoField(logicalName = "start_date", physicalName = "start_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "시작일")
    private String start_date;

    @ElDtoField(logicalName = "end_date", physicalName = "end_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "종료일")
    private String end_date;

    @ElVoField(physicalName = "insu_id")
    public String getInsu_id(){
        return insu_id;
    }

    @ElVoField(physicalName = "insu_id")
    public void setInsu_id(String insu_id){
        this.insu_id = insu_id;
    }

    @ElVoField(physicalName = "insu_name")
    public String getInsu_name(){
        return insu_name;
    }

    @ElVoField(physicalName = "insu_name")
    public void setInsu_name(String insu_name){
        this.insu_name = insu_name;
    }

    @ElVoField(physicalName = "user_name")
    public String getUser_name(){
        return user_name;
    }

    @ElVoField(physicalName = "user_name")
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    @ElVoField(physicalName = "ID")
    public String getID(){
        return ID;
    }

    @ElVoField(physicalName = "ID")
    public void setID(String ID){
        this.ID = ID;
    }

    @ElVoField(physicalName = "insu_amt")
    public String getInsu_amt(){
        return insu_amt;
    }

    @ElVoField(physicalName = "insu_amt")
    public void setInsu_amt(String insu_amt){
        this.insu_amt = insu_amt;
    }

    @ElVoField(physicalName = "claim_amt")
    public String getClaim_amt(){
        return claim_amt;
    }

    @ElVoField(physicalName = "claim_amt")
    public void setClaim_amt(String claim_amt){
        this.claim_amt = claim_amt;
    }

    @ElVoField(physicalName = "payment_amt")
    public String getPayment_amt(){
        return payment_amt;
    }

    @ElVoField(physicalName = "payment_amt")
    public void setPayment_amt(String payment_amt){
        this.payment_amt = payment_amt;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        return status;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "start_date")
    public String getStart_date(){
        return start_date;
    }

    @ElVoField(physicalName = "start_date")
    public void setStart_date(String start_date){
        this.start_date = start_date;
    }

    @ElVoField(physicalName = "end_date")
    public String getEnd_date(){
        return end_date;
    }

    @ElVoField(physicalName = "end_date")
    public void setEnd_date(String end_date){
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "SubInsuranceProductVo [insu_id=" + insu_id + ",insu_name=" + insu_name + ",user_name=" + user_name + ",ID=" + ID + ",insu_amt=" + insu_amt + ",claim_amt=" + claim_amt + ",payment_amt=" + payment_amt + ",status=" + status + ",start_date=" + start_date + ",end_date=" + end_date + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

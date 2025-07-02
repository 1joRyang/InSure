package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "청구")
public class ClaimVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "claim_type", physicalName = "claim_type", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_type;

    @ElDtoField(logicalName = "receipt_date", physicalName = "receipt_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String receipt_date;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String status;

    @ElDtoField(logicalName = "emp_no", physicalName = "emp_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String emp_no;

    @ElDtoField(logicalName = "ID", physicalName = "ID", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String ID;

    @ElDtoField(logicalName = "claim_content", physicalName = "claim_content", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_content;

    @ElDtoField(logicalName = "disease_code", physicalName = "disease_code", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String disease_code;

    @ElDtoField(logicalName = "date_of_accident", physicalName = "date_of_accident", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String date_of_accident;

    @ElDtoField(logicalName = "search_receipt_date", physicalName = "SC_receipt_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_receipt_date;

    @ElDtoField(logicalName = "search_ID", physicalName = "SC_ID", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_ID;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "claim_type")
    public String getClaim_type(){
        return claim_type;
    }

    @ElVoField(physicalName = "claim_type")
    public void setClaim_type(String claim_type){
        this.claim_type = claim_type;
    }

    @ElVoField(physicalName = "receipt_date")
    public String getReceipt_date(){
        return receipt_date;
    }

    @ElVoField(physicalName = "receipt_date")
    public void setReceipt_date(String receipt_date){
        this.receipt_date = receipt_date;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        return status;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "emp_no")
    public String getEmp_no(){
        return emp_no;
    }

    @ElVoField(physicalName = "emp_no")
    public void setEmp_no(String emp_no){
        this.emp_no = emp_no;
    }

    @ElVoField(physicalName = "ID")
    public String getID(){
        return ID;
    }

    @ElVoField(physicalName = "ID")
    public void setID(String ID){
        this.ID = ID;
    }

    @ElVoField(physicalName = "claim_content")
    public String getClaim_content(){
        return claim_content;
    }

    @ElVoField(physicalName = "claim_content")
    public void setClaim_content(String claim_content){
        this.claim_content = claim_content;
    }

    @ElVoField(physicalName = "disease_code")
    public String getDisease_code(){
        return disease_code;
    }

    @ElVoField(physicalName = "disease_code")
    public void setDisease_code(String disease_code){
        this.disease_code = disease_code;
    }

    @ElVoField(physicalName = "date_of_accident")
    public String getDate_of_accident(){
        return date_of_accident;
    }

    @ElVoField(physicalName = "date_of_accident")
    public void setDate_of_accident(String date_of_accident){
        this.date_of_accident = date_of_accident;
    }

    @ElVoField(physicalName = "SC_receipt_date")
    public String getSC_receipt_date(){
        return SC_receipt_date;
    }

    @ElVoField(physicalName = "SC_receipt_date")
    public void setSC_receipt_date(String SC_receipt_date) {
        this.SC_receipt_date = SC_receipt_date;
    }

    @ElVoField(physicalName = "SC_ID")
    public String getSC_ID(){
        return SC_ID;
    }

    @ElVoField(physicalName = "SC_ID")
    public void setSC_ID(String SC_ID) {
        this.SC_ID = SC_ID;
    }

    @Override
    public String toString() {
        return "ClaimVo [claim_no=" + claim_no + ",claim_type=" + claim_type + ",receipt_date=" + receipt_date + ",status=" + status + ",emp_no=" + emp_no + ",ID=" + ID + ",claim_content=" + claim_content + ",disease_code=" + disease_code + ",date_of_accident=" + date_of_accident + ",SC_receipt_date=" + SC_receipt_date + ",SC_ID=" + SC_ID + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

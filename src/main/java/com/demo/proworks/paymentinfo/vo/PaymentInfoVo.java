package com.demo.proworks.paymentinfo.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "지불정보")
public class PaymentInfoVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "total_medical_fee", physicalName = "total_medical_fee", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String total_medical_fee;

    @ElDtoField(logicalName = "total_patient_payment", physicalName = "total_patient_payment", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String total_patient_payment;

    @ElDtoField(logicalName = "amount_paid", physicalName = "amount_paid", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String amount_paid;

    @ElDtoField(logicalName = "due_amt", physicalName = "due_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String due_amt;

    @ElDtoField(logicalName = "due_pay", physicalName = "due_pay", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String due_pay;

    @ElDtoField(logicalName = "treatment_id", physicalName = "treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String treatment_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "search_treatment_id", physicalName = "SC_treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_treatment_id;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_claim_no;

    @ElVoField(physicalName = "total_medical_fee")
    public String getTotal_medical_fee(){
        return total_medical_fee;
    }

    @ElVoField(physicalName = "total_medical_fee")
    public void setTotal_medical_fee(String total_medical_fee){
        this.total_medical_fee = total_medical_fee;
    }

    @ElVoField(physicalName = "total_patient_payment")
    public String getTotal_patient_payment(){
        return total_patient_payment;
    }

    @ElVoField(physicalName = "total_patient_payment")
    public void setTotal_patient_payment(String total_patient_payment){
        this.total_patient_payment = total_patient_payment;
    }

    @ElVoField(physicalName = "amount_paid")
    public String getAmount_paid(){
        return amount_paid;
    }

    @ElVoField(physicalName = "amount_paid")
    public void setAmount_paid(String amount_paid){
        this.amount_paid = amount_paid;
    }

    @ElVoField(physicalName = "due_amt")
    public String getDue_amt(){
        return due_amt;
    }

    @ElVoField(physicalName = "due_amt")
    public void setDue_amt(String due_amt){
        this.due_amt = due_amt;
    }

    @ElVoField(physicalName = "due_pay")
    public String getDue_pay(){
        return due_pay;
    }

    @ElVoField(physicalName = "due_pay")
    public void setDue_pay(String due_pay){
        this.due_pay = due_pay;
    }

    @ElVoField(physicalName = "treatment_id")
    public String getTreatment_id(){
        return treatment_id;
    }

    @ElVoField(physicalName = "treatment_id")
    public void setTreatment_id(String treatment_id){
        this.treatment_id = treatment_id;
    }

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "SC_treatment_id")
    public String getSC_treatment_id(){
        return SC_treatment_id;
    }

    @ElVoField(physicalName = "SC_treatment_id")
    public void setSC_treatment_id(String SC_treatment_id) {
        this.SC_treatment_id = SC_treatment_id;
    }

    @ElVoField(physicalName = "SC_claim_no")
    public String getSC_claim_no(){
        return SC_claim_no;
    }

    @ElVoField(physicalName = "SC_claim_no")
    public void setSC_claim_no(String SC_claim_no) {
        this.SC_claim_no = SC_claim_no;
    }

    @Override
    public String toString() {
        return "PaymentInfoVo [total_medical_fee=" + total_medical_fee + ",total_patient_payment=" + total_patient_payment + ",amount_paid=" + amount_paid + ",due_amt=" + due_amt + ",due_pay=" + due_pay + ",treatment_id=" + treatment_id + ",claim_no=" + claim_no + ",SC_treatment_id=" + SC_treatment_id + ",SC_claim_no=" + SC_claim_no + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

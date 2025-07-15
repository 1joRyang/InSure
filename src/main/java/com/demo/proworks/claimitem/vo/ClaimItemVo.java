package com.demo.proworks.claimitem.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "청구 아이템들")
public class ClaimItemVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "item_id", physicalName = "item_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String item_id;

    @ElDtoField(logicalName = "treatment_id", physicalName = "treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String treatment_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "item_name", physicalName = "item_name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String item_name;

    @ElDtoField(logicalName = "patient_amt", physicalName = "patient_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String patient_amt;

    @ElDtoField(logicalName = "insur_amt", physicalName = "insur_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String insur_amt;

    @ElDtoField(logicalName = "full_amt", physicalName = "full_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String full_amt;

    @ElDtoField(logicalName = "select_fee", physicalName = "select_fee", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String select_fee;

    @ElDtoField(logicalName = "select_disc", physicalName = "select_disc", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String select_disc;

    @ElDtoField(logicalName = "search_treatment_id", physicalName = "SC_treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_treatment_id;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_claim_no;

    @ElVoField(physicalName = "item_id")
    public String getItem_id(){
        return item_id;
    }

    @ElVoField(physicalName = "item_id")
    public void setItem_id(String item_id){
        this.item_id = item_id;
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

    @ElVoField(physicalName = "item_name")
    public String getItem_name(){
        return item_name;
    }

    @ElVoField(physicalName = "item_name")
    public void setItem_name(String item_name){
        this.item_name = item_name;
    }

    @ElVoField(physicalName = "patient_amt")
    public String getPatient_amt(){
        return patient_amt;
    }

    @ElVoField(physicalName = "patient_amt")
    public void setPatient_amt(String patient_amt){
        this.patient_amt = patient_amt;
    }

    @ElVoField(physicalName = "insur_amt")
    public String getInsur_amt(){
        return insur_amt;
    }

    @ElVoField(physicalName = "insur_amt")
    public void setInsur_amt(String insur_amt){
        this.insur_amt = insur_amt;
    }

    @ElVoField(physicalName = "full_amt")
    public String getFull_amt(){
        return full_amt;
    }

    @ElVoField(physicalName = "full_amt")
    public void setFull_amt(String full_amt){
        this.full_amt = full_amt;
    }

    @ElVoField(physicalName = "select_fee")
    public String getSelect_fee(){
        return select_fee;
    }

    @ElVoField(physicalName = "select_fee")
    public void setSelect_fee(String select_fee){
        this.select_fee = select_fee;
    }

    @ElVoField(physicalName = "select_disc")
    public String getSelect_disc(){
        return select_disc;
    }

    @ElVoField(physicalName = "select_disc")
    public void setSelect_disc(String select_disc){
        this.select_disc = select_disc;
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
        return "ClaimItemVo [item_id=" + item_id + ",treatment_id=" + treatment_id + ",claim_no=" + claim_no + ",item_name=" + item_name + ",patient_amt=" + patient_amt + ",insur_amt=" + insur_amt + ",full_amt=" + full_amt + ",select_fee=" + select_fee + ",select_disc=" + select_disc + ",SC_treatment_id=" + SC_treatment_id + ",SC_claim_no=" + SC_claim_no + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

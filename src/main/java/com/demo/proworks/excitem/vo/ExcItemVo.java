package com.demo.proworks.excitem.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "제외항목")
public class ExcItemVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "exc_id", physicalName = "exc_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String exc_id;

    @ElDtoField(logicalName = "item_id", physicalName = "item_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String item_id;

    @ElDtoField(logicalName = "treatment_id", physicalName = "treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String treatment_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "exc_reason", physicalName = "exc_reason", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String exc_reason;

    @ElDtoField(logicalName = "exc_cost", physicalName = "exc_cost", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String exc_cost;

    @ElDtoField(logicalName = "exc_category", physicalName = "exc_category", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String exc_category;

    @ElVoField(physicalName = "exc_id")
    public String getExc_id(){
        return exc_id;
    }

    @ElVoField(physicalName = "exc_id")
    public void setExc_id(String exc_id){
        this.exc_id = exc_id;
    }

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

    @ElVoField(physicalName = "exc_reason")
    public String getExc_reason(){
        return exc_reason;
    }

    @ElVoField(physicalName = "exc_reason")
    public void setExc_reason(String exc_reason){
        this.exc_reason = exc_reason;
    }

    @ElVoField(physicalName = "exc_cost")
    public String getExc_cost(){
        return exc_cost;
    }

    @ElVoField(physicalName = "exc_cost")
    public void setExc_cost(String exc_cost){
        this.exc_cost = exc_cost;
    }

    @ElVoField(physicalName = "exc_category")
    public String getExc_category(){
        return exc_category;
    }

    @ElVoField(physicalName = "exc_category")
    public void setExc_category(String exc_category){
        this.exc_category = exc_category;
    }

    @Override
    public String toString() {
        return "ExcItemVo [exc_id=" + exc_id + ",item_id=" + item_id + ",treatment_id=" + treatment_id + ",claim_no=" + claim_no + ",exc_reason=" + exc_reason + ",exc_cost=" + exc_cost + ",exc_category=" + exc_category + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

package com.demo.proworks.insurance.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "보험")
public class InsuranceVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "insu_id", physicalName = "insu_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String insu_id;

    @ElDtoField(logicalName = "insu_name", physicalName = "insu_name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String insu_name;

    @ElDtoField(logicalName = "insu_terms", physicalName = "insu_terms", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String insu_terms;

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

    @ElVoField(physicalName = "insu_terms")
    public String getInsu_terms(){
        return insu_terms;
    }

    @ElVoField(physicalName = "insu_terms")
    public void setInsu_terms(String insu_terms){
        this.insu_terms = insu_terms;
    }

    @Override
    public String toString() {
        return "InsuranceVo [insu_id=" + insu_id + ",insu_name=" + insu_name + ",insu_terms=" + insu_terms + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

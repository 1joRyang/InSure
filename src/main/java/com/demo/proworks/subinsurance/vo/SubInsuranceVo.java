package com.demo.proworks.subinsurance.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "가입보험정보")
public class SubInsuranceVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "insu_id", physicalName = "insu_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String insu_id;

    @ElDtoField(logicalName = "ID", physicalName = "ID", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String ID;

    @ElDtoField(logicalName = "start_date", physicalName = "start_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String start_date;

    @ElDtoField(logicalName = "end_date", physicalName = "end_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String end_date;

    @ElDtoField(logicalName = "insu_amt", physicalName = "insu_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String insu_amt;

    @ElVoField(physicalName = "insu_id")
    public String getInsu_id(){
        return insu_id;
    }

    @ElVoField(physicalName = "insu_id")
    public void setInsu_id(String insu_id){
        this.insu_id = insu_id;
    }

    @ElVoField(physicalName = "ID")
    public String getID(){
        return ID;
    }

    @ElVoField(physicalName = "ID")
    public void setID(String ID){
        this.ID = ID;
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

    @ElVoField(physicalName = "insu_amt")
    public String getInsu_amt(){
        return insu_amt;
    }

    @ElVoField(physicalName = "insu_amt")
    public void setInsu_amt(String insu_amt){
        this.insu_amt = insu_amt;
    }

    @Override
    public String toString() {
        return "SubInsuranceVo [insu_id=" + insu_id + ",ID=" + ID + ",start_date=" + start_date + ",end_date=" + end_date + ",insu_amt=" + insu_amt + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

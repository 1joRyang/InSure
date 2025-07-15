package com.demo.proworks.treatment.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "진료정보")
public class TreatmentVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "treatment_id", physicalName = "treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String treatment_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "department", physicalName = "department", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String department;

    @ElDtoField(logicalName = "treatment_date", physicalName = "treatment_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String treatment_date;

    @ElDtoField(logicalName = "patient_type", physicalName = "patient_type", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String patient_type;

    @ElDtoField(logicalName = "hospital_name", physicalName = "hospital_name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String hospital_name;

    @ElDtoField(logicalName = "hospital_grade", physicalName = "hospital_grade", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String hospital_grade;

    @ElDtoField(logicalName = "search_treatment_id", physicalName = "SC_treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_treatment_id;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_claim_no;

    @ElDtoField(logicalName = "search_treatment_date", physicalName = "SC_treatment_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_treatment_date;

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

    @ElVoField(physicalName = "department")
    public String getDepartment(){
        return department;
    }

    @ElVoField(physicalName = "department")
    public void setDepartment(String department){
        this.department = department;
    }

    @ElVoField(physicalName = "treatment_date")
    public String getTreatment_date(){
        return treatment_date;
    }

    @ElVoField(physicalName = "treatment_date")
    public void setTreatment_date(String treatment_date){
        this.treatment_date = treatment_date;
    }

    @ElVoField(physicalName = "patient_type")
    public String getPatient_type(){
        return patient_type;
    }

    @ElVoField(physicalName = "patient_type")
    public void setPatient_type(String patient_type){
        this.patient_type = patient_type;
    }

    @ElVoField(physicalName = "hospital_name")
    public String getHospital_name(){
        return hospital_name;
    }

    @ElVoField(physicalName = "hospital_name")
    public void setHospital_name(String hospital_name){
        this.hospital_name = hospital_name;
    }

    @ElVoField(physicalName = "hospital_grade")
    public String getHospital_grade(){
        return hospital_grade;
    }

    @ElVoField(physicalName = "hospital_grade")
    public void setHospital_grade(String hospital_grade){
        this.hospital_grade = hospital_grade;
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

    @ElVoField(physicalName = "SC_treatment_date")
    public String getSC_treatment_date(){
        return SC_treatment_date;
    }

    @ElVoField(physicalName = "SC_treatment_date")
    public void setSC_treatment_date(String SC_treatment_date) {
        this.SC_treatment_date = SC_treatment_date;
    }

    @Override
    public String toString() {
        return "TreatmentVo [treatment_id=" + treatment_id + ",claim_no=" + claim_no + ",department=" + department + ",treatment_date=" + treatment_date + ",patient_type=" + patient_type + ",hospital_name=" + hospital_name + ",hospital_grade=" + hospital_grade + ",SC_treatment_id=" + SC_treatment_id + ",SC_claim_no=" + SC_claim_no + ",SC_treatment_date=" + SC_treatment_date + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

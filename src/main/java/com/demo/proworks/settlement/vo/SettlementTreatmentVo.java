package com.demo.proworks.settlement.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "정산치료정보")
public class SettlementTreatmentVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SettlementTreatmentVo(){
    }

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "treatment_id", physicalName = "treatment_id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String treatment_id;

    @ElDtoField(logicalName = "final_due", physicalName = "final_due", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String final_due;

    @ElDtoField(logicalName = "refund_amt", physicalName = "refund_amt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String refund_amt;

    @ElDtoField(logicalName = "treatment_date", physicalName = "treatment_date", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String treatment_date;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_claim_no;

    @ElDtoField(logicalName = "hospital_prepaid", physicalName = "hospital_prepaid", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String hospital_prepaid;

    @ElDtoField(logicalName = "deducation_amt", physicalName = "deducation_amt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String deducation_amt;

    @ElDtoField(logicalName = "hospital_name", physicalName = "hospital_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String hospital_name;

    @ElDtoField(logicalName = "hospital_grade", physicalName = "hospital_grade", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String hospital_grade;

    @ElDtoField(logicalName = "patient_type", physicalName = "patient_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String patient_type;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "treatment_id")
    public String getTreatment_id(){
        String ret = this.treatment_id;
        return ret;
    }

    @ElVoField(physicalName = "treatment_id")
    public void setTreatment_id(String treatment_id){
        this.treatment_id = treatment_id;
    }

    @ElVoField(physicalName = "final_due")
    public String getFinal_due(){
        String ret = this.final_due;
        return ret;
    }

    @ElVoField(physicalName = "final_due")
    public void setFinal_due(String final_due){
        this.final_due = final_due;
    }

    @ElVoField(physicalName = "refund_amt")
    public String getRefund_amt(){
        String ret = this.refund_amt;
        return ret;
    }

    @ElVoField(physicalName = "refund_amt")
    public void setRefund_amt(String refund_amt){
        this.refund_amt = refund_amt;
    }

    @ElVoField(physicalName = "treatment_date")
    public String getTreatment_date(){
        String ret = this.treatment_date;
        return ret;
    }

    @ElVoField(physicalName = "treatment_date")
    public void setTreatment_date(String treatment_date){
        this.treatment_date = treatment_date;
    }

    @ElVoField(physicalName = "SC_claim_no")
    public String getSC_claim_no(){
        String ret = this.SC_claim_no;
        return ret;
    }

    @ElVoField(physicalName = "SC_claim_no")
    public void setSC_claim_no(String SC_claim_no){
        this.SC_claim_no = SC_claim_no;
    }

    @ElVoField(physicalName = "hospital_prepaid")
    public String getHospital_prepaid(){
        String ret = this.hospital_prepaid;
        return ret;
    }

    @ElVoField(physicalName = "hospital_prepaid")
    public void setHospital_prepaid(String hospital_prepaid){
        this.hospital_prepaid = hospital_prepaid;
    }

    @ElVoField(physicalName = "deducation_amt")
    public String getDeducation_amt(){
        String ret = this.deducation_amt;
        return ret;
    }

    @ElVoField(physicalName = "deducation_amt")
    public void setDeducation_amt(String deducation_amt){
        this.deducation_amt = deducation_amt;
    }

    @ElVoField(physicalName = "hospital_name")
    public String getHospital_name(){
        String ret = this.hospital_name;
        return ret;
    }

    @ElVoField(physicalName = "hospital_name")
    public void setHospital_name(String hospital_name){
        this.hospital_name = hospital_name;
    }

    @ElVoField(physicalName = "hospital_grade")
    public String getHospital_grade(){
        String ret = this.hospital_grade;
        return ret;
    }

    @ElVoField(physicalName = "hospital_grade")
    public void setHospital_grade(String hospital_grade){
        this.hospital_grade = hospital_grade;
    }

    @ElVoField(physicalName = "patient_type")
    public String getPatient_type(){
        String ret = this.patient_type;
        return ret;
    }

    @ElVoField(physicalName = "patient_type")
    public void setPatient_type(String patient_type){
        this.patient_type = patient_type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SettlementTreatmentVo [");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("treatment_id").append("=").append(treatment_id).append(",");
        sb.append("final_due").append("=").append(final_due).append(",");
        sb.append("refund_amt").append("=").append(refund_amt).append(",");
        sb.append("treatment_date").append("=").append(treatment_date).append(",");
        sb.append("SC_claim_no").append("=").append(SC_claim_no).append(",");
        sb.append("hospital_prepaid").append("=").append(hospital_prepaid).append(",");
        sb.append("deducation_amt").append("=").append(deducation_amt).append(",");
        sb.append("hospital_name").append("=").append(hospital_name).append(",");
        sb.append("hospital_grade").append("=").append(hospital_grade).append(",");
        sb.append("patient_type").append("=").append(patient_type);
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

package com.demo.proworks.settlement.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "정산정보")
public class SettlementVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "final_due", physicalName = "final_due", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String final_due;

    @ElDtoField(logicalName = "hospital_prepaid", physicalName = "hospital_prepaid", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String hospital_prepaid;

    @ElDtoField(logicalName = "deducation_amt", physicalName = "deducation_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String deducation_amt;

    @ElDtoField(logicalName = "refund_amt", physicalName = "refund_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String refund_amt;

    @ElDtoField(logicalName = "treatment_id", physicalName = "treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String treatment_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "search_treatment_id", physicalName = "SC_treatment_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_treatment_id;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_claim_no;

    @ElVoField(physicalName = "final_due")
    public String getFinal_due(){
        return final_due;
    }

    @ElVoField(physicalName = "final_due")
    public void setFinal_due(String final_due){
        this.final_due = final_due;
    }

    @ElVoField(physicalName = "hospital_prepaid")
    public String getHospital_prepaid(){
        return hospital_prepaid;
    }

    @ElVoField(physicalName = "hospital_prepaid")
    public void setHospital_prepaid(String hospital_prepaid){
        this.hospital_prepaid = hospital_prepaid;
    }

    @ElVoField(physicalName = "deducation_amt")
    public String getDeducation_amt(){
        return deducation_amt;
    }

    @ElVoField(physicalName = "deducation_amt")
    public void setDeducation_amt(String deducation_amt){
        this.deducation_amt = deducation_amt;
    }

    @ElVoField(physicalName = "refund_amt")
    public String getRefund_amt(){
        return refund_amt;
    }

    @ElVoField(physicalName = "refund_amt")
    public void setRefund_amt(String refund_amt){
        this.refund_amt = refund_amt;
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
        return "SettlementVo [final_due=" + final_due + ",hospital_prepaid=" + hospital_prepaid + ",deducation_amt=" + deducation_amt + ",refund_amt=" + refund_amt + ",treatment_id=" + treatment_id + ",claim_no=" + claim_no + ",SC_treatment_id=" + SC_treatment_id + ",SC_claim_no=" + SC_claim_no + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

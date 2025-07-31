package com.demo.proworks.settlement.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "정산집계정보")
public class SettlementSummaryVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "total_final_due", physicalName = "total_final_due", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String total_final_due;

    @ElDtoField(logicalName = "total_refund_amt", physicalName = "total_refund_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String total_refund_amt;

    @ElDtoField(logicalName = "total_hp_prepaid", physicalName = "total_hp_prepaid", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String total_hp_prepaid;

    @ElDtoField(logicalName = "total_deducation_amt", physicalName = "total_deducation_amt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String total_deducation_amt;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_claim_no;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "total_final_due")
    public String getTotal_final_due(){
        return total_final_due;
    }

    @ElVoField(physicalName = "total_final_due")
    public void setTotal_final_due(String total_final_due){
        this.total_final_due = total_final_due;
    }

    @ElVoField(physicalName = "total_refund_amt")
    public String getTotal_refund_amt(){
        return total_refund_amt;
    }

    @ElVoField(physicalName = "total_refund_amt")
    public void setTotal_refund_amt(String total_refund_amt){
        this.total_refund_amt = total_refund_amt;
    }

    @ElVoField(physicalName = "total_hp_prepaid")
    public String getTotal_hp_prepaid(){
        return total_hp_prepaid;
    }

    @ElVoField(physicalName = "total_hp_prepaid")
    public void setTotal_hp_prepaid(String total_hp_prepaid){
        this.total_hp_prepaid = total_hp_prepaid;
    }

    @ElVoField(physicalName = "total_deducation_amt")
    public String getTotal_deducation_amt(){
        return total_deducation_amt;
    }

    @ElVoField(physicalName = "total_deducation_amt")
    public void setTotal_deducation_amt(String total_deducation_amt){
        this.total_deducation_amt = total_deducation_amt;
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
        return "SettlementSummaryVo [claim_no=" + claim_no + 
               ", total_final_due=" + total_final_due + 
               ", total_refund_amt=" + total_refund_amt + 
               ", total_hp_prepaid=" + total_hp_prepaid + 
               ", total_deducation_amt=" + total_deducation_amt + 
               ", SC_claim_no=" + SC_claim_no + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }
}

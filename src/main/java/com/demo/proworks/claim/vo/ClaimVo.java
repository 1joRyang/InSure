package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "청구")
public class ClaimVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimVo(){
    }

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "claim_type", physicalName = "claim_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_type;

    @ElDtoField(logicalName = "receipt_date", physicalName = "receipt_date", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String receipt_date;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "emp_no", physicalName = "emp_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emp_no;

    @ElDtoField(logicalName = "ID", physicalName = "ID", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String ID;

    @ElDtoField(logicalName = "claim_content", physicalName = "claim_content", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_content;

    @ElDtoField(logicalName = "disease_code", physicalName = "disease_code", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String disease_code;

    @ElDtoField(logicalName = "date_of_accident", physicalName = "date_of_accident", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String date_of_accident;

    @ElDtoField(logicalName = "search_receipt_date", physicalName = "SC_receipt_date", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_receipt_date;

    @ElDtoField(logicalName = "search_ID", physicalName = "SC_ID", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_ID;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "claim_type")
    public String getClaim_type(){
        String ret = this.claim_type;
        return ret;
    }

    @ElVoField(physicalName = "claim_type")
    public void setClaim_type(String claim_type){
        this.claim_type = claim_type;
    }

    @ElVoField(physicalName = "receipt_date")
    public String getReceipt_date(){
        String ret = this.receipt_date;
        return ret;
    }

    @ElVoField(physicalName = "receipt_date")
    public void setReceipt_date(String receipt_date){
        this.receipt_date = receipt_date;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        String ret = this.status;
        return ret;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "emp_no")
    public String getEmp_no(){
        String ret = this.emp_no;
        return ret;
    }

    @ElVoField(physicalName = "emp_no")
    public void setEmp_no(String emp_no){
        this.emp_no = emp_no;
    }

    @ElVoField(physicalName = "ID")
    public String getID(){
        String ret = this.ID;
        return ret;
    }

    @ElVoField(physicalName = "ID")
    public void setID(String ID){
        this.ID = ID;
    }

    @ElVoField(physicalName = "claim_content")
    public String getClaim_content(){
        String ret = this.claim_content;
        return ret;
    }

    @ElVoField(physicalName = "claim_content")
    public void setClaim_content(String claim_content){
        this.claim_content = claim_content;
    }

    @ElVoField(physicalName = "disease_code")
    public String getDisease_code(){
        String ret = this.disease_code;
        return ret;
    }

    @ElVoField(physicalName = "disease_code")
    public void setDisease_code(String disease_code){
        this.disease_code = disease_code;
    }

    @ElVoField(physicalName = "date_of_accident")
    public String getDate_of_accident(){
        String ret = this.date_of_accident;
        return ret;
    }

    @ElVoField(physicalName = "date_of_accident")
    public void setDate_of_accident(String date_of_accident){
        this.date_of_accident = date_of_accident;
    }

    @ElVoField(physicalName = "SC_receipt_date")
    public String getSC_receipt_date(){
        String ret = this.SC_receipt_date;
        return ret;
    }

    @ElVoField(physicalName = "SC_receipt_date")
    public void setSC_receipt_date(String SC_receipt_date){
        this.SC_receipt_date = SC_receipt_date;
    }

    @ElVoField(physicalName = "SC_ID")
    public String getSC_ID(){
        String ret = this.SC_ID;
        return ret;
    }

    @ElVoField(physicalName = "SC_ID")
    public void setSC_ID(String SC_ID){
        this.SC_ID = SC_ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimVo [");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("claim_type").append("=").append(claim_type).append(",");
        sb.append("receipt_date").append("=").append(receipt_date).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("emp_no").append("=").append(emp_no).append(",");
        sb.append("ID").append("=").append(ID).append(",");
        sb.append("claim_content").append("=").append(claim_content).append(",");
        sb.append("disease_code").append("=").append(disease_code).append(",");
        sb.append("date_of_accident").append("=").append(date_of_accident).append(",");
        sb.append("SC_receipt_date").append("=").append(SC_receipt_date).append(",");
        sb.append("SC_ID").append("=").append(SC_ID);
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

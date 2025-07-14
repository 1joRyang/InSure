package com.demo.proworks.approvalreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "결재요청")
public class ApprovalReqVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApprovalReqVo(){
    }

    @ElDtoField(logicalName = "approval_id", physicalName = "approval_id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approval_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "emp_no", physicalName = "emp_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emp_no;

    @ElDtoField(logicalName = "manager_no", physicalName = "manager_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String manager_no;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "approval_memo", physicalName = "approval_memo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String approval_memo;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_claim_no;

    @ElDtoField(logicalName = "search_emp_no", physicalName = "SC_emp_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_emp_no;

    @ElDtoField(logicalName = "search_manager_no", physicalName = "SC_manager_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_manager_no;

    @ElDtoField(logicalName = "search_status", physicalName = "SC_status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String SC_status;

    @ElVoField(physicalName = "approval_id")
    public String getApproval_id(){
        String ret = this.approval_id;
        return ret;
    }

    @ElVoField(physicalName = "approval_id")
    public void setApproval_id(String approval_id){
        this.approval_id = approval_id;
    }

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
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

    @ElVoField(physicalName = "manager_no")
    public String getManager_no(){
        String ret = this.manager_no;
        return ret;
    }

    @ElVoField(physicalName = "manager_no")
    public void setManager_no(String manager_no){
        this.manager_no = manager_no;
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

    @ElVoField(physicalName = "approval_memo")
    public String getApproval_memo(){
        String ret = this.approval_memo;
        return ret;
    }

    @ElVoField(physicalName = "approval_memo")
    public void setApproval_memo(String approval_memo){
        this.approval_memo = approval_memo;
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

    @ElVoField(physicalName = "SC_emp_no")
    public String getSC_emp_no(){
        String ret = this.SC_emp_no;
        return ret;
    }

    @ElVoField(physicalName = "SC_emp_no")
    public void setSC_emp_no(String SC_emp_no){
        this.SC_emp_no = SC_emp_no;
    }

    @ElVoField(physicalName = "SC_manager_no")
    public String getSC_manager_no(){
        String ret = this.SC_manager_no;
        return ret;
    }

    @ElVoField(physicalName = "SC_manager_no")
    public void setSC_manager_no(String SC_manager_no){
        this.SC_manager_no = SC_manager_no;
    }

    @ElVoField(physicalName = "SC_status")
    public String getSC_status(){
        String ret = this.SC_status;
        return ret;
    }

    @ElVoField(physicalName = "SC_status")
    public void setSC_status(String SC_status){
        this.SC_status = SC_status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApprovalReqVo [");
        sb.append("approval_id").append("=").append(approval_id).append(",");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("emp_no").append("=").append(emp_no).append(",");
        sb.append("manager_no").append("=").append(manager_no).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("approval_memo").append("=").append(approval_memo).append(",");
        sb.append("SC_claim_no").append("=").append(SC_claim_no).append(",");
        sb.append("SC_emp_no").append("=").append(SC_emp_no).append(",");
        sb.append("SC_manager_no").append("=").append(SC_manager_no).append(",");
        sb.append("SC_status").append("=").append(SC_status);
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

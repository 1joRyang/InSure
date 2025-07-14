package com.demo.proworks.approvalreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "결재요청")
public class ApprovalReqVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "approval_id", physicalName = "approval_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String approval_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "emp_no", physicalName = "emp_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String emp_no;

    @ElDtoField(logicalName = "manager_no", physicalName = "manager_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String manager_no;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String status;

    @ElDtoField(logicalName = "req_date", physicalName = "req_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String req_date;

    @ElDtoField(logicalName = "process_date", physicalName = "process_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String process_date;

    @ElDtoField(logicalName = "approval_memo", physicalName = "approval_memo", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String approval_memo;

    @ElDtoField(logicalName = "search_claim_no", physicalName = "SC_claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_claim_no;

    @ElDtoField(logicalName = "search_emp_no", physicalName = "SC_emp_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_emp_no;

    @ElDtoField(logicalName = "search_manager_no", physicalName = "SC_manager_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_manager_no;

    @ElDtoField(logicalName = "search_status", physicalName = "SC_status", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String SC_status;

    @ElVoField(physicalName = "approval_id")
    public String getApproval_id(){
        return approval_id;
    }

    @ElVoField(physicalName = "approval_id")
    public void setApproval_id(String approval_id){
        this.approval_id = approval_id;
    }

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "emp_no")
    public String getEmp_no(){
        return emp_no;
    }

    @ElVoField(physicalName = "emp_no")
    public void setEmp_no(String emp_no){
        this.emp_no = emp_no;
    }

    @ElVoField(physicalName = "manager_no")
    public String getManager_no(){
        return manager_no;
    }

    @ElVoField(physicalName = "manager_no")
    public void setManager_no(String manager_no){
        this.manager_no = manager_no;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        return status;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "req_date")
    public String getReq_date(){
        return req_date;
    }

    @ElVoField(physicalName = "req_date")
    public void setReq_date(String req_date){
        this.req_date = req_date;
    }

    @ElVoField(physicalName = "process_date")
    public String getProcess_date(){
        return process_date;
    }

    @ElVoField(physicalName = "process_date")
    public void setProcess_date(String process_date){
        this.process_date = process_date;
    }

    @ElVoField(physicalName = "approval_memo")
    public String getApproval_memo(){
        return approval_memo;
    }

    @ElVoField(physicalName = "approval_memo")
    public void setApproval_memo(String approval_memo){
        this.approval_memo = approval_memo;
    }

    @ElVoField(physicalName = "SC_claim_no")
    public String getSC_claim_no(){
        return SC_claim_no;
    }

    @ElVoField(physicalName = "SC_claim_no")
    public void setSC_claim_no(String SC_claim_no) {
        this.SC_claim_no = SC_claim_no;
    }

    @ElVoField(physicalName = "SC_emp_no")
    public String getSC_emp_no(){
        return SC_emp_no;
    }

    @ElVoField(physicalName = "SC_emp_no")
    public void setSC_emp_no(String SC_emp_no) {
        this.SC_emp_no = SC_emp_no;
    }

    @ElVoField(physicalName = "SC_manager_no")
    public String getSC_manager_no(){
        return SC_manager_no;
    }

    @ElVoField(physicalName = "SC_manager_no")
    public void setSC_manager_no(String SC_manager_no) {
        this.SC_manager_no = SC_manager_no;
    }

    @ElVoField(physicalName = "SC_status")
    public String getSC_status(){
        return SC_status;
    }

    @ElVoField(physicalName = "SC_status")
    public void setSC_status(String SC_status) {
        this.SC_status = SC_status;
    }

    @Override
    public String toString() {
        return "ApprovalReqVo [approval_id=" + approval_id + ",claim_no=" + claim_no + ",emp_no=" + emp_no + ",manager_no=" + manager_no + ",status=" + status + ",req_date=" + req_date + ",process_date=" + process_date + ",approval_memo=" + approval_memo + ",SC_claim_no=" + SC_claim_no + ",SC_emp_no=" + SC_emp_no + ",SC_manager_no=" + SC_manager_no + ",SC_status=" + SC_status + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

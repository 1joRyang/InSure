package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "청구-사용자-직원-결과 조인 VO")
public class ClaimFullJoinVo extends com.demo.proworks.cmmn.ProworksCommVO {

    private static final long serialVersionUID = 1L;

    public ClaimFullJoinVo(){
    }

    // CLAIM 테이블 필드들
    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "청구번호", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "claim_type", physicalName = "claim_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "청구유형", attr = "")
    private String claim_type;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "상태", attr = "")
    private String status;

    // USER 테이블 필드들
    @ElDtoField(logicalName = "user_name", physicalName = "user_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자명", attr = "")
    private String user_name;

    // EMPLOYEE 테이블 필드들
    @ElDtoField(logicalName = "emp_name", physicalName = "emp_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "직원명", attr = "")
    private String emp_name;

    // CLAIM_RESULT 테이블 필드들
    @ElDtoField(logicalName = "amount", physicalName = "amount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "지급금액", attr = "")
    private String amount;

    // 검색 조건용 필드들
    @ElDtoField(logicalName = "SC_claim_type", physicalName = "SC_claim_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "검색_청구유형", attr = "")
    private String SC_claim_type;

    @ElDtoField(logicalName = "SC_status", physicalName = "SC_status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "검색_상태", attr = "")
    private String SC_status;

    @ElDtoField(logicalName = "SC_emp_name", physicalName = "SC_emp_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "검색_직원명", attr = "")
    private String SC_emp_name;

    @ElDtoField(logicalName = "SC_user_name", physicalName = "SC_user_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "검색_사용자명", attr = "")
    private String SC_user_name;

    // getter/setter methods
    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return this.claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "claim_type")
    public String getClaim_type(){
        return this.claim_type;
    }

    @ElVoField(physicalName = "claim_type")
    public void setClaim_type(String claim_type){
        this.claim_type = claim_type;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        return this.status;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "user_name")
    public String getUser_name(){
        return this.user_name;
    }

    @ElVoField(physicalName = "user_name")
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    @ElVoField(physicalName = "emp_name")
    public String getEmp_name(){
        return this.emp_name;
    }

    @ElVoField(physicalName = "emp_name")
    public void setEmp_name(String emp_name){
        this.emp_name = emp_name;
    }

    @ElVoField(physicalName = "amount")
    public String getAmount(){
        return this.amount;
    }

    @ElVoField(physicalName = "amount")
    public void setAmount(String amount){
        this.amount = amount;
    }

    @ElVoField(physicalName = "SC_claim_type")
    public String getSC_claim_type(){
        return this.SC_claim_type;
    }

    @ElVoField(physicalName = "SC_claim_type")
    public void setSC_claim_type(String SC_claim_type){
        this.SC_claim_type = SC_claim_type;
    }

    @ElVoField(physicalName = "SC_status")
    public String getSC_status(){
        return this.SC_status;
    }

    @ElVoField(physicalName = "SC_status")
    public void setSC_status(String SC_status){
        this.SC_status = SC_status;
    }

    @ElVoField(physicalName = "SC_emp_name")
    public String getSC_emp_name(){
        return this.SC_emp_name;
    }

    @ElVoField(physicalName = "SC_emp_name")
    public void setSC_emp_name(String SC_emp_name){
        this.SC_emp_name = SC_emp_name;
    }

    @ElVoField(physicalName = "SC_user_name")
    public String getSC_user_name(){
        return this.SC_user_name;
    }

    @ElVoField(physicalName = "SC_user_name")
    public void setSC_user_name(String SC_user_name){
        this.SC_user_name = SC_user_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimFullJoinVo [");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("claim_type").append("=").append(claim_type).append(",");
        sb.append("user_name").append("=").append(user_name).append(",");
        sb.append("amount").append("=").append(amount).append(",");
        sb.append("emp_name").append("=").append(emp_name).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("SC_claim_type").append("=").append(SC_claim_type).append(",");
        sb.append("SC_status").append("=").append(SC_status).append(",");
        sb.append("SC_emp_name").append("=").append(SC_emp_name).append(",");
        sb.append("SC_user_name").append("=").append(SC_user_name);
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

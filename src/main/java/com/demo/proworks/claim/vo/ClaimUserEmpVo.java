package com.demo.proworks.claim.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "")
public class ClaimUserEmpVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimUserEmpVo(){
    }

    @ElDtoField(logicalName = "emp_name", physicalName = "emp_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emp_name;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "user_name", physicalName = "user_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String user_name;

    @ElVoField(physicalName = "emp_name")
    public String getEmp_name(){
        String ret = this.emp_name;
        return ret;
    }

    @ElVoField(physicalName = "emp_name")
    public void setEmp_name(String emp_name){
        this.emp_name = emp_name;
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

    @ElVoField(physicalName = "user_name")
    public String getUser_name(){
        String ret = this.user_name;
        return ret;
    }

    @ElVoField(physicalName = "user_name")
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimUserEmpVo [");
        sb.append("emp_name").append("=").append(emp_name).append(",");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("user_name").append("=").append(user_name);
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

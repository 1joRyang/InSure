package com.demo.proworks.dashboard.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "부서정보")
public class UrgentCountVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UrgentCountVo(){
    }

    @ElDtoField(logicalName = "empNo", physicalName = "empNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empNo;

    @ElDtoField(logicalName = "role", physicalName = "role", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String role;

    @ElVoField(physicalName = "empNo")
    public String getEmpNo(){
        String ret = this.empNo;
        return ret;
    }

    @ElVoField(physicalName = "empNo")
    public void setEmpNo(String empNo){
        this.empNo = empNo;
    }

    @ElVoField(physicalName = "role")
    public String getRole(){
        String ret = this.role;
        return ret;
    }

    @ElVoField(physicalName = "role")
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UrgentCountVo [");
        sb.append("empNo").append("=").append(empNo).append(",");
        sb.append("role").append("=").append(role);
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

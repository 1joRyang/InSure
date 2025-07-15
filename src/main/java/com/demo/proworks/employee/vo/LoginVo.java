package com.demo.proworks.employee.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "로그인VO")
public class LoginVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public LoginVo(){
    }

    @ElDtoField(logicalName = "아이디", physicalName = "empNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empNo;

    @ElDtoField(logicalName = "비밀번호", physicalName = "empPw", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empPw;

    @ElDtoField(logicalName = "로그인타입", physicalName = "loginType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String loginType;

    @ElVoField(physicalName = "empNo")
    public String getEmpNo(){
        String ret = this.empNo;
        return ret;
    }

    @ElVoField(physicalName = "empNo")
    public void setEmpNo(String empNo){
        this.empNo = empNo;
    }

    @ElVoField(physicalName = "empPw")
    public String getEmpPw(){
        String ret = this.empPw;
        return ret;
    }

    @ElVoField(physicalName = "empPw")
    public void setEmpPw(String empPw){
        this.empPw = empPw;
    }

    @ElVoField(physicalName = "loginType")
    public String getLoginType(){
        String ret = this.loginType;
        return ret;
    }

    @ElVoField(physicalName = "loginType")
    public void setLoginType(String loginType){
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginVo [");
        sb.append("empNo").append("=").append(empNo).append(",");
        sb.append("empPw").append("=").append(empPw).append(",");
        sb.append("loginType").append("=").append(loginType);
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

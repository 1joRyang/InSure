package com.demo.proworks.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "사용자정보")
public class UserVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UserVo(){
    }

    @ElDtoField(logicalName = "ID", physicalName = "id", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int id;

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "pw", physicalName = "pw", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pw;

    @ElDtoField(logicalName = "simple_pw", physicalName = "simplePw", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String simplePw;

    @ElDtoField(logicalName = "RRN", physicalName = "rrn", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String rrn;

    @ElDtoField(logicalName = "user_name", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElDtoField(logicalName = "account", physicalName = "account", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String account;

    @ElDtoField(logicalName = "default_bank_code", physicalName = "default_bank_code", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String default_bank_code;

    @ElDtoField(logicalName = "default_bank_name", physicalName = "default_bank_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String default_bank_name;

    @ElVoField(physicalName = "id")
    public int getId(){
        return id;
    }

    @ElVoField(physicalName = "id")
    public void setId(int id){
        this.id = id;
    }

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        String ret = this.userId;
        return ret;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "pw")
    public String getPw(){
        String ret = this.pw;
        return ret;
    }

    @ElVoField(physicalName = "pw")
    public void setPw(String pw){
        this.pw = pw;
    }

    @ElVoField(physicalName = "simplePw")
    public String getSimplePw(){
        String ret = this.simplePw;
        return ret;
    }

    @ElVoField(physicalName = "simplePw")
    public void setSimplePw(String simplePw){
        this.simplePw = simplePw;
    }

    @ElVoField(physicalName = "rrn")
    public String getRrn(){
        String ret = this.rrn;
        return ret;
    }

    @ElVoField(physicalName = "rrn")
    public void setRrn(String rrn){
        this.rrn = rrn;
    }

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        String ret = this.userName;
        return ret;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
    }

    @ElVoField(physicalName = "account")
    public String getAccount(){
        String ret = this.account;
        return ret;
    }

    @ElVoField(physicalName = "account")
    public void setAccount(String account){
        this.account = account;
    }

    @ElVoField(physicalName = "default_bank_code")
    public String getDefault_bank_code(){
        String ret = this.default_bank_code;
        return ret;
    }

    @ElVoField(physicalName = "default_bank_code")
    public void setDefault_bank_code(String default_bank_code){
        this.default_bank_code = default_bank_code;
    }

    @ElVoField(physicalName = "default_bank_name")
    public String getDefault_bank_name(){
        String ret = this.default_bank_name;
        return ret;
    }

    @ElVoField(physicalName = "default_bank_name")
    public void setDefault_bank_name(String default_bank_name){
        this.default_bank_name = default_bank_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserVo [");
        sb.append("id").append("=").append(id).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("pw").append("=").append(pw).append(",");
        sb.append("simplePw").append("=").append(simplePw).append(",");
        sb.append("rrn").append("=").append(rrn).append(",");
        sb.append("userName").append("=").append(userName).append(",");
        sb.append("account").append("=").append(account).append(",");
        sb.append("default_bank_code").append("=").append(default_bank_code).append(",");
        sb.append("default_bank_name").append("=").append(default_bank_name);
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

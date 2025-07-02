package com.demo.proworks.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "유저정보")
public class UserVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String id;

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String userId;

    @ElDtoField(logicalName = "pw", physicalName = "pw", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String pw;

    @ElDtoField(logicalName = "simple_pw", physicalName = "simplePw", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String simplePw;

    @ElDtoField(logicalName = "RRN", physicalName = "rrn", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String rrn;

    @ElDtoField(logicalName = "user_name", physicalName = "userName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String userName;

    @ElDtoField(logicalName = "account", physicalName = "account", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String account;

    @ElVoField(physicalName = "id")
    public String getId(){
        return id;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "pw")
    public String getPw(){
        return pw;
    }

    @ElVoField(physicalName = "pw")
    public void setPw(String pw){
        this.pw = pw;
    }

    @ElVoField(physicalName = "simplePw")
    public String getSimplePw(){
        return simplePw;
    }

    @ElVoField(physicalName = "simplePw")
    public void setSimplePw(String simplePw){
        this.simplePw = simplePw;
    }

    @ElVoField(physicalName = "rrn")
    public String getRrn(){
        return rrn;
    }

    @ElVoField(physicalName = "rrn")
    public void setRrn(String rrn){
        this.rrn = rrn;
    }

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        return userName;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
    }

    @ElVoField(physicalName = "account")
    public String getAccount(){
        return account;
    }

    @ElVoField(physicalName = "account")
    public void setAccount(String account){
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserVo [id=" + id + ",userId=" + userId + ",pw=" + pw + ",simplePw=" + simplePw + ",rrn=" + rrn + ",userName=" + userName + ",account=" + account + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

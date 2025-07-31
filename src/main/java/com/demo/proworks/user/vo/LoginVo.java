package com.demo.proworks.user.vo;

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

    @ElDtoField(logicalName = "사용자아이디", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "비밀번호", physicalName = "pw", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pw;

    @ElDtoField(logicalName = "아이디", physicalName = "id", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int id;

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

    @ElVoField(physicalName = "id")
    public int getId(){
        return id;
    }

    @ElVoField(physicalName = "id")
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginVo [");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("pw").append("=").append(pw).append(",");
        sb.append("id").append("=").append(id);
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

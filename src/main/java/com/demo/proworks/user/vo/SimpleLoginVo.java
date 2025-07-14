package com.demo.proworks.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "간편로그인VO")
public class SimpleLoginVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SimpleLoginVo(){
    }

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "간편비밀번호", physicalName = "simplePw", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String simplePw;

    @ElDtoField(logicalName = "로그인타입", physicalName = "loginType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String loginType;

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        String ret = this.userId;
        return ret;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
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
        sb.append("SimpleLoginVo [");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("simplePw").append("=").append(simplePw).append(",");
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

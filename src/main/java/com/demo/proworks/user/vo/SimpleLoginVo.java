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

    @ElDtoField(logicalName = "간편비밀번호", physicalName = "simplePw", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String simplePw;

    @ElVoField(physicalName = "simplePw")
    public String getSimplePw(){
        String ret = this.simplePw;
        return ret;
    }

    @ElVoField(physicalName = "simplePw")
    public void setSimplePw(String simplePw){
        this.simplePw = simplePw;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleLoginVo [");
        sb.append("simplePw").append("=").append(simplePw);
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

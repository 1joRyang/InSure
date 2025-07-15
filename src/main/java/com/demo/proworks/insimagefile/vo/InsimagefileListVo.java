package com.demo.proworks.insimagefile.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "이미지파일테이블")
public class InsimagefileListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "이미지파일테이블List", physicalName = "insimagefileVoList", type = "com.demo.proworks.insimagefile.InsimagefileVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.insimagefile.vo.InsimagefileVo> insimagefileVoList;

    public java.util.List<com.demo.proworks.insimagefile.vo.InsimagefileVo> getInsimagefileVoList(){
        return insimagefileVoList;
    }

    public void setInsimagefileVoList(java.util.List<com.demo.proworks.insimagefile.vo.InsimagefileVo> insimagefileVoList){
        this.insimagefileVoList = insimagefileVoList;
    }

    @Override
    public String toString() {
        return "InsimagefileListVo [insimagefileVoList=" + insimagefileVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

package com.demo.proworks.excitem.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "제외항목")
public class ExcItemListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "제외항목List", physicalName = "excItemVoList", type = "com.demo.proworks.excitem.ExcItemVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.excitem.vo.ExcItemVo> excItemVoList;

    public java.util.List<com.demo.proworks.excitem.vo.ExcItemVo> getExcItemVoList(){
        return excItemVoList;
    }

    public void setExcItemVoList(java.util.List<com.demo.proworks.excitem.vo.ExcItemVo> excItemVoList){
        this.excItemVoList = excItemVoList;
    }

    @Override
    public String toString() {
        return "ExcItemListVo [excItemVoList=" + excItemVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

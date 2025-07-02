package com.demo.proworks.insdept.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "부서정보")
public class InsDeptListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "부서정보List", physicalName = "insDeptVoList", type = "com.demo.proworks.insdept.InsDeptVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.insdept.vo.InsDeptVo> insDeptVoList;

    public java.util.List<com.demo.proworks.insdept.vo.InsDeptVo> getInsDeptVoList(){
        return insDeptVoList;
    }

    public void setInsDeptVoList(java.util.List<com.demo.proworks.insdept.vo.InsDeptVo> insDeptVoList){
        this.insDeptVoList = insDeptVoList;
    }

    @Override
    public String toString() {
        return "InsDeptListVo [insDeptVoList=" + insDeptVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

package com.demo.proworks.subinsurance.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "가입보험정보")
public class SubInsuranceListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "가입보험정보List", physicalName = "subInsuranceVoList", type = "com.demo.proworks.subinsurance.SubInsuranceVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.subinsurance.vo.SubInsuranceVo> subInsuranceVoList;

    public java.util.List<com.demo.proworks.subinsurance.vo.SubInsuranceVo> getSubInsuranceVoList(){
        return subInsuranceVoList;
    }

    public void setSubInsuranceVoList(java.util.List<com.demo.proworks.subinsurance.vo.SubInsuranceVo> subInsuranceVoList){
        this.subInsuranceVoList = subInsuranceVoList;
    }

    @Override
    public String toString() {
        return "SubInsuranceListVo [subInsuranceVoList=" + subInsuranceVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

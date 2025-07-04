package com.demo.proworks.insurance.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "보험")
public class InsuranceListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "보험List", physicalName = "insuranceVoList", type = "com.demo.proworks.insurance.InsuranceVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.insurance.vo.InsuranceVo> insuranceVoList;

    public java.util.List<com.demo.proworks.insurance.vo.InsuranceVo> getInsuranceVoList(){
        return insuranceVoList;
    }

    public void setInsuranceVoList(java.util.List<com.demo.proworks.insurance.vo.InsuranceVo> insuranceVoList){
        this.insuranceVoList = insuranceVoList;
    }

    @Override
    public String toString() {
        return "InsuranceListVo [insuranceVoList=" + insuranceVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

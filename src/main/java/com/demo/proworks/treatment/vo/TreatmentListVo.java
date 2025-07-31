package com.demo.proworks.treatment.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "진료정보")
public class TreatmentListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "진료정보List", physicalName = "treatmentVoList", type = "com.demo.proworks.treatment.TreatmentVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.treatment.vo.TreatmentVo> treatmentVoList;

    public java.util.List<com.demo.proworks.treatment.vo.TreatmentVo> getTreatmentVoList(){
        return treatmentVoList;
    }

    public void setTreatmentVoList(java.util.List<com.demo.proworks.treatment.vo.TreatmentVo> treatmentVoList){
        this.treatmentVoList = treatmentVoList;
    }

    @Override
    public String toString() {
        return "TreatmentListVo [treatmentVoList=" + treatmentVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

package com.demo.proworks.settlement.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "정산정보")
public class SettlementListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "정산정보List", physicalName = "settlementVoList", type = "com.demo.proworks.settlement.SettlementVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.settlement.vo.SettlementVo> settlementVoList;

    public java.util.List<com.demo.proworks.settlement.vo.SettlementVo> getSettlementVoList(){
        return settlementVoList;
    }

    public void setSettlementVoList(java.util.List<com.demo.proworks.settlement.vo.SettlementVo> settlementVoList){
        this.settlementVoList = settlementVoList;
    }

    @Override
    public String toString() {
        return "SettlementListVo [settlementVoList=" + settlementVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

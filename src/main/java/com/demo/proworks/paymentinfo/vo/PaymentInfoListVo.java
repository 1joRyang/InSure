package com.demo.proworks.paymentinfo.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "지불정보")
public class PaymentInfoListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "지불정보List", physicalName = "paymentInfoVoList", type = "com.demo.proworks.paymentinfo.PaymentInfoVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.paymentinfo.vo.PaymentInfoVo> paymentInfoVoList;

    public java.util.List<com.demo.proworks.paymentinfo.vo.PaymentInfoVo> getPaymentInfoVoList(){
        return paymentInfoVoList;
    }

    public void setPaymentInfoVoList(java.util.List<com.demo.proworks.paymentinfo.vo.PaymentInfoVo> paymentInfoVoList){
        this.paymentInfoVoList = paymentInfoVoList;
    }

    @Override
    public String toString() {
        return "PaymentInfoListVo [paymentInfoVoList=" + paymentInfoVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

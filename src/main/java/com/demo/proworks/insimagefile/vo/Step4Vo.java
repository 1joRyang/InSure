package com.demo.proworks.insimagefile.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이미지파일테이블")
public class Step4Vo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public Step4Vo(){
    }

    @ElDtoField(logicalName = "accountOption", physicalName = "accountOption", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountOption;

    @ElDtoField(logicalName = "bankCode", physicalName = "bankCode", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String bankCode;

    @ElDtoField(logicalName = "bankName", physicalName = "bankName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String bankName;

    @ElDtoField(logicalName = "accountNo", physicalName = "accountNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountNo;

    @ElDtoField(logicalName = "accountHolder", physicalName = "accountHolder", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountHolder;

    @ElVoField(physicalName = "accountOption")
    public String getAccountOption(){
        String ret = this.accountOption;
        return ret;
    }

    @ElVoField(physicalName = "accountOption")
    public void setAccountOption(String accountOption){
        this.accountOption = accountOption;
    }

    @ElVoField(physicalName = "bankCode")
    public String getBankCode(){
        String ret = this.bankCode;
        return ret;
    }

    @ElVoField(physicalName = "bankCode")
    public void setBankCode(String bankCode){
        this.bankCode = bankCode;
    }

    @ElVoField(physicalName = "bankName")
    public String getBankName(){
        String ret = this.bankName;
        return ret;
    }

    @ElVoField(physicalName = "bankName")
    public void setBankName(String bankName){
        this.bankName = bankName;
    }

    @ElVoField(physicalName = "accountNo")
    public String getAccountNo(){
        String ret = this.accountNo;
        return ret;
    }

    @ElVoField(physicalName = "accountNo")
    public void setAccountNo(String accountNo){
        this.accountNo = accountNo;
    }

    @ElVoField(physicalName = "accountHolder")
    public String getAccountHolder(){
        String ret = this.accountHolder;
        return ret;
    }

    @ElVoField(physicalName = "accountHolder")
    public void setAccountHolder(String accountHolder){
        this.accountHolder = accountHolder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Step4Vo [");
        sb.append("accountOption").append("=").append(accountOption).append(",");
        sb.append("bankCode").append("=").append(bankCode).append(",");
        sb.append("bankName").append("=").append(bankName).append(",");
        sb.append("accountNo").append("=").append(accountNo).append(",");
        sb.append("accountHolder").append("=").append(accountHolder);
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

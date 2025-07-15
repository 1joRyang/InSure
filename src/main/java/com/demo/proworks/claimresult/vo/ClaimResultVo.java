package com.demo.proworks.claimresult.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "심사결과")
public class ClaimResultVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ClaimResultVo(){
    }

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "claim_memo", physicalName = "claim_memo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_memo;

    @ElDtoField(logicalName = "amount", physicalName = "amount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String amount;

    @ElDtoField(logicalName = "date", physicalName = "date", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String date;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "claim_memo")
    public String getClaim_memo(){
        String ret = this.claim_memo;
        return ret;
    }

    @ElVoField(physicalName = "claim_memo")
    public void setClaim_memo(String claim_memo){
        this.claim_memo = claim_memo;
    }

    @ElVoField(physicalName = "amount")
    public String getAmount(){
        String ret = this.amount;
        return ret;
    }

    @ElVoField(physicalName = "amount")
    public void setAmount(String amount){
        this.amount = amount;
    }

    @ElVoField(physicalName = "date")
    public String getDate(){
        String ret = this.date;
        return ret;
    }

    @ElVoField(physicalName = "date")
    public void setDate(String date){
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClaimResultVo [");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("claim_memo").append("=").append(claim_memo).append(",");
        sb.append("amount").append("=").append(amount).append(",");
        sb.append("date").append("=").append(date);
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

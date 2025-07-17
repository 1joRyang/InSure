package com.demo.proworks.additionalreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "추가요청 정보 커스텀")
public class AdditionalReqCusVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public AdditionalReqCusVo(){
    }

    @ElDtoField(logicalName = "additional_memo", physicalName = "additional_memo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String additional_memo;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "request_type", physicalName = "request_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String request_type;

    @ElVoField(physicalName = "additional_memo")
    public String getAdditional_memo(){
        String ret = this.additional_memo;
        return ret;
    }

    @ElVoField(physicalName = "additional_memo")
    public void setAdditional_memo(String additional_memo){
        this.additional_memo = additional_memo;
    }

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        String ret = this.claim_no;
        return ret;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "request_type")
    public String getRequest_type(){
        String ret = this.request_type;
        return ret;
    }

    @ElVoField(physicalName = "request_type")
    public void setRequest_type(String request_type){
        this.request_type = request_type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdditionalReqCusVo [");
        sb.append("additional_memo").append("=").append(additional_memo).append(",");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("request_type").append("=").append(request_type);
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

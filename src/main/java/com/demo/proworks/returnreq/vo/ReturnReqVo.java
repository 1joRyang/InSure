package com.demo.proworks.returnreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "반송요청정보")
public class ReturnReqVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String claim_no;

    @ElDtoField(logicalName = "return_memo", physicalName = "return_memo", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String return_memo;

    @ElDtoField(logicalName = "request_date", physicalName = "request_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String request_date;

    @ElDtoField(logicalName = "completed_date", physicalName = "completed_date", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String completed_date;

    @ElVoField(physicalName = "claim_no")
    public String getClaim_no(){
        return claim_no;
    }

    @ElVoField(physicalName = "claim_no")
    public void setClaim_no(String claim_no){
        this.claim_no = claim_no;
    }

    @ElVoField(physicalName = "return_memo")
    public String getReturn_memo(){
        return return_memo;
    }

    @ElVoField(physicalName = "return_memo")
    public void setReturn_memo(String return_memo){
        this.return_memo = return_memo;
    }

    @ElVoField(physicalName = "request_date")
    public String getRequest_date(){
        return request_date;
    }

    @ElVoField(physicalName = "request_date")
    public void setRequest_date(String request_date){
        this.request_date = request_date;
    }

    @ElVoField(physicalName = "completed_date")
    public String getCompleted_date(){
        return completed_date;
    }

    @ElVoField(physicalName = "completed_date")
    public void setCompleted_date(String completed_date){
        this.completed_date = completed_date;
    }

    @Override
    public String toString() {
        return "ReturnReqVo [claim_no=" + claim_no + ",return_memo=" + return_memo + ",request_date=" + request_date + ",completed_date=" + completed_date + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

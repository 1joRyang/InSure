package com.demo.proworks.approvalreq.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "결재요청")
public class ApprovalReqListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "결재요청List", physicalName = "approvalReqVoList", type = "com.demo.proworks.approvalreq.ApprovalReqVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.approvalreq.vo.ApprovalReqVo> approvalReqVoList;

    public java.util.List<com.demo.proworks.approvalreq.vo.ApprovalReqVo> getApprovalReqVoList(){
        return approvalReqVoList;
    }

    public void setApprovalReqVoList(java.util.List<com.demo.proworks.approvalreq.vo.ApprovalReqVo> approvalReqVoList){
        this.approvalReqVoList = approvalReqVoList;
    }

    @Override
    public String toString() {
        return "ApprovalReqListVo [approvalReqVoList=" + approvalReqVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

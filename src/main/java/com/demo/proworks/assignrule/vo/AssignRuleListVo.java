package com.demo.proworks.assignrule.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "배정규칙")
public class AssignRuleListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "배정규칙List", physicalName = "assignRuleVoList", type = "com.demo.proworks.assignrule.AssignRuleVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.assignrule.vo.AssignRuleVo> assignRuleVoList;

    public java.util.List<com.demo.proworks.assignrule.vo.AssignRuleVo> getAssignRuleVoList(){
        return assignRuleVoList;
    }

    public void setAssignRuleVoList(java.util.List<com.demo.proworks.assignrule.vo.AssignRuleVo> assignRuleVoList){
        this.assignRuleVoList = assignRuleVoList;
    }

    @Override
    public String toString() {
        return "AssignRuleListVo [assignRuleVoList=" + assignRuleVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

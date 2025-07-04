package com.demo.proworks.assignrule.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "배정규칙")
public class AssignRuleVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public AssignRuleVo(){
    }

    @ElDtoField(logicalName = "rule_id", physicalName = "rule_id", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int rule_id;

    @ElDtoField(logicalName = "keyword", physicalName = "keyword", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String keyword;

    @ElDtoField(logicalName = "dept", physicalName = "dept", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String dept;

    @ElVoField(physicalName = "rule_id")
    public int getRule_id(){
        return rule_id;
    }

    @ElVoField(physicalName = "rule_id")
    public void setRule_id(int rule_id){
        this.rule_id = rule_id;
    }

    @ElVoField(physicalName = "keyword")
    public String getKeyword(){
        String ret = this.keyword;
        return ret;
    }

    @ElVoField(physicalName = "keyword")
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    @ElVoField(physicalName = "dept")
    public String getDept(){
        String ret = this.dept;
        return ret;
    }

    @ElVoField(physicalName = "dept")
    public void setDept(String dept){
        this.dept = dept;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AssignRuleVo [");
        sb.append("rule_id").append("=").append(rule_id).append(",");
        sb.append("keyword").append("=").append(keyword).append(",");
        sb.append("dept").append("=").append(dept);
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

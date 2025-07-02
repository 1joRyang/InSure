package com.demo.proworks.insdept.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "부서정보")
public class InsDeptVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "dept_id", physicalName = "dept_id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String dept_id;

    @ElDtoField(logicalName = "dept_name", physicalName = "dept_name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String dept_name;

    @ElVoField(physicalName = "dept_id")
    public String getDept_id(){
        return dept_id;
    }

    @ElVoField(physicalName = "dept_id")
    public void setDept_id(String dept_id){
        this.dept_id = dept_id;
    }

    @ElVoField(physicalName = "dept_name")
    public String getDept_name(){
        return dept_name;
    }

    @ElVoField(physicalName = "dept_name")
    public void setDept_name(String dept_name){
        this.dept_name = dept_name;
    }

    @Override
    public String toString() {
        return "InsDeptVo [dept_id=" + dept_id + ",dept_name=" + dept_name + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

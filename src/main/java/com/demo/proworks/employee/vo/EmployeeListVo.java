package com.demo.proworks.employee.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "실무자,관리자정보")
public class EmployeeListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "실무자,관리자정보List", physicalName = "employeeVoList", type = "com.demo.proworks.employee.EmployeeVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.employee.vo.EmployeeVo> employeeVoList;

    public java.util.List<com.demo.proworks.employee.vo.EmployeeVo> getEmployeeVoList(){
        return employeeVoList;
    }

    public void setEmployeeVoList(java.util.List<com.demo.proworks.employee.vo.EmployeeVo> employeeVoList){
        this.employeeVoList = employeeVoList;
    }

    @Override
    public String toString() {
        return "EmployeeListVo [employeeVoList=" + employeeVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}

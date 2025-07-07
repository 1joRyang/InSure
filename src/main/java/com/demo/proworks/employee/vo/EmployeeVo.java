package com.demo.proworks.employee.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "실무자,관리자정보")
public class EmployeeVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public EmployeeVo(){
    }

    @ElDtoField(logicalName = "emp_no", physicalName = "empNo", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int empNo;

    @ElDtoField(logicalName = "emp_name", physicalName = "empName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empName;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "dept_id", physicalName = "deptId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String deptId;

    @ElDtoField(logicalName = "role", physicalName = "role", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String role;

    // 🔥 라운드로빈용 마지막 직원 번호 (검색 조건용)
    @ElDtoField(logicalName = "last_emp_no", physicalName = "lastEmpNo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String lastEmpNo;

    @ElVoField(physicalName = "empNo")
    public int getEmpNo(){
        return empNo;
    }

    @ElVoField(physicalName = "empNo")
    public void setEmpNo(int empNo){
        this.empNo = empNo;
    }

    @ElVoField(physicalName = "empName")
    public String getEmpName(){
        String ret = this.empName;
        return ret;
    }

    @ElVoField(physicalName = "empName")
    public void setEmpName(String empName){
        this.empName = empName;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        String ret = this.status;
        return ret;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "deptId")
    public String getDeptId(){
        String ret = this.deptId;
        return ret;
    }

    @ElVoField(physicalName = "deptId")
    public void setDeptId(String deptId){
        this.deptId = deptId;
    }

    @ElVoField(physicalName = "role")
    public String getRole(){
        String ret = this.role;
        return ret;
    }

    @ElVoField(physicalName = "role")
    public void setRole(String role){
        this.role = role;
    }

    @ElVoField(physicalName = "lastEmpNo")
    public String getLastEmpNo(){
        String ret = this.lastEmpNo;
        return ret;
    }

    @ElVoField(physicalName = "lastEmpNo")
    public void setLastEmpNo(String lastEmpNo){
        this.lastEmpNo = lastEmpNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeVo [");
        sb.append("empNo").append("=").append(empNo).append(",");
        sb.append("empName").append("=").append(empName).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("deptId").append("=").append(deptId).append(",");
        sb.append("role").append("=").append(role).append(",");
        sb.append("lastEmpNo").append("=").append(lastEmpNo);
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

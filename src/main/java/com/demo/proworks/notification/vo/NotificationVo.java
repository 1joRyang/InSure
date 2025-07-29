package com.demo.proworks.notification.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "알림")
public class NotificationVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public NotificationVo(){
    }

    @ElDtoField(logicalName = "noti_id", physicalName = "noti_id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String noti_id;

    @ElDtoField(logicalName = "noti_content", physicalName = "noti_content", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String noti_content;

    @ElDtoField(logicalName = "noti_type", physicalName = "noti_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String noti_type;

    @ElDtoField(logicalName = "emp_no", physicalName = "emp_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emp_no;

    @ElDtoField(logicalName = "is_read", physicalName = "is_read", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String is_read;

    @ElDtoField(logicalName = "created_date", physicalName = "created_date", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String created_date;

    @ElVoField(physicalName = "noti_id")
    public String getNoti_id(){
        String ret = this.noti_id;
        return ret;
    }

    @ElVoField(physicalName = "noti_id")
    public void setNoti_id(String noti_id){
        this.noti_id = noti_id;
    }

    @ElVoField(physicalName = "noti_content")
    public String getNoti_content(){
        String ret = this.noti_content;
        return ret;
    }

    @ElVoField(physicalName = "noti_content")
    public void setNoti_content(String noti_content){
        this.noti_content = noti_content;
    }

    @ElVoField(physicalName = "noti_type")
    public String getNoti_type(){
        String ret = this.noti_type;
        return ret;
    }

    @ElVoField(physicalName = "noti_type")
    public void setNoti_type(String noti_type){
        this.noti_type = noti_type;
    }

    @ElVoField(physicalName = "emp_no")
    public String getEmp_no(){
        String ret = this.emp_no;
        return ret;
    }

    @ElVoField(physicalName = "emp_no")
    public void setEmp_no(String emp_no){
        this.emp_no = emp_no;
    }

    @ElVoField(physicalName = "is_read")
    public String getIs_read(){
        String ret = this.is_read;
        return ret;
    }

    @ElVoField(physicalName = "is_read")
    public void setIs_read(String is_read){
        this.is_read = is_read;
    }

    @ElVoField(physicalName = "created_date")
    public String getCreated_date(){
        String ret = this.created_date;
        return ret;
    }

    @ElVoField(physicalName = "created_date")
    public void setCreated_date(String created_date){
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NotificationVo [");
        sb.append("noti_id").append("=").append(noti_id).append(",");
        sb.append("noti_content").append("=").append(noti_content).append(",");
        sb.append("noti_type").append("=").append(noti_type).append(",");
        sb.append("emp_no").append("=").append(emp_no).append(",");
        sb.append("is_read").append("=").append(is_read).append(",");
        sb.append("created_date").append("=").append(created_date);
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

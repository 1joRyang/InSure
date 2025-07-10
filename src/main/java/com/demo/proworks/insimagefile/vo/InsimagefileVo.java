package com.demo.proworks.insimagefile.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이미지파일테이블")
public class InsimagefileVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public InsimagefileVo(){
    }

    @ElDtoField(logicalName = "file_id", physicalName = "file_id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String file_id;

    @ElDtoField(logicalName = "claim_no", physicalName = "claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String claim_no;

    @ElDtoField(logicalName = "original_file_name", physicalName = "original_file_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String original_file_name;

    @ElDtoField(logicalName = "stored_file_name", physicalName = "stored_file_name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stored_file_name;

    @ElDtoField(logicalName = "file_path", physicalName = "file_path", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String file_path;

    @ElDtoField(logicalName = "file_size", physicalName = "file_size", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String file_size;

    @ElDtoField(logicalName = "file_type", physicalName = "file_type", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String file_type;

    @ElDtoField(logicalName = "created_at", physicalName = "created_at", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String created_at;

    @ElVoField(physicalName = "file_id")
    public String getFile_id(){
        String ret = this.file_id;
        return ret;
    }

    @ElVoField(physicalName = "file_id")
    public void setFile_id(String file_id){
        this.file_id = file_id;
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

    @ElVoField(physicalName = "original_file_name")
    public String getOriginal_file_name(){
        String ret = this.original_file_name;
        return ret;
    }

    @ElVoField(physicalName = "original_file_name")
    public void setOriginal_file_name(String original_file_name){
        this.original_file_name = original_file_name;
    }

    @ElVoField(physicalName = "stored_file_name")
    public String getStored_file_name(){
        String ret = this.stored_file_name;
        return ret;
    }

    @ElVoField(physicalName = "stored_file_name")
    public void setStored_file_name(String stored_file_name){
        this.stored_file_name = stored_file_name;
    }

    @ElVoField(physicalName = "file_path")
    public String getFile_path(){
        String ret = this.file_path;
        return ret;
    }

    @ElVoField(physicalName = "file_path")
    public void setFile_path(String file_path){
        this.file_path = file_path;
    }

    @ElVoField(physicalName = "file_size")
    public String getFile_size(){
        String ret = this.file_size;
        return ret;
    }

    @ElVoField(physicalName = "file_size")
    public void setFile_size(String file_size){
        this.file_size = file_size;
    }

    @ElVoField(physicalName = "file_type")
    public String getFile_type(){
        String ret = this.file_type;
        return ret;
    }

    @ElVoField(physicalName = "file_type")
    public void setFile_type(String file_type){
        this.file_type = file_type;
    }

    @ElVoField(physicalName = "created_at")
    public String getCreated_at(){
        String ret = this.created_at;
        return ret;
    }

    @ElVoField(physicalName = "created_at")
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InsimagefileVo [");
        sb.append("file_id").append("=").append(file_id).append(",");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("original_file_name").append("=").append(original_file_name).append(",");
        sb.append("stored_file_name").append("=").append(stored_file_name).append(",");
        sb.append("file_path").append("=").append(file_path).append(",");
        sb.append("file_size").append("=").append(file_size).append(",");
        sb.append("file_type").append("=").append(file_type).append(",");
        sb.append("created_at").append("=").append(created_at);
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

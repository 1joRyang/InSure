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

    @ElDtoField(logicalName = "file_path", physicalName = "file_path", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String file_path;

    @ElDtoField(logicalName = "created_at", physicalName = "created_at", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String created_at;

    @ElDtoField(logicalName = "idx_claim_no", physicalName = "idx_claim_no", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String idx_claim_no;

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

    @ElVoField(physicalName = "file_path")
    public String getFile_path(){
        String ret = this.file_path;
        return ret;
    }

    @ElVoField(physicalName = "file_path")
    public void setFile_path(String file_path){
        this.file_path = file_path;
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

    @ElVoField(physicalName = "idx_claim_no")
    public String getIdx_claim_no(){
        String ret = this.idx_claim_no;
        return ret;
    }

    @ElVoField(physicalName = "idx_claim_no")
    public void setIdx_claim_no(String idx_claim_no){
        this.idx_claim_no = idx_claim_no;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InsimagefileVo [");
        sb.append("file_id").append("=").append(file_id).append(",");
        sb.append("claim_no").append("=").append(claim_no).append(",");
        sb.append("file_path").append("=").append(file_path).append(",");
        sb.append("created_at").append("=").append(created_at).append(",");
        sb.append("idx_claim_no").append("=").append(idx_claim_no);
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

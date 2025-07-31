package com.demo.proworks.notification.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "알림")
public class NotificationListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public NotificationListVo(){
    }

    @ElDtoField(logicalName = "알림List", physicalName = "notificationVoList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.notification.vo.NotificationVo> notificationVoList;

    @ElDtoField(logicalName = "전체개수", physicalName = "totalCount", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalCount;

    @ElDtoField(logicalName = "페이지크기", physicalName = "pageSize", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int pageSize;

    @ElDtoField(logicalName = "페이지번호", physicalName = "pageIndex", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long pageIndex;

    @ElDtoField(logicalName = "미읽음개수", physicalName = "unreadCount", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int unreadCount;

    @ElDtoField(logicalName = "성공여부", physicalName = "success", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean success;

    @ElDtoField(logicalName = "메시지", physicalName = "message", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String message;

    @ElVoField(physicalName = "notificationVoList")
    public java.util.List<com.demo.proworks.notification.vo.NotificationVo> getNotificationVoList(){
        return notificationVoList;
    }

    @ElVoField(physicalName = "notificationVoList")
    public void setNotificationVoList(java.util.List<com.demo.proworks.notification.vo.NotificationVo> notificationVoList){
        this.notificationVoList = notificationVoList;
    }

    @ElVoField(physicalName = "totalCount")
    public long getTotalCount(){
        return totalCount;
    }

    @ElVoField(physicalName = "totalCount")
    public void setTotalCount(long totalCount){
        this.totalCount = totalCount;
    }

    @ElVoField(physicalName = "pageSize")
    public int getPageSize(){
        return pageSize;
    }

    @ElVoField(physicalName = "pageSize")
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    @ElVoField(physicalName = "pageIndex")
    public long getPageIndex(){
        return pageIndex;
    }

    @ElVoField(physicalName = "pageIndex")
    public void setPageIndex(long pageIndex){
        this.pageIndex = pageIndex;
    }

    @ElVoField(physicalName = "unreadCount")
    public int getUnreadCount(){
        return unreadCount;
    }

    @ElVoField(physicalName = "unreadCount")
    public void setUnreadCount(int unreadCount){
        this.unreadCount = unreadCount;
    }

    @ElVoField(physicalName = "success")
    public boolean isSuccess(){
        return success;
    }

    @ElVoField(physicalName = "success")
    public void setSuccess(boolean success){
        this.success = success;
    }

    @ElVoField(physicalName = "message")
    public String getMessage(){
        String ret = this.message;
        return ret;
    }

    @ElVoField(physicalName = "message")
    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NotificationListVo [");
        sb.append("notificationVoList").append("=").append(notificationVoList).append(",");
        sb.append("totalCount").append("=").append(totalCount).append(",");
        sb.append("pageSize").append("=").append(pageSize).append(",");
        sb.append("pageIndex").append("=").append(pageIndex).append(",");
        sb.append("unreadCount").append("=").append(unreadCount).append(",");
        sb.append("success").append("=").append(success).append(",");
        sb.append("message").append("=").append(message);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; notificationVoList != null && i < notificationVoList.size() ; i++ ) {
            com.demo.proworks.notification.vo.NotificationVo vo = (com.demo.proworks.notification.vo.NotificationVo)notificationVoList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; notificationVoList != null && i < notificationVoList.size() ; i++ ) {
            com.demo.proworks.notification.vo.NotificationVo vo = (com.demo.proworks.notification.vo.NotificationVo)notificationVoList.get(i);
            vo._xStreamDec();	 
        }
    }


}

package com.neusoft.clw.common.servlet.domain;

public class AccessoryInfo {
    private String accessoryId = "";

    private String noticeId = "";

    private String accessoryName = "";

    private byte[] accessoryContent = null;

    private String accessoryType = "";

    private String edit_accessory_ids;

    public String getEdit_accessory_ids() {
        return edit_accessory_ids;
    }

    public void setEdit_accessory_ids(String edit_accessory_ids) {
        this.edit_accessory_ids = edit_accessory_ids;
    }

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public byte[] getAccessoryContent() {
        return accessoryContent;
    }

    public void setAccessoryContent(byte[] accessoryContent) {
        this.accessoryContent = accessoryContent;
    }

    public String getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(String accessoryType) {
        this.accessoryType = accessoryType;
    }

}

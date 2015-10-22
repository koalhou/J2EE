package com.neusoft.clw.yw.xs.noticemanage.domain;

/**
 * 企业公告
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: August 15, 2011 2:48:14 PM
 */
public class AjtNotice {
    private String notice_id;

    private String notice_type;

    private String notice_theme;

    private String notice_content;

    private String notice_status;

    private String publish_user;

    private String publish_time;

    private String accessory_flag;

    private String creater;

    private String create_time;

    private String modifier;

    private String modify_time;

    private String valid_flag;

    private String vaset_user_id;

    private String vaset_time;

    private String sortname;

    private String sortorder;

    private String delAccessoryIds = "";

    private String edit_accessory_ids;

    public String getEdit_accessory_ids() {
        return edit_accessory_ids;
    }

    public void setEdit_accessory_ids(String edit_accessory_ids) {
        this.edit_accessory_ids = edit_accessory_ids;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_theme() {
        return notice_theme;
    }

    public void setNotice_theme(String notice_theme) {
        this.notice_theme = notice_theme;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getNotice_status() {
        return notice_status;
    }

    public void setNotice_status(String notice_status) {
        this.notice_status = notice_status;
    }

    public String getPublish_user() {
        return publish_user;
    }

    public void setPublish_user(String publish_user) {
        this.publish_user = publish_user;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getAccessory_flag() {
        return accessory_flag;
    }

    public void setAccessory_flag(String accessory_flag) {
        this.accessory_flag = accessory_flag;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getValid_flag() {
        return valid_flag;
    }

    public void setValid_flag(String valid_flag) {
        this.valid_flag = valid_flag;
    }

    public String getVaset_user_id() {
        return vaset_user_id;
    }

    public void setVaset_user_id(String vaset_user_id) {
        this.vaset_user_id = vaset_user_id;
    }

    public String getVaset_time() {
        return vaset_time;
    }

    public void setVaset_time(String vaset_time) {
        this.vaset_time = vaset_time;
    }

    public String getDelAccessoryIds() {
        return delAccessoryIds;
    }

    public void setDelAccessoryIds(String delAccessoryIds) {
        this.delAccessoryIds = delAccessoryIds;
    }
}

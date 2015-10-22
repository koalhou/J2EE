/*******************************************************************************
 * @(#)SmsReminderInfo.java 2012-7-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.smsreminder.domain;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-4 下午03:26:20
 */
public class SmsReminderInfo {
    /** 学生ID **/
    private String studentId = "";
    /** 学生姓名 **/
    private String studentName = "";
    /** 学生学号 **/
    private String studentCode = "";
    /** 学生学校 **/
    private String studentSchool = "";
    /** 学生班级 **/
    private String studentClass = "";
    /** 开通时间 **/
    private String orderTime = "";
    /** 手机号1 **/
    private String phone1 = "";
    /** 手机号2 **/
    private String phone2 = "";
    /** 手机号3 **/
    private String phone3 = "";
    /** 查询开始时间 **/
    private String queryStartTime = "";
    /** 查询结束时间 **/
    private String queryEndTime = "";
    /** 企业ID **/
    private String enterpriseId = "";
    /** 组织ID **/
    private String organizationId = "";
    private String sortname;
    private String sortorder;
    
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentCode() {
        return studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    public String getStudentSchool() {
        return studentSchool;
    }
    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }
    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    public String getPhone1() {
        return phone1;
    }
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    public String getPhone2() {
        return phone2;
    }
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    public String getPhone3() {
        return phone3;
    }
    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }
    public String getQueryStartTime() {
        return queryStartTime;
    }
    public void setQueryStartTime(String queryStartTime) {
        this.queryStartTime = queryStartTime;
    }
    public String getQueryEndTime() {
        return queryEndTime;
    }
    public void setQueryEndTime(String queryEndTime) {
        this.queryEndTime = queryEndTime;
    }
    public String getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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
}

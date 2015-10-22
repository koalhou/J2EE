package com.neusoft.clw.sysmanage.datamanage.appconfig.domain;

public class Appcfg {
    /** 主键，应用 **/
    private String app_id;

    /** 应用名称 **/
    private String app_name;

    /** 应用ip **/
    private String app_ip;

    /** 核心服务请求路径 **/
    private String send_path;

    /** 主键，核心服务ID **/
    private String core_id;

    /** 登陆核心服务的密码 **/
    private String core_pass;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_ip() {
		return app_ip;
	}

	public void setApp_ip(String app_ip) {
		this.app_ip = app_ip;
	}

	public String getSend_path() {
		return send_path;
	}

	public void setSend_path(String send_path) {
		this.send_path = send_path;
	}

	public String getCore_id() {
		return core_id;
	}

	public void setCore_id(String core_id) {
		this.core_id = core_id;
	}

	public String getCore_pass() {
		return core_pass;
	}

	public void setCore_pass(String core_pass) {
		this.core_pass = core_pass;
	}

}
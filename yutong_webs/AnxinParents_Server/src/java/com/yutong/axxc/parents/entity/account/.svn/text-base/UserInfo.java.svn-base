package com.yutong.axxc.parents.entity.account;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo  implements Serializable{
	
	private static final long serialVersionUID = -1705443036688829773L;
	
	@JsonProperty("usr_id")
	private String id;
	@JsonProperty("usr_no")
	private String no;//安芯号
	@JsonProperty("usr_login_name")
	private String name;//登陆名
	@JsonProperty("usr_pwd")
	private String pwd;
	@JsonProperty("usr_alias")
	private String alias;
	@JsonProperty("usr_sex")
	private String sex;
	@JsonProperty("usr_main")
	private String main;//是否为主账号
	@JsonProperty("usr_phone")
	private String phone;
	@JsonProperty("usr_photo")
	private String photo;
	@JsonProperty("usr_addr")
	private String addr;
	@JsonProperty("usr_email")
	private String email;
	@JsonProperty("usr_name")
	private String xinming;//真实姓名
	@JsonProperty("phone_bind")
	private String bound;//是否绑定手机  1-绑定   0-未绑定
	@JsonProperty("p_usr_no")
	private String authedAccountCode;//申请人的账号
	@JsonProperty("p_usr_login_name")
	private String authedAccountName;//授权人的用户名
	
	@JsonIgnore
	private Date expireTime;
	@JsonIgnore
	private int able;
	@JsonIgnore
	private String clientID;
	@JsonIgnore
	private String accessToken;
	@JsonIgnore
	private String refreshToken;
	
	private String relationType;
	@JsonIgnore
	private int expire;
	
	@JsonIgnore
	private Date lastLogin;

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getAble() {
		return able;
	}

	public void setAble(int able) {
		this.able = able;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getXinming() {
		return xinming;
	}

	public void setXinming(String xinming) {
		this.xinming = xinming;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getAuthedAccountCode() {
		return authedAccountCode;
	}

	public void setAuthedAccountCode(String authedAccountCode) {
		this.authedAccountCode = authedAccountCode;
	}

	public String getAuthedAccountName() {
		return authedAccountName;
	}

	public void setAuthedAccountName(String authedAccountName) {
		this.authedAccountName = authedAccountName;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", no=" + no + ", name=" + name+ ", alias=" + alias
				+ ", pwd=" + pwd + ", sex=" + sex + ", main=" + main
				+ ", phone=" + phone + ", addr=" + addr + ", xinming="
				+ xinming + ", accessToken=" + accessToken + ", refreshToken="
				+ refreshToken + "]";
	}

	
}

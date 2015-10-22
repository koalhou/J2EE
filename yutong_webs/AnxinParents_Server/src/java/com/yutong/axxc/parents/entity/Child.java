package com.yutong.axxc.parents.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Child  implements Serializable{
	
	private static final long serialVersionUID = -7654448696666578422L;

	@JsonProperty("cld_id")
	private String id;
	
	@JsonProperty("cld_no")
	private String no;//学号
	
	@JsonProperty("cld_code")
	private String cardNo;//卡号
	
	@JsonProperty("cld_name")
	private String name;
	
	@JsonProperty("cld_alias")
	private String alias;
	
	@JsonProperty("cld_sex")
	private String sex;
	
	@JsonProperty("cld_addr")
	private String addr;
	
	@JsonProperty("cld_school")
	private String school;
	
	@JsonProperty("cld_class")
	private String className;//班级
	
	@JsonProperty("cld_class_adviser")
	private String adviser;//班主任姓名
	
	@JsonProperty("cld_class_adviser_phone")
	private String adviserPhone;//班主任手机
	
	@JsonProperty("usr_salutation")
	private String relation;
	
	@JsonProperty("cld_bg")
	private String bg;
	
	@JsonProperty("cld_photo")
	private String photo;
	
	@JsonProperty("cld_audio")
	private String audio;
	
	@JsonProperty("cld_color")
	private String color;
	
	@JsonProperty("status")
	private String status;
	
	@JsonIgnore
	private String pid;
	
	@JsonProperty("usr_main")
	private String main;
	
	
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getClassNo() {
		return className;
	}
	public void setClassNo(String classNo) {
		this.className = className;
	}
	public String getAdviser() {
		return adviser;
	}
	public void setAdviser(String adviser) {
		this.adviser = adviser;
	}
	public String getAdviserPhone() {
		return adviserPhone;
	}
	public void setAdviserPhone(String adviserPhone) {
		this.adviserPhone = adviserPhone;
	}

	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	@Override
	public String toString() {
		return "Child [id=" + id + ", no=" + no + ", cardNo=" + cardNo
				+ ", name=" + name + ", alias=" + alias + ", sex=" + sex
				+ ", addr=" + addr + ", school=" + school + ", classNo="
				+ className + ", adviser=" + adviser + ", adviserPhone="
				+ adviserPhone + ", relation=" + relation + ", bg=" + bg
				+ ", photo=" + photo + ", audio=" + audio + ", color=" + color
				+ ", status=" + status + ", pid=" + pid + "]";
	}
	
	
}

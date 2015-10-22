package com.yutong.clw.beans.xc;


public class StudentTrip {
	private String stu_name;
	private String stu_id;
	private String stu_card_id;
	private String stu_school;
	private String stu_class;
	private String teacher_tel;
	private String relative_tel;
	private String photoname;
	private String getonbus;
	private String getdownbus;
	private byte[] stu_photo;
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stuName) {
		stu_name = stuName;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stuId) {
		stu_id = stuId;
	}
	public String getStu_card_id() {
		return stu_card_id;
	}
	public void setStu_card_id(String stuCardId) {
		stu_card_id = stuCardId;
	}
	public String getStu_school() {
		return stu_school;
	}
	public void setStu_school(String stuSchool) {
		stu_school = stuSchool;
	}
	public String getStu_class() {
		return stu_class;
	}
	public void setStu_class(String stuClass) {
		stu_class = stuClass;
	}
	public String getTeacher_tel() {
		return teacher_tel;
	}
	public void setTeacher_tel(String teacherTel) {
		teacher_tel = teacherTel;
	}
	public String getRelative_tel() {
		return relative_tel;
	}
	public void setRelative_tel(String relativeTel) {
		relative_tel = relativeTel;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public String getGetonbus() {
		return getonbus;
	}
	public void setGetonbus(String getonbus) {
		this.getonbus = getonbus;
	}
	public String getGetdownbus() {
		return getdownbus;
	}
	public void setGetdownbus(String getdownbus) {
		this.getdownbus = getdownbus;
	}
//	public Blob getStu_photo() {
//		return stu_photo;
//	}
//	public void setStu_photo(Blob stuPhoto) {
//		stu_photo = stuPhoto;
//	}
	
	public byte[] getStu_photo() {
		return stu_photo;
	}
	public void setStu_photo(byte[] stuPhoto) {
		stu_photo = stuPhoto;
	}
}

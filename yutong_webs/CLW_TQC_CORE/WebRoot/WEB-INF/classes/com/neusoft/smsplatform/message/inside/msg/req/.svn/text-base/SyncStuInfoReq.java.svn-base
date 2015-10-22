package com.neusoft.smsplatform.message.inside.msg.req;

import java.io.UnsupportedEncodingException;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;

public class SyncStuInfoReq extends MesssageInsideMsg {

	public static final String COMMAND = "04";

	private static final int STUIDSIZE = 10;
	private static final int STUCARDIDSIZE = 20;
	private static final int STUCODESIZE = 20;
	private static final int STUNAMESIZE = 64;
	private static final int STUSEXSIZE = 1;
	private static final int STUBIRTHSIZE = 10;
	private static final int STUADDRESSSIZE = 128;
	private static final int PROVINCESIZE = 10;
	private static final int CITYSIZE = 10;
	private static final int COUNTYSIZE = 10;
	private static final int STUSCHOOLSIZE = 64;
	private static final int STUCLASSSIZE = 64;
	private static final int STUREMARKSIZE = 128;
	private static final int TEACHERNAMESIZE = 64;
	private static final int TEACHERTELSIZE = 20;
	private static final int RELATIVENAMESIZE = 64;
	private static final int RELATIVETELSIZE = 20;
	private static final int RELATIVETYPESIZE = 1;
	private static final int ENTERPRISEIDSIZE = 64;
	private static final int ORGANIZATIONIDSIZE = 64;
	private static final int VALIDFLAGSIZE = 2;
	public static final int total = MSGHEADERSIZE + STUIDSIZE + STUCARDIDSIZE + STUCODESIZE
	+ STUNAMESIZE + STUSEXSIZE + STUBIRTHSIZE + STUADDRESSSIZE
	+ PROVINCESIZE + CITYSIZE + COUNTYSIZE + STUSCHOOLSIZE
	+ STUCLASSSIZE + STUREMARKSIZE + TEACHERNAMESIZE
	+ TEACHERTELSIZE + RELATIVENAMESIZE + RELATIVETELSIZE
	+ RELATIVETYPESIZE + ENTERPRISEIDSIZE
	+ ORGANIZATIONIDSIZE+VALIDFLAGSIZE;
	private String stu_id;
	private String stu_card_id;
	private String stu_code;
	private String stu_name;
	private String stu_sex;
	private String stu_birth;
	private String stu_address;
	private String stu_province;
	private String stu_city;
	private String stu_district;
	private String stu_school;
	private String stu_class;
	private String stu_remark;
	private String teacher_name;
	private String teacher_tel;
	private String relative_name;
	private String relative_tel;
	private String relative_type;
	private String enterprise_id;
	private String organization_id;
	private String valid_flag;
	
	private int totallen;

	public int getTotallen() {
		return totallen;
	}

	public void setTotallen(int totallen) {
		this.totallen = totallen;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stuId) {
		stu_id = SmsCommonMsgUtils.format(stuId, STUIDSIZE);
	}

	public String getStu_card_id() {
		return stu_card_id;
	}

	public void setStu_card_id(String stuCardId) {
		stu_card_id = (stuCardId == null || stuCardId.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUCARDIDSIZE)
				: SmsCommonMsgUtils.rightspaceformat(stuCardId, STUCARDIDSIZE);
	}

	public String getStu_code() {
		return stu_code;
	}

	public void setStu_code(String stuCode) {
		stu_code = (stuCode == null || stuCode.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUCODESIZE) : SmsCommonMsgUtils
				.rightspaceformat(stuCode, STUCODESIZE);
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stuName) {
		stu_name = (stuName == null || stuName.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUNAMESIZE) : SmsCommonMsgUtils
				.rightspaceformat(stuName, STUNAMESIZE);
	}

	public String getStu_sex() {
		return stu_sex;
	}

	public void setStu_sex(String stuSex) {
		stu_sex = (stuSex==null||stuSex.equals(""))?SmsCommonMsgUtils.format("", STUSEXSIZE):SmsCommonMsgUtils.format(stuSex, STUSEXSIZE);
	}

	public String getStu_birth() {
		return stu_birth;
	}

	public void setStu_birth(String stuBirth) {
		stu_birth = (stuBirth == null || stuBirth.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUBIRTHSIZE) : SmsCommonMsgUtils
				.rightspaceformat(stuBirth, STUBIRTHSIZE);
	}

	public String getStu_address() {
		return stu_address;
	}

	public void setStu_address(String stuAddress) {
		stu_address = (stuAddress == null || stuAddress.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUADDRESSSIZE)
				: SmsCommonMsgUtils.rightspaceformat(stuAddress, STUADDRESSSIZE);
	}

	public String getStu_province() {
		return stu_province;
	}

	public void setStu_province(String stuProvince) {
		stu_province = (stuProvince == null || stuProvince.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", PROVINCESIZE)
				: SmsCommonMsgUtils.rightspaceformat(stuProvince, PROVINCESIZE);
	}

	public String getStu_city() {
		return stu_city;
	}

	public void setStu_city(String stuCity) {
		stu_city = (stuCity == null || stuCity.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", CITYSIZE) : SmsCommonMsgUtils
				.rightspaceformat(stuCity, CITYSIZE);
	}

	public String getStu_district() {
		return stu_district;
	}

	public void setStu_district(String stuDistrict) {
		stu_district = (stuDistrict == null || stuDistrict.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", COUNTYSIZE)
				: SmsCommonMsgUtils.rightspaceformat(stuDistrict, COUNTYSIZE);
	}

	public String getStu_school() {
		return stu_school;
	}

	public void setStu_school(String stuSchool) {
		stu_school = (stuSchool == null || stuSchool.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUSCHOOLSIZE)
				: SmsCommonMsgUtils.rightspaceformat(stuSchool, STUSCHOOLSIZE);
	}

	public String getStu_class() {
		return stu_class;
	}

	public void setStu_class(String stuClass) {
		stu_class = (stuClass == null || stuClass.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUCLASSSIZE) : SmsCommonMsgUtils
				.rightspaceformat(stuClass, STUCLASSSIZE);
	}

	public String getStu_remark() {
		return stu_remark;
	}

	public void setStu_remark(String stuRemark) {
		stu_remark = (stuRemark == null || stuRemark.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", STUREMARKSIZE)
				: SmsCommonMsgUtils.rightspaceformat(stuRemark, STUREMARKSIZE);
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacherName) {
		teacher_name = (teacherName == null || teacherName.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", TEACHERNAMESIZE)
				: SmsCommonMsgUtils.rightspaceformat(teacherName, TEACHERNAMESIZE);
	}

	public String getTeacher_tel() {
		return teacher_tel;
	}

	public void setTeacher_tel(String teacherTel) {
		teacher_tel = (teacherTel == null || teacherTel.equals("")) ? SmsCommonMsgUtils
				.format("0", TEACHERTELSIZE)
				: SmsCommonMsgUtils.format(teacherTel, TEACHERTELSIZE);
	}

	public String getRelative_name() {
		return relative_name;
	}

	public void setRelative_name(String relativeName) {
		relative_name = (relativeName == null || relativeName.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", RELATIVENAMESIZE)
				: SmsCommonMsgUtils.rightspaceformat(relativeName,
						RELATIVENAMESIZE);
	}

	public String getRelative_tel() {
		return relative_tel;
	}

	public void setRelative_tel(String relativeTel) {
		relative_tel = (relativeTel == null || relativeTel.equals("")) ? SmsCommonMsgUtils
				.format("0", RELATIVETELSIZE)
				: SmsCommonMsgUtils.format(relativeTel, RELATIVETELSIZE);
	}

	public String getRelative_type() {
		return relative_type;
	}

	public void setRelative_type(String relativeType) {
		relative_type = (relativeType == null || relativeType.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", RELATIVETYPESIZE)
				: SmsCommonMsgUtils.rightspaceformat(relativeType,
						RELATIVETYPESIZE);
	}

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = (enterpriseId == null || enterpriseId.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ENTERPRISEIDSIZE)
				: SmsCommonMsgUtils.rightspaceformat(enterpriseId,
						ENTERPRISEIDSIZE);
	}

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organizationId) {
		organization_id = (organizationId == null || organizationId.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ORGANIZATIONIDSIZE)
				: SmsCommonMsgUtils.rightspaceformat(organizationId,
						ORGANIZATIONIDSIZE);
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String validFlag) {
		valid_flag = (validFlag == null || validFlag.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", VALIDFLAGSIZE)
				: SmsCommonMsgUtils.rightspaceformat(validFlag, VALIDFLAGSIZE);
	}

	public byte[] getBytes() {
		System.out.println("SyncStuInfo:"+toString());
		try {
			return toString().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		setMsgLength(String.valueOf(total));
		setCommand(COMMAND);
		sb.append(getMsgLength());
		sb.append(getCommand());
		sb.append(getSeqLength());
		sb.append(getStu_id());
		sb.append(getStu_card_id());
		sb.append(getStu_code());
		sb.append(getStu_name());
		sb.append(getStu_sex());
		sb.append(getStu_birth());
		sb.append(getStu_address());
		sb.append(getStu_province());
		sb.append(getStu_city());
		sb.append(getStu_district());
		sb.append(getStu_school());
		sb.append(getStu_class());
		sb.append(getStu_remark());
		sb.append(getTeacher_name());
		sb.append(getTeacher_tel());
		sb.append(getRelative_name());
		sb.append(getRelative_tel());
		sb.append(getRelative_type());
		sb.append(getEnterprise_id());
		sb.append(getOrganization_id());
		sb.append(getValid_flag());
		return sb.toString();
	}

	public static void main(String[] args) {
		SyncStuInfoReq req = new SyncStuInfoReq();
		req.setStu_id("");
		System.out.println(req.getStu_id());
		System.out.println(req.getStu_id().length());
	}
}

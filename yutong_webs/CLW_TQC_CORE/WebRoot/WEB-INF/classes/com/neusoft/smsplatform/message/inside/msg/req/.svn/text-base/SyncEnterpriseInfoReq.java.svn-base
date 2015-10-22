package com.neusoft.smsplatform.message.inside.msg.req;

import java.io.UnsupportedEncodingException;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;

public class SyncEnterpriseInfoReq extends MesssageInsideMsg {

	public static final String COMMAND = "09";

	private static final int ENTERPRISEIDSIZE = 64;
	private static final int ENTERPRISECODESIZE = 64;
	private static final int FULLNAMESIZE = 256;
	private static final int SHORTNAMESIZE = 128;
	private static final int PARENTIDSIZE = 64;
	private static final int ENTERPRISECOUNTRYSIZE = 64;
	private static final int ENTERPRISEPROVINCESIZE = 64;
	private static final int ENTERPRISECITYSIZE = 64;
	private static final int ENTERPRISEDESCSIZE = 400;
	private static final int ADDRESSSIZE = 256;
	private static final int CONTACTPSIZE = 128;
	private static final int CONTACTTELSIZE = 16;
	private static final int VALIDFLAGSIZE = 2;
	public static final int total = MSGHEADERSIZE+ENTERPRISEIDSIZE + ENTERPRISECODESIZE + FULLNAMESIZE + SHORTNAMESIZE
	+ PARENTIDSIZE + ENTERPRISECOUNTRYSIZE + ENTERPRISEPROVINCESIZE + ENTERPRISECITYSIZE
	+ ENTERPRISEDESCSIZE + ADDRESSSIZE + CONTACTPSIZE + CONTACTTELSIZE
	+ VALIDFLAGSIZE ;
	private String enterprise_id;
	private String enterprise_code;
	private String full_name;
	private String short_name;
	private String parent_id;
	private String enterprise_country;
	private String enterprise_province;
	private String enterprise_city;
	private String enterprise_desc;
	private String address;
	private String contact_p;
	private String contact_tel;
	private String valid_flag;

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = (enterpriseId == null || enterpriseId.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ENTERPRISEIDSIZE)
				: SmsCommonMsgUtils.rightspaceformat(enterpriseId,
						ENTERPRISEIDSIZE);
	}

	public String getEnterprise_code() {
		return enterprise_code;
	}

	public void setEnterprise_code(String enterpriseCode) {
		enterprise_code = (enterpriseCode == null || enterpriseCode.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ENTERPRISECODESIZE)
				: SmsCommonMsgUtils.rightspaceformat(enterpriseCode,
						ENTERPRISECODESIZE);
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String fullName) {
		full_name = (fullName == null || fullName.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", FULLNAMESIZE) : SmsCommonMsgUtils
				.rightspaceformat(fullName, FULLNAMESIZE);
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String shortName) {
		short_name = (shortName == null || shortName.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", SHORTNAMESIZE)
				: SmsCommonMsgUtils.rightspaceformat(shortName, SHORTNAMESIZE);
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = (parentId == null || parentId.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", PARENTIDSIZE)
				: SmsCommonMsgUtils.rightspaceformat(parentId, PARENTIDSIZE);
	}

	public String getEnterprise_country() {
		return enterprise_country;
	}

	public void setEnterprise_country(String enterpriseCountry) {
		enterprise_country = (enterpriseCountry == null || enterpriseCountry
				.equals("")) ? SmsCommonMsgUtils.rightspaceformat("",
				ENTERPRISECOUNTRYSIZE) : SmsCommonMsgUtils.rightspaceformat(
				enterpriseCountry, ENTERPRISECOUNTRYSIZE);
	}

	public String getEnterprise_province() {
		return enterprise_province;
	}

	public void setEnterprise_province(String enterpriseProvince) {
		enterprise_province = (enterpriseProvince == null || enterpriseProvince
				.equals("")) ? SmsCommonMsgUtils.rightspaceformat("",
				ENTERPRISEPROVINCESIZE) : SmsCommonMsgUtils.rightspaceformat(
				enterpriseProvince, ENTERPRISEPROVINCESIZE);
	}

	public String getEnterprise_city() {
		return enterprise_city;
	}

	public void setEnterprise_city(String enterpriseCity) {
		enterprise_city = (enterpriseCity == null || enterpriseCity.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ENTERPRISECITYSIZE)
				: SmsCommonMsgUtils.rightspaceformat(enterpriseCity,
						ENTERPRISECITYSIZE);
	}

	public String getEnterprise_desc() {
		return enterprise_desc;
	}

	public void setEnterprise_desc(String enterpriseDesc) {
		enterprise_desc = (enterpriseDesc == null || enterpriseDesc.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ENTERPRISEDESCSIZE)
				: SmsCommonMsgUtils.rightspaceformat(enterpriseDesc,
						ENTERPRISEDESCSIZE);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = (address == null || address.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", ADDRESSSIZE) : SmsCommonMsgUtils
				.rightspaceformat(address, ADDRESSSIZE);
	}

	public String getContact_p() {
		return contact_p;
	}

	public void setContact_p(String contactP) {
		contact_p = (contactP == null || contactP.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", CONTACTPSIZE) : SmsCommonMsgUtils
				.rightspaceformat(contactP, CONTACTPSIZE);
	}

	public String getContact_tel() {
		return contact_tel;
	}

	public void setContact_tel(String contactTel) {
		contact_tel = (contactTel == null || contactTel.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", CONTACTTELSIZE) : SmsCommonMsgUtils
				.rightspaceformat(contactTel, CONTACTTELSIZE);
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String validFlag) {
		valid_flag = (validFlag == null || validFlag.equals("")) ? SmsCommonMsgUtils
				.rightspaceformat("", VALIDFLAGSIZE) : SmsCommonMsgUtils
				.rightspaceformat(validFlag, VALIDFLAGSIZE);
	}

	public byte[] getByte() {	
		System.out.println("SyncEnterprise:"+toString());
		try {
			return toString().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		setMsgLength(String.valueOf(total));
		setCommand(COMMAND);
		sb.append(getMsgLength());
		sb.append(getCommand());
		sb.append(getSeqLength());
		sb.append(getEnterprise_id());
		sb.append(getEnterprise_code());
		sb.append(getFull_name());
		sb.append(getShort_name());
		sb.append(getParent_id());
		sb.append(getEnterprise_country());
		sb.append(getEnterprise_province());
		sb.append(getEnterprise_city());
		sb.append(getEnterprise_desc());
		sb.append(getAddress());
		sb.append(getContact_p());
		sb.append(getContact_tel());
		sb.append(getValid_flag());
		return sb.toString();
	}

	public static void main(String[] args) {
		
	}
}

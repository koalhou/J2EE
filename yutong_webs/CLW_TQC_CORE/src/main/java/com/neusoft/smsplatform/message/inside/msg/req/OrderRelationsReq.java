package com.neusoft.smsplatform.message.inside.msg.req;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;

public class OrderRelationsReq extends MesssageInsideMsg {

    public static final String COMMAND = "05";
    
    public static final int STUIDSIZE = 10;
    public static final int TAGSIZE =1;
    public static final int PHONE1SIZE=11;
    public static final int RELATIVETYPE1SIZE=1;
    public static final int PHONE2SIZE=11;
    public static final int RELATIVETYPE2SIZE=1;
    public static final int PHONE3SIZE=11;
    public static final int RELATIVETYPE3SIZE=1;
    private String stu_id;
    //订购与退订
    private String tag;
    private String phone1;
    private String relative_type1;
    private String phone2;
    private String relative_type2;
    private String phone3;
    private String relative_type3;
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stuId) {
		stu_id = stuId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getRelative_type1() {
		return relative_type1;
	}
	public void setRelative_type1(String relativeType1) {
		relative_type1 = relativeType1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getRelative_type2() {
		return relative_type2;
	}
	public void setRelative_type2(String relativeType2) {
		relative_type2 = relativeType2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getRelative_type3() {
		return relative_type3;
	}
	public void setRelative_type3(String relativeType3) {
		relative_type3 = relativeType3;
	}
	
}

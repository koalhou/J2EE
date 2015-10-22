package com.neusoft.clw.sysmanage.datamanage.entimanage.domain;

public class EnterpriseDataInfo {

    private String parent_id = "";

    private String enterprise_id = "";

    private String enterprise_code = "";

    private String full_name = "";

    private String short_name = "";

    private String enterprise_country = "";
    
    private String enterprise_country_code = "";

    private String enterprise_province = "";
    
    private String enterprise_province_code = "";

    private String enterprise_city = "";
    
    private String enterprise_city_code = "";

    private String enterprise_type = "";

    private String enterprise_desc = "";

    private String address = "";

    private String email = "";

    private String postcode = "";

    private String contact_p = "";

    private String contact_tel = "";

    private String msg_num = "";

    private String img_path = "";

    private String creater = "";

    private String modifier = "";

    private String netaddress = "";

    // 新增字段
    private String enterprise_type_cfg = "";

    private String enterprise_leve_cfg = "";

    private String enterprise_kind_cfg = "";

    private String money_kind_cfg = "";

    private String language_cfg = "";

    private String isused = "";// 有效性
    
    private String en_mould;
    
    private String en_gateway;
    
    private String is_mobile_allow="";
    
    public String getIs_mobile_allow() {
		return is_mobile_allow;
	}

	public void setIs_mobile_allow(String is_mobile_allow) {
		this.is_mobile_allow = is_mobile_allow;
	}

	public String getEn_gateway() {
        return en_gateway;
    }

    public void setEn_gateway(String en_gateway) {
        this.en_gateway = en_gateway;
    }

    public String getEn_mould() {
        return en_mould;
    }

    public void setEn_mould(String en_mould) {
        this.en_mould = en_mould;
    }

    public String getEnterprise_type_cfg() {
        return enterprise_type_cfg;
    }

    public void setEnterprise_type_cfg(String enterprise_type_cfg) {
        this.enterprise_type_cfg = enterprise_type_cfg;
    }

    public String getEnterprise_leve_cfg() {
        return enterprise_leve_cfg;
    }

    public void setEnterprise_leve_cfg(String enterprise_leve_cfg) {
        this.enterprise_leve_cfg = enterprise_leve_cfg;
    }

    public String getEnterprise_kind_cfg() {
        return enterprise_kind_cfg;
    }

    public void setEnterprise_kind_cfg(String enterprise_kind_cfg) {
        this.enterprise_kind_cfg = enterprise_kind_cfg;
    }

    public String getMoney_kind_cfg() {
        return money_kind_cfg;
    }

    public void setMoney_kind_cfg(String money_kind_cfg) {
        this.money_kind_cfg = money_kind_cfg;
    }

    public String getLanguage_cfg() {
        return language_cfg;
    }

    public void setLanguage_cfg(String language_cfg) {
        this.language_cfg = language_cfg;
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    private String fax = "";

    public String getNetaddress() {
        return netaddress;
    }

    public void setNetaddress(String netaddress) {
        this.netaddress = netaddress;
    }

    private String out_flag = "";

    private String out_message = "";

    public String getContact_p() {
        return contact_p;
    }

    public void setContact_p(String contact_p) {
        this.contact_p = contact_p;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getMsg_num() {
        return msg_num;
    }

    public void setMsg_num(String msg_num) {
        this.msg_num = msg_num;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_code(String enterprise_code) {
        this.enterprise_code = enterprise_code;
    }

    public String getEnterprise_code() {
        return enterprise_code;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setEnterprise_country(String enterprise_country) {
        this.enterprise_country = enterprise_country;
    }

    public String getEnterprise_country() {
        return enterprise_country;
    }

    public void setEnterprise_province(String enterprise_province) {
        this.enterprise_province = enterprise_province;
    }

    public String getEnterprise_province() {
        return enterprise_province;
    }

    public void setEnterprise_city(String enterprise_city) {
        this.enterprise_city = enterprise_city;
    }

    public String getEnterprise_city() {
        return enterprise_city;
    }

    public void setEnterprise_type(String enterprise_type) {
        this.enterprise_type = enterprise_type;
    }

    public String getEnterprise_type() {
        return enterprise_type;
    }

    public void setEnterprise_desc(String enterprise_desc) {
        this.enterprise_desc = enterprise_desc;
    }

    public String getEnterprise_desc() {
        return enterprise_desc;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setOut_flag(String out_flag) {
        this.out_flag = out_flag;
    }

    public String getOut_flag() {
        return out_flag;
    }

    public void setOut_message(String out_message) {
        this.out_message = out_message;
    }

    public String getOut_message() {
        return out_message;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

	public String getEnterprise_country_code() {
		return enterprise_country_code;
	}

	public void setEnterprise_country_code(String enterprise_country_code) {
		this.enterprise_country_code = enterprise_country_code;
	}

	public String getEnterprise_province_code() {
		return enterprise_province_code;
	}

	public void setEnterprise_province_code(String enterprise_province_code) {
		this.enterprise_province_code = enterprise_province_code;
	}

	public String getEnterprise_city_code() {
		return enterprise_city_code;
	}

	public void setEnterprise_city_code(String enterprise_city_code) {
		this.enterprise_city_code = enterprise_city_code;
	}

}

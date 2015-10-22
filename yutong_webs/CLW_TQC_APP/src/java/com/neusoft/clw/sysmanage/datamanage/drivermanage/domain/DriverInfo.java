package com.neusoft.clw.sysmanage.datamanage.drivermanage.domain;

public class DriverInfo {
    // 主键,ID
    private String driver_id;

    // 驾驶员姓名
    private String driver_name;

    // 驾驶证号码
    private String driver_license;

    // 驾驶员卡号
    private String driver_card;
    
    // 旧驾驶员卡号
    private String old_driver_card;
    
    private String old_driver_license;
    
    // 性别0:男 1:女
    private String driver_sex;

    // 家庭住址
    private String driver_address;

    // 出生日期
    private String driver_birth;

    // 联系电话
    private String cell_number;
    
    // 备注
    private String remarks;
    
    // 初次领证日期
    private String gain_time;

    // 准驾车型
    private String driver_level;

    // 有效起始日期
    private String license_start_time;

    // 有效年限
    private String license_limit;

    // 所属企业
    private String enterprise_id;

    // 所属组织
    private String organization_id;

    // 创建人
    private String creater;

    // 创建时间
    private String create_time;

    // 最后修改人
    private String modifier;

    // 最后修改时间
    private String modify_time;

    // 驾驶员是否有效标记0:有效数据 1:无效数据
    private String valid_flag;

    // 无效设置用户
    private String vaset_user_id;

    // 无效时间
    private String vaset_time;

    // 所属企业全名称
    private String short_allname;

    private Integer total;// 总记录数

    private String query;

    private Integer page;// 当前第page页

    private Integer rp;// 每页记录数

    private String sortname;

    private String sortorder;

    // 相片删除flag
    private String photoDelFlag = "";
    
    // 相片名称
    private String photo_name;
    
    // 相片
    private byte[] photoContent = null;
    
    private String vehicle_vin;
    
    private String vehicle_ln;
    
    private String route_name;
    
    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
    public String getShort_allname() {
        return short_allname;
    }

    public void setShort_allname(String short_allname) {
        this.short_allname = short_allname;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public String getDriver_sex() {
        return driver_sex;
    }

    public void setDriver_sex(String driver_sex) {
        this.driver_sex = driver_sex;
    }

    public String getDriver_address() {
        return driver_address;
    }

    public void setDriver_address(String driver_address) {
        this.driver_address = driver_address;
    }

    public String getDriver_birth() {
        return driver_birth;
    }

    public void setDriver_birth(String driver_birth) {
        this.driver_birth = driver_birth;
    }

    public String getGain_time() {
        return gain_time;
    }

    public void setGain_time(String gain_time) {
        this.gain_time = gain_time;
    }

    public String getDriver_level() {
        return driver_level;
    }

    public void setDriver_level(String driver_level) {
        this.driver_level = driver_level;
    }

    public String getLicense_start_time() {
        return license_start_time;
    }

    public void setLicense_start_time(String license_start_time) {
        this.license_start_time = license_start_time;
    }

    public String getLicense_limit() {
        return license_limit;
    }

    public void setLicense_limit(String license_limit) {
        this.license_limit = license_limit;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
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

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the rp
     */
    public Integer getRp() {
        return rp;
    }

    /**
     * @param rp the rp to set
     */
    public void setRp(Integer rp) {
        this.rp = rp;
    }

    /**
     * @return the sortname
     */
    public String getSortname() {
        return sortname;
    }

    /**
     * @param sortname the sortname to set
     */
    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    /**
     * @return the sortorder
     */
    public String getSortorder() {
        return sortorder;
    }

    /**
     * @param sortorder the sortorder to set
     */
    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getDriver_card() {
        return driver_card;
    }

    public void setDriver_card(String driver_card) {
        this.driver_card = driver_card;
    }

    public String getOld_driver_card() {
        return old_driver_card;
    }

    public void setOld_driver_card(String old_driver_card) {
        this.old_driver_card = old_driver_card;
    }

    public String getOld_driver_license() {
		return old_driver_license;
	}

	public void setOld_driver_license(String old_driver_license) {
		this.old_driver_license = old_driver_license;
	}

	public String getCell_number() {
        return cell_number;
    }

    public void setCell_number(String cell_number) {
        this.cell_number = cell_number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhotoDelFlag() {
        return photoDelFlag;
    }

    public void setPhotoDelFlag(String photoDelFlag) {
        this.photoDelFlag = photoDelFlag;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public byte[] getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(byte[] photoContent) {
        this.photoContent = photoContent;
    }
}

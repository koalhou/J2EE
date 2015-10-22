package com.neusoft.clw.businessmanage.overload.domain;

public class OverloadInfo {
    private String vehicle_vin;

    private String vehicle_ln;

    private String photo_time;

    private String photo_desc;

    private String user_name;

    private String photo_id;

    private Integer total;// 总记录数

    private Integer page;// 当前第page页

    private Integer rp;// 每页记录数

    private String sortname;

    private String sortorder;

    private String start_time;

    private String end_time;
    
    private String organization_id;
    
    private String photo_file;
    
    private String photo_flag;
    
    private String flag_user;
    
    public String getFlag_user() {
		return flag_user;
	}

	public void setFlag_user(String flag_user) {
		this.flag_user = flag_user;
	}

	//标记为超载的时间
    private String flag_time;
    
    private String reli_user;
    
    private String reli_time;

    public String getReli_user() {
		return reli_user;
	}

	public void setReli_user(String reli_user) {
		this.reli_user = reli_user;
	}

	public String getReli_time() {
		return reli_time;
	}

	public void setReli_time(String reli_time) {
		this.reli_time = reli_time;
	}

	public String getFlag_time() {
        return flag_time;
    }

    public void setFlag_time(String flag_time) {
        this.flag_time = flag_time;
    }

    public String getPhoto_flag() {
        return photo_flag;
    }

    public void setPhoto_flag(String photo_flag) {
        this.photo_flag = photo_flag;
    }

    public String getPhoto_file() {
        return photo_file;
    }

    public void setPhoto_file(String photo_file) {
        this.photo_file = photo_file;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getPhoto_time() {
        return photo_time;
    }

    public void setPhoto_time(String photo_time) {
        this.photo_time = photo_time;
    }

    public String getPhoto_desc() {
        return photo_desc;
    }

    public void setPhoto_desc(String photo_desc) {
        this.photo_desc = photo_desc;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRp() {
        return rp;
    }

    public void setRp(Integer rp) {
        this.rp = rp;
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

package com.neusoft.clw.businessmanage.sendcommandhis.domain;

public class SendCommandDomain {
    private String vehicle_ln;
    private String vehicle_code;

    private String vehicle_vin;

    private String send_type;

    private String operate_time;

    private String operate_user_id;

    private String id;

    private String organization_id;

    private String rp;

    private String page;

    private String sortname;

    private String sortorder;

    private String operate_user_name;

    private String deal_state;

    private String start_time;

    private String end_time;

    public String getVehicle_code() {
		return vehicle_code;
	}

	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
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

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getOperate_user_id() {
        return operate_user_id;
    }

    public void setOperate_user_id(String operate_user_id) {
        this.operate_user_id = operate_user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperate_user_name() {
        return operate_user_name;
    }

    public void setOperate_user_name(String operate_user_name) {
        this.operate_user_name = operate_user_name;
    }

    public String getDeal_state() {
        return deal_state;
    }

    public void setDeal_state(String deal_state) {
        this.deal_state = deal_state;
    }

}

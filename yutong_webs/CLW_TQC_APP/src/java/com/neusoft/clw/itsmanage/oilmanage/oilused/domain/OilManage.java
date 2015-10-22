package com.neusoft.clw.itsmanage.oilmanage.oilused.domain;

/**
 * 加油数据
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 2, 2011 3:25:14 PM
 */
public class OilManage {
    private String refuel_id;

    private String vehicle_vin;

    private String refuel_amount;

    private String refuel_cost;

    private String refuel_time;

    private String money_unit;

    private String refuel_person;

    private String run_mileage;

    private String creater;

    private String create_time;

    private String modifier;

    private String modify_time;

    private String valid_flag;

    private String vaset_user_id;

    private String vaset_time;

    private String enterprise_id;

    private String organization_id;

    private String full_name;

    private String short_name;

    private String route_name;

    private String vehicle_code;

    // add by cj
    private String chooseorgid;

    private String vehicle_ln;

    // 查询时的开始时间
    private String refstart_time;

    private String refend_time;

    private String OIL_STATION;

    private String code_name;

    // 查询开始时间
    private String start_time;

    // 查询结束时间
    private String end_time;

    private String month;

    private String quarter;

    private String year;

    private String selectradio;

    private String user_id;

    private int mypgnum;

    private int myoldpagesize;

    // 选择按钮
    private String infoorgid;

    private String drivingoldorgid;

    private String detailvin;

    private Integer total;// 总记录数

    private String query;

    private Integer page;// 当前第page页

    private Integer rp;// 每页记录数

    private String sortname;

    private String sortorder;

    // add by jinp start
    private String time_list = "";

    public String getTime_list() {
        return time_list;
    }

    public void setTime_list(String time_list) {
        this.time_list = time_list;
    }

    // add by jinp stop

    public String getDetailvin() {
        return detailvin;
    }

    public void setDetailvin(String detailvin) {
        this.detailvin = detailvin;
    }

    public String getDrivingoldorgid() {
        return drivingoldorgid;
    }

    public void setDrivingoldorgid(String drivingoldorgid) {
        this.drivingoldorgid = drivingoldorgid;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSelectradio() {
        return selectradio;
    }

    public void setSelectradio(String selectradio) {
        this.selectradio = selectradio;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getMypgnum() {
        return mypgnum;
    }

    public void setMypgnum(int mypgnum) {
        this.mypgnum = mypgnum;
    }

    public int getMyoldpagesize() {
        return myoldpagesize;
    }

    public void setMyoldpagesize(int myoldpagesize) {
        this.myoldpagesize = myoldpagesize;
    }

    public String getInfoorgid() {
        return infoorgid;
    }

    public void setInfoorgid(String infoorgid) {
        this.infoorgid = infoorgid;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getVehicle_code() {
        return vehicle_code;
    }

    public void setVehicle_code(String vehicle_code) {
        this.vehicle_code = vehicle_code;
    }

    public String getOIL_STATION() {
        return OIL_STATION;
    }

    public void setOIL_STATION(String oil_station) {
        OIL_STATION = oil_station;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getRefstart_time() {
        return refstart_time;
    }

    public void setRefstart_time(String refstart_time) {
        this.refstart_time = refstart_time;
    }

    public String getRefend_time() {
        return refend_time;
    }

    public void setRefend_time(String refend_time) {
        this.refend_time = refend_time;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getChooseorgid() {
        return chooseorgid;
    }

    public void setChooseorgid(String chooseorgid) {
        this.chooseorgid = chooseorgid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getRefuel_id() {
        return refuel_id;
    }

    public void setRefuel_id(String refuel_id) {
        this.refuel_id = refuel_id;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getRefuel_amount() {
        return refuel_amount;
    }

    public void setRefuel_amount(String refuel_amount) {
        this.refuel_amount = refuel_amount;
    }

    public String getRefuel_cost() {
        return refuel_cost;
    }

    public void setRefuel_cost(String refuel_cost) {
        this.refuel_cost = refuel_cost;
    }

    public String getRefuel_time() {
        return refuel_time;
    }

    public void setRefuel_time(String refuel_time) {
        this.refuel_time = refuel_time;
    }

    public String getMoney_unit() {
        return money_unit;
    }

    public void setMoney_unit(String money_unit) {
        this.money_unit = money_unit;
    }

    public String getRefuel_person() {
        return refuel_person;
    }

    public void setRefuel_person(String refuel_person) {
        this.refuel_person = refuel_person;
    }

    public String getRun_mileage() {
        return run_mileage;
    }

    public void setRun_mileage(String run_mileage) {
        this.run_mileage = run_mileage;
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

}

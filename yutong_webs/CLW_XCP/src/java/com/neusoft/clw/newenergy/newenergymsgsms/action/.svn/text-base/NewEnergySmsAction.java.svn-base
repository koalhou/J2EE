package com.neusoft.clw.newenergy.newenergymsgsms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.newenergy.newenergymsgsms.domain.EnergyAlarmType;
import com.neusoft.clw.newenergy.newenergymsgsms.domain.EnergySms;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class NewEnergySmsAction extends PaginationAction{
	/** service共通类 */
	private transient Service service;
	/** TAB也用的MAP */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Map<String, Object> usermap = new HashMap<String, Object>();
	
	private List<EnergyAlarmType> dicList = new ArrayList<EnergyAlarmType>();
	
	/** 显示数据list **/
    private List < EnergySms > smsList;
    
    private List<EnergySms> dayList;
    
    private String routeName;
    
    private String vehicleVin;
    
    private String vehicleln;
    
    private String organization_id;
    /* 添加时用车架号 */
    private String vins;
    /* 添加时用提醒类型 */
    private String alarmTypeList;
    /* 短信发送用户及手机号 */
    private String smsNames;
    
    private String smsPhones;
    
    private String smsUserIds;
    
    private String flag;
    
    private String organizname;
    
    private List < UserInfo > userbeanList;
    
    
    
    /**
     * 获得当前操作LOGID
     * @return
     */
    private String getlogid() {
        return (String) ActionContext.getContext().getSession().get(
                Constants.LOG_USE_ID);
    }
	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession().get(
				Constants.USER_SESSION_KEY);
	}
	
	
	public String init(){
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findSmsPageList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源短信提醒信息分页处理!");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        Map<String,Object> mpas = new HashMap<String,Object>();
        try {
			
			// 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
        	
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
            }
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", user.getOrganizationID());
			}
//			if(null != organizname && organizname.length() != 0){
//				mpas.put("organizname", organizname);
//			}
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("energysms.energySmsPageListCount", mpas);
            
        	smsList = service.getObjectsByPage(
					"energysms.energySmsPageList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	
        	map = getPagination(smsList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--短信提醒信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--短信提醒信息分页处理失败！");
		}
		return SUCCESS;
	}
    
	
	public Map<String, Object> getPagination(List<EnergySms> smsList, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < smsList.size(); i++) {

        	EnergySms s = (EnergySms) smsList.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getSmsId());
            cellMap.put("cell", new Object[]{null,
            	s.getSmsId(),s.getVehicleVin(),s.getVehicleln(),
            	s.getOrgName(),s.getRouteName(),s.getAlarmType(),s.getSmsCount()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	public String iframeSmsSet (){
		
		return SUCCESS;
	}
	
	public String iframeUser(){
		return SUCCESS;
	}
	
	/**
	 * 增加新的短信提醒
	 * @return
	 */
	public String addSmsSet(){
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源短信提醒信息设置开始!");
        UserInfo user = getCurrentUser();
        List<EnergySms> addList = new ArrayList<EnergySms>();
        Map<String, Object> mpas = new HashMap<String, Object>();
        try {
        	String[] dicType = alarmTypeList.split("!");
        	String[] vehicleArr = vins.split(",");
        	String[] names = this.smsNames.split(",");
        	String[] phones = this.smsPhones.split(",");
        	String[] smsuserIds = this.smsUserIds.split(",");
        	int len = vehicleArr.length;
        	int dicLen = dicType.length;
        	int phoneLen = phones.length;
        	
        	if(vins.indexOf(",") > -1){
        		mpas.put("vins", StringUtil.toStringList(vins));
        	} else {
        		mpas.put("vehicleVin", vins);
        	}
        	
        	int a = service.delete("energysms.delVehiclesmsSet", mpas);        	
        	
        	for(int i = 0; i < len; i++){
        		String vehVin = vehicleArr[i];
        		for(int j = 0; j < dicLen; j++){
        			String dic = dicType[j];
        			String[] alarmType = dic.split(",");
        			for(int k = 0; k < phoneLen; k++){
        				String ph = phones[k];
        				String un = names[k];
        				String unid = smsuserIds[k];
        				EnergySms sms = new EnergySms();
        				sms.setSmsId(UUIDGenerator.getUUID().replace("-", ""));
        				sms.setVehicleVin(vehVin);
        				sms.setUserId(unid);
        				sms.setUserName(un);
        				sms.setTelePhone(ph);
        				sms.setCreateBy(user.getUserID());
        				if(alarmType.length == 3){
	        				sms.setAlarmAddress(alarmType[0]);
	        				sms.setAlarmLevel(alarmType[1]);
	        				sms.setAlarmCode(alarmType[2]);
        				} else {
        					sms.setAlarmAddress(alarmType[0]);
        					sms.setAlarmLevel("");
	        				sms.setAlarmCode(alarmType[1]);
        				}
        				addList.add(sms);
        			}
        		}
        	}
        	service.addBatchEnergySmsSet(addList);
        	
        	map.put("message", "success");
        	log.info("logid:" + logid + "," + " 新能源短信提醒信息设置成功!");
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error("logid:" + logid + "," + " 新能源短信提醒信息设置失败!");
        }
		return SUCCESS;
	}
	
	/**
	 * 车辆查询短信提醒设置
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findVehicleSmsSign(){
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源短信提醒信息单车查询!");
        Map<String,Object> mpas = new HashMap<String,Object>();
        try {        	
            if(null != vins && vins.length() != 0){
            	mpas.put("vehicleVin", vins);
            }
        	smsList = service.getObjects("energysms.findVehicleSmsSetSign", mpas);
        	map.put("message", "success");
        	map.put("smsCount", smsList.size());
        	map.put("smsList", smsList);
        	vehicleVin = "";
			log.info("logid:" + logid + "," + "新能源系统--新能源短信提醒信息单车查询成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--新能源短信提醒信息单车查询失败！");
		}
		return SUCCESS;
	}
	
	/**
	 * 提醒类型字典表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findSmsTypeList(){
		
		try {
			dicList = service.getObjects("energysms.findDic", null);
			map.put("message", "success");
			map.put("dicCount", dicList.size());
			map.put("dicList", dicList);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
    
	/**
	 * 查询可以设置的用户（司机+管理员）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        Map<String,Object> mpas = new HashMap<String,Object>();
        try {
			// 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            String userName = request.getParameter("userName");
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
        	mpas.put("userName", userName);
        	mpas.put("organization_id", user.getOrganizationID());
        	totalCount = service.getCount("energysms.queryUserCount", mpas);
        	userbeanList = service.getObjectsByPage("energysms.queryUser", mpas, (Integer
							.parseInt(pageIndex) - 1)* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	usermap = getUserPagination(userbeanList,totalCount,pageIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getUserPagination(List<UserInfo> userbeanList, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < userbeanList.size(); i++) {
        	UserInfo s = (UserInfo) userbeanList.get(i);
            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
            cellMap.put("id", s.getUserID());
            cellMap.put("cell", new Object[] {s.getUserName(),s.getMoblie(),s.getUserType()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
	}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public List<EnergySms> getSmsList() {
		return smsList;
	}
	public void setSmsList(List<EnergySms> smsList) {
		this.smsList = smsList;
	}
	public List<EnergySms> getDayList() {
		return dayList;
	}

	public void setDayList(List<EnergySms> dayList) {
		this.dayList = dayList;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getVehicleVin() {
		return vehicleVin;
	}

	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}

	public String getVehicleln() {
		return vehicleln;
	}

	public void setVehicleln(String vehicleln) {
		this.vehicleln = vehicleln;
	}
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public List<EnergyAlarmType> getDicList() {
		return dicList;
	}
	public void setDicList(List<EnergyAlarmType> dicList) {
		this.dicList = dicList;
	}
	public String getVins() {
		return vins;
	}
	public void setVins(String vins) {
		this.vins = vins;
	}
	public String getAlarmTypeList() {
		return alarmTypeList;
	}
	public void setAlarmTypeList(String alarmTypeList) {
		this.alarmTypeList = alarmTypeList;
	}
	public String getSmsNames() {
		return smsNames;
	}
	public void setSmsNames(String smsNames) {
		this.smsNames = smsNames;
	}
	public String getSmsPhones() {
		return smsPhones;
	}
	public void setSmsPhones(String smsPhones) {
		this.smsPhones = smsPhones;
	}
	public String getSmsUserIds() {
		return smsUserIds;
	}
	public void setSmsUserIds(String smsUserIds) {
		this.smsUserIds = smsUserIds;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOrganizname() {
		return organizname;
	}
	public void setOrganizname(String organizname) {
		this.organizname = organizname;
	}
	public List<UserInfo> getUserbeanList() {
		return userbeanList;
	}
	public void setUserbeanList(List<UserInfo> userbeanList) {
		this.userbeanList = userbeanList;
	}
	public Map<String, Object> getUsermap() {
		return usermap;
	}
	public void setUsermap(Map<String, Object> usermap) {
		this.usermap = usermap;
	}

	
	
	
}

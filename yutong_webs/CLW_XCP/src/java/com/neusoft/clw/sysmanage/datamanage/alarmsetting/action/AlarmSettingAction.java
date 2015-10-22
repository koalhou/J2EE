package com.neusoft.clw.sysmanage.datamanage.alarmsetting.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.Alarm;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class AlarmSettingAction extends PaginationAction {
	 protected final Logger log = Logger.getLogger(AlarmSettingAction.class);
	/** service共通类 */
	private transient Service service;

	private Map map = new HashMap();

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	}

	/**
	 * 
	 * @函数介绍：列表
	 * @参数：
	 * @返回值：
	 */
	public String list() {
		HttpServletRequest request = ServletActionContext.getRequest();

		map.clear();

		List<Object> grid = new ArrayList<Object>();
		Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "40");
		cellMap.put("cell", new Object[] {  "紧急告警", "SOS告警", "1" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "32");
		cellMap.put("cell", new Object[] {  "超速告警", "超速告警", "1" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		// 车辆故障
		cellMap.put("id", "10");
		cellMap.put("cell", new Object[] {  "车辆故障", "油压告警", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "67");
		cellMap.put("cell", new Object[] {  "车辆故障", "终端主电源掉电", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "66");
		cellMap.put("cell", new Object[] {  "车辆故障", "终端LCD故障", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "09");
		cellMap.put("cell", new Object[] {  "车辆故障", "制动气压告警", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "13");
		cellMap.put("cell", new Object[] {  "车辆故障", "制动蹄片磨损告警", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "65");
		cellMap.put("cell", new Object[] {  "车辆故障", "TTS模块故障", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "64");
		cellMap.put("cell", new Object[] {  "车辆故障", "摄像头故障", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "25");
		cellMap.put("cell", new Object[] {  "车辆故障", "缓速器高温告警", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "69,70,71");
		cellMap.put("cell", new Object[] {  "车辆故障", "GPS故障", "1" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "26");
		cellMap.put("cell", new Object[] {  "车辆故障", "仓温告警", "0" });
		grid.add(cellMap);
		cellMap = new LinkedHashMap<String, Object>();
		cellMap.put("id", "68");
		cellMap.put("cell", new Object[] {  "车辆故障", "终端主电源欠压", "0" });
		grid.add(cellMap);
		map.put("page", 1);// 从前台获取当前第page页
		map.put("total", 13);// 从数据库获取总记录数
		map.put("rows", grid);
		return SUCCESS;
	}

	/**
	 * 
	  * @函数介绍：查询当前用户企业的告警，没有则设置为默认的
	  * @参数：
	  * @返回值：
	 */
	public String checked() throws BusinessException{
		map.clear();
		String checked=(String)service.getObject("AlarmManage.listShow", getCurrentUser().getEntiID());
		if(!StringUtils.hasText(checked)){
			checked="40,32,10,67,66,09,13,65,64,25,69,70,71,26,68";
			Alarm alarm=new Alarm();
			alarm.setAlarm_id(checked);
			alarm.setEnterprise_id(getCurrentUser().getEntiID());
			service.insert("AlarmManage.setShow", alarm);
		}
		
		map.put("list",checked);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @throws BusinessException 
	 * @函数介绍：设置告警是否显示
	 * @参数：
	 * @返回值：
	 */
	public String set() throws BusinessException {
		map.clear();
		HttpServletRequest request = ServletActionContext.getRequest();
		String aids=request.getParameter("aids");
		log.info(aids);
		log.info(getCurrentUser().getEntiID());
		Alarm alarm=new Alarm();
		alarm.setAlarm_id(aids);
		alarm.setEnterprise_id(getCurrentUser().getEntiID());
		int i=service.getCount("AlarmManage.countShow", getCurrentUser().getEntiID());
		if(i==0){
			service.insert("AlarmManage.setShow", alarm);
		}else{
			service.update("AlarmManage.updateShow", alarm);
		}
		map.put("ret", "ok");
		
		return SUCCESS;
	}

}

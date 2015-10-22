package com.yutong.axxc.tqc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yutong.axxc.tqc.entity.site.Remind;
import com.yutong.axxc.tqc.mapper.MybatisDAO;

@Service
public class RemindService {
	private static Logger logger = LoggerFactory.getLogger(RemindService.class);


	@Autowired
	protected MybatisDAO dao;

	@Transactional
	public int saveRemind(Map<?, ?> map, String emp_code) {
		Object vehicle_vin = map.get("vehicle_vin");
		Object area_type = map.get("area_type");                   //厂区
		Object line_range = map.get("line_range");  			   //厂内厂外   1厂内    2厂外  3厂间
		Object station_id = map.get("station_id");  			   //站点ID
		Object line_id = map.get("line_id");        
		Object status_range = map.get("status_range"); 			   //0早班   1晚班
		Object line_name = map.get("line_name");
		Object remind_range = map.get("remind_range");  		   //0仅站点提醒    1站点车辆提醒
		Object remind_type = map.get("remind_type");    	       //0按时间提醒    1按提前距离提醒
		Object remind_value = map.get("remind_value");  
		Object remind_week = map.get("remind_week");   
		Object remind_status = map.get("remind_status");  		   //0没有开启   1开启提醒
		Object no_remind_date = map.get("no_remind_date");
		
		HashMap<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
		if(null != vehicle_vin){
			conditions.put("vehicle_vin", String.valueOf(vehicle_vin));
		}else{
			conditions.put("vehicle_vin", null);
		}
		if(null != area_type){
			conditions.put("area_type", String.valueOf(area_type));
		}else{
			conditions.put("area_type", null);
		}
		if(null != line_range){
			conditions.put("line_range", String.valueOf(line_range));
		}else{
			conditions.put("line_range", null);
		}
		if(null != station_id){
			conditions.put("station_id", String.valueOf(station_id));
		}else{
			conditions.put("station_id", null);
		}
		if(null != line_id){
			conditions.put("line_id", String.valueOf(line_id));
		}else{
			conditions.put("line_id", null);
		}
		if(null != status_range){
			conditions.put("status_range", String.valueOf(status_range));
		}else{
			conditions.put("status_range", null);
		}
		if(null != line_name){
			conditions.put("line_name", String.valueOf(line_name));
		}else{
			conditions.put("line_name", null);
		}
		if(null != remind_range){
			conditions.put("remind_range", String.valueOf(remind_range));
		}else{
			conditions.put("remind_range", null);
		}
		if(null != remind_type){
			conditions.put("remind_type", String.valueOf(remind_type));
			if("0".equals(String.valueOf(remind_type))){
				if(null != remind_value){
					conditions.put("time", String.valueOf(remind_value));
				}else{
					conditions.put("time", "0");
				}
				conditions.put("distance", "0");
			}
			if("1".equals(String.valueOf(remind_type))){
				if(null != remind_value){
					conditions.put("distance", String.valueOf(remind_value));
				}else{
					conditions.put("distance", "0");
				}
				conditions.put("time", "0");
			}
		}else{
			conditions.put("remind_type", "2");
			conditions.put("time", "0");
			conditions.put("distance", "0");
		}
		if(null != remind_value){
			conditions.put("remind_value", String.valueOf(remind_value));
		}else{
			conditions.put("remind_value", null);
		}
		if(null != remind_week){
			conditions.put("remind_week", String.valueOf(remind_week));
		}else{
			conditions.put("remind_week", null);
		}
		if(null != no_remind_date){
			conditions.put("no_remind_date", String.valueOf(no_remind_date));
		}else{
			conditions.put("no_remind_date", null);
		}
		if(null != remind_status){
			conditions.put("remind_status", String.valueOf(remind_status));
		}else{
			conditions.put("remind_status", null);
		}
		List<Remind> lr = dao.getList("Station.getTestRemind",conditions);
		if(lr.size()>0){
			if(conditions.get("status_range") != null && lr.get(0).getStatus_range() != null && !lr.get(0).getStatus_range().equals(conditions.get("status_range"))){
				conditions.put("note_id", lr.get(0).getNote_id());
				conditions.put("status_range", "-1");
				dao.update("Station.updateRemindDayly", conditions);
			}
			if("2".equals(lr.get(0).getRemind_range())){
				conditions.put("note_id", lr.get(0).getNote_id());
				dao.update("Station.updateRemindDayly", conditions);
			}
			logger.info("数据库中已经存在该条提醒");
			return 1;
		}
		int ret = dao.save("Station.addRemind", conditions);
		logger.info("插入用户站点提醒信息:{}", ret);
		return ret;
	}
	
	@Transactional
	public int updateRemind(Map<?, ?> map, String emp_code){
		Object note_id = map.get("id");
		Object remind_type = map.get("remind_type");
		Object remind_value = map.get("remind_value");
		Object remind_week = map.get("remind_week");
		Object no_remind_date = map.get("no_remind_date");
		Object remind_status = map.get("remind_status");
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("note_id", String.valueOf(note_id));
		if(null != remind_type){
			conditions.put("remind_type", String.valueOf(remind_type));
			if("0".equals(String.valueOf(remind_type))){
				if(null != remind_value){
					conditions.put("time", String.valueOf(remind_value));
				}else{
					conditions.put("time", "0");
				}
				conditions.put("distance", "0");
			}
			if("1".equals(String.valueOf(remind_type))){
				if(null != remind_value){
					conditions.put("distance", String.valueOf(remind_value));
				}else{
					conditions.put("distance", "0");
				}
				conditions.put("time", "0");
			}
		}else{
			conditions.put("remind_type", "2");
		}
		if(null != remind_week){
			conditions.put("remind_week", String.valueOf(remind_week));
		}else{
			conditions.put("remind_week", null);
		}
		if(null != remind_status){
			conditions.put("remind_status", String.valueOf(remind_status));
		}else{
			conditions.put("remind_status", null);
		}
		if(null != no_remind_date){
			conditions.put("no_remind_date", String.valueOf(no_remind_date));
		}else{
			conditions.put("no_remind_date", null);
		}
		Remind r = dao.get("Station.getTestRemindById",conditions);
		if(null == r){
			return 0;
		}
		int ret = 0;
		if("2".equals(conditions.get("remind_status"))){
			ret = dao.delete("Station.deleteRemind", conditions);
			logger.info("删除用户站点（厂内）提醒信息:{}", ret);
			return ret;
		}
		if("3".equals(conditions.get("remind_status"))){
			if("2".equals(r.getRemind_type()) || "0000000".equals(r.getRemind_week())){
				conditions.put("remind_status", "0");
			}else{
				conditions.put("remind_status", "1");
			}
			return dao.update("Station.updateRemindNoRemind", conditions);
			
		}
		if(r.getRemind_week() != null){
			ret = dao.update("Station.updateRemindWeekly", conditions);
		}else{
			ret = dao.update("Station.updateRemindDayly", conditions);
		}
		logger.info("更新用户站点提醒信息:{}", ret);
		return ret;
	}

}

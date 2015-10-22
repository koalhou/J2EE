package com.yutong.axxc.tqc.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.entity.env.EnvInfo;
import com.yutong.axxc.tqc.mapper.MybatisDAO;

@Service
public class LogService {
	private static Logger logger = LoggerFactory
			.getLogger(LogService.class);
	
	@Autowired
	private MybatisDAO	dao;
	
	
	@Transactional
	public int addAdviseInfo(UserInfo user) {
		return dao.save("Account.insertAdviseInfo", user);
	}
	@Transactional
	public int saveErrorInfo(Map<String, String> errorInfo){
		return dao.save("Log.insertErrorInfo", errorInfo);
	}
	
	@Transactional
	public int saveEnvInfo(EnvInfo iEnvInfo) {
		EnvInfo list = dao.get("Log.getEnv", iEnvInfo);
		if(list != null){
			return dao.update("Log.updateEnvInfo", iEnvInfo);
		}
		return dao.save("Log.insertEnvInfo", iEnvInfo);
	}
	@Transactional
	public void saveUsedTime(List list,String uid) {
		if(list!=null)
		for (Object object : list) {
			Map map=(Map)object;
			map.put("id", uid);
			dao.save("Log.insertUsedTime", map);
		}
	}
	@Transactional
	public void saveBehavior(List list,String uid) {
		if(list!=null)
			for (Object object : list) {
				Map map=(Map)object;
				map.put("id", uid);
				dao.save("Log.insertUserBehavior", map);
			}
	}
	@Transactional
	public void saveUsage(Map<String, String> map, String emp_code){
		List<String> list = Arrays.asList( map.get("module_son_id").toString().split("_"));
		map.put("MODULE_ID",list.get(0) + "_" + list.get(1));
		map.put("EMP_CODE", emp_code);
		map.put("MODULE_SON_ID", map.get("module_son_id"));
		dao.save("Log.insertUsage", map);
		
	}
}

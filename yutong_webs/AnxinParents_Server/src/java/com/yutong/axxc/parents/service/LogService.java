package com.yutong.axxc.parents.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.entity.env.EnvInfo;
import com.yutong.axxc.parents.mapper.MybatisDAO;

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
	public int saveEnvInfo(EnvInfo iEnvInfo) {
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
}

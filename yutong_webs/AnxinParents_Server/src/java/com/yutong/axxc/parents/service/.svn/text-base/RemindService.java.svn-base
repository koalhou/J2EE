package com.yutong.axxc.parents.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yutong.axxc.parents.entity.site.Remind;
import com.yutong.axxc.parents.mapper.MybatisDAO;

@Service
public class RemindService {
	private static Logger logger = LoggerFactory.getLogger(RemindService.class);


	@Autowired
	protected MybatisDAO dao;

	@Transactional
	public int saveRemind(Remind remind) {

		int ret = dao.get("Station.countStuStationRemind", remind);
		if (ret == 0) {
			ret = dao.save("Station.addRemind", remind);
		} else {
			ret = dao.save("Station.updateRemind", remind);
		}
		logger.info("更新用户站点提醒信息:{}", ret);
		return ret;
	}
	

	
}

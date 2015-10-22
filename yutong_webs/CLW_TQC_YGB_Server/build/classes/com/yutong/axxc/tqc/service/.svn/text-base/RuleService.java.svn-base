package com.yutong.axxc.tqc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.entity.pushRule.PushRuleInfo;
import com.yutong.axxc.tqc.mapper.MybatisDAO;

@Service
public class RuleService {

	private static Logger logger = LoggerFactory.getLogger(RuleService.class);

	private final static String CHILD_LEVEL_RULE_ID = "01_";
	private final static String SYSTEM_LEVEL_RULE_ID = "02_";

	@Autowired
	protected MybatisDAO dao;

	/**
	 *  
	  * 函数介绍：初始化规则，根据规则区分用户
	  * 参数：
	  * 返回值：
	 */
	@Transactional
	public int initRule(String uid, String sid) {
		int ret=0;
		if(StringUtils.hasText(sid)){
			Map map = new HashMap();
			map.put("uid", uid);
			map.put("sid", sid);
			List<PushRuleInfo> list = dao.getList("PushRules.getChildTemplateRule", map);
			if (list.size() > 0) {
				for (PushRuleInfo pushRuleInfo : list) {
					pushRuleInfo.setUsrId(uid);
					pushRuleInfo.setChildId(sid);
					ret=dao.save("PushRules.insertPersonalRule", pushRuleInfo);
					logger.info("用户{}学生{}插入规则{}结果为{}",uid,sid,pushRuleInfo,ret);
				}
			}
		}else{
			List<PushRuleInfo> list = dao.getList("PushRules.getSystemTemplateRule", uid);
			if ( list.size() > 0) {
				for (PushRuleInfo pushRuleInfo : list) {
					pushRuleInfo.setUsrId(uid);
					ret=dao.save("PushRules.insertPersonalRule", pushRuleInfo);
					logger.info("用户{}插入规则{}结果为{}",uid,pushRuleInfo,ret);
				}
			}
		}
		
		return ret;
	}

	/**
	 * 
	  * 函数介绍：删除指定用户的所有规则
	  * 参数：
	  * 返回值：
	 */
	@Transactional
	public int delRule(String uid, String sid) {
		int ret=0;
		Map map = new HashMap();
		map.put("uid", uid);
		map.put("sid", sid);
		ret=dao.delete("PushRules.delRule", map);
		logger.info("用户{}学生{}删除规则结果为{}",uid,sid,ret);
		return ret;
	}

	/**
	 * 
	  * 函数介绍：更新规则
	  * 参数：
	  * 返回值：
	 */
	@Transactional
	public int updateRule(String uid, List<Map<String, String>> list) {
		int ret = 0;
		
		for (Map<String, String> map : list) {
			map.put("usr_id", uid);
			ret = dao.save("PushRules.updatePersonalRule", map);
			logger.info("{} 更改规则，结果为{}", map,ret);
		}
		return ret;
	}

}

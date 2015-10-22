package com.yutong.axxc.parents.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.entity.UserSeesion;

@Service
public class SessionService {
	private static Logger logger = LoggerFactory
			.getLogger(SessionService.class);
	@Autowired
	private EtagService etagService;

	public void updateSession(String token, String key) {
		String sessionKey = CachedCommon.CACHED_TOKEN_KEY + token;
		UserSeesion session = (UserSeesion) etagService.get(sessionKey);
		if (session != null) {
			session.addKey(key);
		}
	}

	/**
	 * 
	 * 函数介绍：清除session中保存的緩存，并刪除session 参数： 返回值：
	 */
	public void clear(String token) {
		String sessionKey = CachedCommon.CACHED_TOKEN_KEY + token;
		UserSeesion session = (UserSeesion) etagService.get(sessionKey);
		if (session != null) {

			List<String> keys = session.getKeys();
			if (keys != null && keys.size() > 0) {
				for (String key : keys) {
					etagService.del(key);
				}
			}
		}
		boolean ret=etagService.del(sessionKey);
		logger.info("删除session{}-{}",sessionKey,ret);
	}
}

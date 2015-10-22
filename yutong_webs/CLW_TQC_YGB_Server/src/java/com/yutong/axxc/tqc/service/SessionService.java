package com.yutong.axxc.tqc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.entity.UserSeesion;

@Service
public class SessionService {
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
		etagService.del(token);
	}
}

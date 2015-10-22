package com.yutong.axxc.parents.service;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;

@Service
public class EtagService {
	private static Logger logger = LoggerFactory.getLogger(EtagService.class);
	@Autowired
	private MemcachedClient memcachedClient;

	public boolean put(String key, int minter, Object obj) {
		if (StringUtils.hasText(key)) {
			try {
				logger.debug("set cache key:{}", CachedCommon.CACHED_PRE + key);
				boolean b = memcachedClient.set(CachedCommon.CACHED_PRE + key,
						minter, obj);
				logger.info("put: key={},obj={},[{}]", key, obj, b);
				return b;
			} catch (TimeoutException e) {
				logger.error("", e);
			} catch (InterruptedException e) {
				logger.error("", e);
			} catch (MemcachedException e) {
				logger.error("", e);
			}
		}
		return false;
	}

	public Object get(String key) {
		if (StringUtils.hasText(key)) {
			try {
				Object obj = memcachedClient.get(CachedCommon.CACHED_PRE + key);
				logger.debug("get cache key:{}", CachedCommon.CACHED_PRE + key);
				return obj;
			} catch (TimeoutException e) {
				logger.error("", e);
			} catch (InterruptedException e) {
				logger.error("", e);
			} catch (MemcachedException e) {
				logger.error("", e);
			}
		}
		return null;
	}

	public boolean del(String key) {
		if (StringUtils.hasText(key)) {
			try {
				logger.debug("del cache key:{}", CachedCommon.CACHED_PRE + key);
				boolean b = memcachedClient.delete(CachedCommon.CACHED_PRE
						+ key);
				logger.info("del: key={},[{}]", key, b);
				return b;
			} catch (TimeoutException e) {
				logger.error("", e);
			} catch (InterruptedException e) {
				logger.error("", e);
			} catch (MemcachedException e) {
				logger.error("", e);
			}
		}
		return false;
	}
}

/**
 * @(#)MemcacheCache.java 2013-4-18
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.clw.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.yutong.clw.config.CachedCommon;
import com.yutong.clw.config.ModCommonConstant;


/**
 * @author <a href="mailto:majch@neusoft.com">majch </a>
 * @version $Revision 1.0 $ 2013-4-18 下午3:33:49
 */
public class MemcacheCache implements Cache {

	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory.getLogger(ModCommonConstant.LOGGER_NAME);

	private MemcachedClient client;
	private String name;

	public MemcacheCache() {

	}

	public MemcacheCache(String name, MemcachedClient client) {
		this.client = client;
		this.name = name;
	}

	/**
	 * Return the cache name.
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Return the the underlying native cache provider.
	 */
	@Override
	public Object getNativeCache() {
		return this.client;
	}

	/**
	 * Return the value to which this cache maps the specified key. Returns
	 * <code>null</code> if the cache contains no mapping for this key.
	 * 
	 * @param key
	 *            key whose associated value is to be returned.
	 * @return the value to which this cache maps the specified key, or
	 *         <code>null</code> if the cache contains no mapping for this key
	 */
	@Override
	public ValueWrapper get(Object key) {
		MDC.put("SUB_ID", "[CACHE_GET]");
		Object value = null;
		if (null == key || "".equals(key)) {
			logger.error("key值[" + key + "]非法");
			MDC.remove("SUB_ID");
			throw new IllegalArgumentException("key值[" + key + "]非法");
		} else {
			String getKey = getCacheKey(key);
			try {
				value = this.client.get(getKey);
				if (null != value) {
					logger.info("从memcached中获取key=" + getKey + "数据...");
				}
			} catch (TimeoutException e) {
				logger.error("从memcached中获取key=" + key + "数据时memcached应答超时", e);
			} catch (InterruptedException e) {
				logger.error("从memcached中获取key=" + key + "数据时发生中断异常", e);
			} catch (MemcachedException e) {
				logger.error("从memcached中获取key=" + key + "数据时发生Xmemcached内部异常", e);
			}
		}
		MDC.remove("SUB_ID");
		return (value != null ? new SimpleValueWrapper(value) : null);
	}

	/**
	 * Associate the specified value with the specified key in this cache.
	 * <p>
	 * If the cache previously contained a mapping for this key, the old value
	 * is replaced by the specified value.
	 * 
	 * @param key
	 *            the key with which the specified value is to be associated
	 * @param value
	 *            the value to be associated with the specified key
	 */
	@Override
	public void put(Object key, Object value) {
		MDC.put("SUB_ID", "[CACHE_PUT]");
		if (null == key || "".equals(key)) {
			logger.error("key值[" + key + "]非法");
			MDC.remove("SUB_ID");
			throw new IllegalArgumentException("key值[" + key + "]非法");
		} else {
			String setKey = getCacheKey(key);
			try {
				boolean cacheSetFlg = false;
				if (null != value) {
					cacheSetFlg = this.client.set(setKey, 0, value);
					if (cacheSetFlg) {
						logger.info("成功向memcached添加key=" + setKey + "数据");
					} else {
						logger.error("向memcached添加key=" + setKey + "数据操作失败");
					}
				} else {
					logger.debug("因key=" + setKey + "对应的数据内容为[" + value + "],故不添加缓存");
				}
			} catch (TimeoutException e) {
				logger.error("向memcached添加key=" + setKey + "数据时memcached应答超时", e);
			} catch (InterruptedException e) {
				logger.error("向memcached添加key=" + setKey + "数据时发生中断异常", e);
			} catch (MemcachedException e) {
				logger.error("向memcached添加key=" + setKey + "数据时发生Xmemcached内部异常", e);
			}
		}
		MDC.remove("SUB_ID");
	}
	
	

	/**
	 * Evict the mapping for this key from this cache if it is present.
	 * 
	 * @param key
	 *            the key whose mapping is to be removed from the cache
	 */
	@Override
	public void evict(Object key) {
		MDC.put("SUB_ID", "[CACHE_EVICT]");
		if (null == key || "".equals(key)) {
			logger.error("key值[" + key + "]非法");
			MDC.remove("SUB_ID");
			throw new IllegalArgumentException("key值[" + key + "]非法");
		} else {
			String evictKey = getCacheKey(key);
			try {
				boolean cacheDelFlg = this.client.delete(evictKey);
				if (cacheDelFlg) {
					logger.info("成功从memcached释放key=" + evictKey + "数据");
				} else {
					logger.error("从memcached释放key=" + evictKey + "数据失败");
				}
			} catch (TimeoutException e) {
				logger.error(
						"从memcached释放key=" + evictKey + "数据时memcached应答超时", e);
			} catch (InterruptedException e) {
				logger.error("从memcached释放key=" + evictKey + "数据时发生中断异常", e);
			} catch (MemcachedException e) {
				logger.error("从memcached释放key=" + evictKey
						+ "数据时发生Xmemcached内部异常", e);
			}
		}
		MDC.remove("SUB_ID");
	}

	/**
	 * Remove all mappings from the cache.
	 */
	@Override
	public void clear() {
		// delete all data
	}

	/**
	 * 拼装缓存Key值.
	 * @param key 传入key值
	 * @return 添加了前缀的key值.
	 */
	private String getCacheKey(Object key) {
		if (null == key) {
			throw new IllegalArgumentException("key值[" + key + "]非法");
		} else if (key instanceof String) {
			return CachedCommon.CACHED_NAME + (String) key;
		} else {
			return CachedCommon.CACHED_NAME + key.toString();
		}
	}

	/**
	 * @return Returns the client.
	 */
	public MemcachedClient getClient() {
		return client;
	}

	/**
	 * @param client
	 *            The client to set.
	 */
	@Required
	public void setClient(MemcachedClient client) {
		this.client = client;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	@Required
	public void setName(String name) {
		this.name = name;
	}

}

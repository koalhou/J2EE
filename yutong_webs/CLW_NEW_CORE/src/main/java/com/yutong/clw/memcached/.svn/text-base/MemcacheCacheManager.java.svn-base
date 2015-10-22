/**
 * @(#)MemcacheCacheManager.java 2013-4-18
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.clw.memcached;

import java.util.Collection;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

/**
 * @author <a href="mailto:majch@neusoft.com">majch </a>
 * @version $Revision 1.0 $ 2013-4-18 下午3:42:31
 */
public class MemcacheCacheManager extends AbstractCacheManager {

	private Collection<Cache> caches;
	private MemcachedClient client;

	public MemcacheCacheManager() {

	}

	public MemcacheCacheManager(MemcachedClient client) {
		setClient(client);
	}

	/**
	 * Load the caches for this cache manager. Occurs at startup.
	 * The returned collection must not be null.
	 */
	@Override
	protected Collection<? extends Cache> loadCaches() {
		return this.caches;
	}

	@Required
	public void setCaches(Collection<Cache> caches) {
		this.caches = caches;
	}

	@Required
	public void setClient(MemcachedClient client) {
		this.client = client;
		updateCaches();
	}

	public Cache getCache(String name) {
		checkState();
		Cache cache = super.getCache(name);
		if (cache == null) {
			cache = new MemcacheCache(name, client);
			addCache(cache);
		}
		return cache;
	}

	private void checkState() {
		if (client == null) {
			throw new IllegalStateException("MemcacheClient must not be null.");
		}
		// TODO check memcache state
	}

	private void updateCaches() {
		if (caches != null) {
			for (Cache cache : caches) {
				if (cache instanceof MemcacheCache) {
					MemcacheCache memcacheCache = (MemcacheCache) cache;
					memcacheCache.setClient(client);
				}
			}
		}

	}
}

package com.legendshop.core.cache;

import java.io.Serializable;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

public class MemcachedCache extends AbstractLegendCache implements
		InitializingBean {
	private final Logger log = LoggerFactory.getLogger(MemcachedCache.class);
	private String name;
	private MemcachedClient memcachedClient;
	private static final int MAX_EXPIRED_DURATION = 2592000;
	private int expiredDuration = 2592000;
	private static final Object NULL_HOLDER = new NullHolder();
	private boolean allowNullValues = true;

	public MemcachedCache() {
	}

	public MemcachedCache(LegendCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public String getName() {
		return this.name;
	}

	public MemcachedClient getNativeCache() {
		return this.memcachedClient;
	}

	public Cache.ValueWrapper get(Object key) {
		if (this.log.isDebugEnabled())
			this.log.debug("Get cache by name {}, key {}", getName(), key);

		Object value = this.memcachedClient.get(this.name + key.toString());
		return ((value != null) ? new SimpleValueWrapper(fromStoreValue(value))
				: null);
	}

	public void put(Object key, Object value) {
		if (this.log.isDebugEnabled())
			this.log.debug("put into cache {} by key {}, value {}",
					new Object[] { getName(), key, value });

		this.memcachedClient.add(this.name + key.toString(),
				this.expiredDuration, toStoreValue(value));
		putObject(key, value);
	}

	public void evict(Object key) {
		if (this.log.isDebugEnabled())
			this.log.debug("Evict from cache {} by key {}", getName(), key);

		evictObject(key);
		this.memcachedClient.delete(this.name + key.toString());
	}

	public void clear() {
		this.memcachedClient.flush();
	}

	protected Object fromStoreValue(Object storeValue) {
		if ((this.allowNullValues) && (storeValue instanceof NullHolder))
			return null;

		return storeValue;
	}

	protected Object toStoreValue(Object userValue) {
		if ((this.allowNullValues) && (userValue == null))
			return NULL_HOLDER;

		return userValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setExpiredDuration(int expiredDuration) {
		this.expiredDuration = expiredDuration;
	}

	public void setAllowNullValues(boolean allowNullValues) {
		this.allowNullValues = allowNullValues;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.memcachedClient,
				"memcachedClient must not be null!");
	}

	public String generateRelCacheKey(String relCacheKey) {
		return this.name + relCacheKey;
	}

	private static class NullHolder implements Serializable {
		private static final long serialVersionUID = -99681708140860560L;
	}
}
package com.legendshop.core.cache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

public class LegendCache extends AbstractLegendCache {
	
	private final Logger _$3 = LoggerFactory.getLogger(LegendCache.class);
	private final Ehcache _$2;

	public LegendCache(LegendCacheManager paramLegendCacheManager,
			Ehcache paramEhcache) {
		Assert.notNull(paramEhcache, "Ehcache must not be null");
		Status localStatus = paramEhcache.getStatus();
		Assert.isTrue(Status.STATUS_ALIVE.equals(localStatus),
				"An 'alive' Ehcache is required - current cache is "
						+ localStatus.toString());
		this.cacheManager = paramLegendCacheManager;
		this._$2 = paramEhcache;
	}

	public String getName() {
		return this._$2.getName();
	}

	public Ehcache getNativeCache() {
		return this._$2;
	}

	public void clear() {
		this._$2.removeAll();
	}

	public Cache.ValueWrapper get(Object paramObject) {
		Element localElement = this._$2.get(paramObject);
		if (this._$3.isDebugEnabled()) {
			this._$3.debug("----->Get cache by name {}, key {}", getName(),
					paramObject);
			if (localElement != null)
				this._$3.debug("<----- Cache  result {}",
						localElement.getObjectValue());
		}
		return ((localElement != null) ? new SimpleValueWrapper(
				localElement.getObjectValue()) : null);
	}

	public void put(Object paramObject1, Object paramObject2) {
		if (this._$3.isDebugEnabled())
			this._$3.debug("Put into cache {} by key {}, value {}",
					new Object[] { getName(), paramObject1, paramObject2 });
		this._$2.put(new Element(paramObject1, paramObject2));
		putObject(paramObject1, paramObject2);
	}

	public void evict(Object paramObject) {
		evictObject(paramObject);
		Boolean localBoolean = Boolean.valueOf(this._$2.remove(paramObject));
		if (this._$3.isDebugEnabled())
			this._$3.debug("Evict from cache {} by key {}, RESULT = {}",
					new Object[] { getName(), paramObject, localBoolean });
	}

	public String generateRelCacheKey(String paramString) {
		return paramString;
	}
}
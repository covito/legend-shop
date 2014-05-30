package com.legendshop.core.cache;

import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.BaseEntity;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public abstract class AbstractLegendCache implements Cache {
	private final Logger logger = LoggerFactory
			.getLogger(AbstractLegendCache.class);
	protected String SUFFIX = "List";
	protected LegendCacheManager cacheManager;

	protected void evictObject(Object paramObject) {
		Cache localCache1;
		if (this.logger.isDebugEnabled())
			this.logger.debug("Evict by key {}", paramObject);
		if ((this.cacheManager.isSupportQueryCache())
				&& (!(getName().endsWith(this.SUFFIX)))
				&& (!(getName().equals(this.cacheManager.getRelCacheName())))) {
			localCache1 = this.cacheManager.getCache(this.cacheManager
					.getRelCacheName());
			if (localCache1 != null) {
				String str = generateRelCacheKey(getName() + paramObject);
				SimpleValueWrapper localSimpleValueWrapper = (SimpleValueWrapper) localCache1
						.get(str);
				if (localSimpleValueWrapper != null) {
					IdListRel localIdListRel = (IdListRel) localSimpleValueWrapper
							.get();
					if ((localIdListRel != null)
							&& (localIdListRel.getRelObject() != null)) {
						Iterator localIterator = localIdListRel.getRelObject()
								.iterator();
						while (localIterator.hasNext()) {
							CacheNameAndItemWrapper localCacheNameAndItemWrapper = (CacheNameAndItemWrapper) localIterator
									.next();
							if (this.logger.isDebugEnabled())
								this.logger.debug(
										"Evict from cache {} by key {}",
										localCacheNameAndItemWrapper
												.getCacheName(),
										localCacheNameAndItemWrapper.getKey());
							Cache localCache2 = this.cacheManager
									.getCache(localCacheNameAndItemWrapper
											.getCacheName());
							localCache2.evict(localCacheNameAndItemWrapper
									.getKey());
						}
					}
				}
				if (this.logger.isDebugEnabled())
					this.logger.debug("Evict key {}  from cache {}", str,
							this.cacheManager.getRelCacheName());
				localCache1.evict(str);
			}
		} else if (this.cacheManager.isRemoveAllEntries()) {
			localCache1 = this.cacheManager.getCache(getName() + this.SUFFIX);
			if (localCache1 != null) {
				if (this.logger.isDebugEnabled())
					this.logger.debug("Evict all cache from {}",
							localCache1.getName());
				localCache1.clear();
			}
		}
	}

	protected void putObject(Object paramObject1, Object paramObject2) {
		if ((this.cacheManager.isSupportQueryCache())
				&& (getName().endsWith(this.SUFFIX))
				&& (!(getName().equals(this.cacheManager.getRelCacheName())))
				&& (paramObject2 != null)) {
			Object localObject = null;
			if (Collection.class.isAssignableFrom(paramObject2.getClass()))
				localObject = (Collection) paramObject2;
			else if (PageSupport.class
					.isAssignableFrom(paramObject2.getClass()))
				localObject = ((PageSupport) paramObject2).getResultList();
			if (localObject != null) {
				Iterator localIterator = ((Collection) localObject).iterator();
				while (localIterator.hasNext()) {
					BaseEntity localBaseEntity = (BaseEntity) localIterator
							.next();
					Cache localCache = this.cacheManager
							.getCache(this.cacheManager.getRelCacheName());
					String str = generateRelCacheKey(getName().substring(0,
							getName().length() - 4)
							+ localBaseEntity.getId());
					SimpleValueWrapper localSimpleValueWrapper = (SimpleValueWrapper) localCache
							.get(str);
					IdListRel localIdListRel = null;
					if (localSimpleValueWrapper != null)
						localIdListRel = (IdListRel) localSimpleValueWrapper
								.get();
					if (localSimpleValueWrapper == null)
						localIdListRel = new IdListRel(localBaseEntity.getId());
					if (localIdListRel.addRelObject(getName(), paramObject1)) {
						if (this.logger.isDebugEnabled())
							this.logger
									.debug("put into rel cache {} by key {}, value {}",
											new Object[] { str, str,
													localSimpleValueWrapper });
						localCache.put(str, localIdListRel);
					}
				}
			}
		}
	}

	public abstract String generateRelCacheKey(String paramString);

	public void setCacheManager(LegendCacheManager paramLegendCacheManager) {
		this.cacheManager = paramLegendCacheManager;
	}
}
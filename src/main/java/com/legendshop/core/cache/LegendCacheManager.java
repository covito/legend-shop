package com.legendshop.core.cache;

import org.springframework.cache.CacheManager;

public abstract interface LegendCacheManager extends CacheManager
{
  public abstract boolean isSupportQueryCache();

  public abstract boolean isRemoveAllEntries();

  public abstract String getRelCacheName();
}
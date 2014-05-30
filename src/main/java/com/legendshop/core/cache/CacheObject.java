package com.legendshop.core.cache;

import net.sf.ehcache.Cache;

public abstract interface CacheObject
{
  public abstract Object getCache(Cache paramCache, String paramString);

  public abstract void putInCache(Cache paramCache, String paramString, Object paramObject);

  public abstract void removeFromCache(Cache paramCache, String paramString);

  public abstract void removeFromCacheStartWithName(Cache paramCache, String paramString)
    throws Throwable;

  public abstract String getKey(String paramString, Object[] paramArrayOfObject);

  public abstract Object getObjectFromCache(String paramString, CacheCallBack paramCacheCallBack);
}
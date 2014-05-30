package com.legendshop.core.cache;

import net.sf.ehcache.Cache;

public abstract interface CacheCallBack<T>
{
  public abstract T doInCache(String paramString, Cache paramCache);
}
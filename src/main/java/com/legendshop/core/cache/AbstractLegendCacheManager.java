package com.legendshop.core.cache;

import org.springframework.cache.support.AbstractCacheManager;

public abstract class AbstractLegendCacheManager extends AbstractCacheManager
{
  protected boolean supportQueryCache;
  protected boolean removeAllEntries;
  protected final String relCacheName = "LEGENDSHOP_CACHE";

  public boolean isSupportQueryCache()
  {
    return this.supportQueryCache;
  }

  public void setSupportQueryCache(boolean paramBoolean)
  {
    this.supportQueryCache = paramBoolean;
  }

  public boolean isRemoveAllEntries()
  {
    return this.removeAllEntries;
  }

  public void setRemoveAllEntries(boolean paramBoolean)
  {
    this.removeAllEntries = paramBoolean;
  }

  public String getRelCacheName()
  {
    return "LEGENDSHOP_CACHE";
  }
}
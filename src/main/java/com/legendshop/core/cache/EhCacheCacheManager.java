package com.legendshop.core.cache;

import java.util.Collection;
import java.util.LinkedHashSet;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import org.springframework.cache.Cache;
import org.springframework.util.Assert;

public class EhCacheCacheManager extends AbstractLegendCacheManager
  implements LegendCacheManager
{
  private CacheManager _$1;

  protected Collection<Cache> loadCaches()
  {
    Assert.notNull(this._$1, "A backing EhCache CacheManager is required");
    Status localStatus = this._$1.getStatus();
    Assert.isTrue(Status.STATUS_ALIVE.equals(localStatus), "An 'alive' EhCache CacheManager is required - current cache is " + localStatus.toString());
    String[] arrayOfString1 = this._$1.getCacheNames();
    LinkedHashSet localLinkedHashSet = new LinkedHashSet(arrayOfString1.length);
    String[] arrayOfString2 = arrayOfString1;
    int i = arrayOfString2.length;
    for (int j = 0; j < i; ++j)
    {
      String str = arrayOfString2[j];
      localLinkedHashSet.add(new LegendCache(this, this._$1.getEhcache(str)));
    }
    return localLinkedHashSet;
  }

  public Cache getCache(String paramString)
  {
    Object localObject = super.getCache(paramString);
    if (localObject == null)
    {
      Ehcache localEhcache = this._$1.getEhcache(paramString);
      if (localEhcache != null)
      {
        localObject = new LegendCache(this, localEhcache);
        addCache((Cache)localObject);
      }
    }
    return ((Cache)localObject);
  }

  public void setCacheManager(CacheManager paramCacheManager)
  {
    this._$1 = paramCacheManager;
  }
}
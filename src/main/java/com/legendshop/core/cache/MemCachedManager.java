package com.legendshop.core.cache;

import java.util.Collection;
import java.util.Iterator;
import org.springframework.beans.factory.InitializingBean;

public class MemCachedManager extends AbstractLegendCacheManager
  implements LegendCacheManager, InitializingBean
{
  private Collection<? extends MemcachedCache> caches;

  public void setCaches(Collection<? extends MemcachedCache> caches)
  {
    this.caches = caches;
  }

  protected Collection<? extends MemcachedCache> loadCaches()
  {
    return this.caches;
  }

  public void afterPropertiesSet()
  {
    if (this.caches != null)
      for (Iterator localIterator = this.caches.iterator(); localIterator.hasNext(); ) { MemcachedCache cache = (MemcachedCache)localIterator.next();
        cache.setCacheManager(this);
        addCache(cache);
      }
  }
}
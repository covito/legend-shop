package com.legendshop.core.cache;

import com.legendshop.model.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

public class LegendQueryCache
  implements Cache
{
  private final Logger _$4 = LoggerFactory.getLogger(LegendQueryCache.class);
  private final LegendCacheManager _$3;
  private final Ehcache _$2;
  private final String _$1 = "List";

  public LegendQueryCache(LegendCacheManager paramLegendCacheManager, Ehcache paramEhcache)
  {
    Assert.notNull(paramEhcache, "Ehcache must not be null");
    Status localStatus = paramEhcache.getStatus();
    Assert.isTrue(Status.STATUS_ALIVE.equals(localStatus), "An 'alive' Ehcache is required - current cache is " + localStatus.toString());
    this._$3 = paramLegendCacheManager;
    this._$2 = paramEhcache;
  }

  public String getName()
  {
    return this._$2.getName();
  }

  public Ehcache getNativeCache()
  {
    return this._$2;
  }

  public void clear()
  {
    this._$2.removeAll();
  }

  public Cache.ValueWrapper get(Object paramObject)
  {
    Element localElement = this._$2.get(paramObject);
    if (localElement != null)
    {
      Object localObject = localElement.getObjectValue();
      if ((this._$3.isSupportQueryCache()) && (getName().endsWith("List")) && (Collection.class.isAssignableFrom(localObject.getClass())))
      {
        String str = getName().substring(0, getName().length() - 4);
        Collection localCollection = (Collection)localObject;
        ArrayList localArrayList = new ArrayList();
        if (localCollection == null)
          return null;
        Cache localCache = this._$3.getCache(str);
        Iterator localIterator = localCollection.iterator();
        while (localIterator.hasNext())
        {
          Serializable localSerializable = (Serializable)localIterator.next();
          Cache.ValueWrapper localValueWrapper = localCache.get(localSerializable);
          if (localValueWrapper == null)
            return null;
          localArrayList.add(localValueWrapper.get());
        }
        return new SimpleValueWrapper(localArrayList);
      }
      this._$4.info("get from cache {} by key {}, result {}", new Object[] { getName(), paramObject, localObject });
      return new SimpleValueWrapper(localObject);
    }
    return null;
  }

  public void put(Object paramObject1, Object paramObject2)
  {
    if ((getName().endsWith("List")) && (this._$3.isSupportQueryCache()) && (Collection.class.isAssignableFrom(paramObject2.getClass())))
    {
      Collection localCollection = (Collection)paramObject2;
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        String str1 = getName().substring(0, getName().length() - 4);
        Cache localCache1 = this._$3.getCache(str1);
        BaseEntity localBaseEntity = (BaseEntity)localObject;
        localArrayList.add(localBaseEntity.getId());
        localCache1.put(localBaseEntity.getId(), localObject);
        Cache localCache2 = this._$3.getCache(this._$3.getRelCacheName());
        String str2 = str1 + localBaseEntity.getId();
        IdListRel localIdListRel = (IdListRel)localCache2.get(str2);
        if (localIdListRel == null)
          localIdListRel = new IdListRel(localBaseEntity.getId());
        if (localIdListRel.addRelObject(getName(), paramObject1))
        {
          this._$4.info("put into cache {} by key {}, value {}", new Object[] { str2, localBaseEntity.getId(), localIdListRel });
          localCache2.put(str2, localIdListRel);
        }
      }
      this._$2.put(new Element(paramObject1, localArrayList));
    }
    else
    {
      this._$4.info("put into cache {} by key {}, value {}", new Object[] { getName(), paramObject1, paramObject2 });
      this._$2.put(new Element(paramObject1, paramObject2));
    }
  }

  public void evict(Object paramObject)
  {
    this._$4.info("evict from cache {} by key {}", getName(), paramObject);
    this._$2.remove(paramObject);
    if ((this._$3.isSupportQueryCache()) && (!(getName().endsWith("List"))))
    {
      Cache localCache = this._$3.getCache(this._$3.getRelCacheName());
      if (localCache != null)
      {
        String str = getName() + paramObject;
        IdListRel localIdListRel = (IdListRel)localCache.get(str);
        if (localIdListRel != null)
        {
          Iterator localIterator = localIdListRel.getRelObject().iterator();
          while (localIterator.hasNext())
          {
            CacheNameAndItemWrapper localCacheNameAndItemWrapper = (CacheNameAndItemWrapper)localIterator.next();
            this._$4.info("evict from cache {} by key {}", localCacheNameAndItemWrapper.getCacheName(), localCacheNameAndItemWrapper.getKey());
            this._$3.getCache(localCacheNameAndItemWrapper.getCacheName()).evict(localCacheNameAndItemWrapper.getKey());
          }
        }
        localCache.evict(str);
      }
    }
  }
}
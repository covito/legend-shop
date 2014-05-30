package com.legendshop.core.cache;

import com.legendshop.util.AppUtils;
import java.io.Serializable;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.dao.DataRetrievalFailureException;

public class CacheObjectImpl
  implements CacheObject
{
  private static Logger _$1 = Logger.getLogger(CacheObjectImpl.class);
  protected Cache obejctCache;

  public Object getCache(Cache paramCache, String paramString)
  {
    Element localElement = null;
    try
    {
      localElement = paramCache.get(paramString);
    }
    catch (CacheException localCacheException)
    {
      throw new DataRetrievalFailureException("Cache failure: " + localCacheException.getMessage());
    }
    if (_$1.isTraceEnabled())
      _$1.trace("Cache hit: " + (localElement != null) + "; Cache name: " + paramString + " Name: " + paramCache.getName() + " ,size: " + paramCache.getSize());
    if (localElement == null)
      return null;
    return localElement.getValue();
  }

  public void putInCache(Cache paramCache, String paramString, Object paramObject)
  {
    if (paramObject != null)
    {
      Element localElement = new Element(paramString, (Serializable)paramObject);
      if (_$1.isTraceEnabled())
        _$1.trace("Cache put: " + localElement.getKey());
      paramCache.put(localElement);
    }
    else
    {
      _$1.info("Cache is null with cahceName " + paramString);
    }
  }

  public void removeFromCache(Cache paramCache, String paramString)
  {
    paramCache.remove(paramString);
  }

  public void removeFromCacheStartWithName(Cache paramCache, String paramString)
    throws Throwable
  {
    List localList = paramCache.getKeys();
    if (AppUtils.isNotBlank(localList))
      for (int i = 0; i < localList.size(); ++i)
      {
        String str = String.valueOf(localList.get(i));
        if (str.startsWith(paramString))
          paramCache.remove(str);
      }
  }

  public String getKey(String paramString, Object[] paramArrayOfObject)
  {
    if (AppUtils.isBlank(paramString))
      return null;
    StringBuffer localStringBuffer = new StringBuffer().append(paramString);
    if (AppUtils.isNotBlank(paramArrayOfObject))
    {
      Object[] arrayOfObject = paramArrayOfObject;
      int i = arrayOfObject.length;
      for (int j = 0; j < i; ++j)
      {
        Object localObject = arrayOfObject[j];
        localStringBuffer.append(localObject);
      }
    }
    return localStringBuffer.toString();
  }

  public void setObejctCache(Cache paramCache)
  {
    this.obejctCache = paramCache;
  }

  public Object getObjectFromCache(String paramString, CacheCallBack paramCacheCallBack)
  {
    if ((AppUtils.isBlank(paramString)) || (this.obejctCache == null))
      return null;
    Object localObject = getCache(this.obejctCache, paramString);
    if (localObject == null)
    {
      localObject = paramCacheCallBack.doInCache(paramString, this.obejctCache);
      putInCache(this.obejctCache, paramString, localObject);
    }
    return localObject;
  }
}
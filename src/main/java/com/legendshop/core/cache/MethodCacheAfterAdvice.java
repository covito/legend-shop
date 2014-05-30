package com.legendshop.core.cache;

import java.lang.reflect.Method;
import java.util.List;
import net.sf.ehcache.Cache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class MethodCacheAfterAdvice
  implements AfterReturningAdvice, InitializingBean
{
  private static final Log _$2 = LogFactory.getLog(MethodCacheAfterAdvice.class);
  private Cache _$1;

  public void setCache(Cache paramCache)
  {
    this._$1 = paramCache;
  }

  public void afterReturning(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2)
    throws Throwable
  {
    String str1 = paramObject2.getClass().getName();
    List localList = this._$1.getKeys();
    for (int i = 0; i < localList.size(); ++i)
    {
      String str2 = String.valueOf(localList.get(i));
      if (str2.startsWith(str1))
      {
        this._$1.remove(str2);
        _$2.debug("remove cache " + str2);
      }
    }
  }

  public void afterPropertiesSet()
    throws Exception
  {
    Assert.notNull(this._$1, "Need a cache. Please use setCache(Cache) create it.");
  }
}
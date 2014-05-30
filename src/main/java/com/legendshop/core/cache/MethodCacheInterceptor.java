package com.legendshop.core.cache;

import java.io.Serializable;
import java.lang.reflect.Method;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class MethodCacheInterceptor
  implements MethodInterceptor, InitializingBean
{
  private static final Log _$2 = LogFactory.getLog(MethodCacheInterceptor.class);
  private Cache _$1;

  public void setCache(Cache paramCache)
  {
    this._$1 = paramCache;
  }

  public Object invoke(MethodInvocation paramMethodInvocation)
    throws Throwable
  {
    String str1 = paramMethodInvocation.getThis().getClass().getName();
    String str2 = paramMethodInvocation.getMethod().getName();
    Object[] arrayOfObject = paramMethodInvocation.getArguments();
    _$2.debug("Find object from cache is " + this._$1.getName());
    String str3 = _$1(str1, str2, arrayOfObject);
    Element localElement = this._$1.get(str3);
    if (localElement == null)
    {
      _$2.debug("Hold up method , Get method result and create cache........!");
      Object localObject = paramMethodInvocation.proceed();
      localElement = new Element(str3, (Serializable)localObject);
      this._$1.put(localElement);
    }
    return localElement.getValue();
  }

  private String _$1(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString1).append(".").append(paramString2);
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
      for (int i = 0; i < paramArrayOfObject.length; ++i)
        localStringBuffer.append(".").append(paramArrayOfObject[i]);
    return localStringBuffer.toString();
  }

  public void afterPropertiesSet()
    throws Exception
  {
    Assert.notNull(this._$1, "Need a cache. Please use setCache(Cache) create it.");
  }
}
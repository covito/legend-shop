package com.legendshop.core.cache;

import java.lang.reflect.Method;
import org.springframework.cache.interceptor.KeyGenerator;

public class StringKeyGenerator
  implements KeyGenerator
{
  public Object generate(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder(50);
    localStringBuilder.append(paramObject.getClass().getName());
    localStringBuilder.append(paramMethod.getName());
    Object[] arrayOfObject = paramArrayOfObject;
    int i = arrayOfObject.length;
    for (int j = 0; j < i; ++j)
    {
      Object localObject = arrayOfObject[j];
      if (localObject != null)
        localStringBuilder.append(localObject.toString());
      localStringBuilder.append(".");
    }
    return localStringBuilder.toString();
  }
}
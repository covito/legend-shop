package com.legendshop.core.security;

import com.legendshop.core.security.dao.ResourcesDao;
import com.legendshop.core.security.model.Resource;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class InvocationSecurityMetadataSourceService
  implements FilterInvocationSecurityMetadataSource
{
  private ResourcesDao _$2;
  private static Map<String, Collection<ConfigAttribute>> _$1 = null;

  public InvocationSecurityMetadataSourceService(ResourcesDao paramResourcesDao)
  {
    this._$2 = paramResourcesDao;
    System.out.println("加载MyInvocationSecurityMetadataSourceService..." + paramResourcesDao);
    _$1();
  }

  private void _$1()
  {
    if (_$1 == null)
    {
      _$1 = new HashMap();
      List localList = this._$2.findAll();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Resource localResource = (Resource)localIterator.next();
        Collection localCollection = this._$2.loadRoleByResource(localResource.getResString());
        System.out.println("权限=" + localCollection);
        _$1.put(localResource.getResString(), localCollection);
      }
    }
  }

  public Collection<ConfigAttribute> getAttributes(Object paramObject)
    throws IllegalArgumentException
  {
    String str1 = ((FilterInvocation)paramObject).getRequestUrl();
    System.out.println("requestUrl is " + str1);
    int i = str1.indexOf("?");
    if (i != -1)
      str1 = str1.substring(0, i);
    if (_$1 == null)
      _$1();
    Iterator localIterator = _$1.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      if (str2.equals(str1))
        return ((Collection)_$1.get(str2));
    }
    return null;
  }

  public boolean supports(Class<?> paramClass)
  {
    return true;
  }

  public Collection<ConfigAttribute> getAllConfigAttributes()
  {
    return null;
  }
}
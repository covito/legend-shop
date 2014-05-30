package com.legendshop.core.security;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager
  implements AccessDecisionManager
{
  Logger _$1 = LoggerFactory.getLogger(MyAccessDecisionManager.class);

  public void decide(Authentication paramAuthentication, Object paramObject, Collection<ConfigAttribute> paramCollection)
    throws AccessDeniedException, InsufficientAuthenticationException
  {
    this._$1.debug("decide calling {},{}", paramObject, paramCollection);
    if (paramCollection == null)
      return;
    System.out.println("decide " + paramObject.toString());
    Iterator localIterator1 = paramCollection.iterator();
    while (localIterator1.hasNext())
    {
      ConfigAttribute localConfigAttribute = (ConfigAttribute)localIterator1.next();
      String str = ((SecurityConfig)localConfigAttribute).getAttribute();
      Iterator localIterator2 = paramAuthentication.getAuthorities().iterator();
      while (localIterator2.hasNext())
      {
        GrantedAuthority localGrantedAuthority = (GrantedAuthority)localIterator2.next();
        if (str.equals(localGrantedAuthority.getAuthority()))
          return;
      }
    }
    throw new AccessDeniedException("no right");
  }

  public boolean supports(ConfigAttribute paramConfigAttribute)
  {
    return true;
  }

  public boolean supports(Class<?> paramClass)
  {
    return true;
  }
}
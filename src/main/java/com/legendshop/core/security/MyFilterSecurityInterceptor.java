package com.legendshop.core.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor
  implements Filter
{
  private Logger _$2 = LoggerFactory.getLogger(MyFilterSecurityInterceptor.class);
  private FilterInvocationSecurityMetadataSource _$1;

  public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws IOException, ServletException
  {
    this._$2.debug("doFilter calling");
    FilterInvocation localFilterInvocation = new FilterInvocation(paramServletRequest, paramServletResponse, paramFilterChain);
    invoke(localFilterInvocation);
  }

  public FilterInvocationSecurityMetadataSource getSecurityMetadataSource()
  {
    return this._$1;
  }

  public Class<? extends Object> getSecureObjectClass()
  {
    return FilterInvocation.class;
  }

  public void invoke(FilterInvocation paramFilterInvocation)
    throws IOException, ServletException
  {
    this._$2.debug("invoke calling");
    InterceptorStatusToken localInterceptorStatusToken = super.beforeInvocation(paramFilterInvocation);
    try
    {
      paramFilterInvocation.getChain().doFilter(paramFilterInvocation.getRequest(), paramFilterInvocation.getResponse());
    }
    finally
    {
      super.afterInvocation(localInterceptorStatusToken, null);
    }
  }

  public SecurityMetadataSource obtainSecurityMetadataSource()
  {
    return this._$1;
  }

  public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource paramFilterInvocationSecurityMetadataSource)
  {
    this._$1 = paramFilterInvocationSecurityMetadataSource;
  }

  public void destroy()
  {
  }

  public void init(FilterConfig paramFilterConfig)
    throws ServletException
  {
  }
}
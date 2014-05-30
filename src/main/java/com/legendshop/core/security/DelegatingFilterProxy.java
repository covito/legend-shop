package com.legendshop.core.security;

import com.legendshop.core.helper.PropertiesUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

public class DelegatingFilterProxy extends GenericFilterBean
{
  private String _$6;
  private WebApplicationContext _$5;
  private String _$4;
  private boolean _$3;
  private Filter _$2;
  private final Object _$1;

  public DelegatingFilterProxy()
  {
    this._$3 = false;
    this._$1 = new Object();
  }

  public DelegatingFilterProxy(Filter paramFilter)
  {
    this._$3 = false;
    this._$1 = new Object();
    Assert.notNull(paramFilter, "delegate Filter object must not be null");
    this._$2 = paramFilter;
  }

  public DelegatingFilterProxy(String paramString)
  {
    this(paramString, null);
  }

  public DelegatingFilterProxy(String paramString, WebApplicationContext paramWebApplicationContext)
  {
    this._$3 = false;
    this._$1 = new Object();
    Assert.hasText(paramString, "target Filter bean name must not be null or empty");
    setTargetBeanName(paramString);
    this._$5 = paramWebApplicationContext;
    if (paramWebApplicationContext != null)
      setEnvironment(paramWebApplicationContext.getEnvironment());
  }

  public void setContextAttribute(String paramString)
  {
    this._$6 = paramString;
  }

  public String getContextAttribute()
  {
    return this._$6;
  }

  public void setTargetBeanName(String paramString)
  {
    this._$4 = paramString;
  }

  protected String getTargetBeanName()
  {
    return this._$4;
  }

  public void setTargetFilterLifecycle(boolean paramBoolean)
  {
    this._$3 = paramBoolean;
  }

  protected boolean isTargetFilterLifecycle()
  {
    return this._$3;
  }

  protected void initFilterBean()
    throws ServletException
  {
    synchronized (this._$1)
    {
      if (this._$2 == null)
      {
        if (this._$4 == null)
          this._$4 = getFilterName();
        WebApplicationContext localWebApplicationContext = findWebApplicationContext();
        if (localWebApplicationContext != null)
          this._$2 = initDelegate(localWebApplicationContext);
      }
    }
  }

  public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws ServletException, IOException
  {
    Filter localFilter = null;
    synchronized (this._$1)
    {
      if ((this._$2 == null) && (PropertiesUtil.isSystemInstalled()))
      {
        WebApplicationContext localWebApplicationContext = findWebApplicationContext();
        if (localWebApplicationContext == null)
          throw new IllegalStateException("No WebApplicationContext found: no ContextLoaderListener registered?");
        this._$2 = initDelegate(localWebApplicationContext);
      }
      localFilter = this._$2;
    }
    if (localFilter != null)
      invokeDelegate(localFilter, paramServletRequest, paramServletResponse, paramFilterChain);
    else
      paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
  }

  public void destroy()
  {
    Filter localFilter = null;
    synchronized (this._$1)
    {
      localFilter = this._$2;
    }
    if (localFilter != null)
      destroyDelegate(localFilter);
  }

  protected WebApplicationContext findWebApplicationContext()
  {
    if (this._$5 != null)
    {
      if ((this._$5 instanceof ConfigurableApplicationContext) && (!(((ConfigurableApplicationContext)this._$5).isActive())))
        ((ConfigurableApplicationContext)this._$5).refresh();
      return this._$5;
    }
    String str = getContextAttribute();
    if (str != null)
      return WebApplicationContextUtils.getWebApplicationContext(getServletContext(), str);
    return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
  }

  protected Filter initDelegate(WebApplicationContext paramWebApplicationContext)
    throws ServletException
  {
    Filter localFilter = (Filter)paramWebApplicationContext.getBean(getTargetBeanName(), Filter.class);
    if (isTargetFilterLifecycle())
      localFilter.init(getFilterConfig());
    return localFilter;
  }

  protected void invokeDelegate(Filter paramFilter, ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws ServletException, IOException
  {
    paramFilter.doFilter(paramServletRequest, paramServletResponse, paramFilterChain);
  }

  protected void destroyDelegate(Filter paramFilter)
  {
    if (isTargetFilterLifecycle())
      paramFilter.destroy();
  }
}
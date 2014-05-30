package com.legendshop.util;

import java.io.PrintStream;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public final class ContextServiceLocator
  implements ServiceLocatorIF
{
  private static Logger _$4 = LoggerFactory.getLogger(ContextServiceLocator.class);
  private static ServiceLocatorIF _$3 = null;
  private ApplicationContext _$2;
  private boolean _$1 = false;

  public static ServiceLocatorIF getInstance()
  {
    if (_$3 == null)
      _$3 = new ContextServiceLocator();
    return _$3;
  }

  public static ServiceLocatorIF getInstance(String paramString)
  {
    if (_$3 == null)
    {
      _$3 = new ContextServiceLocator();
      if (_$3.getContext() == null)
        _$3.setContext(new ClassPathXmlApplicationContext(paramString));
    }
    return _$3;
  }

  private ContextServiceLocator()
  {
  }

  public ContextServiceLocator(ApplicationContext paramApplicationContext)
  {
    this._$2 = paramApplicationContext;
  }

  public ApplicationContext getContext()
  {
    return this._$2;
  }

  public Object getBean(String paramString)
  {
    if (this._$2 == null)
      return null;
    return this._$2.getBean(paramString);
  }

  public void autowireService(Object paramObject)
  {
    ((AbstractApplicationContext)this._$2).getBeanFactory().autowireBeanProperties(paramObject, 1, false);
  }

  public void setContext(ApplicationContext paramApplicationContext)
  {
    this._$2 = paramApplicationContext;
  }

  public <T> T getBean(Class<T> paramClass, String paramString)
  {
    return this._$2.getBean(paramString, paramClass);
  }

  public <T> T getBean(Class<T> paramClass)
  {
    return getBean(paramClass, paramClass.getSimpleName());
  }

  public boolean containsBean(String paramString)
  {
    return this._$2.containsBean(paramString);
  }

  public void refresh(ServletContext paramServletContext)
  {
    _$4.info("spring context refreshing");
    WebApplicationContext localWebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(paramServletContext);
    ConfigurableApplicationContext localConfigurableApplicationContext = (ConfigurableApplicationContext)localWebApplicationContext;
    localConfigurableApplicationContext.refresh();
    System.out.println("ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = " + paramServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
    paramServletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, localConfigurableApplicationContext);
    this._$2 = localConfigurableApplicationContext;
    this._$1 = true;
    _$4.info("spring context refresh successful");
  }

  public boolean isContextRefreshed()
  {
    return this._$1;
  }

  public void setContextRefreshed(boolean paramBoolean)
  {
    this._$1 = paramBoolean;
  }
}
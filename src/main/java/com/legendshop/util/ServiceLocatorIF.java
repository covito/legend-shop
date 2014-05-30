package com.legendshop.util;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;

public abstract interface ServiceLocatorIF
{
  public abstract ApplicationContext getContext();

  public abstract Object getBean(String paramString);

  public abstract void setContext(ApplicationContext paramApplicationContext);

  public abstract <T> T getBean(Class<T> paramClass, String paramString);

  public abstract <T> T getBean(Class<T> paramClass);

  public abstract boolean containsBean(String paramString);

  public abstract void refresh(ServletContext paramServletContext);

  public abstract boolean isContextRefreshed();

  public abstract void setContextRefreshed(boolean paramBoolean);
}
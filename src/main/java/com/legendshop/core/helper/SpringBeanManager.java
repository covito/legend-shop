package com.legendshop.core.helper;

import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBeanManager
{
  private static Logger _$1 = LoggerFactory.getLogger(SpringBeanManager.class);

  public static void addDataSource(ServletContext paramServletContext, DataSource paramDataSource)
    throws ClassNotFoundException
  {
    DefaultListableBeanFactory localDefaultListableBeanFactory = (DefaultListableBeanFactory)WebApplicationContextUtils.getWebApplicationContext(paramServletContext).getAutowireCapableBeanFactory();
    AbstractBeanDefinition localAbstractBeanDefinition = BeanDefinitionReaderUtils.createBeanDefinition(null, paramDataSource.getClass().getName(), WebApplicationContextUtils.getWebApplicationContext(paramServletContext).getClassLoader());
    localDefaultListableBeanFactory.registerBeanDefinition("dataSource", localAbstractBeanDefinition);
  }

  public static void removeBean(ServletContext paramServletContext, String paramString)
  {
    _$1.debug("remove beanName {} from spring contxt ", paramString);
    DefaultListableBeanFactory localDefaultListableBeanFactory = (DefaultListableBeanFactory)WebApplicationContextUtils.getWebApplicationContext(paramServletContext).getAutowireCapableBeanFactory();
    localDefaultListableBeanFactory.removeBeanDefinition(paramString);
  }

  public void removeBean(ServletContext paramServletContext, Set<String> paramSet)
  {
    if (paramSet == null)
      return;
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      removeBean(paramServletContext, str);
    }
  }
}
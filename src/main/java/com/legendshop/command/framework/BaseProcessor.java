package com.legendshop.command.framework;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.UnsatisfiedDependencyException;

public abstract class BaseProcessor
  implements InitializingBean, BeanNameAware, BeanFactoryAware, Processor
{
  protected BeanFactory beanFactory;
  private String _$3;
  private List _$2;
  private ErrorHandler _$1;

  public void setBeanName(String paramString)
  {
    this._$3 = paramString;
  }

  public void setBeanFactory(BeanFactory paramBeanFactory)
    throws BeansException
  {
    this.beanFactory = paramBeanFactory;
  }

  public void afterPropertiesSet()
    throws Exception
  {
    if (!(this.beanFactory instanceof ListableBeanFactory))
      throw new BeanInitializationException("The workflow processor [" + this._$3 + "] " + "is not managed by a ListableBeanFactory, please re-deploy using some dirivative of ListableBeanFactory such as" + "ClassPathXmlApplicationContext ");
    if ((this._$2 == null) || (this._$2.isEmpty()))
      throw new UnsatisfiedDependencyException(getBeanDesc(), this._$3, "activities", "No activities were wired for this workflow");
    Iterator localIterator = this._$2.iterator();
    while (localIterator.hasNext())
    {
      Command localCommand = (Command)localIterator.next();
      if (!(supports(localCommand)))
        throw new BeanInitializationException("The workflow processor [" + this._$3 + "] does " + "not support the activity of type" + localCommand.getClass().getName());
    }
  }

  protected String getBeanDesc()
  {
    return "Workflow Processor: " + this._$3;
  }

  public void setActivities(List paramList)
  {
    this._$2 = paramList;
  }

  public void setErrorHandler(ErrorHandler paramErrorHandler)
  {
    this._$1 = paramErrorHandler;
  }

  public List getActivities()
  {
    return this._$2;
  }

  public String getBeanName()
  {
    return this._$3;
  }

  public ErrorHandler getErrorHandler()
  {
    return this._$1;
  }

  public BeanFactory getBeanFactory()
  {
    return this.beanFactory;
  }
}
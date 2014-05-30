package com.legendshop.command.framework.facade;

import com.legendshop.command.framework.BaseProcessor;
import com.legendshop.command.framework.Request;
import com.legendshop.command.framework.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class PojoSessionFacade
  implements BeanFactoryAware
{
  private BeanFactory _$1;

  public void doActivities(Request paramRequest, Response paramResponse, String paramString)
    throws Exception
  {
    BaseProcessor localBaseProcessor = (BaseProcessor)this._$1.getBean(paramString);
    localBaseProcessor.doActivities(paramRequest, paramResponse);
  }

  public void setBeanFactory(BeanFactory paramBeanFactory)
    throws BeansException
  {
    this._$1 = paramBeanFactory;
  }
}
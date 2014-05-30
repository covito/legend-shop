package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.SystemParameter;

public abstract interface SystemParameterService
{
  public abstract SystemParameter getSystemParameter(String paramString);

  public abstract void delete(String paramString);

  public abstract void update(SystemParameter paramSystemParameter);

  public abstract PageSupport getSystemParameterList(CriteriaQuery paramCriteriaQuery);

  public abstract void initSystemParameter();
}
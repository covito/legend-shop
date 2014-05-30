package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.SystemParameter;

public abstract interface SystemParameterDao extends BaseDao
{
  public abstract void deleteSystemParameterById(String paramString);

  public abstract void updateSystemParameter(SystemParameter paramSystemParameter);
}
package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.SystemParameterDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.SystemParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemParameterDaoImpl extends BaseDaoImpl
  implements SystemParameterDao
{
  private static Logger log = LoggerFactory.getLogger(SystemParameterDaoImpl.class);

  public void deleteSystemParameterById(String id)
  {
    deleteById(SystemParameter.class, id);
  }

  public void updateSystemParameter(SystemParameter systemParameter)
  {
    update(systemParameter);
  }
}
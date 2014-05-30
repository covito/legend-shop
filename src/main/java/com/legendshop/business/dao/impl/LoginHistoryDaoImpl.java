package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.LoginHistoryDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;

public class LoginHistoryDaoImpl extends BaseDaoImpl
  implements LoginHistoryDao
{
  public PageSupport getLoginHistoryBySQL(SqlQuery query)
  {
    return find(query);
  }

  public PageSupport getLoginHistory(CriteriaQuery cq)
  {
    return find(cq);
  }
}
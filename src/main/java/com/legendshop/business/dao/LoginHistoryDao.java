package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;

public abstract interface LoginHistoryDao extends BaseDao
{
  public abstract PageSupport getLoginHistoryBySQL(SqlQuery paramSqlQuery);

  public abstract PageSupport getLoginHistory(CriteriaQuery paramCriteriaQuery);
}
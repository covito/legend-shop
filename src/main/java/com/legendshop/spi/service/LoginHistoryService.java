package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;

public abstract interface LoginHistoryService
{
  public abstract void saveLoginHistory(String paramString1, String paramString2);

  public abstract PageSupport getLoginHistory(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getLoginHistoryBySQL(SqlQuery paramSqlQuery);
}
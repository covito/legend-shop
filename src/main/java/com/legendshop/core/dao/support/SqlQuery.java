package com.legendshop.core.dao.support;

import com.legendshop.core.constant.PageProviderEnum;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlQuery extends AbstractSQLQuery
  implements Serializable
{
  private static final long serialVersionUID = -8185266081416916752L;
  private Map<String, Class> _$1;

  public SqlQuery(String paramString1, String paramString2, List paramList)
  {
    this.queryString = paramString1;
    this.allCountString = paramString2;
    this.params = paramList;
  }

  public SqlQuery(int paramInt, String paramString, PageProviderEnum paramPageProviderEnum)
  {
    this.pageSize = paramInt;
    this.curPage = paramString;
    this.pageProvider = paramPageProviderEnum;
  }

  public SqlQuery(int paramInt, String paramString)
  {
    this(paramInt, paramString, PageProviderEnum.PAGE_PROVIDER);
  }

  public SqlQuery(String paramString)
  {
    this.myaction = paramString;
  }

  public Map<String, Class> getEntityClass()
  {
    return this._$1;
  }

  public void setEntityClass(Map<String, Class> paramMap)
  {
    this._$1 = paramMap;
  }

  public void addEntityClass(String paramString, Class paramClass)
  {
    if (this._$1 == null)
      this._$1 = new HashMap();
    this._$1.put(paramString, paramClass);
  }
}
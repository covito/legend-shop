package com.legendshop.core.dao.support;

import com.legendshop.core.constant.PageProviderEnum;
import java.io.Serializable;

public class SimpleSqlQuery extends AbstractQuery
  implements Serializable
{
  private static final long serialVersionUID = 8858066913104616330L;
  protected String queryString;
  protected String allCountString;
  protected Object[] param;
  private Class<?> _$1;

  public SimpleSqlQuery(Class<?> paramClass, String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    this._$1 = paramClass;
    this.queryString = paramString1;
    this.allCountString = paramString2;
    this.param = paramArrayOfObject;
  }

  public SimpleSqlQuery(int paramInt, String paramString, PageProviderEnum paramPageProviderEnum)
  {
    this.pageSize = paramInt;
    this.curPage = paramString;
    this.pageProvider = paramPageProviderEnum;
  }

  public SimpleSqlQuery(int paramInt, String paramString)
  {
    this(paramInt, paramString, PageProviderEnum.PAGE_PROVIDER);
  }

  public SimpleSqlQuery(String paramString)
  {
    this.myaction = paramString;
  }

  public String getQueryString()
  {
    return this.queryString;
  }

  public void setQueryString(String paramString)
  {
    this.queryString = paramString;
  }

  public String getAllCountString()
  {
    return this.allCountString;
  }

  public void setAllCountString(String paramString)
  {
    this.allCountString = paramString;
  }

  public Object[] getParam()
  {
    return this.param;
  }

  public void setParam(Object[] paramArrayOfObject)
  {
    this.param = paramArrayOfObject;
  }

  public Class<?> getEntityClass()
  {
    return this._$1;
  }

  public void setEntityClass(Class<?> paramClass)
  {
    this._$1 = paramClass;
  }
}
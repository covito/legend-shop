package com.legendshop.core.dao.support;

import com.legendshop.core.constant.PageProviderEnum;
import java.io.Serializable;
import java.util.List;
import org.hibernate.type.Type;

public class HqlQuery extends AbstractSQLQuery
  implements Serializable
{
  private static final long serialVersionUID = 6728373478527870071L;

  public HqlQuery(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    this.queryString = paramString1;
    this.allCountString = paramString2;
    this.param = paramArrayOfObject;
  }

  public HqlQuery(String paramString, PageProviderEnum paramPageProviderEnum)
  {
    this.myaction = paramString;
    this.pageProvider = paramPageProviderEnum;
  }

  public HqlQuery(String paramString)
  {
    this.myaction = paramString;
  }

  public HqlQuery()
  {
  }

  public HqlQuery(int paramInt, String paramString1, String paramString2)
  {
    this.pageSize = paramInt;
    this.curPage = paramString1;
    this.myaction = paramString2;
  }

  public HqlQuery(int paramInt, String paramString)
  {
    this(paramInt, paramString, PageProviderEnum.PAGE_PROVIDER);
  }

  public HqlQuery(int paramInt, String paramString, PageProviderEnum paramPageProviderEnum)
  {
    this.pageSize = paramInt;
    this.curPage = paramString;
    this.pageProvider = paramPageProviderEnum;
  }

  public HqlQuery(String paramString1, String paramString2, String paramString3, List<Object> paramList)
  {
    this.myaction = paramString1;
    this.queryString = paramString2;
    this.allCountString = paramString3;
    this.params = paramList;
  }

  public HqlQuery(String paramString1, String paramString2, List<Object> paramList)
  {
    this.queryString = paramString1;
    this.allCountString = paramString2;
    this.params = paramList;
  }

  public HqlQuery(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Type[] paramArrayOfType)
  {
    this.myaction = paramString1;
    this.queryString = paramString2;
    this.allCountString = paramString3;
    this.param = paramArrayOfObject;
    this.types = paramArrayOfType;
  }

  public HqlQuery(String paramString, Object[] paramArrayOfObject, Type[] paramArrayOfType)
  {
    this.queryString = paramString;
    this.param = paramArrayOfObject;
    this.types = paramArrayOfType;
  }
}
package com.legendshop.core.dao.support;

import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.type.Type;

public abstract class AbstractSQLQuery extends AbstractQuery
{
  protected String queryString;
  protected String allCountString;
  protected Object[] param;
  protected Type[] types;
  protected List<Object> params = new ArrayList();

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

  public Type[] getTypes()
  {
    return this.types;
  }

  public void setTypes(Type[] paramArrayOfType)
  {
    this.types = paramArrayOfType;
  }

  public List getParams()
  {
    return this.params;
  }

  public void setParams(List paramList)
  {
    this.params = paramList;
  }

  public void addParams(Object paramObject)
  {
    if (!(AppUtils.isBlank(paramObject)))
      this.params.add(paramObject);
  }
}
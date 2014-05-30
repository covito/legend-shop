package com.legendshop.core.security.model;

import java.io.Serializable;

public class Resource
  implements Serializable
{
  private static final long serialVersionUID = -6474624754451676966L;
  private Integer _$5;
  private String _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  public Integer getId()
  {
    return this._$5;
  }

  public void setId(Integer paramInteger)
  {
    this._$5 = paramInteger;
  }

  public String getName()
  {
    return this._$4;
  }

  public void setName(String paramString)
  {
    this._$4 = paramString;
  }

  public String getResType()
  {
    return this._$3;
  }

  public void setResType(String paramString)
  {
    this._$3 = paramString;
  }

  public String getResString()
  {
    return this._$2;
  }

  public void setResString(String paramString)
  {
    this._$2 = paramString;
  }

  public String getDescn()
  {
    return this._$1;
  }

  public void setDescn(String paramString)
  {
    this._$1 = paramString;
  }
}
package com.legendshop.core;

import java.io.Serializable;

public class SystemConfig
  implements Serializable
{
  private static final long serialVersionUID = 460061477804527117L;
  private String _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  public String getShopName()
  {
    return this._$4;
  }

  public void setShopName(String paramString)
  {
    this._$4 = paramString;
  }

  public String getUrl()
  {
    return this._$3;
  }

  public void setUrl(String paramString)
  {
    this._$3 = paramString;
  }

  public String getLogo()
  {
    return this._$2;
  }

  public void setLogo(String paramString)
  {
    this._$2 = paramString;
  }

  public String getIcpInfo()
  {
    return this._$1;
  }

  public void setIcpInfo(String paramString)
  {
    this._$1 = paramString;
  }
}
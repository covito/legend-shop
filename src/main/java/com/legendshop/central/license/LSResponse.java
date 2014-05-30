package com.legendshop.central.license;

import java.io.Serializable;

public class LSResponse extends DtoEntity
  implements Serializable
{
  private static final long serialVersionUID = 5411216601389890098L;
  private String _$9;
  private String _$8;
  private String _$7;
  private String _$6;
  private String _$5;
  private String _$4;
  private int _$3;
  private Integer _$2 = Integer.valueOf(10);

  public String getIp()
  {
    return this._$9;
  }

  public void setIp(String paramString)
  {
    this._$9 = paramString;
  }

  public String getHostname()
  {
    return this._$8;
  }

  public void setHostname(String paramString)
  {
    this._$8 = paramString;
  }

  public String getDomainName()
  {
    return this._$7;
  }

  public void setDomainName(String paramString)
  {
    this._$7 = paramString;
  }

  public String getExpireDate()
  {
    return this._$6;
  }

  public void setExpireDate(String paramString)
  {
    this._$6 = paramString;
  }

  public String getNewestVersion()
  {
    return this._$5;
  }

  public void setNewestVersion(String paramString)
  {
    this._$5 = paramString;
  }

  public String getLicense()
  {
    return this._$4;
  }

  public void setLicense(String paramString)
  {
    this._$4 = paramString;
  }

  public Integer getShopCount()
  {
    return this._$2;
  }

  public void setShopCount(Integer paramInteger)
  {
    this._$2 = paramInteger;
  }

  public String toString()
  {
    return "response: returnCode = " + this._$3 + ", IP = " + this._$9 + ", hostname = " + this._$8 + ", domainName = " + this._$7 + ", expireDate = " + this._$6 + ", = newestVersion = " + this._$5 + ", license = " + this._$4 + ", shopCount = " + this._$2;
  }

  public int getReturnCode()
  {
    return this._$3;
  }

  public void setReturnCode(int paramInt)
  {
    this._$3 = paramInt;
  }
}
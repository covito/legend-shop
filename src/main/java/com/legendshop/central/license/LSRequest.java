package com.legendshop.central.license;

import java.io.Serializable;

public class LSRequest extends DtoEntity
  implements Serializable
{
  private static final long serialVersionUID = -2573903794153845729L;
  private String _$9;
  private String _$8;
  private String _$7;
  private String _$6;
  private String _$5;
  private String _$4;
  private String _$3;
  private String _$2;

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

  public String getDate()
  {
    return this._$6;
  }

  public void setDate(String paramString)
  {
    this._$6 = paramString;
  }

  public String getBusinessMode()
  {
    return this._$5;
  }

  public void setBusinessMode(String paramString)
  {
    this._$5 = paramString;
  }

  public String getLanguage()
  {
    return this._$4;
  }

  public void setLanguage(String paramString)
  {
    this._$4 = paramString;
  }

  public String toString()
  {
    return "LSRequest: " + "action = " + getAction() + ", ip = " + this._$9 + ", hostname = " + this._$8 + " ,domainName = " + this._$7 + ", date = " + this._$6 + ", businessMode = " + this._$5 + ", language = " + this._$4 + ", version=" + this._$3 + ", licenseKey = " + this._$2;
  }

  public String getVersion()
  {
    return this._$3;
  }

  public void setVersion(String paramString)
  {
    this._$3 = paramString;
  }

  public String getLicenseKey()
  {
    return this._$2;
  }

  public void setLicenseKey(String paramString)
  {
    this._$2 = paramString;
  }
}
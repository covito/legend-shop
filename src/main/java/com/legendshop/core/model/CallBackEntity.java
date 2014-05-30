package com.legendshop.core.model;

import java.io.Serializable;

public class CallBackEntity
  implements Serializable
{
  private static final long serialVersionUID = 8483149238149350917L;
  private String _$3;
  private String _$2;
  private String _$1;

  public String getCallBackTitle()
  {
    return this._$3;
  }

  public void setCallBackTitle(String paramString)
  {
    this._$3 = paramString;
  }

  public String getCallBackDesc()
  {
    return this._$2;
  }

  public void setCallBackDesc(String paramString)
  {
    this._$2 = paramString;
  }

  public String getCallBackHref()
  {
    return this._$1;
  }

  public void setCallBackHref(String paramString)
  {
    this._$1 = paramString;
  }

  public CallBackEntity(String paramString1, String paramString2, String paramString3)
  {
    this._$3 = paramString1;
    this._$2 = paramString2;
    this._$1 = paramString3;
  }
}
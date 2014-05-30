package com.legendshop.command.framework;

import java.util.Map;

public class Request extends DataTransferObject
{
  private String _$2 = null;

  public Request(Map paramMap)
  {
    super(paramMap);
  }

  public Request()
  {
  }

  public String getServiceName()
  {
    return this._$2;
  }

  public void setServiceName(String paramString)
  {
    this._$2 = paramString;
  }
}
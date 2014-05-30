package com.legendshop.command.framework.servicerepository;

import java.util.Map;

public class ObjectMetaData
  implements IMetaData
{
  private String _$2;
  private String _$1;

  public ObjectMetaData(String paramString1, String paramString2)
  {
    this._$2 = paramString1;
    this._$1 = paramString2;
  }

  public Object get(String paramString)
  {
    return paramString;
  }

  public Map get()
  {
    return null;
  }

  public Object getOne()
  {
    return this._$1;
  }
}
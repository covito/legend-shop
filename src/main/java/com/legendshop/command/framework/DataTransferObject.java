package com.legendshop.command.framework;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataTransferObject
  implements Serializable
{
  private Map _$1;

  public DataTransferObject(Map paramMap)
  {
    this._$1 = paramMap;
  }

  public DataTransferObject()
  {
    this._$1 = new HashMap();
  }

  public Map getValues()
  {
    return this._$1;
  }

  public Object getValue(Object paramObject)
  {
    return this._$1.get(paramObject);
  }

  public void setValues(Map paramMap)
  {
    this._$1.putAll(paramMap);
  }

  public void setValue(Object paramObject1, Object paramObject2)
  {
    this._$1.put(paramObject1, paramObject2);
  }
}
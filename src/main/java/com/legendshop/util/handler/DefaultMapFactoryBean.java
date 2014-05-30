package com.legendshop.util.handler;

import java.util.Map;
import org.springframework.beans.factory.config.MapFactoryBean;

public class DefaultMapFactoryBean extends MapFactoryBean
{
  private String _$1;

  protected Map createInstance()
  {
    Map localMap = super.createInstance();
    if (localMap instanceof DefaultKeyMap)
      ((DefaultKeyMap)localMap).setDefaultKey(this._$1);
    return localMap;
  }

  public String getDefaultKey()
  {
    return this._$1;
  }

  public void setDefaultKey(String paramString)
  {
    this._$1 = paramString;
  }
}
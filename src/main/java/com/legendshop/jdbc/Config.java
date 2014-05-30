package com.legendshop.jdbc;

import com.legendshop.util.DatasourcePropertiesFactory;
import com.legendshop.util.EnvironmentConfig;

public class Config
{
  private static Config _$6 = null;
  private String _$5;
  private String _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  private Config()
  {
    loadFromFile("spring/jdbc.properties");
  }

  public static Config getInstance()
  {
    if (_$6 == null)
      _$6 = new Config();
    return _$6;
  }

  public String getDbConnectString()
  {
    return this._$3;
  }

  public String getDbPasswd()
  {
    return this._$4;
  }

  public String getDbUsername()
  {
    return this._$5;
  }

  public void setDbUsername(String paramString)
  {
    this._$5 = paramString;
  }

  public void setDbPasswd(String paramString)
  {
    this._$4 = paramString;
  }

  public void setDbConnectString(String paramString)
  {
    this._$3 = paramString;
  }

  public void loadFromFile(String paramString)
  {
    this._$5 = EnvironmentConfig.getInstance().getPropertyValue(paramString, "jdbc.username");
    this._$3 = EnvironmentConfig.getInstance().getPropertyValue(paramString, "jdbc.url");
    this._$2 = EnvironmentConfig.getInstance().getPropertyValue(paramString, "jdbc.driverClassName");
    this._$1 = EnvironmentConfig.getInstance().getPropertyValue(paramString, "password.protected");
    if (getProtectedPassword())
      try
      {
        this._$4 = DatasourcePropertiesFactory.decode(EnvironmentConfig.getInstance().getPropertyValue(paramString, "jdbc.password"));
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    else
      this._$4 = EnvironmentConfig.getInstance().getPropertyValue(paramString, "jdbc.password");
  }

  public String getDbDriver()
  {
    return this._$2;
  }

  public void setDbDriver(String paramString)
  {
    this._$2 = paramString;
  }

  public boolean getProtectedPassword()
  {
    if (this._$1 == null)
      return false;
    return "true".equalsIgnoreCase(this._$1.trim());
  }

  public void setProtectedPassword(String paramString)
  {
    this._$1 = paramString;
  }
}
package com.legendshop.plugins;

public class PluginConfig
{
  private String _$8;
  private String _$7;
  private PluginStatusEnum _$6;
  private String _$5;
  private boolean _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  public String getPulginId()
  {
    return this._$8;
  }

  public void setPulginId(String paramString)
  {
    this._$8 = paramString;
  }

  public String getPulginVersion()
  {
    return this._$7;
  }

  public void setPulginVersion(String paramString)
  {
    this._$7 = paramString;
  }

  public PluginStatusEnum getStatus()
  {
    return this._$6;
  }

  public void setStatus(PluginStatusEnum paramPluginStatusEnum)
  {
    this._$6 = paramPluginStatusEnum;
  }

  public String getDescription()
  {
    return this._$5;
  }

  public void setDescription(String paramString)
  {
    this._$5 = paramString;
  }

  public boolean isRequired()
  {
    return this._$4;
  }

  public void setRequired(boolean paramBoolean)
  {
    this._$4 = paramBoolean;
  }

  public String getSpringConfiguration()
  {
    return this._$3;
  }

  public void setSpringConfiguration(String paramString)
  {
    this._$3 = paramString;
  }

  public String getI18n()
  {
    return this._$1;
  }

  public void setI18n(String paramString)
  {
    this._$1 = paramString;
  }

  public String getProvider()
  {
    return this._$2;
  }

  public void setProvider(String paramString)
  {
    this._$2 = paramString;
  }
}
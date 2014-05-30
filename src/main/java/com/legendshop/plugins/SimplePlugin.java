package com.legendshop.plugins;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Required;

public class SimplePlugin
  implements Plugin
{
  private PluginConfig _$1;

  public SimplePlugin()
  {
  }

  public SimplePlugin(PluginConfig paramPluginConfig)
  {
    this._$1 = paramPluginConfig;
  }

  @Required
  public void setPluginConfig(PluginConfig paramPluginConfig)
  {
    this._$1 = paramPluginConfig;
  }

  public void updateStatus(PluginStatusEnum paramPluginStatusEnum)
  {
    this._$1.setStatus(paramPluginStatusEnum);
  }

  public void unbind(ServletContext paramServletContext)
  {
  }

  public void bind(ServletContext paramServletContext)
  {
  }

  public PluginConfig getPluginConfig()
  {
    return this._$1;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Plugin))
      return false;
    Plugin localPlugin = (Plugin)paramObject;
    PluginConfig localPluginConfig = localPlugin.getPluginConfig();
    if (localPluginConfig == null)
      throw new PluginRuntimeException("PluginConfig not found for " + localPlugin.getClass().getSimpleName());
    String str = localPluginConfig.getPulginId();
    if (str == null)
      throw new PluginRuntimeException("pluginId not found for " + localPlugin.getClass().getSimpleName());
    return str.equals(getPluginConfig().getPulginId());
  }

  public int hashCode()
  {
    if ((getPluginConfig() != null) && (getPluginConfig().getPulginId() != null))
      return getPluginConfig().getPulginId().hashCode();
    return super.hashCode();
  }
}
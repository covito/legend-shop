package com.legendshop.plugins;

import javax.servlet.ServletContext;

public abstract interface Plugin
{
  public abstract void bind(ServletContext paramServletContext);

  public abstract void unbind(ServletContext paramServletContext);

  public abstract void updateStatus(PluginStatusEnum paramPluginStatusEnum);

  public abstract PluginConfig getPluginConfig();

  public abstract void setPluginConfig(PluginConfig paramPluginConfig);
}
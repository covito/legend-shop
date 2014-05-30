package com.legendshop.plugins;

import java.util.List;
import javax.servlet.ServletContext;

public abstract interface PluginManager
{
  public abstract List<Plugin> getPlugins();

  public abstract void registerPlugins(Plugin paramPlugin);

  public abstract String turnOn(String paramString);

  public abstract boolean isPluginRunning(String paramString);

  public abstract String turnOff(String paramString);

  public abstract void startPlugins(ServletContext paramServletContext);

  public abstract void stopPlugins(ServletContext paramServletContext);

  public abstract void savePlugin(PluginConfig paramPluginConfig);

  public abstract List<PluginConfig> getPluginConfigFromDb();
}
package com.legendshop.plugins;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader
{
  public PluginClassLoader(URL[] paramArrayOfURL, ClassLoader paramClassLoader)
  {
    super(paramArrayOfURL, paramClassLoader);
  }
}
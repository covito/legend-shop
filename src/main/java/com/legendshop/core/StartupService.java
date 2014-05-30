package com.legendshop.core;

import javax.servlet.ServletContext;

public abstract interface StartupService
{
  public abstract void startup(ServletContext paramServletContext);

  public abstract void destory(ServletContext paramServletContext);
}
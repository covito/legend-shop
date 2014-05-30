package com.legendshop.central;

import com.legendshop.plugins.SimplePlugin;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CentralPlugin extends SimplePlugin
{
  private final Logger _$1 = LoggerFactory.getLogger(CentralPlugin.class);

  public void bind(ServletContext paramServletContext)
  {
    HealthCheckerHolder.isInitialized(paramServletContext);
  }
}
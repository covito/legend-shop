package com.legendshop.central;

import com.legendshop.event.processor.BaseProcessor;
import java.io.PrintStream;
import javax.servlet.ServletContextEvent;

public class SysInitProcessor extends BaseProcessor<ServletContextEvent>
{
  public void process(ServletContextEvent paramServletContextEvent)
  {
    System.out.println("SysInitProcessor calling");
    HealthCheckerHolder.isInitialized(paramServletContextEvent.getServletContext());
  }
}
package com.legendshop.command.framework;

import java.io.Serializable;
import java.util.Map;
import org.springframework.beans.factory.BeanNameAware;

public abstract interface Command
{
  public abstract void init(String paramString)
    throws Exception;

  public abstract void execute(Map paramMap1, Map paramMap2)
    throws Exception;

  public abstract void fini()
    throws Exception;

  public abstract ErrorHandler getErrorHandler();

  public abstract String getBeanName();
}
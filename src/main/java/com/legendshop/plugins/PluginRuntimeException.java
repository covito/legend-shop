package com.legendshop.plugins;

public class PluginRuntimeException extends RuntimeException
{
  private static final long serialVersionUID = 7923496076274766942L;

  public PluginRuntimeException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }

  public PluginRuntimeException(String paramString)
  {
    super(paramString);
  }
}
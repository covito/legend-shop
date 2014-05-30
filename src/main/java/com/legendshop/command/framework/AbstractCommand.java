package com.legendshop.command.framework;

public abstract class AbstractCommand
  implements Command
{
  private ErrorHandler _$2;
  private String _$1;

  public ErrorHandler getErrorHandler()
  {
    return this._$2;
  }

  public void setBeanName(String paramString)
  {
    this._$1 = paramString;
  }

  public void setErrorHandler(ErrorHandler paramErrorHandler)
  {
    this._$2 = paramErrorHandler;
  }

  public String getBeanName()
  {
    return this._$1;
  }
}
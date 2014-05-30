package com.legendshop.command.framework;

public class StateImpl
  implements State
{
  private String _$2 = "0";
  private Throwable _$1 = null;

  public String getErrCode()
  {
    return this._$2;
  }

  public void setErrCode(String paramString)
  {
    this._$2 = paramString;
  }

  public Throwable getThrowable()
  {
    return this._$1;
  }

  public void setThrowable(Throwable paramThrowable)
  {
    this._$1 = paramThrowable;
  }

  public boolean isOK()
  {
    return (("0".equalsIgnoreCase(this._$2)) && (this._$1 == null));
  }
}
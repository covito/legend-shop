package com.legendshop.command.framework;

import java.io.Serializable;

public abstract interface State extends Serializable
{
  public static final String OK = "0";

  public abstract String getErrCode();

  public abstract void setErrCode(String paramString);

  public abstract Throwable getThrowable();

  public abstract void setThrowable(Throwable paramThrowable);

  public abstract boolean isOK();
}
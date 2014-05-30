package com.legendshop.core.exception;

public class LimitationException extends BaseException
{
  private static final long serialVersionUID = -4882836842833494358L;

  public LimitationException(String paramString)
  {
    super(paramString, "416");
  }

  public LimitationException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public LimitationException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
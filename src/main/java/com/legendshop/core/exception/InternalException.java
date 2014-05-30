package com.legendshop.core.exception;

public class InternalException extends BaseException
{
  private static final long serialVersionUID = 1134208068875011459L;

  public InternalException(String paramString)
  {
    super(paramString, "500");
  }

  public InternalException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public InternalException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
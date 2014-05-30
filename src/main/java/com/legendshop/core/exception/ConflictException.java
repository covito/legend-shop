package com.legendshop.core.exception;

public class ConflictException extends BaseException
{
  private static final long serialVersionUID = 1134208068875011459L;

  public ConflictException(String paramString)
  {
    super(paramString, "409");
  }

  public ConflictException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public ConflictException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
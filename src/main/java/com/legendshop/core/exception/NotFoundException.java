package com.legendshop.core.exception;

public class NotFoundException extends BaseException
{
  private static final long serialVersionUID = 1107139638880131340L;

  public NotFoundException(String paramString)
  {
    super(paramString, "404");
  }

  public NotFoundException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public NotFoundException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
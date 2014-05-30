package com.legendshop.core.exception;

public class InvalidFormatException extends BaseException
{
  private static final long serialVersionUID = 1474030746589727246L;

  public InvalidFormatException(String paramString)
  {
    super(paramString, "604");
  }

  public InvalidFormatException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public InvalidFormatException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
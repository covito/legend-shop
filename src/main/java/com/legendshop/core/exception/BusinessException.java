package com.legendshop.core.exception;

public class BusinessException extends BaseException
{
  private static final long serialVersionUID = -1072822638462729389L;

  public BusinessException(String paramString)
  {
    super(paramString, "998");
  }

  public BusinessException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public BusinessException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
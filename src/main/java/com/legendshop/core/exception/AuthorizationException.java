package com.legendshop.core.exception;

public class AuthorizationException extends BaseException
{
  private static final long serialVersionUID = 3853365722199544447L;

  public AuthorizationException(String paramString)
  {
    super(paramString, "401");
  }

  public AuthorizationException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public AuthorizationException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
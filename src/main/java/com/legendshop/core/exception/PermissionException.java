package com.legendshop.core.exception;

public class PermissionException extends BaseException
{
  private static final long serialVersionUID = 1474030746589727246L;

  public PermissionException(String paramString)
  {
    super(paramString, "402");
  }

  public PermissionException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public PermissionException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
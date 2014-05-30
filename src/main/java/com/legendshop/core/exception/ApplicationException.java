package com.legendshop.core.exception;

public class ApplicationException extends BaseException
{
  private static final long serialVersionUID = -8958497176157909350L;

  public ApplicationException(String paramString)
  {
    super(paramString, "999");
  }

  public ApplicationException(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public ApplicationException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramThrowable, paramString1, paramString2);
  }
}
package com.legendshop.command.framework;

public class ClientException extends RuntimeException
{
  private static final long serialVersionUID = 2848225058035251507L;
  private String _$1;

  public ClientException()
  {
  }

  public ClientException(String paramString1, String paramString2)
  {
    super(paramString1);
    this._$1 = paramString2;
  }

  public ClientException(String paramString)
  {
    super(paramString);
    this._$1 = null;
  }

  public ClientException(Throwable paramThrowable, String paramString)
  {
    super(paramThrowable);
    this._$1 = paramString;
  }

  public ClientException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    super(paramString1, paramThrowable);
    this._$1 = paramString2;
  }

  public String getErrorCode()
  {
    return this._$1;
  }

  public void setErrorCode(String paramString)
  {
    this._$1 = paramString;
  }
}
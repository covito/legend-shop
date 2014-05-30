package com.legendshop.command.framework;

import java.io.PrintStream;
import java.io.PrintWriter;

public class GoOnException extends Exception
{
  private Throwable _$2 = null;
  private String _$1;
  public static final String defaultCode = "1";
  public static final String sameAsLocal = "-1";

  public GoOnException()
  {
    this._$1 = "1";
  }

  public GoOnException(String paramString1, String paramString2)
  {
    super(paramString2);
    this._$1 = paramString1;
    if (paramString1 == null)
      this._$1 = "1";
  }

  public GoOnException(String paramString1, String paramString2, Throwable paramThrowable)
  {
    super(paramString2);
    this._$2 = paramThrowable;
    this._$1 = paramString1;
    if (paramString1 == null)
      this._$1 = "1";
  }

  public void printStackTrace()
  {
    super.printStackTrace();
    if (this._$2 != null)
    {
      System.err.println("An exception has been caused by: ");
      this._$2.printStackTrace();
    }
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    super.printStackTrace(paramPrintStream);
    if (this._$2 != null)
    {
      paramPrintStream.println("An exception has been caused by: ");
      this._$2.printStackTrace(paramPrintStream);
    }
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    super.printStackTrace(paramPrintWriter);
    if (this._$2 != null)
    {
      paramPrintWriter.println("An exception has been caused by: ");
      this._$2.printStackTrace(paramPrintWriter);
    }
  }

  public String getErrorCode()
  {
    return this._$1;
  }
}
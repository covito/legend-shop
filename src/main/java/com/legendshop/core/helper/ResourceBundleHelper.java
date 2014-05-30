package com.legendshop.core.helper;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleHelper
{
  public static String getString(String paramString)
  {
    return ResourceBundle.getBundle("i18n/ApplicationResources", _$1()).getString(paramString);
  }

  public static String getString(String paramString1, String paramString2)
  {
    String str;
    try
    {
      str = ResourceBundle.getBundle("i18n/ApplicationResources", _$1()).getString(paramString1);
      if (str == null)
        str = paramString2;
    }
    catch (Exception localException)
    {
      str = paramString2;
    }
    return str;
  }

  public static String getDeleteString()
  {
    return ResourceBundle.getBundle("i18n/ApplicationResources", _$1()).getString("entity.deleted");
  }

  public static String getSucessfulString()
  {
    return ResourceBundle.getBundle("i18n/ApplicationResources", _$1()).getString("operation.successful");
  }

  public static String getErrorString()
  {
    return ResourceBundle.getBundle("i18n/ApplicationResources", _$1()).getString("SYSTEM_ERROR");
  }

  public static String getString(Locale paramLocale, String paramString1, String paramString2)
  {
    String str;
    try
    {
      if (paramLocale != null)
        str = ResourceBundle.getBundle("i18n/ApplicationResources", paramLocale).getString(paramString1);
      else
        str = ResourceBundle.getBundle("i18n/ApplicationResources").getString(paramString1);
      if (str == null)
        str = paramString2;
    }
    catch (Exception localException)
    {
      System.out.println(localException.getLocalizedMessage());
      str = paramString2;
    }
    return str;
  }

  public static String getString(Locale paramLocale, String paramString)
  {
    String str;
    if (paramLocale != null)
      str = ResourceBundle.getBundle("i18n/ApplicationResources", paramLocale).getString(paramString);
    else
      str = ResourceBundle.getBundle("i18n/ApplicationResources").getString(paramString);
    if (str == null)
      throw new IllegalArgumentException(paramString + " was not found");
    return str;
  }

  public static String getString(Locale paramLocale, String paramString, Object[] paramArrayOfObject)
  {
    return MessageFormat.format(getString(paramLocale, paramString), paramArrayOfObject);
  }

  private static Locale _$1()
  {
    Locale localLocale = ThreadLocalContext.getLocale();
    if (localLocale == null)
      localLocale = Locale.getDefault();
    return localLocale;
  }
}
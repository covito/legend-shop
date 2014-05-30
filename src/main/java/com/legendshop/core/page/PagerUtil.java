package com.legendshop.core.page;

import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.PrintStream;
import java.util.Locale;

public class PagerUtil
{
  private static String _$1;

  public static String getBar(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
  {
    Pager localPager = null;
    try
    {
      if (paramInt2 > (int)Math.ceil(paramInt1 / paramInt3))
        paramInt2 = (int)Math.ceil(paramInt1 / paramInt3);
      if (paramInt2 < 1)
        paramInt2 = 1;
      int i = (paramInt2 - 1) * paramInt3;
      localPager = new Pager(paramInt1, i, paramInt3);
      localPager.setCurPageNO(paramInt2);
    }
    catch (Exception localException)
    {
      System.out.println("生成工具条出错!");
    }
    return localPager.getToolBar(paramString1, paramString2);
  }

  public static String getBar(String paramString, long paramLong, int paramInt1, int paramInt2)
  {
    Pager localPager = null;
    try
    {
      if (paramInt1 > (int)Math.ceil(paramLong / paramInt2))
        paramInt1 = (int)Math.ceil(paramLong / paramInt2);
      if (paramInt1 < 1)
        paramInt1 = 1;
      int i = (paramInt1 - 1) * paramInt2;
      localPager = new Pager(paramLong, i, paramInt2);
      localPager.setCurPageNO(paramInt1);
    }
    catch (Exception localException)
    {
      System.out.println("生成工具条出错!");
    }
    return localPager.getToolBar(paramString);
  }

  public static String getLocaleBar(Locale paramLocale, String paramString1, long paramLong, int paramInt1, int paramInt2, int paramInt3, String paramString2)
  {
    Pager localPager = (Pager)ContextServiceLocator.getInstance().getBean(paramString2);
    try
    {
      if (localPager == null)
        localPager = new Pager(paramLong, paramInt3, paramInt2);
      else
        localPager.init(paramLong, paramInt3, paramInt2);
      localPager.setCurPageNO(paramInt1);
    }
    catch (Exception localException)
    {
      System.out.println("生成工具条出错!");
    }
    return localPager.getBar(paramLocale, paramString1);
  }

  public static int getOffset(long paramLong, int paramInt1, int paramInt2)
  {
    int i = 0;
    try
    {
      if (paramInt1 > (int)Math.ceil(paramLong / paramInt2))
        paramInt1 = (int)Math.ceil(paramLong / paramInt2);
      if (paramInt1 < 1)
        paramInt1 = 1;
      i = (paramInt1 - 1) * paramInt2;
    }
    catch (Exception localException)
    {
      System.out.println("getOffset出错!");
    }
    return i;
  }

  public static int getCurPageNO(String paramString)
  {
    int i;
    if ((paramString == null) || ("".equals(paramString.trim())))
      i = 1;
    else
      try
      {
        i = Integer.parseInt(paramString);
      }
      catch (Exception localException)
      {
        i = 1;
      }
    return i;
  }

  public static String getPath()
  {
    return _$1;
  }

  public static void setPath(String paramString)
  {
    _$1 = paramString;
  }
}
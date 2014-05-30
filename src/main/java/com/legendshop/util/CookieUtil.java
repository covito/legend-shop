package com.legendshop.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil
{
  public static void addCookie(HttpServletResponse paramHttpServletResponse, int paramInt, String paramString1, String paramString2)
  {
    Cookie localCookie = new Cookie(paramString1, paramString2);
    localCookie.setPath("/");
    localCookie.setMaxAge(paramInt);
    paramHttpServletResponse.addCookie(localCookie);
  }

  public static void addCookie(HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2)
  {
    addCookie(paramHttpServletResponse, -1, paramString1, paramString2);
  }

  public static String getCookieValue(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    if (paramString != null)
    {
      Cookie localCookie = getCookie(paramHttpServletRequest, paramString);
      if (localCookie != null)
        return localCookie.getValue();
    }
    return null;
  }

  public static Cookie getCookie(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    if (paramHttpServletRequest == null)
      return null;
    Cookie[] arrayOfCookie = paramHttpServletRequest.getCookies();
    Cookie localCookie = null;
    try
    {
      if ((arrayOfCookie != null) && (arrayOfCookie.length > 0))
        for (int i = 0; i < arrayOfCookie.length; ++i)
        {
          localCookie = arrayOfCookie[i];
          if (localCookie.getName().equals(paramString))
            return localCookie;
        }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static boolean deleteCookie(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString)
  {
    if (paramString != null)
    {
      Cookie localCookie = getCookie(paramHttpServletRequest, paramString);
      if (localCookie != null)
      {
        localCookie.setMaxAge(0);
        localCookie.setPath("/");
        paramHttpServletResponse.addCookie(localCookie);
        return true;
      }
    }
    return false;
  }
}
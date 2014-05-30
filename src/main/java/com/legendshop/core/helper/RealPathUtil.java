package com.legendshop.core.helper;

import javax.servlet.ServletContext;

public class RealPathUtil
{
  public static String getBigPicRealPath()
  {
    return PropertiesUtil.getBigFilesAbsolutePath();
  }

  public static String getSystemRealPath(ServletContext paramServletContext)
  {
    return paramServletContext.getRealPath("/");
  }

  public static String getSmallPicRealPath()
  {
    return PropertiesUtil.getSmallFilesAbsolutePath();
  }

  public static String getFCKRealPath(String paramString)
  {
    String str1 = PropertiesUtil.getPhotoPathPrefix();
    String str2 = "";
    if ((paramString != null) && (str1 != null))
    {
      int i = paramString.indexOf(str1);
      if (i > -1)
        str2 = paramString.substring(i + str1.length() + 1);
    }
    return PropertiesUtil.getBigFilesAbsolutePath() + "/" + str2;
  }
}
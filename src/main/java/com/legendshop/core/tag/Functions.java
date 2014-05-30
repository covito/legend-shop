package com.legendshop.core.tag;

import com.legendshop.util.AppUtils;
import java.io.PrintStream;
import java.util.List;

public class Functions
{
  public static String replacePlaceHolder(String paramString1, String paramString2)
  {
    return StringConverter.replacePlaceHolderOfTemplateFromSplittedValue(paramString1, "#", paramString2, ",");
  }

  public static String replacePlaceHolderOfTemplate(String paramString, String[] paramArrayOfString)
  {
    if (AppUtils.isBlank(paramArrayOfString))
      return null;
    StringBuilder localStringBuilder = new StringBuilder(paramString);
    for (int i = 0; i < paramArrayOfString.length; ++i)
      StringConverter.replacePlaceHolderOfTemplate(localStringBuilder, "{" + i + "}", paramArrayOfString[i]);
    return localStringBuilder.toString();
  }

  public static boolean contains(List paramList, Object paramObject)
  {
    return paramList.contains(paramObject);
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(replacePlaceHolder("123 # 456 # 789 # 0", "aa,bb,cc"));
    System.out.println(replacePlaceHolderOfTemplate("I {0} {2} newway {1} ", new String[] { "am,123", "good morning", "ok" }));
  }
}
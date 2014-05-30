package com.legendshop.core.tag;

import com.legendshop.util.AppUtils;
import com.legendshop.util.StringUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.oro.text.regex.MalformedPatternException;

public class StringConverter
{
  public static String setFirstCharUpcase(String paramString)
  {
    if ((paramString == null) || (paramString.length() < 1))
      return paramString;
    char[] arrayOfChar = paramString.toCharArray();
    if ((arrayOfChar.length > 0) && (arrayOfChar[0] >= 'a') && (arrayOfChar[0] <= 'z'))
      arrayOfChar[0] = (char)((short)arrayOfChar[0] - 32);
    return String.valueOf(arrayOfChar);
  }

  public static String setFirstCharLowercase(String paramString)
  {
    if ((paramString == null) || (paramString.length() < 1))
      return paramString;
    char[] arrayOfChar = paramString.toCharArray();
    if ((arrayOfChar.length > 0) && (arrayOfChar[0] >= 'A') && (arrayOfChar[0] <= 'Z'))
      arrayOfChar[0] = (char)((short)arrayOfChar[0] + 32);
    return String.valueOf(arrayOfChar);
  }

  public static String[] toDataBaseString(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length <= 0))
      return null;
    String[] arrayOfString = new String[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; ++i)
      arrayOfString[i] = toDataBaseString(paramArrayOfString[i]);
    return arrayOfString;
  }

  public static String toDataBaseString(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() <= 0))
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    char[] arrayOfChar = paramString.toCharArray();
    for (int i = 0; i < arrayOfChar.length; ++i)
      if (Character.isUpperCase(arrayOfChar[i]))
        localStringBuffer.append("_").append(arrayOfChar[i]);
      else if (Character.isLowerCase(arrayOfChar[i]))
        localStringBuffer.append(Character.toUpperCase(arrayOfChar[i]));
      else
        localStringBuffer.append(arrayOfChar[i]);
    return localStringBuffer.toString();
  }

  public static String[] toBoString(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length <= 0))
      return null;
    String[] arrayOfString = new String[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; ++i)
      arrayOfString[i] = toBoString(paramArrayOfString[i]);
    return arrayOfString;
  }

  public static String toBoString(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() <= 0))
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    char[] arrayOfChar = paramString.toLowerCase().toCharArray();
    for (int i = 0; i < arrayOfChar.length; ++i)
      if ((arrayOfChar[i] == '_') && (i == arrayOfChar.length - 1))
      {
        localStringBuffer.append(arrayOfChar[i]);
      }
      else if ((arrayOfChar[i] == '_') && (i < arrayOfChar.length - 1))
      {
        localStringBuffer.append(Character.toUpperCase(arrayOfChar[(i + 1)]));
        ++i;
      }
      else
      {
        localStringBuffer.append(arrayOfChar[i]);
      }
    return localStringBuffer.toString();
  }

  public static String convertTemplate(String paramString1, String paramString2, HashMap paramHashMap)
    throws MalformedPatternException
  {
    String str = null;
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      FileReader localFileReader = new FileReader(paramString1);
      BufferedReader localBufferedReader = new BufferedReader(localFileReader);
      str = new String();
      while ((str = localBufferedReader.readLine()) != null)
        localStringBuffer.append(StringUtil.convert(str, paramString2, paramHashMap) + "\n");
      localBufferedReader.close();
      localFileReader.close();
    }
    catch (IOException localIOException)
    {
      System.out.println("oh! no, got an IOException error!");
      localIOException.printStackTrace();
    }
    return localStringBuffer.toString();
  }

  public static String replacePlaceHolderOfTemplate(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2)) || (AppUtils.isBlank(paramArrayOfString)))
      return paramString1;
    StringBuilder localStringBuilder = new StringBuilder(paramString1);
    int i = -1;
    int j = 0;
    while ((i = localStringBuilder.indexOf(paramString2)) >= 0)
      localStringBuilder.replace(i, i + paramString2.length(), paramArrayOfString[(j++)]);
    return localStringBuilder.toString();
  }

  public static void replacePlaceHolderOfTemplate(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    int i = -1;
    if ((i = paramStringBuilder.indexOf(paramString1)) >= 0)
      paramStringBuilder.replace(i, i + paramString1.length(), paramString2);
  }

  public static String replacePlaceHolderOfTemplateFromSplittedValue(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2)) || (StringUtils.isEmpty(paramString3)))
      return paramString1;
    return replacePlaceHolderOfTemplate(paramString1, paramString2, StringUtils.split(paramString3, paramString4));
  }
}
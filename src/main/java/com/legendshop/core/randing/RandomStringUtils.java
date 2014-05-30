package com.legendshop.core.randing;

import com.legendshop.util.des.DES2;
import java.io.PrintStream;
import java.util.Random;

public class RandomStringUtils
{
  private static final Random _$1 = new Random();

  public static String random(int paramInt)
  {
    return random(paramInt, false, false);
  }

  public static String randomAscii(int paramInt)
  {
    return random(paramInt, 32, 127, false, false);
  }

  public static String randomAlphabetic(int paramInt)
  {
    return random(paramInt, true, false);
  }

  public static String randomAlphanumeric(int paramInt)
  {
    return random(paramInt, true, true);
  }

  public static String randomNumeric(int paramInt)
  {
    return randomNumeric(paramInt, 4);
  }

  public static String randomNumeric(int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramInt2; ++i)
    {
      int j = _$1.nextInt(10);
      localStringBuilder.append(String.valueOf(j));
    }
    return localStringBuilder.toString();
  }

  public static String randomLetter(int paramInt)
  {
    DES2 localDES2 = new DES2();
    String str1 = random(paramInt, false, true);
    String str2 = "1234";
    try
    {
      str2 = localDES2.byteToString(localDES2.createEncryptor(str1));
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str2;
  }

  public static String random(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    return random(paramInt, 0, 0, paramBoolean1, paramBoolean2);
  }

  public static String random(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    return random(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, null);
  }

  public static String random(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, char[] paramArrayOfChar)
  {
    if ((paramInt2 == 0) && (paramInt3 == 0))
    {
      paramInt3 = 122;
      paramInt2 = 32;
      if ((!(paramBoolean1)) && (!(paramBoolean2)))
      {
        paramInt2 = 0;
        paramInt3 = 2147483647;
      }
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramInt3 - paramInt2;
    while (paramInt1-- != 0)
    {
      char c;
      if (paramArrayOfChar == null)
        c = (char)(_$1.nextInt(i) + paramInt2);
      else
        c = paramArrayOfChar[(_$1.nextInt(i) + paramInt2)];
      if (((paramBoolean1) && (paramBoolean2) && (Character.isLetterOrDigit(c))) || ((paramBoolean1) && (Character.isLetter(c))) || ((paramBoolean2) && (Character.isDigit(c))) || ((!(paramBoolean1)) && (!(paramBoolean2))))
        localStringBuffer.append(c);
      else
        ++paramInt1;
    }
    return localStringBuffer.toString();
  }

  public static String random(int paramInt, String paramString)
  {
    return random(paramInt, paramString.toCharArray());
  }

  public static String random(int paramInt, char[] paramArrayOfChar)
  {
    return random(paramInt, 0, paramArrayOfChar.length - 1, false, false, paramArrayOfChar);
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(randomNumeric(3, 6));
  }
}
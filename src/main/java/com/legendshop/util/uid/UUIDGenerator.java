package com.legendshop.util.uid;

import java.io.PrintStream;
import java.net.InetAddress;

public class UUIDGenerator
{
  private static final int _$3;
  private static short _$2;
  private static final int _$1;

  protected static short getHiTime()
  {
    return (short)(int)(System.currentTimeMillis() >>> 32);
  }

  protected static int getLoTime()
  {
    return (int)System.currentTimeMillis();
  }

  protected static String format(int paramInt)
  {
    String str = Integer.toHexString(paramInt);
    StringBuffer localStringBuffer = new StringBuffer("00000000");
    localStringBuffer.replace(8 - str.length(), 8, str);
    return localStringBuffer.toString();
  }

  protected static String format(short paramShort)
  {
    String str = Integer.toHexString(paramShort);
    StringBuffer localStringBuffer = new StringBuffer("0000");
    localStringBuffer.replace(4 - str.length(), 4, str);
    return localStringBuffer.toString();
  }

  public static synchronized String generate()
  {
    short tmp49_46 = _$2;
    _$2 = (short)(tmp49_46 + 1);
    return 20 + format(_$3) + format(_$1) + format(getHiTime()) + format(getLoTime()) + format(tmp49_46);
  }

  public static synchronized String generate8Hex()
  {
    return format(getLoTime());
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(generate());
    System.out.println(generate8Hex());
  }

  static
  {
    int i;
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = InetAddress.getLocalHost().getAddress();
      i = arrayOfByte[0] << 24 & 0xFF000000 | arrayOfByte[1] << 16 & 0xFF0000 | arrayOfByte[2] << 8 & 0xFF00 | arrayOfByte[3] & 0xFF;
    }
    catch (Exception localException)
    {
      i = 0;
    }
    _$3 = i;
    _$2 = 0;
    _$1 = (int)(System.currentTimeMillis() >>> 8);
  }
}
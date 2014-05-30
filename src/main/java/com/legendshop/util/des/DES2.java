package com.legendshop.util.des;

import com.legendshop.util.converter.ByteConverter;
import com.sun.crypto.provider.SunJCE;
import java.io.IOException;
import java.io.PrintStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES2
{
  private final String _$7 = "DES";
  private KeyGenerator _$6;
  private SecretKey _$5;
  private Cipher _$4;
  private byte[] _$3;
  private byte[] _$2;
  private String _$1 = "12345678";

  public DES2(String paramString)
  {
    if (paramString != null)
      init(paramString);
    else
      init(paramString);
  }

  public DES2()
  {
    init(this._$1);
  }

  public void init(String paramString)
  {
    Security.addProvider(new SunJCE());
    try
    {
      this._$6 = KeyGenerator.getInstance("DES");
      byte[] arrayOfByte = paramString.getBytes();
      this._$5 = new SecretKeySpec(arrayOfByte, "DES");
      this._$4 = Cipher.getInstance("DES");
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      localNoSuchPaddingException.printStackTrace();
    }
  }

  public byte[] createEncryptor(byte[] paramArrayOfByte)
  {
    try
    {
      this._$4.init(1, this._$5);
      this._$3 = this._$4.doFinal(paramArrayOfByte);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      localInvalidKeyException.printStackTrace();
    }
    catch (BadPaddingException localBadPaddingException)
    {
      localBadPaddingException.printStackTrace();
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      localIllegalBlockSizeException.printStackTrace();
    }
    return this._$3;
  }

  public byte[] createEncryptor(String paramString)
  {
    return createEncryptor(paramString.getBytes());
  }

  public byte[] createDecryptor(byte[] paramArrayOfByte)
  {
    try
    {
      this._$4.init(2, this._$5);
      this._$2 = this._$4.doFinal(paramArrayOfByte);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      localInvalidKeyException.printStackTrace();
    }
    catch (BadPaddingException localBadPaddingException)
    {
      localBadPaddingException.printStackTrace();
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      localIllegalBlockSizeException.printStackTrace();
    }
    return this._$2;
  }

  public String byteToString(byte[] paramArrayOfByte)
  {
    String str = null;
    BASE64Encoder localBASE64Encoder = new BASE64Encoder();
    str = localBASE64Encoder.encode(paramArrayOfByte);
    return str;
  }

  public byte[] stringToByte(String paramString)
  {
    BASE64Decoder localBASE64Decoder = new BASE64Decoder();
    byte[] arrayOfByte = null;
    try
    {
      arrayOfByte = localBASE64Decoder.decodeBuffer(paramString);
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return arrayOfByte;
  }

  public void printByte(byte[] paramArrayOfByte)
  {
    System.out.println("*********开始输出字节流**********");
    System.out.println("字节流: " + paramArrayOfByte.toString());
    for (int i = 0; i < paramArrayOfByte.length; ++i)
      System.out.println("第 " + i + "字节为：" + paramArrayOfByte[i]);
    System.out.println("*********结束输出字节流**********");
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    DES2 localDES2 = new DES2("ABCDEFGH");
    localDES2.test(localDES2);
  }

  public void test(DES2 paramDES2)
    throws Exception
  {
    String str1 = "gdfndjfjgdfjgoiu3i4ou234uisifsoipfhdf好地方";
    System.out.println("加密前的数据：" + str1);
    String str2 = paramDES2.byteToString(paramDES2.createEncryptor(str1));
    System.out.println("加密后的数据:" + str2);
    String str3 = ByteConverter.encode(str2);
    System.out.println("加密后的16进制编码 :" + str3);
    String str4 = ByteConverter.decode(str3);
    System.out.println("解密后的16进制编码 :" + str4);
    String str5 = new String(paramDES2.createDecryptor(paramDES2.stringToByte(str4)));
    System.out.println("解密后的数据：" + str5);
  }

  public String getKeyString()
  {
    return this._$1;
  }

  public void setKeyString(String paramString)
  {
    this._$1 = paramString;
  }
}
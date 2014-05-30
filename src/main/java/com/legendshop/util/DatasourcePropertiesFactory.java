package com.legendshop.util;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatasourcePropertiesFactory
{
  protected static final Logger log = LoggerFactory.getLogger(DatasourcePropertiesFactory.class);
  private static Boolean _$3 = Boolean.valueOf(true);
  private static final String _$2 = "password";
  private static String _$1 = "LegendShop";

  public static Properties getProperties(String paramString, Boolean paramBoolean)
    throws Exception
  {
    return getProperties(paramString, paramBoolean, null);
  }

  public static Properties getProperties(String paramString1, Boolean paramBoolean, String paramString2)
    throws Exception
  {
    log.debug("jdbc production {} , secureKey {}", new Object[] { paramBoolean, paramString2 });
    Properties localProperties = new Properties();
    _$3 = paramBoolean;
    if (_$3.booleanValue())
      try
      {
        if (AppUtils.isNotBlank(paramString2))
          _$1 = paramString2;
        localProperties.setProperty("password", decode(paramString1));
      }
      catch (Exception localException)
      {
        throw localException;
      }
    localProperties.setProperty("password", paramString1);
    return localProperties;
  }

  private static String _$1(String paramString)
    throws NamingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException
  {
    System.out.println("加密前密码是 " + paramString);
    if (_$3.booleanValue())
    {
      byte[] arrayOfByte1 = _$1.getBytes();
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "Blowfish");
      Cipher localCipher = Cipher.getInstance("Blowfish");
      localCipher.init(1, localSecretKeySpec);
      byte[] arrayOfByte2 = localCipher.doFinal(paramString.getBytes());
      BigInteger localBigInteger = new BigInteger(arrayOfByte2);
      return localBigInteger.toString(16);
    }
    return paramString;
  }

  public static String decode(String paramString)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
  {
    System.out.println("将要解密的密码是 " + paramString);
    if (_$3.booleanValue())
    {
      byte[] arrayOfByte1 = _$1.getBytes();
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "Blowfish");
      BigInteger localBigInteger = new BigInteger(paramString, 16);
      byte[] arrayOfByte2 = localBigInteger.toByteArray();
      Cipher localCipher = Cipher.getInstance("Blowfish");
      localCipher.init(2, localSecretKeySpec);
      byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte2);
      return new String(arrayOfByte3);
    }
    return paramString;
  }

  public static void main(String[] paramArrayOfString)
    throws NamingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException
  {
    _$3 = Boolean.valueOf(true);
    String str1 = "root";
    String str2 = _$1(str1);
    System.out.println("加密后密码是 " + str2);
    System.out.println("还原的明文密码是 " + decode(str2));
  }
}
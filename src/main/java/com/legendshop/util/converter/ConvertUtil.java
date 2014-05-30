package com.legendshop.util.converter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public final class ConvertUtil
{
  private static Logger _$1 = Logger.getLogger(ConvertUtil.class);

  public static Map convertBundleToMap(ResourceBundle paramResourceBundle)
  {
    HashMap localHashMap = new HashMap();
    Enumeration localEnumeration = paramResourceBundle.getKeys();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      localHashMap.put(str, paramResourceBundle.getString(str));
    }
    return localHashMap;
  }

  public static Properties convertBundleToProperties(ResourceBundle paramResourceBundle)
  {
    Properties localProperties = new Properties();
    Enumeration localEnumeration = paramResourceBundle.getKeys();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      localProperties.put(str, paramResourceBundle.getString(str));
    }
    return localProperties;
  }

  public static Object populateObject(Object paramObject, ResourceBundle paramResourceBundle)
  {
    Map localMap;
    try
    {
      localMap = convertBundleToMap(paramResourceBundle);
      BeanUtils.copyProperties(paramObject, localMap);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      _$1.error("Exception occured populating object: " + localException.getMessage());
    }
    return paramObject;
  }
}
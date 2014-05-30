package com.legendshop.util;

import org.apache.commons.beanutils.Converter;

public final class StringConverter
  implements Converter
{
  public Object convert(Class paramClass, Object paramObject)
  {
    if ((paramObject == null) || ("".equals(paramObject.toString())))
      return null;
    return paramObject.toString();
  }
}
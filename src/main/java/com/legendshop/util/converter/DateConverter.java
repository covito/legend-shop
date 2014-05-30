package com.legendshop.util.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

public class DateConverter
  implements Converter
{
  public Object convert(Class paramClass, Object paramObject)
  {
    if (paramObject == null)
      return null;
    if (paramClass == Date.class)
      return convertToDate(paramClass, paramObject);
    if (paramClass == String.class)
      return convertToString(paramClass, paramObject);
    throw new ConversionException("Could not convert " + paramObject.getClass().getName() + " to " + paramClass.getName());
  }

  protected Object convertToDate(Class paramClass, Object paramObject)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(ConvertDateUtil.getDatePattern());
    if (paramObject instanceof String)
      try
      {
        if (StringUtils.isEmpty(paramObject.toString()))
          return null;
        return localSimpleDateFormat.parse((String)paramObject);
      }
      catch (Exception localException)
      {
        throw new ConversionException("Error converting String to Date");
      }
    throw new ConversionException("Could not convert " + paramObject.getClass().getName() + " to " + paramClass.getName());
  }

  protected Object convertToString(Class paramClass, Object paramObject)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(ConvertDateUtil.getDatePattern());
    if (paramObject instanceof Date)
      try
      {
        return localSimpleDateFormat.format(paramObject);
      }
      catch (Exception localException)
      {
        throw new ConversionException("Error converting Date to String");
      }
    return paramObject.toString();
  }
}
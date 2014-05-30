package com.legendshop.util.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;

public class TimestampConverter extends DateConverter
{
  public static final String TS_FORMAT = ConvertDateUtil.getDatePattern() + " HH:mm:ss.S";

  protected Object convertToDate(Class paramClass, Object paramObject)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(TS_FORMAT);
    if (paramObject instanceof String)
      try
      {
        if (StringUtils.isEmpty(paramObject.toString()))
          return null;
        return localSimpleDateFormat.parse((String)paramObject);
      }
      catch (Exception localException)
      {
        throw new ConversionException("Error converting String to Timestamp");
      }
    throw new ConversionException("Could not convert " + paramObject.getClass().getName() + " to " + paramClass.getName());
  }

  protected Object convertToString(Class paramClass, Object paramObject)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(TS_FORMAT);
    if (paramObject instanceof Date)
      try
      {
        return localSimpleDateFormat.format(paramObject);
      }
      catch (Exception localException)
      {
        throw new ConversionException("Error converting Timestamp to String");
      }
    return paramObject.toString();
  }
}
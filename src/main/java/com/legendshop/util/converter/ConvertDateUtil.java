package com.legendshop.util.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.MissingResourceException;
import org.apache.log4j.Logger;

public class ConvertDateUtil
{
  private static Logger _$3 = Logger.getLogger(ConvertDateUtil.class);
  private static String _$2 = null;
  private static String _$1 = "HH:mm";

  public static synchronized String getDatePattern()
  {
    try
    {
      _$2 = "yyyy-MM-dd";
    }
    catch (MissingResourceException localMissingResourceException)
    {
      _$2 = "MM/dd/yyyy";
    }
    return _$2;
  }

  public static final String getDate(Date paramDate)
  {
    SimpleDateFormat localSimpleDateFormat = null;
    String str = "";
    if (paramDate != null)
    {
      localSimpleDateFormat = new SimpleDateFormat(getDatePattern());
      str = localSimpleDateFormat.format(paramDate);
    }
    return str;
  }

  public static final Date convertStringToDate(String paramString1, String paramString2)
    throws ParseException
  {
    SimpleDateFormat localSimpleDateFormat = null;
    Date localDate = null;
    localSimpleDateFormat = new SimpleDateFormat(paramString1);
    if (_$3.isDebugEnabled())
      _$3.debug("converting '" + paramString2 + "' to date with mask '" + paramString1 + "'");
    try
    {
      localDate = localSimpleDateFormat.parse(paramString2);
    }
    catch (ParseException localParseException)
    {
      throw new ParseException(localParseException.getMessage(), localParseException.getErrorOffset());
    }
    return localDate;
  }

  public static String getTimeNow(Date paramDate)
  {
    return getDateTime(_$1, paramDate);
  }

  public static Calendar getToday()
    throws ParseException
  {
    Date localDate = new Date();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(getDatePattern());
    String str = localSimpleDateFormat.format(localDate);
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setTime(convertStringToDate(str));
    return localGregorianCalendar;
  }

  public static final String getDateTime(String paramString, Date paramDate)
  {
    SimpleDateFormat localSimpleDateFormat = null;
    String str = "";
    if (paramDate == null)
    {
      _$3.error("aDate is null!");
    }
    else
    {
      localSimpleDateFormat = new SimpleDateFormat(paramString);
      str = localSimpleDateFormat.format(paramDate);
    }
    return str;
  }

  public static final String convertDateToString(Date paramDate)
  {
    return getDateTime(getDatePattern(), paramDate);
  }

  public static Date convertStringToDate(String paramString)
    throws ParseException
  {
    Date localDate = null;
    try
    {
      if (_$3.isDebugEnabled())
        _$3.debug("converting date with pattern: " + getDatePattern());
      localDate = convertStringToDate(getDatePattern(), paramString);
    }
    catch (ParseException localParseException)
    {
      _$3.error("Could not convert '" + paramString + "' to a date, throwing exception");
      localParseException.printStackTrace();
      throw new ParseException(localParseException.getMessage(), localParseException.getErrorOffset());
    }
    return localDate;
  }
}
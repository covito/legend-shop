package com.legendshop.util.converter;

import java.text.DecimalFormat;
import java.text.ParseException;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CurrencyConverter
  implements Converter
{
  private static Logger _$1 = Logger.getLogger(CurrencyConverter.class);
  protected final DecimalFormat formatter = new DecimalFormat("###,###.00");

  public final Object convert(Class paramClass, Object paramObject)
  {
    if (paramObject == null)
      return null;
    if (paramObject instanceof String)
      if (_$1.isDebugEnabled())
        _$1.debug("value (" + paramObject + ") instance of String");
    try
    {
      if (StringUtils.isBlank(String.valueOf(paramObject)))
        return null;
      if (_$1.isDebugEnabled())
        _$1.debug("converting '" + paramObject + "' to a decimal");
      Number localNumber = this.formatter.parse(String.valueOf(paramObject));
      return new Double(localNumber.doubleValue());
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
      if (paramObject instanceof Double)
      {
        if (_$1.isDebugEnabled())
        {
          _$1.debug("value (" + paramObject + ") instance of Double");
          _$1.debug("returning double: " + this.formatter.format(paramObject));
        }
        return this.formatter.format(paramObject);
      }
      throw new ConversionException("Could not convert " + paramObject + " to " + paramClass.getName() + "!");
    }
  }
}
package com.legendshop.business.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.export.CsvView;

public class SimpleChineseCsvView extends CsvView
{
  public String getMimeType()
  {
    return "text/csv;charset=GBK";
  }

  protected String escapeColumnValue(Object rawValue)
  {
    if (rawValue == null)
      return null;

    String returnString = ObjectUtils.toString(rawValue);

    returnString = StringEscapeUtils.escapeJava(StringUtils.trimToEmpty(returnString));

    returnString = StringUtils.replace(StringUtils.trim(returnString), "\\t", "    ");

    returnString = StringUtils.replace(StringUtils.trim(returnString), "\\r", " ");

    returnString = StringEscapeUtils.unescapeJava(returnString);

    Pattern p = Pattern.compile("</?[div|span|a|font][^>]*>", 2);
    Matcher m = p.matcher(returnString);
    return m.replaceAll("");
  }
}
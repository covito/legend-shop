package com.legendshop.business.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.export.excel.ExcelHssfView;

public class SimpleChineseExcelView extends ExcelHssfView
{
  public String getMimeType()
  {
    return "application/vnd.ms-excel;charset=UTF-8";
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

    Pattern p = Pattern.compile("</?[div|span|a|font|b][^>]*>", 2);
    Matcher m = p.matcher(returnString);
    return m.replaceAll("");
  }
}
package com.legendshop.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyBindingInitializer
  implements WebBindingInitializer
{
  public void initBinder(WebDataBinder paramWebDataBinder, WebRequest paramWebRequest)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localSimpleDateFormat.setLenient(false);
    paramWebDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(localSimpleDateFormat, true));
    paramWebDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }
}
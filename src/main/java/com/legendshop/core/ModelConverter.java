package com.legendshop.core;

import com.legendshop.model.dynamic.Model;
import com.legendshop.util.AppUtils;
import java.io.PrintStream;
import org.springframework.core.convert.converter.Converter;

public class ModelConverter
  implements Converter<String, Model[]>
{
  public Model[] convert(String paramString)
  {
    if (AppUtils.isBlank(paramString))
      return null;
    System.out.println("input source  =" + paramString);
    return null;
  }
}
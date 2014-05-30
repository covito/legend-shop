package com.legendshop.util.sql;

import com.legendshop.util.AppUtils;
import java.util.HashMap;

public class ParamsMap extends HashMap<String, String>
{
  private static final long serialVersionUID = -7720526189745315572L;

  public void addParams(String paramString1, String paramString2)
  {
    if ((AppUtils.isBlank(paramString1)) || (AppUtils.isBlank(paramString2)))
      return;
    super.put(paramString1, paramString2);
  }
}
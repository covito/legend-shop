package com.legendshop.core.tag;

import com.legendshop.util.CookieUtil;
import java.io.IOException;

public class LastLogingUserTag extends LegendShopTag
{
  public static final String LAST_USERNAME_KEY = "LAST_LOGINING_USERNAME";

  public void doTag()
    throws IOException
  {
    String str = CookieUtil.getCookieValue(request(), "LAST_LOGINING_USERNAME");
    if (str != null)
      write(str);
  }
}
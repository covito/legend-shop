package com.legendshop.core.tag;

import com.legendshop.core.helper.ThreadLocalContext;
import java.io.IOException;

public class CurrentShopTag extends LegendShopTag
{
  public void doTag()
    throws IOException
  {
    String str = ThreadLocalContext.getCurrentShopName(request(), response());
    if (str != null)
      write(str);
  }
}
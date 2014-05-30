package com.legendshop.core.tag;

import com.legendshop.core.UserManager;
import java.io.IOException;

public class CurrentUserTag extends LegendShopTag
{
  public void doTag()
    throws IOException
  {
    String str = UserManager.getUserName(request());
    if (str != null)
      write(str);
  }
}
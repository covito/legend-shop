package com.legendshop.business.common.fck;

import javax.servlet.http.HttpServletRequest;
import net.fckeditor.requestcycle.UserAction;

public class EnabledUserAction
  implements UserAction
{
  public boolean isCreateFolderEnabled(HttpServletRequest request)
  {
    return true;
  }

  public boolean isEnabledForFileBrowsing(HttpServletRequest request)
  {
    return true;
  }

  public boolean isEnabledForFileUpload(HttpServletRequest request)
  {
    return true;
  }
}
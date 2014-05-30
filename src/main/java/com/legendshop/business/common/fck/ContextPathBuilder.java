package com.legendshop.business.common.fck;

import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.helper.PropertiesUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.fckeditor.requestcycle.impl.ServerRootPathBuilder;

public class ContextPathBuilder extends ServerRootPathBuilder
{
  public String getUserFilesPath(HttpServletRequest request)
  {
    String userName = (String)request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME");
    if (userName == null)
      throw new AuthorizationException("did not logon yet!");

    String result = request.getContextPath() + super.getUserFilesPath(request) + "/" + 
      userName + "/editor";
    return result;
  }

  public String getUserFilesAbsolutePath(HttpServletRequest request)
  {
    String userName = (String)request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME");
    if (userName == null)
      throw new AuthorizationException("did not logon yet!");

    String result = PropertiesUtil.getBigFilesAbsolutePath() + "/" + userName + 
      "/editor";
    return result;
  }
}
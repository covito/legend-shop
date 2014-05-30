package com.legendshop.core.helper;

import com.legendshop.core.UserManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginChecker
  implements Checker<UserManager>
{
  public boolean check(UserManager paramUserManager, HttpServletRequest paramHttpServletRequest)
  {
    String str = (String)paramHttpServletRequest.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME");
    if (str == null)
      str = UserManager.getUserName(paramHttpServletRequest);
    return (str != null);
  }
}
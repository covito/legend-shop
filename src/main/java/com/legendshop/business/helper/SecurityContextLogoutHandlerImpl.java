package com.legendshop.business.helper;

import com.legendshop.util.CookieUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class SecurityContextLogoutHandlerImpl extends SecurityContextLogoutHandler
{
  private boolean supportSSO = false;

  public void setSupportSSO(boolean supportSSO)
  {
    this.supportSSO = supportSSO;
  }

  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
  {
    if (this.supportSSO)
    {
      CookieUtil.deleteCookie(request, response, "jforumUserInfo");
    }
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.removeAttribute("SPRING_SECURITY_LAST_USERNAME");
      session.removeAttribute("SPRING_SECURITY_CONTEXT");
    }
    super.logout(request, response, authentication);
  }
}
package com.legendshop.business.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler
{
  public static final String TRY_LOGIN_COUNT = "TRY_LOGIN_COUNT";

  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
    throws IOException, ServletException
  {
    increaseTryLoginCount(request);
    super.onAuthenticationFailure(request, response, exception);
  }

  private void increaseTryLoginCount(HttpServletRequest request)
  {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }

    Integer count = (Integer)session.getAttribute("TRY_LOGIN_COUNT");
    if (count == null)
      count = Integer.valueOf(0);

    count = Integer.valueOf(count.intValue() + 1);
    session.setAttribute("TRY_LOGIN_COUNT", count);
  }
}
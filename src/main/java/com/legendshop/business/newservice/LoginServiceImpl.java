package com.legendshop.business.newservice;

import com.legendshop.business.handler.ValidateCodeUsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginServiceImpl extends ValidateCodeUsernamePasswordAuthenticationFilter
{
  Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

  public Authentication onAuthentication(HttpServletRequest request, HttpServletResponse response, String username, String password)
  {
    this.log.debug("userName {} register and login", username);
    Authentication authentication = super.onAuthentication(request, response, username, password);
    SecurityContext securityContext = SecurityContextHolder.getContext();
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.setAttribute("SPRING_SECURITY_LAST_USERNAME", username);
      session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
    return authentication;
  }
}
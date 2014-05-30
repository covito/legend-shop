package com.legendshop.spi.service.impl;

import com.legendshop.spi.service.LoginService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public class DefaultLoginServiceImpl
  implements LoginService
{
  public Authentication onAuthentication(HttpServletRequest request, HttpServletResponse response, String username, String password)
  {
    return null;
  }
}
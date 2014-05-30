package com.legendshop.business.handler;

import com.legendshop.util.MD5Util;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.StringUtils;

public class MyTokenBasedRememberMeServices extends TokenBasedRememberMeServices
{
  public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication)
  {
    String username = retrieveUserName(successfulAuthentication);
    String password = retrievePassword(successfulAuthentication);

    if ((!(StringUtils.hasLength(username))) || (!(StringUtils.hasLength(password)))) {
      return;
    }

    int tokenLifetime = calculateLoginLifetime(request, successfulAuthentication);
    long expiryTime = System.currentTimeMillis();

    expiryTime += 1000L * ((tokenLifetime < 0) ? 1209600 : tokenLifetime);

    String signatureValue = makeTokenSignature(expiryTime, username, MD5Util.Md5Password(username, password));

    setCookie(new String[] { username, Long.toString(expiryTime), signatureValue }, tokenLifetime, request, response);

    if (this.logger.isDebugEnabled())
      this.logger.debug("Added remember-me cookie for user '" + username + "', expiry: '" + new Date(expiryTime) + "'");
  }
}
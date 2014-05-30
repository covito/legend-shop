package com.legendshop.business.handler;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.randing.CaptchaServiceSingleton;
import com.legendshop.spi.service.LoginService;
import com.legendshop.util.CookieUtil;
import com.octo.captcha.service.image.ImageCaptchaService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter
  implements LoginService
{
  Logger log = LoggerFactory.getLogger(ValidateCodeUsernamePasswordAuthenticationFilter.class);
  private boolean postOnly = true;
  public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "randNum";

  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException
  {
    if ((this.postOnly) && (!(request.getMethod().equals("POST")))) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    }

    HttpSession session = request.getSession(false);

    if (session == null) {
      return null;
    }

    if ((CommonServiceUtil.needToValidation(session)) && 
      (!(checkValidateCode(request)))) {
      throw new AuthenticationServiceException("验证码失败");
    }

    String username = obtainUsername(request);
    String password = obtainPassword(request);

    if (username == null) {
      username = "";
    }

    if (password == null) {
      password = "";
    }

    username = username.trim();

    if ((session != null) || (getAllowSessionCreation())) {
      CookieUtil.addCookie(response, "LAST_LOGINING_USERNAME", username);
    }

    return onAuthentication(request, response, username, password);
  }

  public Authentication onAuthentication(HttpServletRequest request, HttpServletResponse response, String username, String password)
  {
    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

    setDetails(request, authRequest);

    Authentication auth = getAuthenticationManager().authenticate(authRequest);
    return auth;
  }

  @Deprecated
  protected void checkValidateCode_bak(HttpServletRequest request)
  {
    String sessionValidateCode = obtainSessionValidateCode(request);
    String validateCodeParameter = obtainValidateCodeParameter(request);
    if ((StringUtils.isEmpty(validateCodeParameter)) || (!(sessionValidateCode.equalsIgnoreCase(validateCodeParameter)))) {
      this.log.error("输入的验证码 {}不匹配 {}", validateCodeParameter, sessionValidateCode);
      throw new AuthenticationServiceException("验证码失败");
    }
  }

  protected boolean checkValidateCode(HttpServletRequest request) {
    String validateCodeParameter = obtainValidateCodeParameter(request);
    return CaptchaServiceSingleton.getInstance().validateResponseForID(request.getSession().getId(), validateCodeParameter).booleanValue();
  }

  private String obtainValidateCodeParameter(HttpServletRequest request)
  {
    return request.getParameter("randNum");
  }

  protected String obtainSessionValidateCode(HttpServletRequest request)
  {
    Object obj = request.getSession().getAttribute("randNum");
    return ((obj == null) ? "" : obj.toString());
  }

  public boolean isPostOnly()
  {
    return this.postOnly;
  }

  public void setPostOnly(boolean postOnly)
  {
    this.postOnly = postOnly;
  }
}
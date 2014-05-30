package com.legendshop.business.handler;

import com.legendshop.core.UserManager;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.handler.AbstractHandler;
import com.legendshop.core.handler.Handler;
import com.legendshop.spi.form.UserForm;
import com.legendshop.spi.service.LoginService;
import com.legendshop.spi.service.UserDetailService;
import com.legendshop.spi.service.impl.DefaultLoginServiceImpl;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.CookieUtil;
import com.legendshop.util.ServiceLocatorIF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class CookiesSSOHandler extends AbstractHandler
  implements Handler
{
  private static Logger log = LoggerFactory.getLogger(CookiesSSOHandler.class);
  private LoginService loginService;
  private UserDetailService userDetailService;
  private boolean invalidateHttpSession = true;
  public static String LOGINED_USER = "LEGENDSHOP_LOGINED_USER";

  public void handle(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String loginedUserName;
    try
    {
      loginedUserName = CookieUtil.getCookieValue(request, LOGINED_USER);
      String userName = UserManager.getUserName(request);
      if (requiresLogin(loginedUserName, userName))
      {
        if (!(this.userDetailService.isUserExist(loginedUserName))) {
          UserForm form = new UserForm();
          form.setName(loginedUserName);
          form.setUserName(loginedUserName);
          form.setEnabled("1");
          form.setPassword(loginedUserName);
          form.setNickName(loginedUserName);
          form.setUserMail(loginedUserName + "@legendshop.cn");
          this.userDetailService.saveUserReg(request, response, form);
        }

        getLoginService().onAuthentication(request, response, loginedUserName, loginedUserName); return;
      }
      if (!(requiresLogout(loginedUserName, userName))) return;
      logout(request, response);
    }
    catch (Exception e) {
      throw new ApplicationException(e, "CookiesSSOHandler Fail", "999");
    }
  }

  protected void logout(HttpServletRequest request, HttpServletResponse response)
  {
    request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", null);
    if (this.invalidateHttpSession) {
      HttpSession session = request.getSession(false);
      if (session != null) {
        log.debug("Invalidating session: " + session.getId());
        session.invalidate();
      }
    }

    SecurityContextHolder.clearContext();
  }

  protected boolean requiresLogout(String loginedUserName, String userName)
  {
    return ((loginedUserName == null) && (userName != null));
  }

  protected boolean requiresLogin(String loginedUserName, String userName)
  {
    return ((loginedUserName != null) && (((userName == null) || (!(loginedUserName.equals(userName))))));
  }

  public void setInvalidateHttpSession(boolean invalidateHttpSession)
  {
    this.invalidateHttpSession = invalidateHttpSession;
  }

  public void setUserDetailService(UserDetailService userDetailService) {
    this.userDetailService = userDetailService;
  }

  private LoginService getLoginService() {
    if (this.loginService == null) {
      if (ContextServiceLocator.getInstance().containsBean("loginService"))
        this.loginService = ((LoginService)ContextServiceLocator.getInstance().getBean("loginService"));

      if (this.loginService == null)
        this.loginService = new DefaultLoginServiceImpl();
    }

    return this.loginService;
  }
}
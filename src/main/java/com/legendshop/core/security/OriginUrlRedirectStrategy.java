package com.legendshop.core.security;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.util.AppUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.RedirectStrategy;

public class OriginUrlRedirectStrategy
  implements RedirectStrategy
{
  protected final Log logger = LogFactory.getLog(super.getClass());
  private boolean _$2;
  private boolean _$1;

  public void sendRedirect(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString)
    throws IOException
  {
    String str1 = paramHttpServletRequest.getParameter("returnUrl");
    if (AppUtils.isNotBlank(str1))
    {
      paramHttpServletResponse.sendRedirect(str1);
      return;
    }
    if ((this._$1) && (UserManager.hasFunction(paramHttpServletRequest, FunctionEnum.FUNCTION_F_ADMIN.value())))
    {
      paramHttpServletResponse.sendRedirect(paramHttpServletRequest.getContextPath() + "/admin/index");
      return;
    }
    String str2 = _$1(paramHttpServletRequest.getContextPath(), paramString);
    str2 = paramHttpServletResponse.encodeRedirectURL(str2);
    if (this.logger.isDebugEnabled())
      this.logger.debug("Redirecting to '" + str2 + "'");
    paramHttpServletResponse.sendRedirect(str2);
  }

  private String _$1(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!(paramString2.startsWith("http://"))))
    {
      if (this._$2)
        return paramString2;
      return paramString1 + paramString2;
    }
    if (!(this._$2))
      return paramString2;
    paramString2 = paramString2.substring(paramString2.indexOf("://") + 3);
    paramString2 = paramString2.substring(paramString2.indexOf(paramString1) + paramString1.length());
    if ((paramString2.length() > 1) && (paramString2.charAt(0) == '/'))
      paramString2 = paramString2.substring(1);
    return paramString2;
  }

  public void setContextRelative(boolean paramBoolean)
  {
    this._$2 = paramBoolean;
  }

  public void setForwardToBackEndForBusiness(boolean paramBoolean)
  {
    this._$1 = paramBoolean;
  }
}
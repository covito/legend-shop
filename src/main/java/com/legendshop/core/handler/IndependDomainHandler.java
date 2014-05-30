package com.legendshop.core.handler;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.service.ShopService;
import com.legendshop.util.AppUtils;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndependDomainHandler extends AbstractHandler
  implements Handler
{
  private static Logger _$2 = LoggerFactory.getLogger(IndependDomainHandler.class);
  private ShopService _$1;

  public void handle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.INDEPEND_DOMAIN, Boolean.class)).booleanValue())
    {
      String str1 = paramHttpServletRequest.getServerName();
      _$2.info("incoming serverName = {} ,remoteAddr = {},   X-Real-IP = {}", new Object[] { str1, paramHttpServletRequest.getRemoteAddr(), paramHttpServletRequest.getHeader("X-Real-IP") });
      String str2 = _$1(str1);
      if (AppUtils.isNotBlank(str2))
      {
        String str3 = this._$1.getShopNameByDomain(str2);
        if (AppUtils.isNotBlank(str3))
        {
          _$2.debug("processing domainName {}, shopName {}", str2, str3);
          ThreadLocalContext.setCurrentShopName(paramHttpServletRequest, paramHttpServletResponse, str3);
        }
      }
    }
  }

  private String _$1(String paramString)
  {
    if (AppUtils.isBlank(paramString))
      return null;
    if (paramString.toLowerCase().startsWith("www."))
      return paramString.substring(4);
    return paramString;
  }

  public void setShopService(ShopService paramShopService)
  {
    this._$1 = paramShopService;
  }

  public static void main(String[] paramArrayOfString)
  {
    String str = "www.legendshop.cn";
    System.out.println(str.substring(4));
  }
}
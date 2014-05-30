package com.legendshop.core.tag;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.service.ShopService;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class ShopDomainTag extends AbstratShopDomainTag
{
  private String _$2;
  private static ShopService _$1;

  public ShopDomainTag()
  {
    _$1 = (ShopService)ContextServiceLocator.getInstance().getBean("shopDetailService");
  }

  public void doTag()
    throws IOException
  {
    String str1 = null;
    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.INDEPEND_DOMAIN, Boolean.class)).booleanValue())
    {
      HttpServletRequest localHttpServletRequest = (HttpServletRequest)pageContext().getRequest();
      HttpServletResponse localHttpServletResponse = (HttpServletResponse)pageContext().getResponse();
      String str2 = this._$2;
      if (AppUtils.isBlank(str2))
        str2 = ThreadLocalContext.getCurrentShopName(localHttpServletRequest, localHttpServletResponse);
      ShopDetailView localShopDetailView = null;
      if (AppUtils.isNotBlank(str2))
        localShopDetailView = _$1.getShopDetailView(str2);
      if ((localShopDetailView != null) && (AppUtils.isNotBlank(localShopDetailView.getDomainName())))
        str1 = getDomainNameFromShop(localShopDetailView);
      else
        str1 = getDomainName(this._$2);
    }
    else
    {
      str1 = getDomainName(this._$2);
    }
    write(str1);
  }

  public void setShopName(String paramString)
  {
    this._$2 = paramString;
  }
}
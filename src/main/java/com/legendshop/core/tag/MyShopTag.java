package com.legendshop.core.tag;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.BusinessModeEnum;
import com.legendshop.core.service.ShopService;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class MyShopTag extends TagSupport
{
  private static final long serialVersionUID = -4870270970591649109L;
  private static ShopService _$1;

  public MyShopTag()
  {
    _$1 = (ShopService)ContextServiceLocator.getInstance().getBean("shopDetailService");
  }

  public int doStartTag()
  {
    String str = UserManager.getUserName(this.pageContext.getSession());
    if (str != null)
    {
      Boolean localBoolean = Boolean.valueOf(BusinessModeEnum.C2C.name().equals(this.pageContext.getServletContext().getAttribute("BUSINESS_MODE")));
      if ((localBoolean.booleanValue()) && (_$1.isShopExists(str).booleanValue()))
        return 1;
    }
    return 0;
  }
}
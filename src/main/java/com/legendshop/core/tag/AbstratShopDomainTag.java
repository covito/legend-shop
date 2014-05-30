package com.legendshop.core.tag;

import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

public abstract class AbstratShopDomainTag extends LegendShopTag
{
  protected String getDomainNameFromShop(ShopDetailView paramShopDetailView)
  {
    String str = paramShopDetailView.getDomainName();
    if (str != null)
      str = "http://www." + str;
    return str;
  }

  protected String getDomainName(String paramString)
  {
    if (AppUtils.isBlank(paramString))
      return ((String)pageContext().getServletContext().getAttribute("DOMAIN_NAME"));
    return ((String)pageContext().getServletContext().getAttribute("DOMAIN_NAME")) + "/shop/" + paramString;
  }
}
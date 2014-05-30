package com.legendshop.business.tag;

import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

public class ShopDetailTag extends LegendShopTag
{
  private String var;

  public void doTag()
    throws JspException, IOException
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request(), response());
    if ((AppUtils.isNotBlank(shopName)) && (request().getAttribute(this.var) == null)) {
      ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(request(), response(), shopName);
      if (shopDetail != null) {
        setAttribute(this.var, shopDetail);
        invokeJspBody();
      }
    }
  }

  public void setVar(String var)
  {
    this.var = var;
  }
}
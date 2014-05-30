package com.legendshop.business.tag;

import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.service.SortService;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;

public class SortTag extends LegendShopTag
{
  private String var;
  private String shopName;
  private String sortType;
  private boolean loadAll;
  private SortService sortService;

  public SortTag()
  {
    if (this.sortService == null)
      this.sortService = ((SortService)getBean("sortService"));
  }

  public void doTag()
    throws JspException, IOException
  {
    List sorts;
    String name = this.shopName;
    if (name == null) {
      String sessionShopName = ThreadLocalContext.getCurrentShopName(request(), response());
      if (sessionShopName != null)
        name = sessionShopName;
      else {
        return;
      }

    }

    if (this.sortType == null)
      sorts = this.sortService.getSort(name, Boolean.valueOf(this.loadAll));
    else {
      sorts = this.sortService.getSort(name, this.sortType, Boolean.valueOf(this.loadAll));
    }

    int index = 1;
    if (sorts != null)
      for (Iterator localIterator = sorts.iterator(); localIterator.hasNext(); ) { Sort sort = (Sort)localIterator.next();
        setAttribute(this.var, sort);
        setAttribute(this.var + "Index", Integer.valueOf(index));
        invokeJspBody();
        ++index;
      }
  }

  public void setVar(String var)
  {
    this.var = var;
  }

  public void setShopName(String shopName)
  {
    this.shopName = shopName;
  }

  public void setLoadAll(boolean loadAll)
  {
    this.loadAll = loadAll;
  }

  public void setSortType(String sortType)
  {
    this.sortType = sortType;
  }
}
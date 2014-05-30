package com.legendshop.core.dao.support;

import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.util.AppUtils;

public abstract class AbstractQuery
{
  protected String curPage = null;
  protected int pageSize = 10;
  protected String myaction = "javascript:pager";
  protected PageProviderEnum pageProvider;

  public String getCurPage()
  {
    return this.curPage;
  }

  public void setCurPage(String paramString)
  {
    if (AppUtils.isBlank(paramString))
      this.curPage = "1";
    else
      this.curPage = paramString;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int paramInt)
  {
    this.pageSize = paramInt;
  }

  public String getMyaction()
  {
    return this.myaction;
  }

  public void setMyaction(String paramString)
  {
    this.myaction = paramString;
  }

  public PageProviderEnum getPageProvider()
  {
    return this.pageProvider;
  }

  public void setPageProvider(PageProviderEnum paramPageProviderEnum)
  {
    this.pageProvider = paramPageProviderEnum;
  }

  public void parseExportPageSize()
  {
    if (!(FoundationUtil.isDataForExport(this, ThreadLocalContext.getRequest())))
      setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
  }
}
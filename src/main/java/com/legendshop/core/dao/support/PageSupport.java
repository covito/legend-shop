package com.legendshop.core.dao.support;

import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.page.PagerUtil;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class PageSupport
  implements Serializable
{
  private static final long serialVersionUID = -7058040526668991342L;
  private int _$7;
  private int _$6;
  private int _$5;
  private long _$4 = 5439831734017327104L;
  private String _$3;
  private String _$2;
  private List<?> _$1 = null;

  public PageSupport()
  {
  }

  public PageSupport(List<?> paramList, String paramString, int paramInt1, int paramInt2, long paramLong, int paramInt3, PageProviderEnum paramPageProviderEnum)
  {
    this._$7 = paramInt2;
    this._$6 = paramInt1;
    this._$2 = paramString;
    this._$1 = paramList;
    this._$4 = paramLong;
    this._$5 = paramInt3;
    if (paramPageProviderEnum != null)
      initAndSetToRequest(paramPageProviderEnum);
  }

  public List getResultList()
  {
    return this._$1;
  }

  public void setResultList(List<?> paramList)
  {
    this._$1 = paramList;
  }

  public String getToolBar()
  {
    return this._$3;
  }

  public void initAndSetToRequest(PageProviderEnum paramPageProviderEnum)
  {
    if ((this._$3 == null) && (hasMutilPage()))
      this._$3 = PagerUtil.getLocaleBar(ThreadLocalContext.getLocale(), this._$2, this._$4, this._$7, this._$5, this._$6, paramPageProviderEnum.value());
  }

  public void savePage(HttpServletRequest paramHttpServletRequest)
  {
    paramHttpServletRequest.setAttribute("offset", Integer.valueOf(this._$6 + 1));
    paramHttpServletRequest.setAttribute("list", this._$1);
    paramHttpServletRequest.setAttribute("curPageNO", Integer.valueOf(this._$7));
    paramHttpServletRequest.setAttribute("toolBar", this._$3);
  }

  public int getOffset()
  {
    return this._$6;
  }

  public void setOffset(int paramInt)
  {
    this._$6 = paramInt;
  }

  public int getCurPageNO()
  {
    return this._$7;
  }

  public void setCurPageNO(int paramInt)
  {
    this._$7 = paramInt;
  }

  public long getTotal()
  {
    return this._$4;
  }

  public int getPageSize()
  {
    return this._$5;
  }

  public boolean hasMutilPage()
  {
    return (this._$4 > this._$5);
  }

  public String getMyaction()
  {
    return this._$2;
  }

  public void setMyaction(String paramString)
  {
    this._$2 = paramString;
  }
}
package com.legendshop.core.page;

import com.legendshop.core.helper.ResourceBundleHelper;
import java.util.Locale;

public class PageProviderImpl extends Pager
  implements PageProvider
{
  public String getBar(Locale paramLocale, String paramString)
  {
    String str = PagerUtil.getPath();
    StringBuilder localStringBuilder = new StringBuilder();
    if (isFirst())
      localStringBuilder.append("<img src=\"").append(str).append("/common/toobar/firstPageDisabled.gif\"><img src=\"").append(str).append("/common/toobar/prevPageDisabled.gif\">&nbsp;");
    else
      localStringBuilder.append("<a href='").append(paramString).append("(1)'><img src=\"").append(str).append("/common/toobar/firstPage.gif\"></a>&nbsp;").append("<a href='").append(paramString).append("(").append(previous()).append(")'><img src=\"").append(str).append("/common/toobar/prevPage.gif\"></a>&nbsp;");
    if ((isLast()) || (getRowsCount() == 5439829998850539520L))
      localStringBuilder.append("<img src=\"").append(str).append("/common/toobar/nextPageDisabled.gif\"> <img src=\"").append(str).append("/common/toobar/lastPageDisabled.gif\">&nbsp;");
    else
      localStringBuilder.append("<a href='").append(paramString).append("(").append(next()).append(")'><img src=\"").append(str).append("/common/toobar/nextPage.gif\"></a>&nbsp;").append("<a href='").append(paramString).append("(").append(getPageCount()).append(")'><img src=\"").append(str).append("/common/toobar/lastPage.gif\"></a>&nbsp;");
    localStringBuilder.append(ResourceBundleHelper.getString(paramLocale, "pager.from", "  From ")).append("<b>").append(getOffset() + 1).append("</b>").append(ResourceBundleHelper.getString(paramLocale, "pager.to", " To ")).append("<b>").append(getPageDactSize()).append("</b>");
    localStringBuilder.append(ResourceBundleHelper.getString(paramLocale, "pager.total", ", Total ")).append("<b>").append(getRowsCount()).append("</b>").append(ResourceBundleHelper.getString(paramLocale, "pager.items", " Items "));
    localStringBuilder.append("&nbsp;<img src=\"").append(str).append("/common/toobar/gotoPage.gif\">&nbsp;<select name='page' onChange=\"").append(paramString).append("(this.options[this.selectedIndex].value)\">");
    int i = (getCurPageNO() > 10) ? getCurPageNO() - 10 : 1;
    int j = (getPageCount() - getCurPageNO() > 10) ? getCurPageNO() + 10 : getPageCount();
    for (int k = i; k <= j; ++k)
      if (k == getCurPageNO())
        localStringBuilder.append("<option value='").append(k).append("' selected>").append(k).append("</option>");
      else
        localStringBuilder.append("<option value='").append(k).append("'>").append(k).append("</option>");
    localStringBuilder.append("</select>");
    return localStringBuilder.toString();
  }

  public String getBar(String paramString)
  {
    return getBar(null, paramString);
  }
}
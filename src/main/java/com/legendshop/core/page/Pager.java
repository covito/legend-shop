package com.legendshop.core.page;

import java.io.PrintStream;
import java.util.Locale;

public class Pager
  implements PageProvider
{
  private final String _$6 = "legendshop";
  private int _$5;
  private int _$4;
  private long _$3;
  private int _$2;
  private int _$1;

  public Pager(long paramLong, int paramInt1, int paramInt2)
  {
    this._$1 = paramInt1;
    this._$5 = ((paramInt1 == 0) ? 1 : (int)Math.ceil(paramInt1 / paramInt2));
    this._$4 = paramInt2;
    this._$3 = paramLong;
    this._$2 = (int)Math.ceil(paramLong / paramInt2);
  }

  public void init(long paramLong, int paramInt1, int paramInt2)
  {
    this._$1 = paramInt1;
    this._$5 = ((paramInt1 == 0) ? 1 : (int)Math.ceil(paramInt1 / paramInt2));
    this._$4 = paramInt2;
    this._$3 = paramLong;
    this._$2 = (int)Math.ceil(paramLong / paramInt2);
  }

  public Pager()
  {
  }

  public int getCurPageNO()
  {
    return this._$5;
  }

  public int getPageSize()
  {
    return this._$4;
  }

  public long getRowsCount()
  {
    return this._$3;
  }

  public int getPageCount()
  {
    return this._$2;
  }

  public int first()
  {
    return 1;
  }

  public int last()
  {
    return this._$2;
  }

  public int previous()
  {
    return ((this._$5 - 1 < 1) ? 1 : this._$5 - 1);
  }

  public int next()
  {
    return ((this._$5 + 1 > this._$2) ? this._$2 : this._$5 + 1);
  }

  public boolean isFirst()
  {
    return (this._$5 == 1);
  }

  public boolean isLast()
  {
    return (this._$5 == this._$2);
  }

  public void setCurPageNO(int paramInt)
  {
    this._$5 = paramInt;
  }

  public String toString()
  {
    return "Pager的值为  curPageNO = " + this._$5 + " limit = " + this._$4 + " rowsCount = " + this._$3 + " pageCount = " + this._$2;
  }

  public String getToolBar(String paramString)
  {
    String str = "";
    if (paramString.indexOf("?") == -1)
      str = "?";
    else
      str = "&";
    StringBuffer localStringBuffer = new StringBuffer();
    if (isFirst())
      localStringBuffer.append("首页 上一页&nbsp;");
    else
      localStringBuffer.append("<a href='").append(paramString).append(str).append("curPageNO=1'>首页</a>&nbsp;").append("<a href='").append(paramString).append(str).append("curPageNO=").append(previous()).append("'>上一页</a>&nbsp;");
    if ((isLast()) || (this._$3 == 5439829998850539520L))
      localStringBuffer.append("下一页 尾页&nbsp;");
    else
      localStringBuffer.append("<a href='").append(paramString).append(str).append("curPageNO=").append(next()).append("'>下一页</a>&nbsp;").append("<a href='").append(paramString).append(str).append("curPageNO=").append(this._$2).append("'>尾页</a>&nbsp;");
    localStringBuffer.append("&nbsp;共<b>").append(this._$3).append("</b>条记录&nbsp;").append("&nbsp;转到<select name='page' onChange=\"location='").append(paramString).append(str).append("curPageNO='+this.options[this.selectedIndex].value\">");
    int i = (this._$5 > 10) ? this._$5 - 10 : 1;
    int j = (this._$2 - this._$5 > 10) ? this._$5 + 10 : this._$2;
    for (int k = i; k <= j; ++k)
      if (k == this._$5)
        localStringBuffer.append("<option value='").append(k).append("' selected>第").append(k).append("页</option>");
      else
        localStringBuffer.append("<option value='").append(k).append("'>第").append(k).append("页</option>");
    localStringBuffer.append("</select>");
    return localStringBuffer.toString();
  }

  @Deprecated
  public String getToolBar_bak(String paramString)
  {
    String str1 = "";
    if (paramString.indexOf("?") == -1)
      str1 = "?";
    else
      str1 = "&";
    String str2 = "";
    if (isFirst())
    {
      str2 = str2 + "首页 上一页&nbsp;";
    }
    else
    {
      str2 = str2 + "<a href='" + paramString + str1 + "curPageNO=1'>首页</a>&nbsp;";
      str2 = str2 + "<a href='" + paramString + str1 + "curPageNO=" + previous() + "'>上一页</a>&nbsp;";
    }
    if ((isLast()) || (this._$3 == 5439829998850539520L))
    {
      str2 = str2 + "下一页 尾页&nbsp;";
    }
    else
    {
      str2 = str2 + "<a href='" + paramString + str1 + "curPageNO=" + next() + "'>下一页</a>&nbsp;";
      str2 = str2 + "<a href='" + paramString + str1 + "curPageNO=" + this._$2 + "'>尾页</a>&nbsp;";
    }
    str2 = str2 + "&nbsp;共<b>" + this._$3 + "</b>条记录&nbsp;";
    str2 = str2 + "&nbsp;转到<select name='page' onChange=\"location='" + paramString + str1 + "curPageNO='+this.options[this.selectedIndex].value\">";
    int i = (this._$5 > 10) ? this._$5 - 10 : 1;
    int j = (this._$2 - this._$5 > 10) ? this._$5 + 10 : this._$2;
    for (int k = i; k <= j; ++k)
      if (k == this._$5)
        str2 = str2 + "<option value='" + k + "' selected>第" + k + "页</option>";
      else
        str2 = str2 + "<option value='" + k + "'>第" + k + "页</option>";
    str2 = str2 + "</select>";
    return str2;
  }

  public String getToolBarEnglish(String paramString)
  {
    String str = "";
    if (isFirst())
    {
      str = str + "First Previous&nbsp;";
    }
    else
    {
      str = str + "<a href='" + paramString + "(1)'>First</a>&nbsp;";
      str = str + "<a href='" + paramString + "(" + previous() + ")'>Previous</a>&nbsp;";
    }
    if ((isLast()) || (this._$3 == 5439829998850539520L))
    {
      str = str + "Next Last&nbsp;";
    }
    else
    {
      str = str + "<a href='" + paramString + "(" + next() + ")'>Next</a>&nbsp;";
      str = str + "<a href='" + paramString + "(" + this._$2 + ")'>Last</a>&nbsp;";
    }
    str = str + "&nbsp;Total &nbsp;<b>" + this._$3 + "</b>&nbsp;Items&nbsp;";
    str = str + "&nbsp;To&nbsp;<select name='page' onChange=\"" + paramString + "(this.options[this.selectedIndex].value)\">";
    int i = (this._$5 > 10) ? this._$5 - 10 : 1;
    int j = (this._$2 - this._$5 > 10) ? this._$5 + 10 : this._$2;
    for (int k = i; k <= j; ++k)
      if (k == this._$5)
        str = str + "<option value='" + k + "' selected>" + k + "</option>";
      else
        str = str + "<option value='" + k + "'>" + k + "</option>";
    str = str + "</select>";
    return str;
  }

  public String getBar(String paramString)
  {
    String str = PagerUtil.getPath();
    StringBuilder localStringBuilder = new StringBuilder();
    if (isFirst())
      localStringBuilder.append("<img src=\"").append(str).append("/common/toobar/firstPageDisabled.gif\">  <img src=\"").append(str).append("/common/toobar/prevPageDisabled.gif\">&nbsp;");
    else
      localStringBuilder.append("<a href='").append(paramString).append("(1)'><img src=\"").append(str).append("/common/toobar/firstPage.gif\"></a>&nbsp;").append("<a href='").append(paramString).append("(").append(previous()).append(")'><img src=\"").append(str).append("/common/toobar/prevPage.gif\"></a>&nbsp;");
    if ((isLast()) || (this._$3 == 5439829998850539520L))
      localStringBuilder.append("<img src=\"").append(str).append("/common/toobar/nextPageDisabled.gif\"> <img src=\"").append(str).append("/common/toobar/lastPageDisabled.gif\">&nbsp;");
    else
      localStringBuilder.append("<a href='").append(paramString).append("(").append(next()).append(")'><img src=\"").append(str).append("/common/toobar/nextPage.gif\"></a>&nbsp;").append("<a href='").append(paramString).append("(").append(this._$2).append(")'><img src=\"").append(str).append("/common/toobar/lastPage.gif\"></a>&nbsp;");
    localStringBuilder.append("&nbsp;Total &nbsp;<b>").append(this._$3).append("</b>&nbsp;Items&nbsp;").append("&nbsp;<img src=\"").append(str).append("/common/toobar/gotoPage.gif\">&nbsp;<select name='page' onChange=\"").append(paramString).append("(this.options[this.selectedIndex].value)\">");
    int i = (this._$5 >= 10) ? this._$5 - 10 : 1;
    int j = (this._$2 - this._$5 > 10) ? this._$5 + 10 : this._$2;
    for (int k = i; k <= j; ++k)
      if (k == this._$5)
        localStringBuilder.append("<option value='").append(k).append("' selected>").append(k).append("</option>");
      else
        localStringBuilder.append("<option value='").append(k).append("'>").append(k).append("</option>");
    localStringBuilder.append("</select>");
    return localStringBuilder.toString();
  }

  public String getToolBar(String paramString1, String paramString2)
  {
    String str = "";
    str = str + "<script language='javascript'>\n";
    str = str + "function commonSubmit(val){\n";
    str = str + "var patrn=/^[0-9]{1,20}$/;\n";
    str = str + "if (!patrn.exec(val)){\n";
    str = str + " alert(\"请输入有效页号！\");\n";
    str = str + " return false ;\n";
    str = str + " }else{\n";
    str = str + "    document." + paramString2 + ".action='" + paramString1 + "curPageNO='+val;" + "\n";
    str = str + "    document." + paramString2 + ".submit();" + "\n";
    str = str + "    return true ;\n";
    str = str + "} \n";
    str = str + " }\n";
    str = str + "</script>\n";
    str = str + "&nbsp;共<b>" + this._$3 + "</b>条&nbsp;共" + this._$2 + "页&nbsp;当前第" + this._$5 + "页&nbsp;&nbsp;&nbsp;";
    if ((this._$5 == 1) || (this._$5 == 0))
    {
      str = str + "首页|前页|";
    }
    else
    {
      str = str + "<a onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(1)\"><b>首页</b></a>|";
      str = str + "<a onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + (this._$5 - 1) + ")\"><b>前页</b></a>|";
    }
    if ((this._$5 - this._$2 == 0) || (this._$2 == 0) || (this._$2 == 1))
    {
      str = str + "后页|尾页&nbsp;";
    }
    else
    {
      str = str + "<a onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + (this._$5 + 1) + ")\"><b>后页</b></a>|";
      str = str + "<a onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + this._$2 + ")\"><b>尾页</b></a>";
    }
    if ((this._$2 == 1) || (this._$2 == 0))
    {
      str = str + " &nbsp;转到:<input type=text maxLength=5  name=\"pageroffset\" size=3  onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffset.value)\" disabled> 页&nbsp;";
      str = str + "<INPUT type=image src='/legendshop/images/pageGo.gif' onclick='return commonSubmit()' width=34 height=17 border=0 disabled='disabled'>";
    }
    else
    {
      str = str + " &nbsp;转到:<input type=text maxLength=5  name=\"pageroffsetll\" size=3  onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
      str = str + "<INPUT type=image src='/legendshop/images/pageGo.gif' onclick='commonSubmit(document.all.pageroffsetll.value)' width=34 height=17 border=0 >";
    }
    return str;
  }

  public static void main(String[] paramArrayOfString)
  {
    Pager localPager = new Pager();
    System.out.println(localPager.getToolBar("search.do", "formName"));
  }

  public void setPageSize(int paramInt)
  {
    this._$4 = paramInt;
  }

  public void setRowsCount(long paramLong)
  {
    this._$3 = paramLong;
  }

  public void setPageCount(int paramInt)
  {
    this._$2 = paramInt;
  }

  public int getOffset()
  {
    return this._$1;
  }

  public long getPageDactSize()
  {
    long l = this._$1 + this._$4;
    return ((l > this._$3) ? this._$3 : l);
  }

  public String getBar(Locale paramLocale, String paramString)
  {
    return getBar(paramString);
  }
}
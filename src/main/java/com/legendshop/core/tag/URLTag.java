package com.legendshop.core.tag;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import org.apache.commons.lang.StringUtils;

public class URLTag extends LegendShopTag
{
  public static final String URL_ENCODE = "UTF-8";
  private String _$2;
  private boolean _$1;

  public void doTag()
    throws JspException, IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(request().getContextPath());
    if (this._$1)
    {
      if (this._$2 == null)
        this._$2 = "";
      String[] arrayOfString1 = this._$2.split("/");
      String[] arrayOfString2 = arrayOfString1;
      int i = arrayOfString2.length;
      for (int j = 0; j < i; ++j)
      {
        String str = arrayOfString2[j];
        if (StringUtils.isNotEmpty(str))
          localStringBuilder.append("/").append(URLEncoder.encode(str, "UTF-8"));
      }
    }
    else
    {
      localStringBuilder.append(this._$2);
    }
    write(localStringBuilder.toString());
  }

  public void setAddress(String paramString)
  {
    this._$2 = paramString;
  }

  public void setEncode(boolean paramBoolean)
  {
    this._$1 = paramBoolean;
  }
}
package com.legendshop.core.helper;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.util.AppUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaticPageFilter
  implements Filter
{
  protected FilterConfig config;
  private String _$3 = null;
  private List<String> _$2;
  private String _$1 = null;

  public void init(FilterConfig paramFilterConfig)
    throws ServletException
  {
    this.config = paramFilterConfig;
    this._$3 = PropertiesUtil.getHtmlPath();
    this._$1 = paramFilterConfig.getServletContext().getContextPath();
    this._$2 = new ArrayList();
    this._$2.add("/sort");
    this._$2.add("/views");
    this._$2.add("/views");
    this._$2.add("/group");
    this._$2.add("/group/view");
  }

  public void destroy()
  {
  }

  public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws IOException, ServletException
  {
    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.STATIC_PAGE_SUPPORT, Boolean.class)).booleanValue())
    {
      HttpServletRequest localHttpServletRequest = (HttpServletRequest)paramServletRequest;
      String str1 = localHttpServletRequest.getRequestURI();
      String str2 = _$1(str1, this._$1);
      if (str2 != null)
      {
        HttpServletResponse localHttpServletResponse = (HttpServletResponse)paramServletResponse;
        String str3 = str1.substring(str1.lastIndexOf("/") + 1);
        if (AppUtils.isBlank(str3))
          str3 = "index";
        String str4 = str3 + ".html";
        String str5 = ThreadLocalContext.getCurrentShopName(localHttpServletRequest, localHttpServletResponse);
        String str6 = this._$3 + str5;
        StringBuilder localStringBuilder = new StringBuilder(str6).append(str2).append("/").append(str4);
        String str7 = localStringBuilder.toString();
        localStringBuilder.setLength(0);
        String str8 = this._$1 + "/" + "html/" + str5 + str2 + "/" + str4;
        File localFile = new File(str7);
        if (!(localFile.exists()))
        {
          CharResponseWrapper localCharResponseWrapper = new CharResponseWrapper(localHttpServletResponse);
          paramFilterChain.doFilter(paramServletRequest, localCharResponseWrapper);
          String str9 = localCharResponseWrapper.toString();
          if (str9 != null)
          {
            FileProcessor.writeFile(str9, str6 + str2, str4);
            localHttpServletResponse.sendRedirect(str8);
          }
        }
        else
        {
          localHttpServletResponse.sendRedirect(str8);
        }
      }
      else
      {
        paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
      }
    }
    else
    {
      paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
    }
  }

  private String _$1(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    if (paramString1.indexOf(".") < 0)
    {
      String str1 = paramString1.substring(paramString2.length());
      String str2 = str1.substring(0, str1.lastIndexOf("/"));
      if ((this._$2.contains(str2)) || (str1.equals("/index")))
        return str2;
    }
    return null;
  }
}
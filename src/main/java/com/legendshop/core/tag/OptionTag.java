package com.legendshop.core.tag;

import com.legendshop.core.UserManager;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.NamedEntity;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.LocaleResolver;

public abstract class OptionTag extends LegendShopTag
{
  private static Log _$1 = LogFactory.getLog(OptionTag.class);
  protected String id;
  protected String defaultDisp = "-- 请选择 --";
  protected String exclude;
  protected LocaleResolver localeResolver;
  protected boolean required;
  protected String selectedValue;
  protected String shopName;
  protected String type;
  protected String onChange;

  public OptionTag()
  {
    if (this.localeResolver == null)
      this.localeResolver = ((LocaleResolver)ContextServiceLocator.getInstance().getBean("localeResolver"));
  }

  public void doTag()
    throws JspException, IOException
  {
    if (AppUtils.isBlank(this.shopName))
      this.shopName = UserManager.getUserName();
    StringBuilder localStringBuilder = new StringBuilder();
    if (AppUtils.isNotBlank(this.onChange))
    {
      localStringBuilder.append("<select id=\"").append(this.id).append("\" name=\"").append(this.id).append("\" ");
      localStringBuilder.append("onChange=\"").append(this.onChange).append("\">");
    }
    else
    {
      localStringBuilder.append("<select id=\"").append(this.id).append("\" name=\"").append(this.id).append("\">");
    }
    if (!(this.required))
      localStringBuilder.append("<option value=\"\">").append(this.defaultDisp).append("</option> ");
    String[] arrayOfString1 = null;
    if (AppUtils.isNotBlank(this.exclude))
      arrayOfString1 = this.exclude.split(",");
    List localList = retrieveData(this.type, this.shopName);
    if (AppUtils.isNotBlank(localList))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        NamedEntity localNamedEntity = (NamedEntity)localObject;
        String str1 = String.valueOf(localNamedEntity.getId());
        int i = 0;
        if (AppUtils.isNotBlank(arrayOfString1))
        {
          String[] arrayOfString2 = arrayOfString1;
          int j = arrayOfString2.length;
          for (int k = 0; k < j; ++k)
          {
            String str2 = arrayOfString2[k];
            if (str2.equals(str1))
            {
              i = 1;
              break;
            }
          }
        }
        if (i == 0)
          _$1(localStringBuilder, str1, localNamedEntity.getName());
      }
    }
    localStringBuilder.append("</select>");
    try
    {
      pageContext().getOut().println(localStringBuilder);
    }
    catch (IOException localIOException)
    {
      _$1.error("IOException in SelectTag: ", localIOException);
    }
  }

  public abstract List<?> retrieveData(String paramString1, String paramString2);

  private void _$1(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (paramString2.startsWith("message:")))
    {
      String str = paramString2.substring("message:".length());
      Locale localLocale = this.localeResolver.resolveLocale(request());
      if (localLocale != null)
        paramString2 = ResourceBundleHelper.getString(localLocale, String.valueOf(str), str);
      else
        paramString2 = ResourceBundleHelper.getString(String.valueOf(str), str);
    }
    paramStringBuilder.append("<option value=\"").append(paramString1).append("\"");
    if (paramString1.equals(this.selectedValue))
      paramStringBuilder.append(" selected ");
    paramStringBuilder.append(">");
    paramStringBuilder.append(paramString2);
    paramStringBuilder.append("</option>");
  }

  public String getId()
  {
    return this.id;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public String getDefaultDisp()
  {
    return this.defaultDisp;
  }

  public void setDefaultDisp(String paramString)
  {
    this.defaultDisp = paramString;
  }

  public String getExclude()
  {
    return this.exclude;
  }

  public void setExclude(String paramString)
  {
    this.exclude = paramString;
  }

  public LocaleResolver getLocaleResolver()
  {
    return this.localeResolver;
  }

  public void setLocaleResolver(LocaleResolver paramLocaleResolver)
  {
    this.localeResolver = paramLocaleResolver;
  }

  public boolean isRequired()
  {
    return this.required;
  }

  public void setRequired(boolean paramBoolean)
  {
    this.required = paramBoolean;
  }

  public String getSelectedValue()
  {
    return this.selectedValue;
  }

  public void setSelectedValue(String paramString)
  {
    this.selectedValue = paramString;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public void setShopName(String paramString)
  {
    this.shopName = paramString;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public String getOnChange()
  {
    return this.onChange;
  }

  public void setOnChange(String paramString)
  {
    this.onChange = paramString;
  }
}
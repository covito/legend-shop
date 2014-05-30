package com.legendshop.core.tag;

import com.legendshop.core.cache.LegendCacheManager;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.ServiceLocatorIF;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class CacheTag extends BodyTagSupport
  implements TryCatchFinally
{
  private static final long serialVersionUID = -262317675765477229L;
  private final Logger _$4 = LoggerFactory.getLogger(CacheTag.class);
  private LegendCacheManager _$3;
  private String _$2 = null;
  private String _$1 = null;

  public CacheTag()
  {
    if (this._$3 == null)
      this._$3 = ((LegendCacheManager)ContextServiceLocator.getInstance().getBean("cacheManager"));
  }

  public int doAfterBody()
    throws JspTagException
  {
    String str = null;
    try
    {
      if (this.bodyContent != null)
        if ((str = this.bodyContent.getString()) != null)
        {
          Cache localCache = this._$3.getCache(this._$1);
          localCache.put(_$1(), str);
          this.bodyContent.clearBody();
          this.bodyContent.write(str);
          this.bodyContent.writeOut(this.bodyContent.getEnclosingWriter());
        }
    }
    catch (IOException localIOException)
    {
      throw new JspTagException("IO Error: " + localIOException.getMessage());
    }
    return 0;
  }

  private String _$1()
  {
    return ((HttpServletRequest)this.pageContext.getRequest()).getRequestURI() + this._$2;
  }

  public void doCatch(Throwable paramThrowable)
    throws Throwable
  {
    throw paramThrowable;
  }

  public int doEndTag()
    throws JspTagException
  {
    return 6;
  }

  public void doFinally()
  {
    this._$2 = null;
  }

  public int doStartTag()
    throws JspTagException
  {
    int i = 2;
    String str = _$1();
    try
    {
      Cache localCache = this._$3.getCache(this._$1);
      SimpleValueWrapper localSimpleValueWrapper = (SimpleValueWrapper)localCache.get(str);
      if (this._$4.isDebugEnabled())
        this._$4.debug("<cache>: Using cached entry : " + str);
      if (localSimpleValueWrapper != null)
      {
        this.pageContext.getOut().write((String)localSimpleValueWrapper.get());
        i = 0;
      }
    }
    catch (IOException localIOException)
    {
      throw new JspTagException("IO Exception: " + localIOException.getMessage());
    }
    if ((i == 2) && (this._$4.isDebugEnabled()))
      this._$4.debug("<cache>: Cached content not used: New cache entry, cache stale or scope flushed : " + str);
    return i;
  }

  public void setKey(String paramString)
  {
    this._$2 = paramString;
  }

  public void setCacheName(String paramString)
  {
    this._$1 = paramString;
  }
}
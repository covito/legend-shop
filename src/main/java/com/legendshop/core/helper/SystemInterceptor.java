package com.legendshop.core.helper;

import com.legendshop.core.handler.Handler;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SystemInterceptor extends HandlerInterceptorAdapter
{
  private static Logger _$2 = LoggerFactory.getLogger(SystemInterceptor.class);
  private List<Handler> _$1;

  public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
    throws Exception
  {
    if (ThreadLocalContext.requestStarted())
    {
      _$2.error("SystemInterceptorgetRequest  = " + ThreadLocalContext.getRequest());
      Iterator localIterator = this._$1.iterator();
      while (localIterator.hasNext())
      {
        Handler localHandler = (Handler)localIterator.next();
        localHandler.handle(paramHttpServletRequest, paramHttpServletResponse);
      }
    }
    return super.preHandle(paramHttpServletRequest, paramHttpServletResponse, paramObject);
  }

  public void setHandlers(List<Handler> paramList)
  {
    this._$1 = paramList;
  }
}
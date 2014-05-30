package com.legendshop.core.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerManager
{
  private List<Handler> _$1;

  public void handle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    Iterator localIterator = this._$1.iterator();
    while (localIterator.hasNext())
    {
      Handler localHandler = (Handler)localIterator.next();
      localHandler.handle(paramHttpServletRequest, paramHttpServletResponse);
    }
  }

  public List<Handler> getHandlers()
  {
    return this._$1;
  }

  public void setHandlers(List<Handler> paramList)
  {
    this._$1 = paramList;
  }
}
package com.legendshop.event;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class EventContext extends HashMap<String, Object>
{
  private static final long serialVersionUID = 5320624271576847671L;
  private final String _$3 = "reqeust";
  private final String _$2 = "response";
  private HttpServletRequest _$1;

  public EventContext()
  {
  }

  public EventContext(HttpServletRequest paramHttpServletRequest)
  {
    this._$1 = paramHttpServletRequest;
  }

  public Object getRequest()
  {
    return get("reqeust");
  }

  public void setRequest(Object paramObject)
  {
    put("reqeust", paramObject);
  }

  public Object getResponse()
  {
    return get("response");
  }

  public Boolean getBooleanResponse()
  {
    Boolean localBoolean = (Boolean)get("response");
    if (localBoolean == null)
      localBoolean = Boolean.valueOf(true);
    return localBoolean;
  }

  public Boolean getBooleanResponse(boolean paramBoolean)
  {
    Boolean localBoolean = (Boolean)get("response");
    if (localBoolean == null)
      localBoolean = Boolean.valueOf(paramBoolean);
    return localBoolean;
  }

  public void setResponse(Object paramObject)
  {
    put("response", paramObject);
  }

  public HttpServletRequest getHttpRequest()
  {
    return this._$1;
  }

  public void setHttpRequest(HttpServletRequest paramHttpServletRequest)
  {
    this._$1 = paramHttpServletRequest;
  }
}
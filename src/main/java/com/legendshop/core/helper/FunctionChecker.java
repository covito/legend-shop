package com.legendshop.core.helper;

import com.legendshop.core.event.CoreEventId;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionChecker
  implements Checker<String>
{
  private static Logger _$1 = LoggerFactory.getLogger(FunctionChecker.class);

  public boolean check(String paramString, HttpServletRequest paramHttpServletRequest)
  {
    EventContext localEventContext = new EventContext(paramHttpServletRequest);
    localEventContext.setRequest(paramString);
    EventHome.publishEvent(new GenericEvent(localEventContext, CoreEventId.FUNCTION_CHECK_EVENT));
    Boolean localBoolean = localEventContext.getBooleanResponse();
    return localBoolean.booleanValue();
  }
}
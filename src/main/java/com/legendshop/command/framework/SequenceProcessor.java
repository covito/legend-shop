package com.legendshop.command.framework;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

public class SequenceProcessor extends BaseProcessor
{
  private static Logger _$4 = Logger.getLogger(SequenceProcessor.class);

  public boolean supports(Command paramCommand)
  {
    return paramCommand instanceof AbstractCommand;
  }

  public void doActivities(Request paramRequest, Response paramResponse)
    throws Exception
  {
    if (_$4.isDebugEnabled())
      _$4.debug("<-- SequenceProcessor start " + getBeanName());
    Map localMap1 = paramResponse.getValues();
    Map localMap2 = paramRequest.getValues();
    List localList = getActivities();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Command localCommand = (Command)localIterator.next();
      if (_$4.isDebugEnabled())
        _$4.debug("    Command " + localCommand.getBeanName());
      try
      {
        localCommand.init("");
        localCommand.execute(localMap2, localMap1);
        localCommand.fini();
      }
      catch (Throwable localThrowable)
      {
        ErrorHandler localErrorHandler1 = localCommand.getErrorHandler();
        if (localErrorHandler1 == null)
        {
          if (_$4.isDebugEnabled())
            _$4.debug("no Errorhandler for AbstractCommand " + localCommand.getBeanName() + ", run processor Errorhandler and abort AbstractCommand ");
          ErrorHandler localErrorHandler2 = getErrorHandler();
          if (localErrorHandler2 == null)
          {
            if (_$4.isDebugEnabled())
              _$4.debug("no error handler for this processor, run defaultErrorHandler and abort processor ");
            ErrorHandler localErrorHandler3 = (ErrorHandler)this.beanFactory.getBean("defaultErrorHandler");
            if (localErrorHandler3 != null)
              localErrorHandler3.handleError(paramResponse, localThrowable);
            else if (_$4.isDebugEnabled())
              _$4.debug("no default errorHandler for this invoke process, abort!!");
          }
          else
          {
            if (_$4.isDebugEnabled())
              _$4.debug("run processor errorHandler and continue");
            localErrorHandler2.handleError(paramResponse, localThrowable);
          }
        }
        else
        {
          if (_$4.isDebugEnabled())
            _$4.debug("run AbstractCommand Errorhandler and continue");
          localErrorHandler1.handleError(paramResponse, localThrowable);
        }
      }
    }
    if (_$4.isDebugEnabled())
      _$4.debug("    SequenceProcessor end -->");
  }
}
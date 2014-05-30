package com.legendshop.command.framework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

public class RunByResultProcessor extends BaseProcessor
{
  private static Logger _$5 = Logger.getLogger(RunByResultProcessor.class);
  private String _$4;

  public String getFirstActivities()
  {
    return this._$4;
  }

  public void setFirstActivities(String paramString)
  {
    this._$4 = paramString;
  }

  public boolean supports(Command paramCommand)
  {
    return paramCommand instanceof AbstractCommand;
  }

  private Map _$1(List paramList)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Command localCommand = (Command)localIterator.next();
      String str = localCommand.getBeanName();
      localHashMap.put(str, localCommand);
    }
    return localHashMap;
  }

  public void doActivities(Request paramRequest, Response paramResponse)
    throws Exception
  {
    _$5.info("<-- RunByResultProcessor " + getBeanName());
    Map localMap1 = paramResponse.getValues();
    Map localMap2 = paramRequest.getValues();
    List localList = getActivities();
    Map localMap3 = _$1(localList);
    String str1 = getFirstActivities();
    if (localMap3.size() != 0)
    {
      Command localCommand = (Command)localMap3.get(str1);
      try
      {
        _$5.info("    AbstractCommand " + localCommand.getBeanName());
        localCommand.execute(localMap2, localMap1);
        for (String str2 = (String)paramResponse.getValue("next"); (str2 != null) && (localMap3.containsKey(str2)); str2 = (String)paramResponse.getValue("next"))
        {
          localCommand = (Command)localMap3.get(str2);
          _$5.info("    AbstractCommand " + localCommand.getBeanName());
          localCommand.init("");
          localCommand.execute(localMap2, localMap1);
          localCommand.fini();
        }
      }
      catch (Throwable localThrowable)
      {
        ErrorHandler localErrorHandler1 = localCommand.getErrorHandler();
        if (localErrorHandler1 == null)
        {
          _$5.info("no error handler for AbstractCommand " + localCommand.getBeanName() + ", run processorerrorHandler and abort AbstractCommand ");
          ErrorHandler localErrorHandler2 = getErrorHandler();
          if (localErrorHandler2 == null)
          {
            _$5.info("no error handler for this processor, run defaultErrorHandler and abort processor ");
            ErrorHandler localErrorHandler3 = (ErrorHandler)this.beanFactory.getBean("defaultErrorHandler");
            if (localErrorHandler3 != null)
              localErrorHandler3.handleError(paramResponse, localThrowable);
            else
              _$5.info("no default errorHandler for this invoke process, abort!!");
          }
          else
          {
            _$5.info("run processor errorHandler and continue");
            localErrorHandler2.handleError(paramResponse, localThrowable);
          }
        }
        else
        {
          _$5.info("run command errorHandler and continue");
          localErrorHandler1.handleError(paramResponse, localThrowable);
        }
      }
    }
    _$5.info("    RunByResultProcessor end -->");
  }
}
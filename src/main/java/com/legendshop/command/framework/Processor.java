package com.legendshop.command.framework;

import java.util.List;

public abstract interface Processor
{
  public abstract boolean supports(Command paramCommand);

  public abstract void doActivities(Request paramRequest, Response paramResponse)
    throws Exception;

  public abstract void setActivities(List paramList);

  public abstract void setErrorHandler(ErrorHandler paramErrorHandler);
}
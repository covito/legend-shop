package com.legendshop.command.framework;

import org.springframework.beans.factory.BeanNameAware;

public abstract interface ErrorHandler extends BeanNameAware
{
  public abstract void handleError(Response paramResponse, Throwable paramThrowable)
    throws Exception;
}
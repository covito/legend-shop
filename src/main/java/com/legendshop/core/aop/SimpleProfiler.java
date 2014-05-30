package com.legendshop.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.util.StopWatch;

public class SimpleProfiler
  implements Ordered
{
  private final Logger _$2 = LoggerFactory.getLogger(SimpleProfiler.class);
  private int _$1;

  public int getOrder()
  {
    return this._$1;
  }

  public void setOrder(int paramInt)
  {
    this._$1 = paramInt;
  }

  public Object profile(ProceedingJoinPoint paramProceedingJoinPoint)
    throws Throwable
  {
    Object localObject1;
    StopWatch localStopWatch = new StopWatch(super.getClass().getName());
    try
    {
      localStopWatch.start(paramProceedingJoinPoint.toShortString());
      localObject1 = paramProceedingJoinPoint.proceed();
    }
    finally
    {
      localStopWatch.stop();
      this._$2.info(localStopWatch.prettyPrint());
    }
    return localObject1;
  }
}
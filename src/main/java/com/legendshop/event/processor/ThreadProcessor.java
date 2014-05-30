package com.legendshop.event.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

public abstract class ThreadProcessor<T> extends AbstractProcessor<T>
{
  private final Logger _$2 = LoggerFactory.getLogger(ThreadProcessor.class);
  private TaskExecutor _$1;

  public void onEvent(T paramT)
  {
    this._$2.debug("{} ThreadProcessor execute {}", this, paramT);
    this._$1.execute(new TaskRunner(paramT, this));
  }

  public void setTaskExecutor(TaskExecutor paramTaskExecutor)
  {
    this._$1 = paramTaskExecutor;
  }
}
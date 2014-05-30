package com.legendshop.business.helper;

import com.legendshop.event.TaskItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskThread extends Thread
{
  private static Logger log = LoggerFactory.getLogger(TaskThread.class);
  public TaskItem item;

  public TaskThread(TaskItem item)
  {
    this.item = item;
  }

  public void run()
  {
    log.debug("{} item start running at {}", this.item.getClass().getSimpleName(), Long.valueOf(System.currentTimeMillis()));
    this.item.execute();
    log.debug("{} item finished running at {}", this.item.getClass().getSimpleName(), Long.valueOf(System.currentTimeMillis()));
  }
}
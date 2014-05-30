package com.legendshop.business.process.event;

import com.legendshop.event.processor.BaseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRegProcessor extends BaseProcessor<String>
{
  private final Logger log = LoggerFactory.getLogger(UserRegProcessor.class);

  public boolean isSupport(String task)
  {
    this.log.info("UserRegProcessor isSupport calling, task= " + task);
    return true;
  }

  public void process(String task)
  {
    this.log.info("UserRegProcessor process calling, task= " + task);
  }
}
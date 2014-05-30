package com.legendshop.business.process.event;

import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.Sub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderSaveProcessor extends BaseProcessor<Sub>
{
  private final Logger log = LoggerFactory.getLogger(OrderSaveProcessor.class);

  public boolean isSupport(Sub sub)
  {
    this.log.info("UserRegProcessor isSupport calling, task= " + sub);
    return true;
  }

  public void process(Sub sub)
  {
    this.log.info("UserRegProcessor process calling, task= " + sub);
  }
}
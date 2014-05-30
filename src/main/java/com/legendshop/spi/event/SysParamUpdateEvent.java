package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.SystemParameter;

public class SysParamUpdateEvent extends SystemEvent<SystemParameter>
{
  public SysParamUpdateEvent(SystemParameter source)
  {
    super(source, EventId.SYS_PARAM_EVENT);
  }
}
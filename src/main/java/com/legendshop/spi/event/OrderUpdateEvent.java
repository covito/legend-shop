package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.Sub;

public class OrderUpdateEvent extends SystemEvent<Sub>
{
  public OrderUpdateEvent(Sub source)
  {
    super(source, EventId.ORDER_UPDATE_EVENT);
  }
}
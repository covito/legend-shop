package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.Sub;

public class OrderSaveEvent extends SystemEvent<Sub>
{
  public OrderSaveEvent(Sub source)
  {
    super(source, EventId.ORDER_SAVE_EVENT);
  }
}
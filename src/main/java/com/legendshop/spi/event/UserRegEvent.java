package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;

public class UserRegEvent extends SystemEvent<String>
{
  public UserRegEvent(String source)
  {
    super(source, EventId.USER_REG_EVENT);
  }
}
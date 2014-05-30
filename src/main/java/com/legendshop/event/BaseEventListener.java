package com.legendshop.event;

import java.util.EventListener;

public abstract interface BaseEventListener<T> extends EventListener
{
  public abstract void onEvent(SystemEvent<T> paramSystemEvent);

  public abstract String getEventId();

  public abstract Integer getOrder();
}
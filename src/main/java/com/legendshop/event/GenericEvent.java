package com.legendshop.event;

public class GenericEvent extends SystemEvent<EventContext>
{
  public GenericEvent(EventContext paramEventContext, BaseEventId paramBaseEventId)
  {
    super(paramEventContext, paramBaseEventId);
  }
}
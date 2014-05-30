package com.legendshop.core.event.impl;

import com.legendshop.core.newservice.EventService;
import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.model.entity.UserEvent;
import com.legendshop.util.AppUtils;

public class UserEventProcessor extends ThreadProcessor<UserEvent>
{
  private EventService _$2;
  private boolean _$1;

  public boolean isSupport(UserEvent paramUserEvent)
  {
    return ((this._$1) && (AppUtils.isNotBlank(paramUserEvent.getUserId())) && (AppUtils.isNotBlank(paramUserEvent.getUserName())));
  }

  public void process(UserEvent paramUserEvent)
  {
    this._$2.saveEvent(paramUserEvent);
  }

  public void setEventService(EventService paramEventService)
  {
    this._$2 = paramEventService;
  }

  public void setLogEvent(boolean paramBoolean)
  {
    this._$1 = paramBoolean;
  }
}
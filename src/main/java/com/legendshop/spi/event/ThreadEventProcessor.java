package com.legendshop.spi.event;

import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.UserManager;
import com.legendshop.core.newservice.EventService;
import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.model.entity.AbstractEntity;
import com.legendshop.model.entity.UserEvent;
import java.util.Date;

public abstract class ThreadEventProcessor<T> extends ThreadProcessor<T>
{
  private EventService eventService;
  private boolean logEvent;
  private OperationTypeEnum operationType;

  public void logUserEvent(AbstractEntity entity, OperationTypeEnum operationType)
  {
    if (this.logEvent) {
      UserEvent userEvent = new UserEvent();
      userEvent.setCreateTime(new Date());
      userEvent.setOperation(operationType.name());
      userEvent.setRelateData(entity.toString());
      userEvent.setType(entity.getClass().getSimpleName());
      userEvent.setUserId(UserManager.getUserId());
      userEvent.setUserName(entity.getUserName());
      userEvent.setModifyUser(UserManager.getUserName());
      userEvent.setRelateId(entity.getId().toString());
      this.eventService.saveEvent(userEvent);
    }
  }

  public void setOperationType(OperationTypeEnum operationType)
  {
    this.operationType = operationType;
  }

  public void setEventService(EventService eventService)
  {
    this.eventService = eventService;
  }

  public void setLogEvent(boolean logEvent)
  {
    this.logEvent = logEvent;
  }

  public boolean isLogEvent()
  {
    return this.logEvent;
  }
}
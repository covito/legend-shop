package com.legendshop.core.event;

import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.UserManager;
import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.AbstractEntity;
import com.legendshop.model.entity.UserEvent;
import java.util.Date;

public class FireEvent extends SystemEvent<UserEvent>
{
  public FireEvent(AbstractEntity paramAbstractEntity, OperationTypeEnum paramOperationTypeEnum)
  {
    super(CoreEventId.FIRE_EVENT);
    setSource(_$1(paramAbstractEntity, paramOperationTypeEnum));
  }

  private UserEvent _$1(AbstractEntity paramAbstractEntity, OperationTypeEnum paramOperationTypeEnum)
  {
    UserEvent localUserEvent = new UserEvent();
    localUserEvent.setCreateTime(new Date());
    localUserEvent.setOperation(paramOperationTypeEnum.name());
    localUserEvent.setRelateData(paramAbstractEntity.toString());
    localUserEvent.setType(paramAbstractEntity.getClass().getSimpleName());
    localUserEvent.setUserId(UserManager.getUserId());
    localUserEvent.setUserName(paramAbstractEntity.getUserName());
    localUserEvent.setModifyUser(UserManager.getUserName());
    localUserEvent.setRelateId(paramAbstractEntity.getId().toString());
    return localUserEvent;
  }
}
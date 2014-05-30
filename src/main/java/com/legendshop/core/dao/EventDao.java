package com.legendshop.core.dao;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserEvent;
import java.util.List;

public abstract interface EventDao extends BaseDao
{
  public abstract List<UserEvent> getEvent(String paramString);

  public abstract UserEvent getEvent(Long paramLong);

  public abstract void deleteEvent(UserEvent paramUserEvent);

  public abstract Long saveEvent(UserEvent paramUserEvent);

  public abstract void updateEvent(UserEvent paramUserEvent);

  public abstract PageSupport getEvent(CriteriaQuery paramCriteriaQuery);
}
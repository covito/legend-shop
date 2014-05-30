package com.legendshop.business.service.impl;

import com.legendshop.core.dao.EventDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.newservice.EventService;
import com.legendshop.model.entity.UserEvent;
import com.legendshop.util.AppUtils;
import java.util.List;

public class EventServiceImpl
  implements EventService
{
  private EventDao eventDao;

  public void setEventDao(EventDao eventDao)
  {
    this.eventDao = eventDao;
  }

  public List<UserEvent> getEvent(String userName) {
    return this.eventDao.getEvent(userName);
  }

  public UserEvent getEvent(Long id) {
    return this.eventDao.getEvent(id);
  }

  public void deleteEvent(UserEvent userEvent) {
    this.eventDao.deleteEvent(userEvent);
  }

  public Long saveEvent(UserEvent userEvent) {
    if (!(AppUtils.isBlank(userEvent.getEventId()))) {
      updateEvent(userEvent);
      return userEvent.getEventId();
    }
    return ((Long)this.eventDao.save(userEvent));
  }

  public void updateEvent(UserEvent userEvent) {
    this.eventDao.updateEvent(userEvent);
  }

  public PageSupport getEvent(CriteriaQuery cq) {
    return this.eventDao.find(cq);
  }
}
package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.MessageDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDaoImpl extends BaseDaoImpl
  implements MessageDao
{
  private static Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);

  public Message getMessage(String id)
  {
    return ((Message)get(Message.class, id));
  }

  public void deleteMessage(Message message) {
    delete(message);
  }

  public String saveMessage(Message message) {
    return ((String)save(message));
  }

  public void updateMessage(Message message) {
    update(message);
  }

  public PageSupport getMessage(CriteriaQuery cq) {
    return find(cq);
  }
}
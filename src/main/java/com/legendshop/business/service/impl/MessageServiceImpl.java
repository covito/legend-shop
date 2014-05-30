package com.legendshop.business.service.impl;

import com.legendshop.business.dao.MessageDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Message;
import com.legendshop.spi.service.MessageService;
import com.legendshop.util.AppUtils;

public class MessageServiceImpl
  implements MessageService
{
  private MessageDao messageDao;

  public void setMessageDao(MessageDao messageDao)
  {
    this.messageDao = messageDao;
  }

  public Message getMessage(String id) {
    return this.messageDao.getMessage(id);
  }

  public void deleteMessage(Message message) {
    this.messageDao.deleteMessage(message);
  }

  public String saveMessage(Message message) {
    if (!(AppUtils.isBlank(message.getMsgId()))) {
      updateMessage(message);
      return message.getMsgId();
    }
    return ((String)this.messageDao.save(message));
  }

  public void updateMessage(Message message) {
    this.messageDao.updateMessage(message);
  }

  public PageSupport getMessage(CriteriaQuery cq) {
    return this.messageDao.find(cq);
  }
}
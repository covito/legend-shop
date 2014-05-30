package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Message;

public abstract interface MessageService
{
  public abstract Message getMessage(String paramString);

  public abstract void deleteMessage(Message paramMessage);

  public abstract String saveMessage(Message paramMessage);

  public abstract void updateMessage(Message paramMessage);

  public abstract PageSupport getMessage(CriteriaQuery paramCriteriaQuery);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Ask;
import java.util.List;

public abstract interface AskService
{
  public abstract List<Ask> getAsk(String paramString);

  public abstract Ask getAsk(Long paramLong);

  public abstract void deleteAsk(Ask paramAsk);

  public abstract Long saveAsk(Ask paramAsk);

  public abstract void updateAsk(Ask paramAsk);

  public abstract PageSupport getAsk(CriteriaQuery paramCriteriaQuery);
}
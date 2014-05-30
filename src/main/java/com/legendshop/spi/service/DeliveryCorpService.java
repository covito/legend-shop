package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryCorp;
import java.util.List;

public abstract interface DeliveryCorpService
{
  public abstract List<DeliveryCorp> getDeliveryCorp(String paramString);

  public abstract DeliveryCorp getDeliveryCorp(Long paramLong);

  public abstract void deleteDeliveryCorp(DeliveryCorp paramDeliveryCorp);

  public abstract Long saveDeliveryCorp(DeliveryCorp paramDeliveryCorp);

  public abstract void updateDeliveryCorp(DeliveryCorp paramDeliveryCorp);

  public abstract PageSupport getDeliveryCorp(CriteriaQuery paramCriteriaQuery);
}
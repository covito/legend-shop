package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryCorp;
import java.util.List;

public abstract interface DeliveryCorpDao extends BaseDao
{
  public abstract List<DeliveryCorp> getDeliveryCorp(String paramString);

  public abstract DeliveryCorp getDeliveryCorp(Long paramLong);

  public abstract void deleteDeliveryCorp(DeliveryCorp paramDeliveryCorp);

  public abstract Long saveDeliveryCorp(DeliveryCorp paramDeliveryCorp);

  public abstract void updateDeliveryCorp(DeliveryCorp paramDeliveryCorp);

  public abstract PageSupport getDeliveryCorp(CriteriaQuery paramCriteriaQuery);

  public abstract void deleteDeliveryCorp(String paramString);
}
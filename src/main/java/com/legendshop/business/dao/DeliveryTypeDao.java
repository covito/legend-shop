package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryType;
import java.util.List;

public abstract interface DeliveryTypeDao extends BaseDao
{
  public abstract List<DeliveryType> getDeliveryType(String paramString);

  public abstract DeliveryType getDeliveryType(Long paramLong);

  public abstract void deleteDeliveryType(DeliveryType paramDeliveryType);

  public abstract void deleteDeliveryType(String paramString);

  public abstract Long saveDeliveryType(DeliveryType paramDeliveryType);

  public abstract void updateDeliveryType(DeliveryType paramDeliveryType);

  public abstract PageSupport getDeliveryType(CriteriaQuery paramCriteriaQuery);
}
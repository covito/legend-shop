package com.legendshop.business.service.impl;

import com.legendshop.business.dao.DeliveryTypeDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryType;
import com.legendshop.spi.service.DeliveryTypeService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class DeliveryTypeServiceImpl
  implements DeliveryTypeService
{
  private DeliveryTypeDao deliveryTypeDao;

  public void setDeliveryTypeDao(DeliveryTypeDao deliveryTypeDao)
  {
    this.deliveryTypeDao = deliveryTypeDao;
  }

  public List<DeliveryType> getDeliveryType(String userName) {
    return this.deliveryTypeDao.getDeliveryType(userName);
  }

  public DeliveryType getDeliveryType(Long id) {
    return this.deliveryTypeDao.getDeliveryType(id);
  }

  public void deleteDeliveryType(DeliveryType deliveryType) {
    this.deliveryTypeDao.deleteDeliveryType(deliveryType);
  }

  public Long saveDeliveryType(DeliveryType deliveryType) {
    if (!(AppUtils.isBlank(deliveryType.getDvyTypeId()))) {
      updateDeliveryType(deliveryType);
      return deliveryType.getDvyTypeId();
    }
    return ((Long)this.deliveryTypeDao.save(deliveryType));
  }

  public void updateDeliveryType(DeliveryType deliveryType) {
    this.deliveryTypeDao.updateDeliveryType(deliveryType);
  }

  public PageSupport getDeliveryType(CriteriaQuery cq) {
    return this.deliveryTypeDao.find(cq);
  }
}
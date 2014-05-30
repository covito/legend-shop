package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.DeliveryTypeDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryTypeDaoImpl extends BaseDaoImpl
  implements DeliveryTypeDao
{
  private static Logger log = LoggerFactory.getLogger(DeliveryTypeDaoImpl.class);
  private BaseJdbcDao baseJdbcDao;

  public List<DeliveryType> getDeliveryType(String userName)
  {
    return findByHQL("from DeliveryType where userName = ?", new Object[] { userName });
  }

  public DeliveryType getDeliveryType(Long id) {
    return ((DeliveryType)get(DeliveryType.class, id));
  }

  public void deleteDeliveryType(DeliveryType deliveryType) {
    delete(deliveryType);
  }

  public Long saveDeliveryType(DeliveryType deliveryType) {
    return ((Long)save(deliveryType));
  }

  public void updateDeliveryType(DeliveryType deliveryType) {
    update(deliveryType);
  }

  public PageSupport getDeliveryType(CriteriaQuery cq) {
    return find(cq);
  }

  public void deleteDeliveryType(String userName)
  {
    this.baseJdbcDao.deleteUserItem("ls_dvy_type", userName);
  }

  public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao)
  {
    this.baseJdbcDao = baseJdbcDao;
  }
}
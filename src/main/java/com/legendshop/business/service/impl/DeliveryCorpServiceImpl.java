package com.legendshop.business.service.impl;

import com.legendshop.business.dao.DeliveryCorpDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryCorp;
import com.legendshop.spi.service.DeliveryCorpService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class DeliveryCorpServiceImpl
  implements DeliveryCorpService
{
  private DeliveryCorpDao deliveryCorpDao;

  public void setDeliveryCorpDao(DeliveryCorpDao deliveryCorpDao)
  {
    this.deliveryCorpDao = deliveryCorpDao;
  }

  public List<DeliveryCorp> getDeliveryCorp(String userName)
  {
    return this.deliveryCorpDao.getDeliveryCorp(userName);
  }

  public DeliveryCorp getDeliveryCorp(Long id)
  {
    return this.deliveryCorpDao.getDeliveryCorp(id);
  }

  public void deleteDeliveryCorp(DeliveryCorp deliveryCorp)
  {
    this.deliveryCorpDao.deleteDeliveryCorp(deliveryCorp);
  }

  public Long saveDeliveryCorp(DeliveryCorp deliveryCorp)
  {
    if (!(AppUtils.isBlank(deliveryCorp.getDvyId()))) {
      updateDeliveryCorp(deliveryCorp);
      return deliveryCorp.getDvyId();
    }
    return this.deliveryCorpDao.saveDeliveryCorp(deliveryCorp);
  }

  public void updateDeliveryCorp(DeliveryCorp deliveryCorp)
  {
    this.deliveryCorpDao.updateDeliveryCorp(deliveryCorp);
  }

  public PageSupport getDeliveryCorp(CriteriaQuery cq)
  {
    return this.deliveryCorpDao.getDeliveryCorp(cq);
  }
}
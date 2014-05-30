package com.legendshop.business.service.impl;

import com.legendshop.business.dao.PayTypeDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.PayType;
import com.legendshop.spi.service.PayTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class PayTypeServiceImpl
  implements PayTypeService
{
  private PayTypeDao payTypeDao;

  @Required
  public void setPayTypeDao(PayTypeDao payTypeDao)
  {
    this.payTypeDao = payTypeDao;
  }

  public List<PayType> getPayTypeList(String userName)
  {
    return this.payTypeDao.findByHQL("from PayType where userName = ?", new Object[] { userName });
  }

  public PayType getPayTypeById(Long id)
  {
    return ((PayType)this.payTypeDao.get(PayType.class, id));
  }

  public PayType getPayTypeByIdAndName(String userName, String payTypeId)
  {
    return this.payTypeDao.getPayTypeByIdAndName(userName, payTypeId);
  }

  public void delete(Long id)
  {
    PayType payType = getPayTypeById(id);
    if (payType != null) {
      EventHome.publishEvent(new FireEvent(payType, OperationTypeEnum.DELETE));
      this.payTypeDao.deletePayTypeById(id);
    }
  }

  public Long save(PayType payType)
  {
    return ((Long)this.payTypeDao.save(payType));
  }

  public void update(PayType payType)
  {
    EventHome.publishEvent(new FireEvent(payType, OperationTypeEnum.UPDATE));
    this.payTypeDao.updatePayType(payType);
  }

  public PageSupport getPayTypeList(CriteriaQuery cq)
  {
    return this.payTypeDao.find(cq);
  }
}
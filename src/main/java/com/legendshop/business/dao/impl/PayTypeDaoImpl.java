package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.PayTypeDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.PayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayTypeDaoImpl extends BaseDaoImpl
  implements PayTypeDao
{
  private static Logger log = LoggerFactory.getLogger(PayTypeDaoImpl.class);

  public void updatePayType(PayType payType)
  {
    update(payType);
  }

  public void deletePayTypeById(Long id)
  {
    deleteById(PayType.class, id);
  }

  public PayType getPayTypeByIdAndName(String userName, String payTypeId)
  {
    return ((PayType)findUniqueBy("from PayType where userName = ? and payTypeId = ?", PayType.class, new Object[] { userName, payTypeId }));
  }
}
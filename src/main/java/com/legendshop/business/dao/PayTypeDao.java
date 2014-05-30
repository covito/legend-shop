package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.PayType;

public abstract interface PayTypeDao extends BaseDao
{
  public abstract void updatePayType(PayType paramPayType);

  public abstract void deletePayTypeById(Long paramLong);

  public abstract PayType getPayTypeByIdAndName(String paramString1, String paramString2);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.PayType;
import java.util.List;

public abstract interface PayTypeService
{
  public abstract List<PayType> getPayTypeList(String paramString);

  public abstract PayType getPayTypeById(Long paramLong);

  public abstract PayType getPayTypeByIdAndName(String paramString1, String paramString2);

  public abstract void delete(Long paramLong);

  public abstract Long save(PayType paramPayType);

  public abstract void update(PayType paramPayType);

  public abstract PageSupport getPayTypeList(CriteriaQuery paramCriteriaQuery);
}
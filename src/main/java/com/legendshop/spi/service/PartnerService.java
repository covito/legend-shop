package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Partner;
import java.util.List;

public abstract interface PartnerService
{
  public abstract List<Partner> getPartner(String paramString);

  public abstract Partner getPartner(Long paramLong);

  public abstract void deletePartner(Partner paramPartner);

  public abstract Long savePartner(Partner paramPartner);

  public abstract void updatePartner(Partner paramPartner);

  public abstract PageSupport getPartner(CriteriaQuery paramCriteriaQuery);
}
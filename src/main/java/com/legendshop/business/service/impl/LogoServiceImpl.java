package com.legendshop.business.service.impl;

import com.legendshop.business.dao.LogoDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.event.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.cache.ShopDetailUpdate;
import com.legendshop.spi.service.LogoService;

public class LogoServiceImpl
  implements LogoService
{
  private LogoDao logoDao;

  @ShopDetailUpdate
  public void deleteLogo(ShopDetail shopDetail)
  {
    this.logoDao.deleteLogo(shopDetail);
  }

  @ShopDetailUpdate
  public void updateLogo(ShopDetail shopDetail)
  {
    EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.UPDATE));
    this.logoDao.updateLogo(shopDetail);
  }

  public PageSupport getLogo(SimpleHqlQuery hql)
  {
    return this.logoDao.getLogo(hql);
  }

  public void setLogoDao(LogoDao logoDao)
  {
    this.logoDao = logoDao;
  }
}
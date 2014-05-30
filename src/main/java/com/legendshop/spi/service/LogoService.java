package com.legendshop.spi.service;

import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.ShopDetail;

public abstract interface LogoService
{
  public abstract void deleteLogo(ShopDetail paramShopDetail);

  public abstract void updateLogo(ShopDetail paramShopDetail);

  public abstract PageSupport getLogo(SimpleHqlQuery paramSimpleHqlQuery);
}
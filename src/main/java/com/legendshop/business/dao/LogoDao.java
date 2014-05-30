package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.ShopDetail;

public abstract interface LogoDao extends BaseDao
{
  public abstract void deleteLogo(ShopDetail paramShopDetail);

  public abstract void updateLogo(ShopDetail paramShopDetail);

  public abstract PageSupport getLogo(SimpleHqlQuery paramSimpleHqlQuery);
}
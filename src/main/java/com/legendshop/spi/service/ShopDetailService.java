package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.service.ShopService;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;

public abstract interface ShopDetailService extends ShopService
{
  public abstract ShopDetail getShopDetailById(Long paramLong);

  public abstract ShopDetail getShopDetailByShopId(Long paramLong);

  public abstract UserDetail getShopDetailByName(String paramString);

  public abstract void delete(ShopDetail paramShopDetail);

  public abstract void save(ShopDetail paramShopDetail);

  public abstract void update(ShopDetail paramShopDetail);

  public abstract PageSupport getShopDetail(CriteriaQuery paramCriteriaQuery);

  public abstract ShopDetail getShopDetailByUserId(String paramString);

  public abstract void updateShopDetail(Product paramProduct);

  public abstract boolean updateShop(String paramString, ShopDetail paramShopDetail, Integer paramInteger);

  public abstract Long getShopIdByName(String paramString);
}
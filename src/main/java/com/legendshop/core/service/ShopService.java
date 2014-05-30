package com.legendshop.core.service;

import com.legendshop.model.entity.ShopDetailView;

public abstract interface ShopService
{
  public abstract ShopDetailView getShopDetailView(String paramString);

  public abstract String getShopNameByDomain(String paramString);

  public abstract Boolean isShopExists(String paramString);
}
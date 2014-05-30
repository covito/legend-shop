package com.legendshop.spi.service;

import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface IndexService extends BaseService
{
  public abstract void getIndex(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, ShopDetailView paramShopDetailView);

  public abstract UserInfo getAdminIndex(String paramString, ShopDetailView paramShopDetailView);
}
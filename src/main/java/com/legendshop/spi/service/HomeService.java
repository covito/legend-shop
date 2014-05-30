package com.legendshop.spi.service;

import com.legendshop.model.entity.ShopDetailView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface HomeService
{
  public abstract String getHome(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, ShopDetailView paramShopDetailView);
}
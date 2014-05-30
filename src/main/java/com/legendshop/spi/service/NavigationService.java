package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Navigation;

public abstract interface NavigationService
{
  public abstract Navigation getNavigation(Long paramLong);

  public abstract void deleteNavigation(Navigation paramNavigation);

  public abstract Long saveNavigation(Navigation paramNavigation);

  public abstract void updateNavigation(Navigation paramNavigation);

  public abstract PageSupport getNavigation(CriteriaQuery paramCriteriaQuery);
}
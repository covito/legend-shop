package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Navigation;
import java.util.List;

public abstract interface NavigationDao extends BaseDao
{
  public abstract Navigation getNavigation(Long paramLong);

  public abstract void deleteNavigation(Navigation paramNavigation);

  public abstract Long saveNavigation(Navigation paramNavigation);

  public abstract void updateNavigation(Navigation paramNavigation);

  public abstract PageSupport getNavigation(CriteriaQuery paramCriteriaQuery);

  public abstract List<Navigation> getNavigationList();
}
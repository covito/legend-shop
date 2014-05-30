package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NavigationItem;
import java.util.List;

public abstract interface NavigationItemDao extends BaseDao
{
  public abstract List<NavigationItem> getNavigationItem();

  public abstract List<NavigationItem> getAllNavigationItem();

  public abstract NavigationItem getNavigationItem(Long paramLong);

  public abstract void deleteNavigationItem(NavigationItem paramNavigationItem);

  public abstract Long saveNavigationItem(NavigationItem paramNavigationItem);

  public abstract void updateNavigationItem(NavigationItem paramNavigationItem);

  public abstract PageSupport getNavigationItem(CriteriaQuery paramCriteriaQuery);

  public abstract List<NavigationItem> getNavigationItemByNaviId(Long paramLong);

  public abstract void deleteNavigationItems(Long paramLong);
}
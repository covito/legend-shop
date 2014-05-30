package com.legendshop.business.service.impl;

import com.legendshop.business.dao.NavigationItemDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NavigationItem;
import com.legendshop.spi.service.NavigationItemService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class NavigationItemServiceImpl
  implements NavigationItemService
{
  private NavigationItemDao navigationItemDao;

  public void setNavigationItemDao(NavigationItemDao navigationItemDao)
  {
    this.navigationItemDao = navigationItemDao;
  }

  public List<NavigationItem> getNavigationItem() {
    return this.navigationItemDao.getNavigationItem();
  }

  public NavigationItem getNavigationItem(Long id) {
    return this.navigationItemDao.getNavigationItem(id);
  }

  public void deleteNavigationItem(NavigationItem navigationItem) {
    this.navigationItemDao.deleteNavigationItem(navigationItem);
  }

  public Long saveNavigationItem(NavigationItem navigationItem) {
    if (!(AppUtils.isBlank(navigationItem.getItemId()))) {
      updateNavigationItem(navigationItem);
      return navigationItem.getItemId();
    }
    return ((Long)this.navigationItemDao.save(navigationItem));
  }

  public void updateNavigationItem(NavigationItem navigationItem) {
    this.navigationItemDao.updateNavigationItem(navigationItem);
  }

  public PageSupport getNavigationItem(CriteriaQuery cq) {
    return this.navigationItemDao.find(cq);
  }

  public List<NavigationItem> getNavigationItemByNaviId(Long Navi_id)
  {
    return this.navigationItemDao.getNavigationItemByNaviId(Navi_id);
  }

  public void deleteNavigationItems(Long naviId)
  {
    this.navigationItemDao.deleteNavigationItems(naviId);
  }

  public List<NavigationItem> getAllNavigationItem()
  {
    return this.navigationItemDao.getAllNavigationItem();
  }
}
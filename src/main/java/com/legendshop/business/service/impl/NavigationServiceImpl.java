package com.legendshop.business.service.impl;

import com.legendshop.business.dao.NavigationDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Navigation;
import com.legendshop.spi.service.NavigationService;
import com.legendshop.util.AppUtils;

public class NavigationServiceImpl
  implements NavigationService
{
  private NavigationDao navigationDao;

  public void setNavigationDao(NavigationDao navigationDao)
  {
    this.navigationDao = navigationDao;
  }

  public Navigation getNavigation(Long id) {
    return this.navigationDao.getNavigation(id);
  }

  public void deleteNavigation(Navigation navigation) {
    this.navigationDao.deleteNavigation(navigation);
  }

  public Long saveNavigation(Navigation navigation) {
    if (!(AppUtils.isBlank(navigation.getNaviId()))) {
      updateNavigation(navigation);
      return navigation.getNaviId();
    }
    return ((Long)this.navigationDao.save(navigation));
  }

  public void updateNavigation(Navigation navigation) {
    this.navigationDao.updateNavigation(navigation);
  }

  public PageSupport getNavigation(CriteriaQuery cq) {
    return this.navigationDao.find(cq);
  }
}
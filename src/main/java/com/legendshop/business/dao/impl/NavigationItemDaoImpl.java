package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.NavigationItemDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NavigationItem;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationItemDaoImpl extends BaseDaoImpl
  implements NavigationItemDao
{
  private static Logger log = LoggerFactory.getLogger(NavigationItemDaoImpl.class);

  public List<NavigationItem> getNavigationItem()
  {
    return findByHQL("from NavigationItem where status = 1", new Object[0]);
  }

  public List<NavigationItem> getAllNavigationItem() {
    return findByHQL("from NavigationItem", new Object[0]);
  }

  public NavigationItem getNavigationItem(Long id) {
    return ((NavigationItem)get(NavigationItem.class, id));
  }

  public void deleteNavigationItem(NavigationItem navigationItem) {
    delete(navigationItem);
  }

  public Long saveNavigationItem(NavigationItem navigationItem) {
    return ((Long)save(navigationItem));
  }

  public void updateNavigationItem(NavigationItem navigationItem) {
    update(navigationItem);
  }

  public PageSupport getNavigationItem(CriteriaQuery cq) {
    return find(cq);
  }

  public List<NavigationItem> getNavigationItemByNaviId(Long Navi_id)
  {
    return findByHQL("from NavigationItem where naviId=?", new Object[] { Navi_id });
  }

  public void deleteNavigationItems(Long naviId)
  {
    exeByHQL("delete from NavigationItem where naviId = ?", new Object[] { naviId });
  }
}
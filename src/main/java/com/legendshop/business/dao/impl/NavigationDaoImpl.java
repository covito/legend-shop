package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.NavigationDao;
import com.legendshop.business.dao.NavigationItemDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Navigation;
import com.legendshop.model.entity.NavigationItem;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class NavigationDaoImpl extends BaseDaoImpl
  implements NavigationDao
{
  private static Logger log = LoggerFactory.getLogger(NavigationDaoImpl.class);
  private NavigationItemDao navigationItemDao;

  public void setNavigationItemDao(NavigationItemDao navigationItemDao)
  {
    this.navigationItemDao = navigationItemDao;
  }

  public Navigation getNavigation(Long id) {
    return ((Navigation)get(Navigation.class, id));
  }

  @CacheEvict(value={"NavigationList"}, allEntries=true)
  public void deleteNavigation(Navigation navigation) {
    this.navigationItemDao.deleteNavigationItems(navigation.getNaviId());
    delete(navigation);
  }

  @CacheEvict(value={"NavigationList"}, allEntries=true)
  public Long saveNavigation(Navigation navigation) {
    return ((Long)save(navigation));
  }

  @CacheEvict(value={"NavigationList"}, allEntries=true)
  public void updateNavigation(Navigation navigation) {
    update(navigation);
  }

  public PageSupport getNavigation(CriteriaQuery cq) {
    return find(cq);
  }

  @Cacheable({"NavigationList"})
  public List<Navigation> getNavigationList()
  {
    List list = findByHQL("from Navigation where status = 1", new Object[0]);

    List navigationItemList = this.navigationItemDao.getNavigationItem();

    Iterator localIterator1 = list.iterator(); while (localIterator1.hasNext()) { Navigation navi = (Navigation)localIterator1.next();
      for (Iterator localIterator2 = navigationItemList.iterator(); localIterator2.hasNext(); ) { NavigationItem navigationItem = (NavigationItem)localIterator2.next();
        navi.addSubItems(navigationItem);
      }
    }
    return list;
  }
}
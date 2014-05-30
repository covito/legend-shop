package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.PubDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Pub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

public class PubDaoImpl extends BaseDaoImpl
  implements PubDao
{
  private static Logger log = LoggerFactory.getLogger(PubDaoImpl.class);

  @Cacheable(value={"PubList"}, key="#shopName")
  public List<Pub> getPub(String shopName)
  {
    log.debug("getPub, shopName = {}", shopName);
    if (shopName == null)
      return null;

    List list = getPubList(shopName);
    if (AppUtils.isBlank(list))
      list = getPubList(PropertiesUtil.getDefaultShopName());

    return list;
  }

  private List<Pub> getPubList(String userName) {
    CriteriaQuery cq = new CriteriaQuery(Pub.class);
    Date today = new Date();
    cq.eq("status", Constants.ONLINE);
    cq.eq("userName", userName);

    cq.or(Restrictions.ge("endDate", today), Restrictions.isNull("endDate"));
    cq.or(Restrictions.le("startDate", today), 
      Restrictions.isNull("startDate"));
    cq.addOrder("desc", "recDate");
    return findListByCriteria(cq, 0, 9);
  }

  @CacheEvict(value={"Pub"}, key="#pub.id")
  public void deletePub(Pub pub)
  {
    deleteById(Pub.class, pub.getId());
  }

  @Caching(evict={@CacheEvict(value={"PubList"}, key="#pub.userName"), @CacheEvict(value={"Pub"}, key="#pub.id")})
  public void updatePub(Pub pub)
  {
    update(pub);
  }

  @CacheEvict(value={"PubList"}, key="#pub.userName")
  public Long savePub(Pub pub)
  {
    return ((Long)save(pub));
  }
}
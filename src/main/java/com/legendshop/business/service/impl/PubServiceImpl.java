package com.legendshop.business.service.impl;

import com.legendshop.business.dao.PubDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.model.entity.Pub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.PubService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class PubServiceImpl extends BaseServiceImpl
  implements PubService
{
  private PubDao pubDao;

  @Required
  public void setPubDao(PubDao pubDao)
  {
    this.pubDao = pubDao;
  }

  public List<Pub> getPubList(String userName)
  {
    return this.pubDao.findByHQL("from Pub where userName = ?", new Object[] { userName });
  }

  public Pub getPubById(Long id)
  {
    return ((Pub)this.pubDao.get(Pub.class, id));
  }

  public void deletePub(Pub pub)
  {
    this.pubDao.deletePub(pub);
  }

  public Long save(Pub pub, String userName, boolean viewAllDataFunction)
  {
    if (!(AppUtils.isBlank(pub.getId()))) {
      Pub entity = (Pub)this.pubDao.get(Pub.class, pub.getId());
      if (entity != null) {
        if ((!(viewAllDataFunction)) && (!(userName.equals(entity.getUserName()))))
          throw new PermissionException("Can't edit Pub does not onw to you!");

        entity.setRecDate(new Date());
        entity.setMsg(pub.getMsg());
        entity.setTitle(pub.getTitle());
        entity.setEndDate(pub.getEndDate());
        entity.setStartDate(pub.getStartDate());
        update(entity);
        return pub.getId();
      }
      return null;
    }
    pub.setStatus(Constants.ONLINE);
    return this.pubDao.savePub(pub);
  }

  public void update(Pub pub)
  {
    this.pubDao.updatePub(pub);
  }

  public PageSupport getPubList(CriteriaQuery cq)
  {
    return this.pubDao.find(cq);
  }
}
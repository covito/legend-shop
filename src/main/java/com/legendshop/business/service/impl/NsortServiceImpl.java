package com.legendshop.business.service.impl;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.dao.NsortDao;
import com.legendshop.spi.service.NsortService;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class NsortServiceImpl
  implements NsortService
{
  private NsortDao nsortDao;

  public List<Nsort> getNsortList(String userName)
  {
    return this.nsortDao.findByHQL("from Nsort where userName = ?", new Object[] { userName });
  }

  public List<Nsort> getNSort3BySort(Long sortId)
  {
    return this.nsortDao.findByHQL("from Nsort where sortId = ? and parentNsortId is not null", new Object[] { sortId });
  }

  public List<Nsort> getNSort2BySort(Long sortId)
  {
    return this.nsortDao.findByHQL("from Nsort where sortId = ? and parentNsortId is null", new Object[] { sortId });
  }

  public List<Nsort> getNSort3ByNSort2(Long nsortId)
  {
    return this.nsortDao.findByHQL("from Nsort where parentNsortId = ?", new Object[] { nsortId });
  }

  public boolean hasChildNsort(String userName, Long id, Long parentNsortId)
  {
    if (parentNsortId != null)
      return false;

    Long result = (Long)this.nsortDao.findUniqueBy("select count(*) from Nsort where parentNsortId = ?", Long.class, new Object[] { id });
    return (result.longValue() > -4648543202968600576L);
  }

  public boolean hasChildNsortBrand(Long id)
  {
    Long result = (Long)this.nsortDao.findUniqueBy("select count(*) from NsortBrand n where n.id.nsortId = ?", Long.class, new Object[] { id });
    return (result.longValue() > -4648543202968600576L);
  }

  public Nsort getNsort(Long id)
  {
    return ((Nsort)this.nsortDao.get(Nsort.class, id));
  }

  public Sort getSort(Long id)
  {
    return ((Sort)this.nsortDao.get(Sort.class, id));
  }

  public void delete(Long id)
  {
    this.nsortDao.deleteNsortById(id);
  }

  public Long save(Nsort nsort)
  {
    if (!(AppUtils.isBlank(nsort.getNsortId()))) {
      Nsort origin = getNsort(nsort.getNsortId());
      if (origin != null) {
        origin.setNsortName(nsort.getNsortName());
        origin.setSeq(nsort.getSeq());
        origin.setSortDeputy(nsort.getSortDeputy());
        update(origin);
      }

      return nsort.getNsortId();
    }
    nsort.setStatus(Constants.ONLINE);
    return ((Long)this.nsortDao.save(nsort));
  }

  public void update(Nsort nsort)
  {
    this.nsortDao.updateNsort(nsort);
  }

  public PageSupport getNsortList(CriteriaQuery cq)
  {
    return this.nsortDao.find(cq);
  }

  public PageSupport getNsortList(HqlQuery hql)
  {
    return this.nsortDao.find(hql);
  }

  public Nsort getNsortById(Long id)
  {
    return this.nsortDao.getNsort(id);
  }

  @Required
  public void setNsortDao(NsortDao nsortDao)
  {
    this.nsortDao = nsortDao;
  }

  public List<Nsort> getNsortBySortId(Long sortId)
  {
    return this.nsortDao.getNsortBySortId(sortId);
  }

  public List<Nsort> getNavigationNsort(String userName)
  {
    return this.nsortDao.getNavigationNsort(userName);
  }

  public void turnOn(Nsort nsort)
  {
    nsort.setStatus(Constants.ONLINE);
    update(nsort);
  }

  public void turnOff(Nsort nsort)
  {
    nsort.setStatus(Constants.OFFLINE);
    update(nsort);
  }

  public String getUserNameByNsortId(Long subNsortId)
  {
    return this.nsortDao.getUserNameByNsortId(subNsortId);
  }

  public boolean hasChildProduct(String userName, Long id, Long parentNsortId)
  {
    return this.nsortDao.hasChildProduct(userName, id, parentNsortId);
  }
}
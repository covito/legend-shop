package com.legendshop.business.service.impl;

import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Myleague;
import com.legendshop.spi.service.MyleagueService;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class MyleagueServiceImpl
  implements MyleagueService
{
  private MyleagueDao myleagueDao;

  @Required
  public void setMyleagueDao(MyleagueDao myleagueDao)
  {
    this.myleagueDao = myleagueDao;
  }

  public List<Myleague> getMyleagueList(String userName)
  {
    return this.myleagueDao.findByHQL("from Myleague where userName = ?", new Object[] { userName });
  }

  public Myleague getMyleagueById(Long id)
  {
    return ((Myleague)this.myleagueDao.get(Myleague.class, id));
  }

  public void delete(Long id)
  {
    this.myleagueDao.deleteMyleagueById(id);
  }

  public Long save(Myleague myleague)
  {
    if (!(AppUtils.isBlank(myleague.getId()))) {
      update(myleague);
      return myleague.getId();
    }
    return ((Long)this.myleagueDao.save(myleague));
  }

  public void update(Myleague myleague)
  {
    this.myleagueDao.updateMyleague(myleague);
  }

  public PageSupport getMyleagueList(CriteriaQuery cq)
  {
    return this.myleagueDao.find(cq);
  }

  public Myleague getMyleague(String userName, String shopName)
  {
    return this.myleagueDao.getMyleague(userName, shopName);
  }
}
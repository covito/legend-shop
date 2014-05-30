package com.legendshop.business.service.impl;

import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.spi.service.HotsearchService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class HotsearchServiceImpl
  implements HotsearchService
{
  Logger log = LoggerFactory.getLogger(HotsearchServiceImpl.class);
  private HotsearchDao hotsearchDao;
  private BaseJdbcDao baseJdbcDao;

  public List<Hotsearch> getHotsearch(String userName)
  {
    return this.hotsearchDao.getHotsearch(userName);
  }

  public Hotsearch getHotsearchById(Long id)
  {
    return ((Hotsearch)this.hotsearchDao.get(Hotsearch.class, id));
  }

  public Hotsearch getHotsearchByIdAndName(Integer id, String userName)
  {
    return this.hotsearchDao.getHotsearchByIdAndName(id, userName);
  }

  public void delete(Long id)
  {
    this.hotsearchDao.deleteHotsearchById(id);
  }

  public Long saveHotsearch(Hotsearch hotsearch)
  {
    return ((Long)this.hotsearchDao.save(hotsearch));
  }

  public void updateHotsearch(Hotsearch hotsearch)
  {
    this.hotsearchDao.updateHotsearch(hotsearch);
  }

  public List<Hotsearch> getHotsearch(Long sortId)
  {
    return this.hotsearchDao.getHotsearch(sortId);
  }

  @Required
  public void setHotsearchDao(HotsearchDao hotsearchDao)
  {
    this.hotsearchDao = hotsearchDao;
  }

  public PageSupport query(SimpleSqlQuery query)
  {
    return this.baseJdbcDao.find(query);
  }

  public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao) {
    this.baseJdbcDao = baseJdbcDao;
  }
}
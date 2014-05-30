package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Hotsearch;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class HotsearchDaoImpl extends BaseDaoImpl
  implements HotsearchDao
{
  private static Logger log = LoggerFactory.getLogger(HotsearchDaoImpl.class);

  public List<Hotsearch> getHotsearch(String userName)
  {
    log.debug("getHotsearch, userName = {}", userName);
    return findByHQL("from Hotsearch where userName = ?", new Object[] { userName });
  }

  @Cacheable(value={"HotsearchList"}, key="#isortId")
  public List<Hotsearch> getHotsearch(Long isortId)
  {
    return findByHQL("from Hotsearch where sort = ?", new Object[] { isortId });
  }

  @CacheEvict(value={"Hotsearch"}, key="#id")
  public void deleteHotsearchById(Long id)
  {
    deleteById(Hotsearch.class, id);
  }

  @CacheEvict(value={"Hotsearch"}, key="#hotsearch.id")
  public void updateHotsearch(Hotsearch hotsearch)
  {
    update(hotsearch);
  }

  public Hotsearch getHotsearchByIdAndName(Integer id, String userName)
  {
    Hotsearch hotsearch = (Hotsearch)findUniqueBy("from Hotsearch where id = ? and userName = ?", Hotsearch.class, new Object[] { id, userName });
    if (hotsearch == null)
      throw new NotFoundException("No hot search product record");

    return hotsearch;
  }
}
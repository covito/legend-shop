package com.legendshop.business.service.impl;

import com.legendshop.business.dao.IndexJpgDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.spi.service.IndexJpgService;
import org.springframework.cache.annotation.CacheEvict;

public class IndexJpgServiceImpl
  implements IndexJpgService
{
  private IndexJpgDao indexJpgDao;

  public void setIndexJpgDao(IndexJpgDao indexJpgDao)
  {
    this.indexJpgDao = indexJpgDao;
  }

  public PageSupport getIndexJpg(CriteriaQuery cq)
  {
    return this.indexJpgDao.queryIndexJpg(cq);
  }

  public Indexjpg getIndexJpgById(Long id)
  {
    return this.indexJpgDao.queryIndexJpg(id);
  }

  @CacheEvict(value={"Indexjpg"}, key="#indexjpg.id")
  public void deleteIndexJpg(Indexjpg indexjpg)
  {
    EventHome.publishEvent(new FireEvent(indexjpg, OperationTypeEnum.DELETE));
    this.indexJpgDao.deleteIndexJpg(indexjpg);
  }

  public Long getIndexJpgNum(String name)
  {
    return this.indexJpgDao.getIndexJpgNum(name);
  }

  @CacheEvict(value={"Indexjpg"}, key="#indexjpg.id")
  public void updateIndexjpg(Indexjpg indexjpg)
  {
    EventHome.publishEvent(new FireEvent(indexjpg, OperationTypeEnum.UPDATE));
    this.indexJpgDao.updateIndexjpg(indexjpg);
  }

  public void saveIndexjpg(Indexjpg indexjpg)
  {
    this.indexJpgDao.saveIndexjpg(indexjpg);
  }
}
package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.IndexJpgDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Indexjpg;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexJpgDaoImpl extends BaseDaoImpl
  implements IndexJpgDao
{
  private static Logger log = LoggerFactory.getLogger(IndexJpgDaoImpl.class);

  public PageSupport queryIndexJpg(CriteriaQuery cq)
  {
    return find(cq);
  }

  public Indexjpg queryIndexJpg(Long id)
  {
    return ((Indexjpg)get(Indexjpg.class, id));
  }

  public void deleteIndexJpg(Indexjpg indexjpg)
  {
    delete(indexjpg);
  }

  public Long getIndexJpgNum(String name)
  {
    return ((Long)findUniqueBy("select count(*) from Indexjpg where userName = ?", Long.class, new Object[] { name }));
  }

  public void updateIndexjpg(Indexjpg origin)
  {
    update(origin);
  }

  public void saveIndexjpg(Indexjpg indexjpg)
  {
    indexjpg.setUploadTime(new Date());
    save(indexjpg);
  }
}
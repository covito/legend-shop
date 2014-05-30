package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.AskDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Ask;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AskDaoImpl extends BaseDaoImpl
  implements AskDao
{
  private static Logger log = LoggerFactory.getLogger(AskDaoImpl.class);

  public List<Ask> getAsk(String userName)
  {
    return findByHQL("from Ask where userName = ?", new Object[] { userName });
  }

  public Ask getAsk(Long id) {
    return ((Ask)get(Ask.class, id));
  }

  public void deleteAsk(Ask ask) {
    delete(ask);
  }

  public Long saveAsk(Ask ask) {
    return ((Long)save(ask));
  }

  public void updateAsk(Ask ask) {
    update(ask);
  }

  public PageSupport getAsk(CriteriaQuery cq) {
    return find(cq);
  }
}
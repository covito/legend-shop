package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.CashDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Cash;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashDaoImpl extends BaseDaoImpl
  implements CashDao
{
  private static Logger log = LoggerFactory.getLogger(CashDaoImpl.class);

  public List<Cash> getCash(String userName)
  {
    return findByHQL("from Cash where userName = ?", new Object[] { userName });
  }

  public Cash getCash(Long id) {
    return ((Cash)get(Cash.class, id));
  }

  public void deleteCash(Cash cash) {
    delete(cash);
  }

  public Long saveCash(Cash cash) {
    return ((Long)save(cash));
  }

  public void updateCash(Cash cash) {
    update(cash);
  }

  public PageSupport getCash(CriteriaQuery cq) {
    return find(cq);
  }
}
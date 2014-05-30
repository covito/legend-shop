package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.CashFlowDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.CashFlow;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashFlowDaoImpl extends BaseDaoImpl
  implements CashFlowDao
{
  private static Logger log = LoggerFactory.getLogger(CashFlowDaoImpl.class);

  public List<CashFlow> getCashFlow(String userName)
  {
    return findByHQL("from CashFlow where userName = ?", new Object[] { userName });
  }

  public CashFlow getCashFlow(Long id) {
    return ((CashFlow)get(CashFlow.class, id));
  }

  public void deleteCashFlow(CashFlow cashFlow) {
    delete(cashFlow);
  }

  public Long saveCashFlow(CashFlow cashFlow) {
    return ((Long)save(cashFlow));
  }

  public void updateCashFlow(CashFlow cashFlow) {
    update(cashFlow);
  }

  public PageSupport getCashFlow(CriteriaQuery cq) {
    return find(cq);
  }
}
package com.legendshop.business.service.impl;

import com.legendshop.business.dao.CashDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Cash;
import com.legendshop.spi.service.CashService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class CashServiceImpl
  implements CashService
{
  private CashDao cashDao;

  public void setCashDao(CashDao cashDao)
  {
    this.cashDao = cashDao;
  }

  public List<Cash> getCash(String userName) {
    return this.cashDao.getCash(userName);
  }

  public Cash getCash(Long id) {
    return this.cashDao.getCash(id);
  }

  public void deleteCash(Cash cash) {
    this.cashDao.deleteCash(cash);
  }

  public Long saveCash(Cash cash) {
    if (!(AppUtils.isBlank(cash.getCashId()))) {
      updateCash(cash);
      return cash.getCashId();
    }
    return ((Long)this.cashDao.save(cash));
  }

  public void updateCash(Cash cash) {
    this.cashDao.updateCash(cash);
  }

  public PageSupport getCash(CriteriaQuery cq) {
    return this.cashDao.find(cq);
  }
}
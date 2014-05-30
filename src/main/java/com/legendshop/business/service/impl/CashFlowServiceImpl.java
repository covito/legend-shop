package com.legendshop.business.service.impl;

import com.legendshop.business.dao.CashFlowDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.CashFlow;
import com.legendshop.spi.service.CashFlowService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class CashFlowServiceImpl
  implements CashFlowService
{
  private CashFlowDao cashFlowDao;

  public void setCashFlowDao(CashFlowDao cashFlowDao)
  {
    this.cashFlowDao = cashFlowDao;
  }

  public List<CashFlow> getCashFlow(String userName) {
    return this.cashFlowDao.getCashFlow(userName);
  }

  public CashFlow getCashFlow(Long id) {
    return this.cashFlowDao.getCashFlow(id);
  }

  public void deleteCashFlow(CashFlow cashFlow) {
    this.cashFlowDao.deleteCashFlow(cashFlow);
  }

  public Long saveCashFlow(CashFlow cashFlow) {
    if (!(AppUtils.isBlank(cashFlow.getFlowId()))) {
      updateCashFlow(cashFlow);
      return cashFlow.getFlowId();
    }
    return ((Long)this.cashFlowDao.save(cashFlow));
  }

  public void updateCashFlow(CashFlow cashFlow) {
    this.cashFlowDao.updateCashFlow(cashFlow);
  }

  public PageSupport getCashFlow(CriteriaQuery cq) {
    return this.cashFlowDao.find(cq);
  }
}
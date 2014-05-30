package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.CashFlow;
import java.util.List;

public abstract interface CashFlowDao extends BaseDao
{
  public abstract List<CashFlow> getCashFlow(String paramString);

  public abstract CashFlow getCashFlow(Long paramLong);

  public abstract void deleteCashFlow(CashFlow paramCashFlow);

  public abstract Long saveCashFlow(CashFlow paramCashFlow);

  public abstract void updateCashFlow(CashFlow paramCashFlow);

  public abstract PageSupport getCashFlow(CriteriaQuery paramCriteriaQuery);
}
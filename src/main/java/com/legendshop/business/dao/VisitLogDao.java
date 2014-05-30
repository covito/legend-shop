package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.VisitLog;

public abstract interface VisitLogDao extends BaseDao
{
  public abstract void updateVisitLog(VisitLog paramVisitLog);

  public abstract void deleteVisitLogById(Long paramLong);

  public abstract VisitLog getVisitedIndexLog(VisitLog paramVisitLog);

  public abstract VisitLog getVisitedProdLog(VisitLog paramVisitLog);
}
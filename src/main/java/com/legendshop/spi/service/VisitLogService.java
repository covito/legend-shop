package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.VisitLog;
import java.util.List;

public abstract interface VisitLogService
{
  public abstract List<VisitLog> getVisitLogList(String paramString);

  public abstract VisitLog getVisitLogById(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(VisitLog paramVisitLog);

  public abstract void update(VisitLog paramVisitLog);

  public abstract PageSupport getVisitLogList(CriteriaQuery paramCriteriaQuery);

  public abstract void process(VisitLog paramVisitLog);
}
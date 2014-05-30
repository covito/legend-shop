package com.legendshop.business.service.impl;

import com.legendshop.business.dao.VisitLogDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.constants.VisitTypeEnum;
import com.legendshop.spi.service.VisitLogService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ip.IPSeeker;
import java.util.Date;
import java.util.List;

public class VisitLogServiceImpl
  implements VisitLogService
{
  private VisitLogDao visitLogDao;

  public void setVisitLogDao(VisitLogDao visitLogDao)
  {
    this.visitLogDao = visitLogDao;
  }

  public List<VisitLog> getVisitLogList(String userName)
  {
    return this.visitLogDao.findByHQL("from VisitLog where userName = ?", new Object[] { userName });
  }

  public VisitLog getVisitLogById(Long id)
  {
    return ((VisitLog)this.visitLogDao.get(VisitLog.class, id));
  }

  public void delete(Long id)
  {
    this.visitLogDao.deleteVisitLogById(id);
  }

  public Long save(VisitLog visitLog)
  {
    if (!(AppUtils.isBlank(visitLog.getVisitId()))) {
      update(visitLog);
      return visitLog.getVisitId();
    }
    return ((Long)this.visitLogDao.save(visitLog));
  }

  public void update(VisitLog visitLog)
  {
    this.visitLogDao.updateVisitLog(visitLog);
  }

  public PageSupport getVisitLogList(CriteriaQuery cq)
  {
    return this.visitLogDao.find(cq);
  }

  public void process(VisitLog visitLog)
  {
    visitLog.setArea(IPSeeker.getInstance().getArea(visitLog.getIp()));
    visitLog.setCountry(IPSeeker.getInstance().getCountry(visitLog.getIp()));
    VisitLog origin = null;
    if (VisitTypeEnum.INDEX.value().equals(visitLog.getPage()))
      origin = this.visitLogDao.getVisitedIndexLog(visitLog);
    else
      origin = this.visitLogDao.getVisitedProdLog(visitLog);

    if (origin != null) {
      Integer num = origin.getVisitNum();
      if (num == null)
        num = Integer.valueOf(1);
      else
        num = Integer.valueOf(num.intValue() + 1);

      origin.setVisitNum(num);
      origin.setDate(new Date());
      this.visitLogDao.updateVisitLog(origin);
    } else {
      visitLog.setVisitNum(Integer.valueOf(1));
      this.visitLogDao.save(visitLog);
    }
  }
}
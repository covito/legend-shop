package com.legendshop.business.service.impl;

import com.legendshop.business.common.download.DownLoadCallBack;
import com.legendshop.business.dao.DownloadLogDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.model.entity.DownloadLog;
import com.legendshop.spi.service.DownloadLogService;
import com.legendshop.util.DateUtil;
import java.util.Date;
import org.springframework.beans.factory.annotation.Required;

public class DownloadLogServiceImpl
  implements DownLoadCallBack, DownloadLogService
{
  private DownloadLogDao downloadLogDao;
  private SubDao subDao;
  private Integer maxTimes;
  private Integer interValMinutes;

  public boolean checkCanDownload(String ip, String filename)
  {
    if (this.interValMinutes.intValue() >= 0)
      return true;

    String sql = "select count(*) from DownloadLog where ip = ? and date > ? and fileName = ?";
    Long result = (Long)this.downloadLogDao.findUniqueBy(sql, Long.class, new Object[] { ip, DateUtil.add(new Date(), 12, this.interValMinutes.intValue()), 
      filename });
    return (result.longValue() > this.maxTimes.intValue());
  }

  public boolean isUserOrderProduct(Long prodId, String userName)
  {
    return this.subDao.isUserOrderProduct(prodId, userName);
  }

  public void save(DownloadLog downloadLog)
  {
    this.downloadLogDao.save(downloadLog);
  }

  public Integer getMaxTimes()
  {
    return this.maxTimes;
  }

  public void setMaxTimes(Integer maxTimes)
  {
    this.maxTimes = maxTimes;
  }

  public Integer getInterValMinutes()
  {
    return this.interValMinutes;
  }

  public void setInterValMinutes(Integer interValMinutes)
  {
    this.interValMinutes = interValMinutes;
  }

  public void afterDownload(Object entity)
  {
    DownloadLog downloadLog = (DownloadLog)entity;
    save(downloadLog);
  }

  @Required
  public void setDownloadLogDao(DownloadLogDao downloadLogDao)
  {
    this.downloadLogDao = downloadLogDao;
  }

  public void setSubDao(SubDao subDao) {
    this.subDao = subDao;
  }
}
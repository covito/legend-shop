package com.legendshop.spi.service;

import com.legendshop.model.entity.DownloadLog;

public abstract interface DownloadLogService
{
  public abstract boolean checkCanDownload(String paramString1, String paramString2);

  public abstract boolean isUserOrderProduct(Long paramLong, String paramString);

  public abstract void save(DownloadLog paramDownloadLog);

  public abstract Integer getMaxTimes();

  public abstract Integer getInterValMinutes();

  public abstract void afterDownload(Object paramObject);
}
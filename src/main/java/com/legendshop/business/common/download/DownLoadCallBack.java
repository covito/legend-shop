package com.legendshop.business.common.download;

public abstract interface DownLoadCallBack
{
  public abstract void afterDownload(Object paramObject);

  public abstract boolean checkCanDownload(String paramString1, String paramString2);

  public abstract boolean isUserOrderProduct(Long paramLong, String paramString);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Advertisement;
import java.util.List;
import java.util.Map;

public abstract interface AdvertisementService extends BaseService
{
  public abstract List<Advertisement> getAdvertisementByUserName(String paramString);

  public abstract Advertisement getAdvertisement(Long paramLong);

  public abstract boolean isMaxNum(String paramString1, String paramString2);

  public abstract void delete(Long paramLong);

  public abstract Long save(Advertisement paramAdvertisement);

  public abstract void update(Advertisement paramAdvertisement);

  public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery paramCriteriaQuery);

  public abstract Map<String, List<Advertisement>> getAdvertisement(String paramString1, String paramString2);
}
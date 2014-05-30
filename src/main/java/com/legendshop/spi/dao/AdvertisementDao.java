package com.legendshop.spi.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Advertisement;
import java.util.List;
import java.util.Map;

public abstract interface AdvertisementDao extends BaseDao
{
  public abstract Map<String, List<Advertisement>> getAdvertisement(String paramString1, String paramString2);

  public abstract List<Advertisement> getOneAdvertisement(String paramString1, String paramString2);

  public abstract boolean isMaxNum(String paramString1, String paramString2);

  public abstract void deleteAdvById(Long paramLong);

  public abstract void updateAdv(Advertisement paramAdvertisement);
}
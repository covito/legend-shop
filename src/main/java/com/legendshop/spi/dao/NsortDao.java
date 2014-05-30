package com.legendshop.spi.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.Nsort;
import java.util.List;

public abstract interface NsortDao extends BaseDao
{
  public abstract Nsort getNsort(Long paramLong);

  public abstract List<Nsort> getOtherNsortList(Long paramLong1, Long paramLong2);

  public abstract List<Nsort> getNsortList(Long paramLong);

  public abstract List<Nsort> getSubNsortBySortId(Long paramLong);

  public abstract List<Nsort> getSubNsortBySortId(Long paramLong, String paramString);

  public abstract List<Nsort> getOthorNsort(List<Nsort> paramList);

  public abstract List<Nsort> getOthorSubNsort(Long paramLong, List<Nsort> paramList);

  public abstract List<Nsort> getNsortBySortId(Long paramLong);

  public abstract List<Nsort> getNavigationNsort(String paramString);

  public abstract void updateNsort(Nsort paramNsort);

  public abstract void deleteNsortById(Long paramLong);

  public abstract List<KeyValueEntity> loadNSorts(Long paramLong);

  public abstract List<KeyValueEntity> loadSubNSorts(Long paramLong);

  public abstract String getUserNameByNsortId(Long paramLong);

  public abstract boolean hasChildProduct(String paramString, Long paramLong1, Long paramLong2);
}
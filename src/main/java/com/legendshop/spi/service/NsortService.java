package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import java.util.List;

public abstract interface NsortService
{
  public abstract List<Nsort> getNsortList(String paramString);

  public abstract List<Nsort> getNSort3BySort(Long paramLong);

  public abstract List<Nsort> getNSort3ByNSort2(Long paramLong);

  public abstract List<Nsort> getNSort2BySort(Long paramLong);

  public abstract boolean hasChildNsort(String paramString, Long paramLong1, Long paramLong2);

  public abstract boolean hasChildNsortBrand(Long paramLong);

  public abstract Nsort getNsort(Long paramLong);

  public abstract Sort getSort(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(Nsort paramNsort);

  public abstract void update(Nsort paramNsort);

  public abstract PageSupport getNsortList(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getNsortList(HqlQuery paramHqlQuery);

  public abstract Nsort getNsortById(Long paramLong);

  public abstract List<Nsort> getNsortBySortId(Long paramLong);

  public abstract List<Nsort> getNavigationNsort(String paramString);

  public abstract void turnOn(Nsort paramNsort);

  public abstract void turnOff(Nsort paramNsort);

  public abstract String getUserNameByNsortId(Long paramLong);

  public abstract boolean hasChildProduct(String paramString, Long paramLong1, Long paramLong2);
}
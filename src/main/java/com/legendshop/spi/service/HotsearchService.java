package com.legendshop.spi.service;

import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.model.entity.Hotsearch;
import java.util.List;

public abstract interface HotsearchService
{
  public abstract List<Hotsearch> getHotsearch(String paramString);

  public abstract Hotsearch getHotsearchById(Long paramLong);

  public abstract Hotsearch getHotsearchByIdAndName(Integer paramInteger, String paramString);

  public abstract void delete(Long paramLong);

  public abstract Long saveHotsearch(Hotsearch paramHotsearch);

  public abstract void updateHotsearch(Hotsearch paramHotsearch);

  public abstract List<Hotsearch> getHotsearch(Long paramLong);

  public abstract PageSupport query(SimpleSqlQuery paramSimpleSqlQuery);
}
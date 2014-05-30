package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Hotsearch;
import java.util.List;

public abstract interface HotsearchDao extends BaseDao
{
  public abstract List<Hotsearch> getHotsearch(String paramString);

  public abstract List<Hotsearch> getHotsearch(Long paramLong);

  public abstract void deleteHotsearchById(Long paramLong);

  public abstract void updateHotsearch(Hotsearch paramHotsearch);

  public abstract Hotsearch getHotsearchByIdAndName(Integer paramInteger, String paramString);
}
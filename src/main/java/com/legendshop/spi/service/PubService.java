package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Pub;
import java.util.List;

public abstract interface PubService extends BaseService
{
  public abstract List<Pub> getPubList(String paramString);

  public abstract Pub getPubById(Long paramLong);

  public abstract void deletePub(Pub paramPub);

  public abstract Long save(Pub paramPub, String paramString, boolean paramBoolean);

  public abstract void update(Pub paramPub);

  public abstract PageSupport getPubList(CriteriaQuery paramCriteriaQuery);
}
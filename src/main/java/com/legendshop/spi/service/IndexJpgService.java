package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Indexjpg;

public abstract interface IndexJpgService
{
  public abstract PageSupport getIndexJpg(CriteriaQuery paramCriteriaQuery);

  public abstract Indexjpg getIndexJpgById(Long paramLong);

  public abstract void deleteIndexJpg(Indexjpg paramIndexjpg);

  public abstract Long getIndexJpgNum(String paramString);

  public abstract void updateIndexjpg(Indexjpg paramIndexjpg);

  public abstract void saveIndexjpg(Indexjpg paramIndexjpg);
}
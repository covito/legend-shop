package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Indexjpg;

public abstract interface IndexJpgDao extends BaseDao
{
  public abstract PageSupport queryIndexJpg(CriteriaQuery paramCriteriaQuery);

  public abstract Indexjpg queryIndexJpg(Long paramLong);

  public abstract void deleteIndexJpg(Indexjpg paramIndexjpg);

  public abstract Long getIndexJpgNum(String paramString);

  public abstract void updateIndexjpg(Indexjpg paramIndexjpg);

  public abstract void saveIndexjpg(Indexjpg paramIndexjpg);
}
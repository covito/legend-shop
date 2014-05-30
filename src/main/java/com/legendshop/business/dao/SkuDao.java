package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Sku;
import java.util.List;

public abstract interface SkuDao extends BaseDao
{
  public abstract List<Sku> getSku(String paramString);

  public abstract Sku getSku(Long paramLong);

  public abstract void deleteSku(Sku paramSku);

  public abstract Long saveSku(Sku paramSku);

  public abstract void updateSku(Sku paramSku);

  public abstract PageSupport getSku(CriteriaQuery paramCriteriaQuery);
}
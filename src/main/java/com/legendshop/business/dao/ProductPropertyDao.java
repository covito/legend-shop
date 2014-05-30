package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductProperty;
import java.util.List;

public abstract interface ProductPropertyDao extends BaseDao
{
  public abstract List<ProductProperty> getProductProperty(String paramString);

  public abstract ProductProperty getProductProperty(Long paramLong);

  public abstract void deleteProductProperty(ProductProperty paramProductProperty);

  public abstract Long saveProductProperty(ProductProperty paramProductProperty);

  public abstract void updateProductProperty(ProductProperty paramProductProperty);

  public abstract PageSupport getProductProperty(CriteriaQuery paramCriteriaQuery);
}
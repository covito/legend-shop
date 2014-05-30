package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductPropertyValue;
import java.util.List;

public abstract interface ProductPropertyValueDao extends BaseDao
{
  public abstract List<ProductPropertyValue> getProductPropertyValue(String paramString);

  public abstract ProductPropertyValue getProductPropertyValue(Long paramLong);

  public abstract void deleteProductPropertyValue(ProductPropertyValue paramProductPropertyValue);

  public abstract void saveProductPropertyValue(ProductPropertyValue paramProductPropertyValue);

  public abstract void updateProductPropertyValue(ProductPropertyValue paramProductPropertyValue);

  public abstract PageSupport getProductPropertyValue(CriteriaQuery paramCriteriaQuery);
}
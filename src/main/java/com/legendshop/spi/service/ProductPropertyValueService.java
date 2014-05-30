package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductPropertyValue;
import java.util.List;

public abstract interface ProductPropertyValueService
{
  public abstract List<ProductPropertyValue> getProductPropertyValue(String paramString);

  public abstract ProductPropertyValue getProductPropertyValue(Long paramLong);

  public abstract void deleteProductPropertyValue(ProductPropertyValue paramProductPropertyValue);

  public abstract Long saveProductPropertyValue(ProductPropertyValue paramProductPropertyValue);

  public abstract void updateProductPropertyValue(ProductPropertyValue paramProductPropertyValue);

  public abstract PageSupport getProductPropertyValue(CriteriaQuery paramCriteriaQuery);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductSpec;
import java.util.List;

public abstract interface ProductSpecService
{
  public abstract List<ProductSpec> getProductSpec(String paramString);

  public abstract ProductSpec getProductSpec(Long paramLong);

  public abstract void deleteProductSpec(ProductSpec paramProductSpec);

  public abstract Long saveProductSpec(ProductSpec paramProductSpec);

  public abstract void updateProductSpec(ProductSpec paramProductSpec);

  public abstract PageSupport getProductSpec(CriteriaQuery paramCriteriaQuery);
}
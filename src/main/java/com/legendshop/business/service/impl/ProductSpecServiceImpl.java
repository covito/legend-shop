package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ProductSpecDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductSpec;
import com.legendshop.spi.service.ProductSpecService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class ProductSpecServiceImpl
  implements ProductSpecService
{
  private ProductSpecDao productSpecDao;

  public void setProductSpecDao(ProductSpecDao productSpecDao)
  {
    this.productSpecDao = productSpecDao;
  }

  public List<ProductSpec> getProductSpec(String userName) {
    return this.productSpecDao.getProductSpec(userName);
  }

  public ProductSpec getProductSpec(Long id) {
    return this.productSpecDao.getProductSpec(id);
  }

  public void deleteProductSpec(ProductSpec productSpec) {
    this.productSpecDao.deleteProductSpec(productSpec);
  }

  public Long saveProductSpec(ProductSpec productSpec) {
    if (!(AppUtils.isBlank(productSpec.getProdSpecId()))) {
      updateProductSpec(productSpec);
      return productSpec.getProdSpecId();
    }
    return ((Long)this.productSpecDao.save(productSpec));
  }

  public void updateProductSpec(ProductSpec productSpec) {
    this.productSpecDao.updateProductSpec(productSpec);
  }

  public PageSupport getProductSpec(CriteriaQuery cq) {
    return this.productSpecDao.find(cq);
  }
}
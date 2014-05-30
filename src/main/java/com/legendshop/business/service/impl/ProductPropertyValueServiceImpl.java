package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ProductPropertyValueDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductPropertyValue;
import com.legendshop.spi.service.ProductPropertyValueService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class ProductPropertyValueServiceImpl
  implements ProductPropertyValueService
{
  private ProductPropertyValueDao productPropertyValueDao;

  public void setProductPropertyValueDao(ProductPropertyValueDao productPropertyValueDao)
  {
    this.productPropertyValueDao = productPropertyValueDao;
  }

  public List<ProductPropertyValue> getProductPropertyValue(String userName) {
    return this.productPropertyValueDao.getProductPropertyValue(userName);
  }

  public ProductPropertyValue getProductPropertyValue(Long id) {
    return this.productPropertyValueDao.getProductPropertyValue(id);
  }

  public void deleteProductPropertyValue(ProductPropertyValue productPropertyValue) {
    this.productPropertyValueDao.deleteProductPropertyValue(productPropertyValue);
  }

  public Long saveProductPropertyValue(ProductPropertyValue productPropertyValue) {
    if (!(AppUtils.isBlank(productPropertyValue.getValueId()))) {
      updateProductPropertyValue(productPropertyValue);
      return productPropertyValue.getValueId();
    }
    return ((Long)this.productPropertyValueDao.save(productPropertyValue));
  }

  public void updateProductPropertyValue(ProductPropertyValue productPropertyValue) {
    this.productPropertyValueDao.updateProductPropertyValue(productPropertyValue);
  }

  public PageSupport getProductPropertyValue(CriteriaQuery cq) {
    return this.productPropertyValueDao.find(cq);
  }
}
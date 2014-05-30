package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ProductPropertyDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductProperty;
import com.legendshop.spi.service.ProductPropertyService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class ProductPropertyServiceImpl
  implements ProductPropertyService
{
  private ProductPropertyDao productPropertyDao;

  public void setProductPropertyDao(ProductPropertyDao productPropertyDao)
  {
    this.productPropertyDao = productPropertyDao;
  }

  public List<ProductProperty> getProductProperty(String userName) {
    return this.productPropertyDao.getProductProperty(userName);
  }

  public ProductProperty getProductProperty(Long id) {
    return this.productPropertyDao.getProductProperty(id);
  }

  public void deleteProductProperty(ProductProperty productProperty) {
    this.productPropertyDao.deleteProductProperty(productProperty);
  }

  public Long saveProductProperty(ProductProperty productProperty) {
    if (!(AppUtils.isBlank(productProperty.getPropId()))) {
      updateProductProperty(productProperty);
      return productProperty.getPropId();
    }
    return this.productPropertyDao.saveProductProperty(productProperty);
  }

  public void updateProductProperty(ProductProperty productProperty) {
    this.productPropertyDao.updateProductProperty(productProperty);
  }

  public PageSupport getProductProperty(CriteriaQuery cq) {
    return this.productPropertyDao.find(cq);
  }
}
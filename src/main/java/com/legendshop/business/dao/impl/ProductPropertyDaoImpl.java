package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ProductPropertyDao;
import com.legendshop.business.dao.ProductPropertyValueDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductProperty;
import com.legendshop.model.entity.ProductPropertyValue;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPropertyDaoImpl extends BaseDaoImpl
  implements ProductPropertyDao
{
  private static Logger log = LoggerFactory.getLogger(ProductPropertyDaoImpl.class);
  private ProductPropertyValueDao productPropertyValueDao;

  public List<ProductProperty> getProductProperty(String userName)
  {
    return findByHQL("from ProductProperty where userName = ?", new Object[] { userName });
  }

  public ProductProperty getProductProperty(Long id) {
    return ((ProductProperty)get(ProductProperty.class, id));
  }

  public void deleteProductProperty(ProductProperty productProperty) {
    delete(productProperty);
  }

  public Long saveProductProperty(ProductProperty productProperty) {
    Long propId = (Long)save(productProperty);
    List propValueList = productProperty.getProductPropertyValueList();
    if (propValueList != null)
      for (Iterator localIterator = propValueList.iterator(); localIterator.hasNext(); ) { ProductPropertyValue productPropertyValue = (ProductPropertyValue)localIterator.next();
        productPropertyValue.setPropId(propId);
        this.productPropertyValueDao.saveProductPropertyValue(productPropertyValue);
      }

    return propId;
  }

  public void updateProductProperty(ProductProperty productProperty) {
    update(productProperty);
  }

  public PageSupport getProductProperty(CriteriaQuery cq) {
    return find(cq);
  }

  public void setProductPropertyValueDao(ProductPropertyValueDao productPropertyValueDao)
  {
    this.productPropertyValueDao = productPropertyValueDao;
  }
}
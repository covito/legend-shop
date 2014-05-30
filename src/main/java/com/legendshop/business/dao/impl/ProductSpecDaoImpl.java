package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ProductSpecDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductSpec;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductSpecDaoImpl extends BaseDaoImpl
  implements ProductSpecDao
{
  private static Logger log = LoggerFactory.getLogger(ProductSpecDaoImpl.class);

  public List<ProductSpec> getProductSpec(String userName)
  {
    return findByHQL("from ProductSpec where userName = ?", new Object[] { userName });
  }

  public ProductSpec getProductSpec(Long id) {
    return ((ProductSpec)get(ProductSpec.class, id));
  }

  public void deleteProductSpec(ProductSpec productSpec) {
    delete(productSpec);
  }

  public Long saveProductSpec(ProductSpec productSpec) {
    return ((Long)save(productSpec));
  }

  public void updateProductSpec(ProductSpec productSpec) {
    update(productSpec);
  }

  public PageSupport getProductSpec(CriteriaQuery cq) {
    return find(cq);
  }
}
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.model.entity.ProductConsult;
import java.util.List;

public abstract interface ProductConsultDao extends BaseDao
{
  public abstract List<ProductConsult> getProductConsultList(Long paramLong);

  public abstract ProductConsult getProductConsult(Long paramLong);

  public abstract void deleteProductConsult(Long paramLong);

  public abstract Long saveProductConsult(ProductConsult paramProductConsult);

  public abstract void updateProductConsult(ProductConsult paramProductConsult);

  public abstract PageSupport getProductConsult(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getProductConsult(SimpleSqlQuery paramSimpleSqlQuery);

  public abstract void deleteProductConsult(ProductConsult paramProductConsult);

  public abstract long checkFrequency(ProductConsult paramProductConsult);
}
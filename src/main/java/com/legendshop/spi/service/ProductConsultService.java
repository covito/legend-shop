package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductConsult;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public abstract interface ProductConsultService extends BaseService
{
  public abstract List<ProductConsult> getProductConsultList(Long paramLong);

  public abstract ProductConsult getProductConsult(Long paramLong);

  public abstract String deleteProductConsult(HttpServletRequest paramHttpServletRequest, Long paramLong);

  public abstract Long saveProductConsult(ProductConsult paramProductConsult);

  public abstract String updateProductConsult(HttpServletRequest paramHttpServletRequest, ProductConsult paramProductConsult);

  public abstract PageSupport getProductConsult(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getProductConsult(String paramString, ProductConsult paramProductConsult);

  public abstract long checkFrequency(ProductConsult paramProductConsult);

  public abstract PageSupport getProductConsultForList(String paramString, Integer paramInteger, Long paramLong);
}
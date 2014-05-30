package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.model.entity.ProductCommentCategory;

public abstract interface ProductCommentService
{
  public abstract ProductComment getProductCommentById(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(ProductComment paramProductComment);

  public abstract void update(ProductComment paramProductComment);

  public abstract PageSupport getProductCommentList(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getProductCommentList(HqlQuery paramHqlQuery);

  public abstract ProductCommentCategory initProductCommentCategory(Long paramLong);

  public abstract String validateComment(Long paramLong, String paramString);
}
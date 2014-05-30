package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.model.entity.ProductCommentCategory;

public abstract interface ProductCommentDao extends BaseDao
{
  public abstract void deleteProductComment(Long paramLong, String paramString);

  public abstract void saveProductComment(ProductComment paramProductComment);

  public abstract void updateProductComment(ProductComment paramProductComment);

  public abstract void deleteProductCommentById(Long paramLong);

  public abstract ProductCommentCategory initProductCommentCategory(Long paramLong);

  public abstract String validateComment(Long paramLong, String paramString);
}
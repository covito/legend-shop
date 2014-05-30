package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.NewsCategory;

public abstract interface NewsCategoryDao extends BaseDao
{
  public abstract void deleteNewsCategoryById(Long paramLong);

  public abstract void updateNewsCategory(NewsCategory paramNewsCategory);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NewsCategory;
import java.util.List;

public abstract interface NewsCategoryService
{
  public abstract List<NewsCategory> getNewsCategoryList(String paramString);

  public abstract NewsCategory getNewsCategoryById(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(NewsCategory paramNewsCategory);

  public abstract void update(NewsCategory paramNewsCategory);

  public abstract PageSupport getNewsCategoryList(CriteriaQuery paramCriteriaQuery);
}
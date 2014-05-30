package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.NewsPositionEnum;
import java.util.List;
import java.util.Map;

public abstract interface NewsService extends BaseService
{
  public abstract List<News> getNewsList(String paramString);

  public abstract News getNewsById(Long paramLong);

  public abstract News getNewsByIdAndName(Long paramLong, String paramString);

  public abstract void delete(Long paramLong);

  public abstract Long save(News paramNews);

  public abstract void update(News paramNews);

  public abstract PageSupport getNewsList(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getNewsList(HqlQuery paramHqlQuery);

  public abstract List<News> getNews(String paramString, NewsPositionEnum paramNewsPositionEnum, Integer paramInteger);

  public abstract PageSupport getNews(String paramString1, String paramString2, Long paramLong);

  public abstract Map<KeyValueEntity, List<News>> getNewsByCategory(String paramString);
}
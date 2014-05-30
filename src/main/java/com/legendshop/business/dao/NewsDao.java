package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.NewsPositionEnum;
import java.util.List;
import java.util.Map;

public abstract interface NewsDao extends BaseDao
{
  public abstract List<News> getNews(String paramString, NewsPositionEnum paramNewsPositionEnum, Integer paramInteger);

  public abstract News getNewsById(Long paramLong);

  public abstract PageSupport getNews(String paramString1, String paramString2, Long paramLong);

  public abstract void updateNews(News paramNews);

  public abstract void deleteNewsById(Long paramLong);

  public abstract Long getAllNews(String paramString);

  public abstract Map<KeyValueEntity, List<News>> getNewsByCategory(String paramString);
}
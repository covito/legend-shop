package com.legendshop.business.service.impl;

import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.News;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.service.NewsService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NewsServiceImpl extends BaseServiceImpl
  implements NewsService
{
  private NewsDao newsDao;

  public void setNewsDao(NewsDao newsDao)
  {
    this.newsDao = newsDao;
  }

  public List<News> getNewsList(String userName)
  {
    return this.newsDao.findByHQL("from News where userName = ?", new Object[] { userName });
  }

  public News getNewsById(Long id)
  {
    News news = (News)this.newsDao.get(News.class, id);
    if (news != null) {
      if (news.getSort() != null)
        news.setSortId(news.getSort().getSortId());

      if (news.getNewsCategory() != null)
        news.setNewsCategoryId(news.getNewsCategory().getNewsCategoryId());

    }

    return news;
  }

  public News getNewsByIdAndName(Long id, String userName)
  {
    News news = (News)this.newsDao.findUniqueBy("from News where newsId = ? and userName = ?", News.class, new Object[] { id, userName });
    if (news == null)
      throw new NotFoundException("No News record");

    return news;
  }

  public void delete(Long id)
  {
    this.newsDao.deleteNewsById(id);
  }

  public Long save(News news)
  {
    if (!(AppUtils.isBlank(news.getNewsId()))) {
      News entity = (News)this.newsDao.get(News.class, news.getNewsId());
      if (entity != null) {
        news.setUserId(entity.getUserId());
        news.setUserName(entity.getUserName());
        update(news);
        return news.getNewsId();
      }
      return null;
    }
    preUpdateNews(news);
    return ((Long)this.newsDao.save(news));
  }

  private void preUpdateNews(News news)
  {
    if (news.getNewsCategoryId() != null) {
      NewsCategory nc = new NewsCategory();
      nc.setNewsCategoryId(news.getNewsCategoryId());
      news.setNewsCategory(nc);
    }
    if (news.getSortId() != null) {
      Sort sort = new Sort();
      sort.setSortId(news.getSortId());
      news.setSort(sort);
    }
  }

  public void update(News news)
  {
    preUpdateNews(news);
    news.setNewsDate(new Date());
    this.newsDao.updateNews(news);
  }

  public PageSupport getNewsList(CriteriaQuery cq)
  {
    return this.newsDao.find(cq);
  }

  public PageSupport getNewsList(HqlQuery hql)
  {
    PageSupport ps = this.newsDao.find(hql);
    return ps;
  }

  public List<News> getNews(String shopName, NewsPositionEnum newsPositionEnum, Integer num)
  {
    return this.newsDao.getNews(shopName, newsPositionEnum, num);
  }

  public PageSupport getNews(String curPageNO, String shopName, Long newsCategoryId)
  {
    return this.newsDao.getNews(curPageNO, shopName, newsCategoryId);
  }

  public Map<KeyValueEntity, List<News>> getNewsByCategory(String shopName)
  {
    return this.newsDao.getNewsByCategory(shopName);
  }
}
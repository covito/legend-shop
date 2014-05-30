package com.legendshop.business.service.impl;

import com.legendshop.business.dao.NewsCategoryDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.spi.service.NewsCategoryService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class NewsCategoryServiceImpl
  implements NewsCategoryService
{
  private NewsCategoryDao newsCategoryDao;

  public List<NewsCategory> getNewsCategoryList(String userName)
  {
    return this.newsCategoryDao.findByHQL("from NewsCategory where userName = ?", new Object[] { userName });
  }

  public NewsCategory getNewsCategoryById(Long id)
  {
    return ((NewsCategory)this.newsCategoryDao.get(NewsCategory.class, id));
  }

  public void delete(Long id)
  {
    this.newsCategoryDao.deleteNewsCategoryById(id);
  }

  public Long save(NewsCategory newsCategory)
  {
    if (!(AppUtils.isBlank(newsCategory.getNewsCategoryId()))) {
      update(newsCategory);
      return newsCategory.getNewsCategoryId();
    }
    return ((Long)this.newsCategoryDao.save(newsCategory));
  }

  public void update(NewsCategory newsCategory)
  {
    this.newsCategoryDao.updateNewsCategory(newsCategory);
  }

  public PageSupport getNewsCategoryList(CriteriaQuery cq)
  {
    return this.newsCategoryDao.find(cq);
  }

  public void setNewsCategoryDao(NewsCategoryDao newsCategoryDao)
  {
    this.newsCategoryDao = newsCategoryDao;
  }
}
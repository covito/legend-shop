package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.NewsCategoryDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.NewsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsCategoryDaoImpl extends BaseDaoImpl
  implements NewsCategoryDao
{
  private static Logger log = LoggerFactory.getLogger(NewsCategoryDaoImpl.class);

  public void deleteNewsCategoryById(Long id)
  {
    deleteById(NewsCategory.class, id);
  }

  public void updateNewsCategory(NewsCategory newsCategory)
  {
    update(newsCategory);
  }
}
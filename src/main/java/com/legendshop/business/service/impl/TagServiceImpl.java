package com.legendshop.business.service.impl;

import com.legendshop.business.dao.TagDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.Tag;
import com.legendshop.spi.service.TagService;
import java.util.List;

public class TagServiceImpl
  implements TagService
{
  private TagDao tagDao;

  public void setTagDao(TagDao tagDao)
  {
    this.tagDao = tagDao;
  }

  public List<Tag> getPageTag(String shopName, String page) {
    return this.tagDao.getPageTag(shopName, page);
  }

  public Tag getTag(Long id) {
    return this.tagDao.getTag(id);
  }

  public void deleteTag(Tag tag) {
    this.tagDao.deleteTag(tag);
  }

  public Long saveTag(Tag tag) {
    return ((Long)this.tagDao.save(tag));
  }

  public void updateTag(Tag tag) {
    this.tagDao.updateTag(tag);
  }

  public PageSupport getTag(CriteriaQuery cq) {
    return this.tagDao.find(cq);
  }

  public PageSupport getTag(SimpleHqlQuery hql)
  {
    return this.tagDao.getTag(hql);
  }

  public Tag getTag(String name, String userName)
  {
    return this.tagDao.getTag(name, userName);
  }
}
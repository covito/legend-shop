package com.legendshop.business.service.impl;

import com.legendshop.business.dao.TagMapDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.TagMap;
import com.legendshop.spi.service.TagMapService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class TagMapServiceImpl
  implements TagMapService
{
  private TagMapDao tagMapDao;

  public void setTagMapDao(TagMapDao tagMapDao)
  {
    this.tagMapDao = tagMapDao;
  }

  public List<TagMap> getTagMap(String userName) {
    return this.tagMapDao.getTagMap(userName);
  }

  public TagMap getTagMap(Long id) {
    return this.tagMapDao.getTagMap(id);
  }

  public void deleteTagMap(TagMap tagMap) {
    this.tagMapDao.deleteTagMap(tagMap);
  }

  public Long saveTagMap(TagMap tagMap) {
    if (!(AppUtils.isBlank(tagMap.getTagMapId()))) {
      updateTagMap(tagMap);
      return tagMap.getTagMapId();
    }
    return ((Long)this.tagMapDao.save(tagMap));
  }

  public void updateTagMap(TagMap tagMap) {
    this.tagMapDao.updateTagMap(tagMap);
  }

  public PageSupport getTagMap(CriteriaQuery cq) {
    return this.tagMapDao.find(cq);
  }
}
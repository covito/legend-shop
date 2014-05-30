package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.TagMapDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.TagMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagMapDaoImpl extends BaseDaoImpl
  implements TagMapDao
{
  private static Logger log = LoggerFactory.getLogger(TagMapDaoImpl.class);

  public List<TagMap> getTagMap(String userName)
  {
    return findByHQL("from TagMap where userName = ?", new Object[] { userName });
  }

  public TagMap getTagMap(Long id) {
    return ((TagMap)get(TagMap.class, id));
  }

  public void deleteTagMap(TagMap tagMap) {
    delete(tagMap);
  }

  public Long saveTagMap(TagMap tagMap) {
    return ((Long)save(tagMap));
  }

  public void updateTagMap(TagMap tagMap) {
    update(tagMap);
  }

  public PageSupport getTagMap(CriteriaQuery cq) {
    return find(cq);
  }
}
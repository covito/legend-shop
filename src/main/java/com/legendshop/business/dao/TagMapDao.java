package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.TagMap;
import java.util.List;

public abstract interface TagMapDao extends BaseDao
{
  public abstract List<TagMap> getTagMap(String paramString);

  public abstract TagMap getTagMap(Long paramLong);

  public abstract void deleteTagMap(TagMap paramTagMap);

  public abstract Long saveTagMap(TagMap paramTagMap);

  public abstract void updateTagMap(TagMap paramTagMap);

  public abstract PageSupport getTagMap(CriteriaQuery paramCriteriaQuery);
}
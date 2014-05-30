package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.Tag;
import java.util.List;

public abstract interface TagService
{
  public abstract PageSupport getTag(SimpleHqlQuery paramSimpleHqlQuery);

  public abstract Tag getTag(Long paramLong);

  public abstract void deleteTag(Tag paramTag);

  public abstract Long saveTag(Tag paramTag);

  public abstract void updateTag(Tag paramTag);

  public abstract PageSupport getTag(CriteriaQuery paramCriteriaQuery);

  public abstract Tag getTag(String paramString1, String paramString2);

  public abstract List<Tag> getPageTag(String paramString1, String paramString2);
}
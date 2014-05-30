package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ExternalLink;
import java.util.List;

public abstract interface ExternalLinkDao extends BaseDao
{
  public abstract List<ExternalLink> getExternalLink(String paramString);

  public abstract void deleteExternalLinkById(Long paramLong);

  public abstract void updateExternalLink(ExternalLink paramExternalLink);
}
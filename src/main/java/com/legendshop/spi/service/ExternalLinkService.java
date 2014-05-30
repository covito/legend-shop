package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ExternalLink;
import java.util.List;

public abstract interface ExternalLinkService
{
  public abstract List<ExternalLink> getExternalLink(String paramString);

  public abstract ExternalLink getExternalLinkById(Long paramLong);

  public abstract ExternalLink getExternalLinkList(Long paramLong, String paramString);

  public abstract void delete(Long paramLong);

  public abstract Long save(ExternalLink paramExternalLink);

  public abstract void update(ExternalLink paramExternalLink);

  public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery paramCriteriaQuery);
}
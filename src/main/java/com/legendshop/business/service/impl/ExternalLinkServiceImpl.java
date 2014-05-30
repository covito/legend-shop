package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.spi.service.ExternalLinkService;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class ExternalLinkServiceImpl
  implements ExternalLinkService
{
  private ExternalLinkDao externalLinkDao;

  @Required
  public void setExternalLinkDao(ExternalLinkDao externalLinkDao)
  {
    this.externalLinkDao = externalLinkDao;
  }

  public List<ExternalLink> getExternalLink(String userName)
  {
    return this.externalLinkDao.findByHQL("from ExternalLink where userName = ?", new Object[] { userName });
  }

  public ExternalLink getExternalLinkById(Long id)
  {
    return ((ExternalLink)this.externalLinkDao.get(ExternalLink.class, id));
  }

  public ExternalLink getExternalLinkList(Long id, String userName)
  {
    ExternalLink externalLink = (ExternalLink)this.externalLinkDao.findUniqueBy("from ExternalLink where id = ? and userName = ?", 
      ExternalLink.class, new Object[] { id, userName });
    if (externalLink == null)
      throw new NotFoundException("no ExternalLink record");

    return externalLink;
  }

  public void delete(Long id)
  {
    this.externalLinkDao.deleteExternalLinkById(id);
  }

  public Long save(ExternalLink externalLink)
  {
    if (!(AppUtils.isBlank(externalLink.getId()))) {
      ExternalLink entity = (ExternalLink)this.externalLinkDao.get(ExternalLink.class, externalLink.getId());
      if (entity != null) {
        entity.setUrl(externalLink.getUrl());
        entity.setWordlink(externalLink.getWordlink());
        entity.setContent(externalLink.getContent());
        entity.setBs(externalLink.getBs());
        update(entity);
        return externalLink.getId();
      }
      return null;
    }
    return ((Long)this.externalLinkDao.save(externalLink));
  }

  public void update(ExternalLink externalLink)
  {
    this.externalLinkDao.updateExternalLink(externalLink);
  }

  public PageSupport getDataByCriteriaQuery(CriteriaQuery cq)
  {
    return this.externalLinkDao.find(cq);
  }
}
package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class ExternalLinkDaoImpl extends BaseDaoImpl
  implements ExternalLinkDao
{
  private static Logger log = LoggerFactory.getLogger(ExternalLinkDaoImpl.class);

  @Cacheable(value={"ExternalLinkList"}, key="#shopName")
  public List<ExternalLink> getExternalLink(String shopName)
  {
    log.debug("getExternalLink, shopName = {}", shopName);
    if (shopName == null)
      return null;

    String sql = "from ExternalLink where userName = ? order by bs";
    List list = findByHQL(sql, new Object[] { shopName });
    if (AppUtils.isBlank(list))
      list = findByHQL(sql, new Object[] { PropertiesUtil.getDefaultShopName() });

    return list;
  }

  @CacheEvict(value={"ExternalLink"}, key="#id")
  public void deleteExternalLinkById(Long id)
  {
    deleteById(ExternalLink.class, id);
  }

  @CacheEvict(value={"ExternalLink"}, key="#externalLink.id")
  public void updateExternalLink(ExternalLink externalLink)
  {
    update(externalLink);
  }
}
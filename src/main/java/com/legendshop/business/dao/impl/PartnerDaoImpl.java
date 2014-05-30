package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.PartnerDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Partner;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartnerDaoImpl extends BaseDaoImpl
  implements PartnerDao
{
  private static Logger log = LoggerFactory.getLogger(PartnerDaoImpl.class);

  public List<Partner> getPartner(String userName)
  {
    return findByHQL("from Partner where userName = ?", new Object[] { userName });
  }

  public Partner getPartner(Long id) {
    return ((Partner)get(Partner.class, id));
  }

  public void deletePartner(Partner partner) {
    delete(partner);
  }

  public Long savePartner(Partner partner) {
    return ((Long)save(partner));
  }

  public void updatePartner(Partner partner) {
    update(partner);
  }

  public PageSupport getPartner(CriteriaQuery cq) {
    return find(cq);
  }
}
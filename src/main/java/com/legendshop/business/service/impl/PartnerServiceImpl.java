package com.legendshop.business.service.impl;

import com.legendshop.business.dao.PartnerDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Partner;
import com.legendshop.spi.service.PartnerService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class PartnerServiceImpl
  implements PartnerService
{
  private PartnerDao partnerDao;

  public void setPartnerDao(PartnerDao partnerDao)
  {
    this.partnerDao = partnerDao;
  }

  public List<Partner> getPartner(String userName) {
    return this.partnerDao.getPartner(userName);
  }

  public Partner getPartner(Long id) {
    return this.partnerDao.getPartner(id);
  }

  public void deletePartner(Partner partner) {
    this.partnerDao.deletePartner(partner);
  }

  public Long savePartner(Partner partner) {
    if (!(AppUtils.isBlank(partner.getPartnerId()))) {
      updatePartner(partner);
      return partner.getPartnerId();
    }
    return ((Long)this.partnerDao.save(partner));
  }

  public void updatePartner(Partner partner) {
    this.partnerDao.updatePartner(partner);
  }

  public PageSupport getPartner(CriteriaQuery cq) {
    return this.partnerDao.find(cq);
  }
}
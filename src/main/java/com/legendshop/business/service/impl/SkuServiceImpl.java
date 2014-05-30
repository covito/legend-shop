package com.legendshop.business.service.impl;

import com.legendshop.business.dao.SkuDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Sku;
import com.legendshop.spi.service.SkuService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class SkuServiceImpl
  implements SkuService
{
  private SkuDao skuDao;

  public void setSkuDao(SkuDao skuDao)
  {
    this.skuDao = skuDao;
  }

  public List<Sku> getSku(String userName) {
    return this.skuDao.getSku(userName);
  }

  public Sku getSku(Long id) {
    return this.skuDao.getSku(id);
  }

  public void deleteSku(Sku sku) {
    this.skuDao.deleteSku(sku);
  }

  public Long saveSku(Sku sku) {
    if (!(AppUtils.isBlank(sku.getSkuId()))) {
      updateSku(sku);
      return sku.getSkuId();
    }
    return ((Long)this.skuDao.save(sku));
  }

  public void updateSku(Sku sku) {
    this.skuDao.updateSku(sku);
  }

  public PageSupport getSku(CriteriaQuery cq) {
    return this.skuDao.find(cq);
  }
}
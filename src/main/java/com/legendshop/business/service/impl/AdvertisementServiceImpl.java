package com.legendshop.business.service.impl;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.spi.dao.AdvertisementDao;
import com.legendshop.spi.service.AdvertisementService;
import com.legendshop.util.AppUtils;
import java.util.List;
import java.util.Map;
import org.springframework.cache.annotation.CacheEvict;

public class AdvertisementServiceImpl extends BaseServiceImpl
  implements AdvertisementService
{
  public List<Advertisement> getAdvertisementByUserName(String userName)
  {
    return this.advertisementDao.findByHQL("from Advertisement where userName = ?", new Object[] { userName });
  }

  public Advertisement getAdvertisement(Long id)
  {
    return ((Advertisement)this.advertisementDao.get(Advertisement.class, id));
  }

  public boolean isMaxNum(String userName, String type)
  {
    return this.advertisementDao.isMaxNum(userName, type);
  }

  @CacheEvict(value={"Advertisement"}, key="#id")
  public void delete(Long id)
  {
    this.advertisementDao.deleteAdvById(id);
  }

  public Long save(Advertisement advertisement)
  {
    if (!(AppUtils.isBlank(advertisement.getId()))) {
      update(advertisement);
      return advertisement.getId();
    }
    return ((Long)this.advertisementDao.save(advertisement));
  }

  @CacheEvict(value={"Advertisement"}, key="#advertisement.id")
  public void update(Advertisement advertisement)
  {
    this.advertisementDao.updateAdv(advertisement);
  }

  public PageSupport getDataByCriteriaQuery(CriteriaQuery cq)
  {
    return this.advertisementDao.find(cq);
  }

  public Map<String, List<Advertisement>> getAdvertisement(String shopName, String page)
  {
    return this.advertisementDao.getAdvertisement(shopName, page);
  }
}
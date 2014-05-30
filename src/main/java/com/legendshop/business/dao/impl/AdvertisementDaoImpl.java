package com.legendshop.business.dao.impl;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.spi.dao.AdvertisementDao;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.Cacheable;

public class AdvertisementDaoImpl extends BaseDaoImpl
  implements AdvertisementDao
{
  private static Logger log = LoggerFactory.getLogger(AdvertisementDaoImpl.class);
  private Integer maxNumPerType;

  @Cacheable(value={"Advertisement"}, key="#shopName + #page")
  public Map<String, List<Advertisement>> getAdvertisement(String shopName, String page)
  {
    log.debug("getAdvertisement shopName = {}, page = {}", shopName, page);
    Map advertisementMap = new LinkedHashMap();
    List list = findByHQL(ConfigCode.getInstance().getCode("ad.getAdvertisement"), new Object[] { shopName, page + "%" });
    for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Advertisement advertisement = (Advertisement)localIterator.next();
      List ads = (List)advertisementMap.get(advertisement.getType());
      if (ads == null)
        ads = new ArrayList();

      ads.add(advertisement);
      advertisementMap.put(advertisement.getType(), ads);
    }
    return advertisementMap;
  }

  @Cacheable({"AdvertisementList"})
  public List<Advertisement> getOneAdvertisement(String shopName, String type)
  {
    if (log.isDebugEnabled())
      log.debug("shopName = {},key = {}", shopName, type);

    List resunt = null;
    List list = findByHQL(ConfigCode.getInstance().getCode("ad.getAdvertisementByType"), new Object[] { shopName, type });
    if (AppUtils.isNotBlank(list)) {
      resunt = new ArrayList(1);
      resunt.add((Advertisement)list.get(CommonServiceUtil.random(list.size())));
    }
    return resunt;
  }

  public boolean isMaxNum(String userName, String type)
  {
    boolean result = false;
    Long num = (Long)findUniqueBy(ConfigCode.getInstance().getCode("ad.getAdvertisementCount"), Long.class, new Object[] { userName, type });
    if (num != null)
      result = num.longValue() <= this.maxNumPerType.intValue();

    if (log.isDebugEnabled())
      log.debug("userName = {},type = {},result = {}", new Object[] { userName, type, Boolean.valueOf(result) });

    return result;
  }

  @Required
  public void setMaxNumPerType(Integer maxNumPerType)
  {
    this.maxNumPerType = maxNumPerType;
  }

  public void deleteAdvById(Long id)
  {
    deleteById(Advertisement.class, id);
  }

  public void updateAdv(Advertisement advertisement)
  {
    update(advertisement);
  }
}
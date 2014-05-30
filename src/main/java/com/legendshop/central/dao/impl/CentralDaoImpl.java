package com.legendshop.central.dao.impl;

import com.legendshop.central.dao.CentralDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;

public class CentralDaoImpl extends BaseDaoImpl
  implements CentralDao
{
  public Long getMaxShopDetail()
  {
    return ((Long)findUniqueBy("select count(*) from ShopDetail sd where sd.status = 1", Long.class, new Object[0]));
  }
}
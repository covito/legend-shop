package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserAddress;
import java.util.List;

public abstract interface UserAddressDao extends BaseDao
{
  public abstract List<UserAddress> getUserAddress(String paramString);

  public abstract UserAddress getUserAddress(Long paramLong);

  public abstract void deleteUserAddress(UserAddress paramUserAddress);

  public abstract Long saveUserAddress(UserAddress paramUserAddress);

  public abstract void updateUserAddress(UserAddress paramUserAddress);

  public abstract PageSupport getUserAddress(CriteriaQuery paramCriteriaQuery);
}
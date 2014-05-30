package com.legendshop.business.service.impl;

import com.legendshop.business.dao.UserAddressDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserAddress;
import com.legendshop.spi.service.UserAddressService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class UserAddressServiceImpl
  implements UserAddressService
{
  private UserAddressDao userAddressDao;

  public void setUserAddressDao(UserAddressDao userAddressDao)
  {
    this.userAddressDao = userAddressDao;
  }

  public List<UserAddress> getUserAddress(String userName) {
    return this.userAddressDao.getUserAddress(userName);
  }

  public UserAddress getUserAddress(Long id) {
    return this.userAddressDao.getUserAddress(id);
  }

  public void deleteUserAddress(UserAddress userAddress) {
    this.userAddressDao.deleteUserAddress(userAddress);
  }

  public Long saveUserAddress(UserAddress userAddress) {
    if (!(AppUtils.isBlank(userAddress.getAddrId()))) {
      updateUserAddress(userAddress);
      return userAddress.getAddrId();
    }
    return ((Long)this.userAddressDao.save(userAddress));
  }

  public void updateUserAddress(UserAddress userAddress) {
    this.userAddressDao.updateUserAddress(userAddress);
  }

  public PageSupport getUserAddress(CriteriaQuery cq) {
    return this.userAddressDao.find(cq);
  }
}
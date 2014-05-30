package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.UserAddressDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserAddress;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAddressDaoImpl extends BaseDaoImpl
  implements UserAddressDao
{
  private static Logger log = LoggerFactory.getLogger(UserAddressDaoImpl.class);

  public List<UserAddress> getUserAddress(String userName)
  {
    return findByHQL("from UserAddress where userName = ?", new Object[] { userName });
  }

  public UserAddress getUserAddress(Long id) {
    return ((UserAddress)get(UserAddress.class, id));
  }

  public void deleteUserAddress(UserAddress userAddress) {
    delete(userAddress);
  }

  public Long saveUserAddress(UserAddress userAddress) {
    return ((Long)save(userAddress));
  }

  public void updateUserAddress(UserAddress userAddress) {
    update(userAddress);
  }

  public PageSupport getUserAddress(CriteriaQuery cq) {
    return find(cq);
  }
}
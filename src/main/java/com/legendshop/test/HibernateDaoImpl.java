package com.legendshop.test;

import com.legendshop.core.dao.impl.BaseDaoImpl;

public class HibernateDaoImpl extends BaseDaoImpl
{
  public long stat(String sql, Object[] args)
  {
    return ((Long)findUniqueBy("select count (*) from User", Long.class, args)).longValue();
  }

  public void saveUser(User user) {
    save(user);
  }

  public User findUser(Long id) {
    return ((User)get(User.class, id));
  }
}
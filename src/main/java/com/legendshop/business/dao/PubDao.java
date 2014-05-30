package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Pub;
import java.util.List;

public abstract interface PubDao extends BaseDao
{
  public abstract List<Pub> getPub(String paramString);

  public abstract void deletePub(Pub paramPub);

  public abstract void updatePub(Pub paramPub);

  public abstract Long savePub(Pub paramPub);
}
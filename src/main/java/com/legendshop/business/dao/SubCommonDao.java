package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Sub;

public abstract interface SubCommonDao extends BaseDao
{
  public abstract void saveSubHistory(Sub paramSub, String paramString);
}
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ConstTable;
import java.util.List;

public abstract interface ConstTableDao extends BaseDao
{
  public abstract List<ConstTable> loadAllConstTable();
}
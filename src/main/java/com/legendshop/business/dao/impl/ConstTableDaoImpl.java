package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ConstTableDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.ConstTable;
import java.util.List;

public class ConstTableDaoImpl extends BaseDaoImpl
  implements ConstTableDao
{
  public List<ConstTable> loadAllConstTable()
  {
    return findByHQL("from ConstTable c order by c.id.type, c.seq", new Object[0]);
  }
}
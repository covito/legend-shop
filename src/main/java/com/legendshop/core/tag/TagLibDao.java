package com.legendshop.core.tag;

import com.legendshop.core.dao.BaseDao;
import java.util.List;

public abstract interface TagLibDao extends BaseDao
{
  public abstract List<Object[]> findDataByHQL(String paramString);

  public abstract List<Object[]> findDataBySQL(String paramString);
}
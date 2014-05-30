package com.legendshop.core.tag;

import com.legendshop.core.dao.impl.BaseDaoImpl;
import java.util.List;

public class TagLibDaoImpl extends BaseDaoImpl
  implements TagLibDao
{
  public List findDataByHQL(String paramString)
  {
    return super.findByHQL(paramString, new Object[0]);
  }

  public List findDataBySQL(String paramString)
  {
    return super.findBySQL(paramString);
  }
}
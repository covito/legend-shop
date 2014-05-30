package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import java.util.List;

public abstract interface CommonUtilDao extends BaseDao
{
  public abstract void removeRole(List<String> paramList, String paramString);

  public abstract void saveAdminRight(String paramString);

  public abstract void saveUserRight(String paramString);

  public abstract void removeAdminRight(String paramString);

  public abstract void removeUserRight(String paramString);
}
package com.legendshop.business.dao;

import com.legendshop.model.entity.Menu;
import com.legendshop.model.entity.RoleMenu;
import java.util.List;

public abstract interface MenuManagerDao
{
  public abstract List<Menu> getMenu();

  public abstract List<RoleMenu> getRoleMenu();

  public abstract List<RoleMenu> getRoleMenu(List<Long> paramList);

  public abstract List<RoleMenu> getRoleMenu(Long paramLong);

  public abstract void deleteRoleMenu(Long paramLong);
}
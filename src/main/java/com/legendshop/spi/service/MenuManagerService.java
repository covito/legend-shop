package com.legendshop.spi.service;

import com.legendshop.model.entity.Menu;
import com.legendshop.model.entity.RoleMenu;
import java.util.List;

public abstract interface MenuManagerService
{
  public abstract List<Menu> getMenu();

  public abstract List<RoleMenu> getRoleMenu(List<Long> paramList);
}
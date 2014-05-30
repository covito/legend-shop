package com.legendshop.business.service.impl;

import com.legendshop.business.dao.MenuManagerDao;
import com.legendshop.model.entity.Menu;
import com.legendshop.model.entity.RoleMenu;
import com.legendshop.spi.service.MenuManagerService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class MenuManagerServiceImpl
  implements MenuManagerService
{
  private MenuManagerDao menuManagerDao;

  public List<Menu> getMenu()
  {
    List menuList = this.menuManagerDao.getMenu();
    List roleMenuList = this.menuManagerDao.getRoleMenu();
    if ((AppUtils.isNotBlank(menuList)) && (AppUtils.isNotBlank(roleMenuList)))
      for (int i = 0; i < menuList.size(); ++i) {
        Menu menu = (Menu)menuList.get(i);
        for (int j = 0; j < roleMenuList.size(); ++j) {
          RoleMenu roleMenu = (RoleMenu)roleMenuList.get(j);
          if (menu.getMenuId().equals(roleMenu.getMenuId()))
            menu.addRequiredAnyFunctions(roleMenu.getRoleName());
        }
      }


    return menuList;
  }

  public void setMenuManagerDao(MenuManagerDao menuManagerDao)
  {
    this.menuManagerDao = menuManagerDao;
  }

  public List<RoleMenu> getRoleMenu(List<Long> menuIdList)
  {
    return this.menuManagerDao.getRoleMenu(menuIdList);
  }
}
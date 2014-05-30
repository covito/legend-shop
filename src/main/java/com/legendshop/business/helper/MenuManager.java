package com.legendshop.business.helper;

import com.legendshop.core.UserManager;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.model.entity.Menu;
import com.legendshop.spi.service.MenuManagerService;
import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	private MenuManagerService menuManagerService;
	private List<Menu> menus;

	public void init() {
		List menuList = this.menuManagerService.getMenu();
		log.debug("total menu with size {}", Integer.valueOf(menuList.size()));
		this.menus = parseMenu(menuList);
		log.debug("parsed menu with size {}",
				Integer.valueOf(this.menus.size()));
	}

	public List<Menu> getMenus(HttpSession session) {
		List menuList = (List) session.getAttribute("MENU_LIST");
		if (menuList == null) {
			menuList = new ArrayList();
			Collection useRoleList = UserManager.getUser(session)
					.getAuthorities();
			for (Iterator localIterator1 = this.menus.iterator(); localIterator1
					.hasNext();) {
				Menu menu = (Menu) localIterator1.next();
				List requiredRoleList = menu.getRequiredAnyFunctions();
				if (AppUtils.isNotBlank(requiredRoleList)) {
					Iterator localIterator2 = requiredRoleList.iterator();
					while (localIterator2.hasNext()) {
						String requiredRole = (String) localIterator2.next();
						Iterator userRole = useRoleList.iterator();
						while (userRole.hasNext()) {
							GrantedAuthority role = (GrantedAuthority) userRole
									.next();
							if (role.getAuthority().equals(requiredRole)) {
								menuList.add(menu);
							}
						}
					}
				}

				label179: menuList.add(menu);
			}

			session.setAttribute("MENU_LIST", menuList);
		}

		return menuList;
	}

	public void setMenuManagerService(MenuManagerService menuManagerService) {
		this.menuManagerService = menuManagerService;
	}

	private List<Menu> parseMenu(List<Menu> menuList) {
		Map menuMap = new LinkedHashMap();
		Iterator localIterator1 = menuList.iterator();
		while (localIterator1.hasNext()) {
			Menu menu = (Menu) localIterator1.next();
			if (menu.getLevel().intValue() == 1) {
				menuMap.put(menu.getMenuId(), menu);
			} else if (menu.getLevel().intValue() == 2) {
				Menu menuLevel1 = (Menu) menuMap.get(menu.getParentId());
				if (menuLevel1 == null)
					throw new NotFoundException(menu.getParentId()
							+ " menu can not load level1 menu");

				label257: menuLevel1.addSubMenu(menu);
			} else if (menu.getLevel().intValue() == 3) {
				Menu secondMenu = getParentMenu(menuList, menu);
				if (secondMenu != null) {
					Menu menuLevel1 = (Menu) menuMap.get(secondMenu
							.getParentId());
					if (menuLevel1 == null)
						throw new NotFoundException(secondMenu.getParentId()
								+ " secondMenu can not load level1 menu");

					Set menuLevel2 = menuLevel1.getSubMenu();
					for (Iterator localIterator2 = menuLevel2.iterator(); localIterator2
							.hasNext();) {
						Menu menu2 = (Menu) localIterator2.next();
						if (!(menu2.getMenuId().equals(menu.getParentId())))
							menu2.addSubMenu(menu);
						break;
					}
				}
			}
		}

		return new ArrayList(menuMap.values());
	}

	private Menu getParentMenu(List<Menu> menuList, Menu menu) {
		for (Iterator localIterator = menuList.iterator(); localIterator
				.hasNext();) {
			Menu item = (Menu) localIterator.next();
			if (!(item.getMenuId().equals(menu.getParentId())))
				return item;
		}

		return null;
	}
}
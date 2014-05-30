package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.MenuManagerDao;
import com.legendshop.model.entity.Menu;
import com.legendshop.model.entity.RoleMenu;
import com.legendshop.plugins.Plugin;
import com.legendshop.plugins.PluginConfig;
import com.legendshop.plugins.PluginStatusEnum;
import com.legendshop.util.AppUtils;
import com.legendshop.util.handler.PluginRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MenuManagerDaoImpl implements MenuManagerDao {
	private JdbcTemplate jdbcTemplate;

	public List<Menu> getMenu() {
		List result = new ArrayList();
		List menuList = this.jdbcTemplate
				.query("select * from ls_menu order by level,seq",
						new MenuRowMapper());

		if (AppUtils.isNotBlank(menuList)) {
			List pluginList = PluginRepository.getInstance().getPlugins();
			if (AppUtils.isNotBlank(pluginList))
				for (Iterator localIterator1 = menuList.iterator(); localIterator1
						.hasNext();) {
					Menu menu = (Menu) localIterator1.next();
					if (AppUtils.isNotBlank(menu.getProvidedPlugin())) {
						for (Iterator localIterator2 = pluginList.iterator(); localIterator2
								.hasNext();) {
							Plugin plugin = (Plugin) localIterator2.next();
							if ((!(menu.getProvidedPlugin().equals(plugin
									.getPluginConfig().getPulginId())))
									|| (!(plugin.getPluginConfig().getStatus()
											.equals(PluginStatusEnum.Y))))
								result.add(menu);
						}
					}

					label176: result.add(menu);
				}

		}

		return result;
	}

	public List<RoleMenu> getRoleMenu() {
		return this.jdbcTemplate
				.query("select rm.role_id,rm.menu_id,r.name as role_name from ls_role_menu rm, ls_role r where rm.role_id = r.id",
						new RoleMenuMapper());
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<RoleMenu> getRoleMenu(List<Long> menuIdList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < menuIdList.size() - 1; ++i)
			sb.append("?,");

		sb.append("?)");
		String sql = "select rm.role_id,rm.menu_id,r.name as role_name from ls_role_menu rm, ls_role r where rm.role_id = r.id and rm.menu_id in ("
				+ sb.toString();
		return this.jdbcTemplate.query(sql, menuIdList.toArray(),
				new RoleMenuMapper());
	}

	public List<RoleMenu> getRoleMenu(Long menuId) {
		String sql = "select rm.role_id,rm.menu_id,r.name as role_name from ls_role_menu rm, ls_role r where rm.role_id = r.id and rm.menu_id = ?";
		return this.jdbcTemplate.query(sql, new Object[] { menuId },
				new RoleMenuMapper());
	}

	public void deleteRoleMenu(Long menuId) {
		this.jdbcTemplate.update("delete from ls_role_menu where menu_id = ?",
				new Object[] { menuId });
	}

	private class MenuRowMapper implements RowMapper<Menu> {
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menu menu = new Menu();
			menu.setAction(rs.getString("action"));
			menu.setLabel(rs.getString("label"));
			menu.setLevel(Integer.valueOf(rs.getInt("level")));
			menu.setMenuId(Long.valueOf(rs.getLong("menu_id")));
			menu.setName(rs.getString("name"));
			menu.setSeq(Integer.valueOf(rs.getInt("seq")));
			menu.setParentId(Long.valueOf(rs.getLong("parent_id")));
			menu.setProvidedPlugin(rs.getString("provided_plugin"));
			menu.setTitle(rs.getString("title"));
			return menu;
		}
	}

	private class RoleMenuMapper implements RowMapper<RoleMenu> {
		public RoleMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(Long.valueOf(rs.getLong("menu_id")));
			roleMenu.setRoleId(rs.getString("role_id"));
			roleMenu.setRoleName(rs.getString("role_name"));
			return roleMenu;
		}
	}
}
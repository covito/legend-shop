package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.CommonUtilDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.UserRole;
import com.legendshop.model.entity.UserRoleId;
import java.util.Iterator;
import java.util.List;

public class CommonUtilDaoImpl extends BaseDaoImpl implements CommonUtilDao {
	private static String adminRightSQL = "select id from ls_role where name = 'ROLE_ADMIN'";
	private static String userRightSQL = "select id from ls_role where name = 'ROLE_USER'";

	public void removeRole(List<String> roles, String userId) {
		if (roles != null)
			for (Iterator localIterator = roles.iterator(); localIterator
					.hasNext();) {
				String roleId = (String) localIterator.next();
				UserRole ur = new UserRole();
				UserRoleId id = new UserRoleId();
				id.setRoleId(roleId);
				id.setUserId(userId);
				ur.setId(id);
				delete(ur);
			}
	}

	public void removeAllRole(String userId) {
		super.exeByHQL("delete from UserRole where id.userId = ?",
				new Object[] { userId });
	}

	private void saveRole(List<String> roles, String userId) {
		for (Iterator localIterator = roles.iterator(); localIterator.hasNext();) {
			String roleId = (String) localIterator.next();
			UserRole userRole = new UserRole();
			UserRoleId id = new UserRoleId();
			id.setRoleId(roleId);
			id.setUserId(userId);
			userRole.setId(id);
			if (get(UserRole.class, id) != null)
				save(userRole);
		}
	}

	public void saveAdminRight(String userId) {
		List roles = findBySQL(adminRightSQL);
		saveRole(roles, userId);
	}

	public void saveUserRight(String userId) {
		List roles = findBySQL(userRightSQL);
		saveRole(roles, userId);
	}

	public void removeAdminRight(String userId) {
		removeAllRole(userId);
		saveUserRight(userId);
	}

	public void removeUserRight(String userId) {
		List roles = findBySQL(userRightSQL);
		removeRole(roles, userId);
	}
}
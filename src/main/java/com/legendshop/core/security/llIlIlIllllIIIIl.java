package com.legendshop.core.security;

import com.legendshop.model.entity.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class llIlIlIllllIIIIl implements RowMapper<UserEntity> {
	public UserEntity mapRow(ResultSet paramResultSet, int paramInt)
			throws SQLException {
		UserEntity localUserEntity = new UserEntity();
		localUserEntity.setEnabled(paramResultSet.getString("enabled"));
		localUserEntity.setId(paramResultSet.getString("id"));
		localUserEntity.setName(paramResultSet.getString("name"));
		localUserEntity.setNote(paramResultSet.getString("note"));
		localUserEntity.setPassword(paramResultSet.getString("password"));
		return localUserEntity;
	}
}
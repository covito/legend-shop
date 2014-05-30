package com.legendshop.core.security;

import com.legendshop.model.entity.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class lIlIlIlIlllIIIIl
  implements RowMapper<Role>
{
  public Role mapRow(ResultSet paramResultSet, int paramInt)
    throws SQLException
  {
    Role localRole = new Role();
    localRole.setEnabled(paramResultSet.getString("enabled"));
    localRole.setId(paramResultSet.getString("id"));
    localRole.setName(paramResultSet.getString("name"));
    localRole.setNote(paramResultSet.getString("note"));
    localRole.setRoleType(paramResultSet.getString("role_type"));
    return localRole;
  }
}
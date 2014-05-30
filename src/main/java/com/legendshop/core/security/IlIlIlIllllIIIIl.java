package com.legendshop.core.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;

class IlIlIlIllllIIIIl
  implements RowMapper<GrantedAuthority>
{
  public GrantedAuthority mapRow(ResultSet paramResultSet, int paramInt)
    throws SQLException
  {
    return new GrantedAuthorityImpl(paramResultSet.getString("name"));
  }
}
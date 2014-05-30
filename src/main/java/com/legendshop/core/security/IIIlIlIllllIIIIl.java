package com.legendshop.core.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class IIIlIlIllllIIIIl
  implements RowMapper<GrantedFunction>
{
  public GrantedFunction mapRow(ResultSet paramResultSet, int paramInt)
    throws SQLException
  {
    return new GrantedFunctionImpl(paramResultSet.getString("name"));
  }
}
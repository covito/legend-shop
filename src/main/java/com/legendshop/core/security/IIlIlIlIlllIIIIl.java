package com.legendshop.core.security;

import com.legendshop.model.entity.Function;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class IIlIlIlIlllIIIIl
  implements RowMapper<Function>
{
  public Function mapRow(ResultSet paramResultSet, int paramInt)
    throws SQLException
  {
    Function localFunction = new Function();
    localFunction.setId(paramResultSet.getString("id"));
    localFunction.setName(paramResultSet.getString("name"));
    localFunction.setNote(paramResultSet.getString("note"));
    localFunction.setProtectFunction(paramResultSet.getString("protect_function"));
    localFunction.setUrl(paramResultSet.getString("url"));
    return localFunction;
  }
}
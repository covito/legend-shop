package com.legendshop.plugins;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class llllIIIIIIllIIII
  implements RowMapper<PluginConfig>
{
  public PluginConfig mapRow(ResultSet paramResultSet, int paramInt)
    throws SQLException
  {
    PluginConfig localPluginConfig = new PluginConfig();
    localPluginConfig.setPulginId(paramResultSet.getString("plugin_id"));
    localPluginConfig.setPulginVersion(paramResultSet.getString("plugin_version"));
    localPluginConfig.setProvider(paramResultSet.getString("provider"));
    try
    {
      PluginStatusEnum localPluginStatusEnum = PluginStatusEnum.valueOf(paramResultSet.getString("status"));
      localPluginConfig.setStatus(localPluginStatusEnum);
    }
    catch (Exception localException1)
    {
      localPluginConfig.setStatus(PluginStatusEnum.N);
    }
    Boolean localBoolean = Boolean.valueOf(false);
    try
    {
      localBoolean = Boolean.valueOf(paramResultSet.getString("is_required"));
    }
    catch (Exception localException2)
    {
    }
    localPluginConfig.setRequired(localBoolean.booleanValue());
    localPluginConfig.setDescription(paramResultSet.getString("description"));
    return localPluginConfig;
  }
}
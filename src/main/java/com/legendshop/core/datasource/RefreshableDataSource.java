package com.legendshop.core.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDataSource;

public class RefreshableDataSource extends AbstractDataSource
{
  private static Logger _$2 = LoggerFactory.getLogger(RefreshableDataSource.class);
  private DataSource _$1;

  public void setDataSource(DataSource paramDataSource)
  {
    this._$1 = paramDataSource;
  }

  public Connection getConnection()
    throws SQLException
  {
    Connection localConnection = this._$1.getConnection();
    return localConnection;
  }

  public Connection getConnection(String paramString1, String paramString2)
    throws SQLException
  {
    return this._$1.getConnection(paramString1, paramString2);
  }
}
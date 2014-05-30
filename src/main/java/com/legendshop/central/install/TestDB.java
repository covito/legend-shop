package com.legendshop.central.install;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import javax.servlet.http.HttpServletRequest;

public class TestDB
{
  private Integer _$3 = Integer.valueOf(0);
  private Integer _$2 = Integer.valueOf(1);
  private Integer _$1 = Integer.valueOf(2);

  public DbInfo testDB(HttpServletRequest paramHttpServletRequest)
  {
    DbInfo localDbInfo = new DbInfo();
    String str1 = paramHttpServletRequest.getParameter("jdbc_driver");
    String str2 = paramHttpServletRequest.getParameter("jdbc_url");
    String str3 = paramHttpServletRequest.getParameter("jdbc_username");
    String str4 = paramHttpServletRequest.getParameter("jdbc_password");
    try
    {
      DBManager localDBManager = new DBManager(str1, str2, str3, str4);
      Connection localConnection = localDBManager.getConnection();
      DatabaseMetaData localDatabaseMetaData = localConnection.getMetaData();
      int i = localDatabaseMetaData.getDatabaseMajorVersion();
      if (i < 4)
      {
        localDbInfo.setResult(this._$2);
        localDbInfo.setDesc("Legend Shop 不支持MySql4");
      }
      else
      {
        localDbInfo.setResult(this._$3);
        localDbInfo.setDesc("当前MySQL版本是：" + localDatabaseMetaData.getDatabaseProductVersion());
      }
    }
    catch (Exception localException)
    {
      localDbInfo.setResult(this._$1);
      localDbInfo.setDesc(localException.getMessage());
    }
    return localDbInfo;
  }
}
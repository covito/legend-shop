package com.legendshop.jdbc;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager
{
  private final Logger _$6 = LoggerFactory.getLogger(DBManager.class);
  private static DBManager _$5 = null;
  private String _$4 = Config.getInstance().getDbDriver();
  private String _$3 = Config.getInstance().getDbConnectString();
  private String _$2 = Config.getInstance().getDbUsername();
  private String _$1 = Config.getInstance().getDbPasswd();

  private DBManager()
  {
    if ((this._$4 == null) || (this._$3 == null) || (this._$2 == null) || (this._$1 == null))
      throw new RuntimeException("missing JDBC information");
    try
    {
      Class.forName(this._$4);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("connectDB(): " + localClassNotFoundException.getMessage());
    }
  }

  public static DBManager getInstance()
  {
    if (_$5 == null)
      _$5 = new DBManager();
    return _$5;
  }

  public Connection getConnection()
    throws SQLException
  {
    return DriverManager.getConnection(this._$3, this._$2, this._$1);
  }

  public Connection getConnection(String paramString1, String paramString2, String paramString3)
    throws SQLException
  {
    return DriverManager.getConnection(paramString1, paramString2, paramString3);
  }

  public void cleanup(Connection paramConnection, Statement paramStatement, ResultSet paramResultSet)
  {
    try
    {
      if (paramResultSet != null)
      {
        paramResultSet.close();
        paramResultSet = null;
      }
    }
    catch (Exception localException1)
    {
      this._$6.error("cleanup", localException1);
    }
    try
    {
      if (paramStatement != null)
      {
        paramStatement.close();
        paramStatement = null;
      }
    }
    catch (Exception localException2)
    {
      this._$6.error("close Statement", localException2);
    }
    try
    {
      if ((paramConnection != null) && (!(paramConnection.isClosed())))
      {
        paramConnection.close();
        paramConnection = null;
      }
    }
    catch (Exception localException3)
    {
      this._$6.error("close Connection", localException3);
    }
  }

  public ResultSet executeQuery(String paramString, Object[] paramArrayOfObject)
  {
    PreparedStatement localPreparedStatement = null;
    Connection localConnection = null;
    ResultSet localResultSet1 = null;
    try
    {
      localConnection = getConnection();
      localPreparedStatement = localConnection.prepareStatement(paramString);
      for (int i = 0; (paramArrayOfObject != null) && (i < paramArrayOfObject.length); ++i)
        localPreparedStatement.setObject(i + 1, paramArrayOfObject[i]);
      localResultSet1 = localPreparedStatement.executeQuery();
      ResultSet localResultSet2 = localResultSet1;
      return localResultSet2;
    }
    catch (Exception localException)
    {
      this._$6.error("SQLException in DBManager.exceuteQuery, sql is :\r\n" + paramString, Integer.valueOf(2));
      this._$6.error("executeQuery", localException);
    }
    finally
    {
      cleanup(localConnection, localPreparedStatement, localResultSet1);
    }
    return localResultSet1;
  }
}
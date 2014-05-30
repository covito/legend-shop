package com.legendshop.central.install;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager
{
  Logger _$6 = LoggerFactory.getLogger(DBManager.class);
  private static DBManager _$5 = null;
  private final String _$4;
  private final String _$3;
  private final String _$2;
  private final String _$1;

  public DBManager(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this._$4 = paramString1;
    this._$3 = paramString2;
    this._$2 = paramString3;
    this._$1 = paramString4;
    try
    {
      Class.forName(paramString1);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("connectDB(): " + localClassNotFoundException.getMessage());
    }
  }

  public Connection getConnection()
    throws SQLException
  {
    DriverManager.setLoginTimeout(10);
    Connection localConnection = DriverManager.getConnection(this._$3, this._$2, this._$1);
    return localConnection;
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
      this._$6.error("", localException1);
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
      this._$6.error("", localException2);
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
      this._$6.error("", localException3);
    }
  }

  public void batchUpdate(String[] paramArrayOfString)
  {
    Statement localStatement = null;
    Connection localConnection = null;
    String str = null;
    try
    {
      localConnection = getConnection();
      localConnection.setAutoCommit(false);
      localStatement = localConnection.createStatement();
      for (int i = 0; i < paramArrayOfString.length; ++i)
        if ((paramArrayOfString[i] != null) && (!(paramArrayOfString[i].equals(""))))
        {
          str = paramArrayOfString[i].trim();
          try
          {
            localStatement.executeUpdate(str);
            if (i % 100 == 0)
              localConnection.commit();
          }
          catch (Exception localException2)
          {
            this._$6.error("ERROR SQL： {},Exception :{}", str, localException2.getMessage());
          }
        }
      localConnection.commit();
    }
    catch (Exception localException1)
    {
      this._$6.error("ERROR SQL: {}", str);
      this._$6.error("", localException1);
      try
      {
        localConnection.rollback();
      }
      catch (SQLException localSQLException)
      {
        localSQLException.printStackTrace();
      }
    }
    finally
    {
      cleanup(localConnection, localStatement, null);
    }
  }
}
package com.legendshop.test;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnectionTest
{
  public static void main(String[] srg)
  {
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL = "jdbc:sqlserver://192.168.2.7:1433; DatabaseName=legendshop3";
    String userName = "legendshop";
    String userPwd = "legendshop";
    try
    {
      Class.forName(driverName);
      Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
      System.out.println("Connection Successful!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
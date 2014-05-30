package com.legendshop.test;

import com.legendshop.util.MD5Util;
import java.io.PrintStream;

public class UserPasswordGenerator
{
  public static void main(String[] args)
  {
    String userName = "root";
    String password = "root";
    System.out.println("User Name is " + userName);
    System.out.println("Password is " + password);
    System.out.println("Encrypted Password is " + MD5Util.Md5Password(userName, password));
  }
}
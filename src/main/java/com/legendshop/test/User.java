package com.legendshop.test;

public class User
{
  private long id;
  private String username;
  private String realname;
  private String password;
  private String memo;

  public User()
  {
  }

  public String toString()
  {
    return " id= " + this.id + ", name = " + this.username + ", username = " + this.username + ", realname = " + this.realname + ", password = " + this.password;
  }

  public User(long id, String username, String realname, String password, String memo)
  {
    this.id = id;
    this.username = username;
    this.realname = realname;
    this.password = password;
    this.memo = memo;
  }

  public long getId()
  {
    return this.id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public String getUsername()
  {
    return this.username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getRealname()
  {
    return this.realname;
  }

  public void setRealname(String realname)
  {
    this.realname = realname;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String memo)
  {
    this.memo = memo;
  }
}
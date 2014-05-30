package com.legendshop.model.entity;

public class User extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = -5151325646123051299L;
  private String id;
  private String name;
  private String password;
  private String passwordag;
  private String enabled;
  private String note;

  public User()
  {
  }

  public User(String id, String name, String password, String enabled)
  {
    this.id = id;
    this.name = name;
    this.password = password;
    this.enabled = enabled;
  }

  public User(String id, String name, String password, String enabled, String note)
  {
    this.id = id;
    this.name = name;
    this.password = password;
    this.enabled = enabled;
    this.note = note;
  }

  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEnabled()
  {
    return this.enabled;
  }

  public void setEnabled(String enabled)
  {
    this.enabled = enabled;
  }

  public String getNote()
  {
    return this.note;
  }

  public void setNote(String note)
  {
    this.note = note;
  }

  public String getPasswordag()
  {
    return this.passwordag;
  }

  public void setPasswordag(String passwordag)
  {
    this.passwordag = passwordag;
  }

  public String getUserName()
  {
    return this.name;
  }
}
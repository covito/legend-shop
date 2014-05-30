package com.legendshop.model.entity;

public class UserEntity
  implements BaseEntity
{
  private static final long serialVersionUID = -8401714272377570649L;
  private String id;
  private String name;
  private String password;
  private String passwordag;
  private String enabled;
  private String note;

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

  public String getPasswordag()
  {
    return this.passwordag;
  }

  public void setPasswordag(String passwordag)
  {
    this.passwordag = passwordag;
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
}
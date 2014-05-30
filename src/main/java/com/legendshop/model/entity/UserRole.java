package com.legendshop.model.entity;

public class UserRole
  implements BaseEntity
{
  private static final long serialVersionUID = 5680621301003712147L;
  private UserRoleId id;

  public UserRole()
  {
  }

  public UserRole(UserRoleId id)
  {
    this.id = id;
  }

  public UserRoleId getId()
  {
    return this.id;
  }

  public void setId(UserRoleId id)
  {
    this.id = id;
  }
}
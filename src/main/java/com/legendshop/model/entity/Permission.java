package com.legendshop.model.entity;

public class Permission
  implements BaseEntity
{
  private static final long serialVersionUID = -6636728502142356224L;
  private PerssionId id;

  public Permission()
  {
  }

  public Permission(PerssionId id)
  {
    this.id = id;
  }

  public PerssionId getId()
  {
    return this.id;
  }

  public void setId(PerssionId id)
  {
    this.id = id;
  }
}
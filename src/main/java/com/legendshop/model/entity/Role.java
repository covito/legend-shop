package com.legendshop.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Role
  implements BaseEntity
{
  private static final long serialVersionUID = 3162332789634848522L;
  private String id;
  private String name;
  private String roleType;
  private String enabled;
  private String note;
  private List<UserEntity> users = new ArrayList();
  private List<Function> functions = new ArrayList();

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

  public String getRoleType()
  {
    return this.roleType;
  }

  public void setRoleType(String roleType)
  {
    this.roleType = roleType;
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

  public List<UserEntity> getUsers()
  {
    return this.users;
  }

  public void setUsers(List<UserEntity> users)
  {
    this.users = users;
  }

  public List<Function> getFunctions()
  {
    return this.functions;
  }

  public void setFunctions(List<Function> functions)
  {
    this.functions = functions;
  }
}
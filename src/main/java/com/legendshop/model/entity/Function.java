package com.legendshop.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Function
  implements BaseEntity
{
  private static final long serialVersionUID = -3353517712968544688L;
  private String id;
  private String name;
  private String url;
  private String parentName;
  private String protectFunction;
  private String note;
  private List<Role> roles = new ArrayList();

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

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getProtectFunction()
  {
    return this.protectFunction;
  }

  public void setProtectFunction(String protectFunction)
  {
    this.protectFunction = protectFunction;
  }

  public String getNote()
  {
    return this.note;
  }

  public void setNote(String note)
  {
    this.note = note;
  }

  public List<Role> getRoles()
  {
    return this.roles;
  }

  public void setRoles(List<Role> roles)
  {
    this.roles = roles;
  }

  public String getParentName()
  {
    return this.parentName;
  }

  public void setParentName(String parentName)
  {
    this.parentName = parentName;
  }
}
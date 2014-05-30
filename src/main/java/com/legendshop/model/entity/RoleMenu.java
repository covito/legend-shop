package com.legendshop.model.entity;

import java.io.Serializable;

public class RoleMenu
  implements Serializable
{
  private static final long serialVersionUID = -8675364817753905504L;
  private Long rmId;
  private Long menuId;
  private String roleId;
  private String roleName;

  public Long getMenuId()
  {
    return this.menuId;
  }

  public void setMenuId(Long menuId)
  {
    this.menuId = menuId;
  }

  public String getRoleId()
  {
    return this.roleId;
  }

  public void setRoleId(String roleId)
  {
    this.roleId = roleId;
  }

  public String getRoleName()
  {
    return this.roleName;
  }

  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }

  public Long getRmId()
  {
    return this.rmId;
  }

  public void setRmId(Long rmId)
  {
    this.rmId = rmId;
  }
}
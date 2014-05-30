package com.legendshop.model.entity;

import java.io.Serializable;

public class UserRoleId
  implements Serializable
{
  private static final long serialVersionUID = 5614770818903175975L;
  private String userId;
  private String roleId;

  public UserRoleId()
  {
  }

  public UserRoleId(String userId, String roleId)
  {
    this.userId = userId;
    this.roleId = roleId;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getRoleId()
  {
    return this.roleId;
  }

  public void setRoleId(String roleId)
  {
    this.roleId = roleId;
  }

  public boolean equals(Object other)
  {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (!(other instanceof UserRoleId))
      return false;
    UserRoleId castOther = (UserRoleId)other;

    return ((((getUserId() == castOther.getUserId()) || (
      (getUserId() != null) && 
      (castOther.getUserId() != null) && (getUserId().equals(
      castOther.getUserId()))))) && ((
      (getRoleId() == castOther.getRoleId()) || (
      (getRoleId() != null) && 
      (castOther.getRoleId() != null) && 
      (getRoleId().equals
      (castOther.getRoleId()))))));
  }

  public int hashCode()
  {
    int result = 17;

    result = 37 * result + 
      ((getUserId() == null) ? 0 : getUserId().hashCode());
    result = 37 * result + 
      ((getRoleId() == null) ? 0 : getRoleId().hashCode());
    return result;
  }
}
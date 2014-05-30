package com.legendshop.model.entity;

import java.io.Serializable;

public class PerssionId
  implements Serializable
{
  private static final long serialVersionUID = -5510312063680243453L;
  private String roleId;
  private String functionId;

  public PerssionId()
  {
  }

  public PerssionId(String roleId, String functionId)
  {
    this.roleId = roleId;
    this.functionId = functionId;
  }

  public String getRoleId()
  {
    return this.roleId;
  }

  public void setRoleId(String roleId)
  {
    this.roleId = roleId;
  }

  public String getFunctionId()
  {
    return this.functionId;
  }

  public void setFunctionId(String functionId)
  {
    this.functionId = functionId;
  }

  public boolean equals(Object other)
  {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (!(other instanceof PerssionId))
      return false;
    PerssionId castOther = (PerssionId)other;

    return ((((getRoleId() == castOther.getRoleId()) || (
      (getRoleId() != null) && 
      (castOther.getRoleId() != null) && (getRoleId().equals(
      castOther.getRoleId()))))) && ((
      (getFunctionId() == castOther.getFunctionId()) || (
      (getFunctionId() != null) && 
      (castOther.getFunctionId() != null) && 
      (getFunctionId().equals(castOther.getFunctionId()))))));
  }

  public int hashCode()
  {
    int result = 17;

    result = 37 * result + 
      ((getRoleId() == null) ? 0 : getRoleId().hashCode());
    result = 37 * 
      result + 
      ((getFunctionId() == null) ? 0 : getFunctionId().hashCode
      ());
    return result;
  }
}
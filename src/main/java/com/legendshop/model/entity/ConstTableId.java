package com.legendshop.model.entity;

import java.io.Serializable;

public class ConstTableId
  implements Serializable
{
  private static final long serialVersionUID = 4645320684663141427L;
  private String key;
  private String type;

  public ConstTableId()
  {
  }

  public ConstTableId(String key, String type)
  {
    this.key = key;
    this.type = type;
  }

  public String getKey()
  {
    return this.key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public boolean equals(Object other)
  {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (!(other instanceof ConstTableId))
      return false;
    ConstTableId castOther = (ConstTableId)other;

    return ((((getKey() == castOther.getKey()) || ((getKey() != null) && 
      (castOther.getKey() != null) && (getKey().equals(
      castOther.getKey()))))) && ((
      (getType() == castOther.getType()) || ((getType() != null) && 
      (castOther.getType() != null) && 
      (getType().equals
      (castOther.getType()))))));
  }

  public int hashCode()
  {
    int result = 17;

    result = 37 * result + 
      ((getKey() == null) ? 0 : getKey().hashCode());
    result = 37 * result + 
      ((getType() == null) ? 0 : getType().hashCode());
    return result;
  }
}
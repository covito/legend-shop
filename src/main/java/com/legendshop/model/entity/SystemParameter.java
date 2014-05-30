package com.legendshop.model.entity;

import java.io.Serializable;

public class SystemParameter
  implements Serializable
{
  private static final long serialVersionUID = 3188172953619133451L;
  private String name;
  private String value;
  private String memo;
  private String type;
  private String optional;
  private String changeOnline;
  private Integer displayOrder;
  private String groupId;

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String memo)
  {
    this.memo = memo;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getOptional()
  {
    return this.optional;
  }

  public void setOptional(String optional)
  {
    this.optional = optional;
  }

  public Integer getDisplayOrder()
  {
    return this.displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  public String getChangeOnline()
  {
    return this.changeOnline;
  }

  public void setChangeOnline(String changeOnline)
  {
    this.changeOnline = changeOnline;
  }

  public String getGroupId() {
    return this.groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
}